package co.edu.usbcali.autosUsbCali.service;

import co.edu.usbcali.autosUsbCali.domain.Marca;
import co.edu.usbcali.autosUsbCali.domain.Modelo;
import co.edu.usbcali.autosUsbCali.dto.request.CrearModificarModeloRequest;
import co.edu.usbcali.autosUsbCali.dto.response.ObtenerModeloResponse;
import co.edu.usbcali.autosUsbCali.exception.MarcaNoEncontradaException;
import co.edu.usbcali.autosUsbCali.exception.ModeloEnUsoException;
import co.edu.usbcali.autosUsbCali.exception.ModeloNoEncontradoException;
import co.edu.usbcali.autosUsbCali.mapper.ModeloMapper;
import co.edu.usbcali.autosUsbCali.repository.MarcaRepository;
import co.edu.usbcali.autosUsbCali.repository.ModeloRepository;
import co.edu.usbcali.autosUsbCali.repository.VehiculoRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class ModeloService implements IModeloService {

    private final ModeloRepository modeloRepository;
    private final MarcaRepository marcaRepository;       // para resolver la FK
    private final VehiculoRepository vehiculoRepository; // para validar antes de borrar

    // ===================== CONSULTAS =====================

    @Override
    @Transactional(readOnly = true)   // readOnly: solo lectura, Hibernate optimiza
    public List<ObtenerModeloResponse> obtenerTodos() {
        return ModeloMapper.listaModelosAListaObtenerModeloResponse(modeloRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public ObtenerModeloResponse obtenerModeloPorId(Long id) {
        return ModeloMapper.modeloAObtenerModeloResponse(buscarModeloOFallar(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ObtenerModeloResponse> obtenerModelosPorMarca(Long marcaId) {
        // Si la marca no existe, respondemos 404 en vez de una lista vacía engañosa
        buscarMarcaOFallar(marcaId);
        return ModeloMapper.listaModelosAListaObtenerModeloResponse(modeloRepository.findByMarca_Id(marcaId));
    }

    // ===================== CREAR =====================

    @Override
    @Transactional
    public ObtenerModeloResponse crearModelo(CrearModificarModeloRequest request) {
        log.debug("Solicitud de creación de modelo: {}", request);
        validarRequest(request);

        // 1. RESOLVER LA FK: convertir el marcaId del JSON en un objeto Marca real
        Marca marca = buscarMarcaOFallar(request.marcaId());

        // 2. Regla de negocio: no se crean modelos para marcas inactivas
        validarMarcaActiva(marca);

        // 3. Respetar UNIQUE(marca_id, nombre)
        if (modeloRepository.existsByMarca_IdAndNombre(marca.getId(), request.nombre())) {
            log.warn("Modelo duplicado: '{}' ya existe en la marca {}", request.nombre(), marca.getNombre());
            throw new IllegalArgumentException(
                    "Ya existe el modelo " + request.nombre() + " en la marca " + marca.getNombre());
        }

        // 4. Construir y guardar. Hibernate escribe marca_id = marca.getId() en la tabla
        Modelo modelo = modeloRepository.save(ModeloMapper.crearModificarModeloRequestAModelo(request, marca));

        log.info("Modelo creado: id={}, nombre={}, marca={}", modelo.getId(), modelo.getNombre(), marca.getNombre());
        return ModeloMapper.modeloAObtenerModeloResponse(modelo);
    }

    // ===================== MODIFICAR =====================

    @Override
    @Transactional
    public ObtenerModeloResponse modificarModelo(Long id, CrearModificarModeloRequest request) {
        log.debug("Solicitud de modificación del modelo {}: {}", id, request);
        validarRequest(request);

        Modelo modelo = buscarModeloOFallar(id);
        Marca nuevaMarca = buscarMarcaOFallar(request.marcaId());
        validarMarcaActiva(nuevaMarca);

        // UNIQUE(marca_id, nombre), sin contar el propio modelo
        if (modeloRepository.existsByMarca_IdAndNombreAndIdNot(nuevaMarca.getId(), request.nombre(), id)) {
            log.warn("Modelo duplicado al modificar: '{}' ya existe en la marca {}",
                    request.nombre(), nuevaMarca.getNombre());
            throw new IllegalArgumentException(
                    "Ya existe otro modelo " + request.nombre() + " en la marca " + nuevaMarca.getNombre());
        }

        String marcaAnterior = modelo.getMarca().getNombre();

        // Cambiar la FK = asignar OTRO objeto Marca. Hibernate genera UPDATE ... SET marca_id = ?
        modelo.setMarca(nuevaMarca);
        modelo.setNombre(request.nombre());
        modelo = modeloRepository.save(modelo);

        log.info("Modelo modificado: id={}, nombre={}, marca {} -> {}",
                modelo.getId(), modelo.getNombre(), marcaAnterior, nuevaMarca.getNombre());
        return ModeloMapper.modeloAObtenerModeloResponse(modelo);
    }

    // ===================== ELIMINAR (FÍSICO) =====================

    @Override
    @Transactional
    public void eliminarModeloFisicamente(Long id) {
        log.debug("Solicitud de eliminación física del modelo con id {}", id);

        Modelo modelo = buscarModeloOFallar(id);

        // La tabla vehiculos tiene FK hacia modelos: si hay vehículos → 409
        if (vehiculoRepository.existsByModelo_Id(id)) {
            log.warn("No se elimina el modelo {} ({}): tiene vehículos asociados", id, modelo.getNombre());
            throw new ModeloEnUsoException("El modelo " + modelo.getNombre() + " tiene vehículos asociados");
        }

        modeloRepository.delete(modelo);
        modeloRepository.flush();

        log.info("AUDITORIA - Modelo eliminado físicamente: id={}, nombre={}, marca={}",
                modelo.getId(), modelo.getNombre(), modelo.getMarca().getNombre());
    }

    // ===================== MÉTODOS DE APOYO (privados) =====================

    private Modelo buscarModeloOFallar(Long id) {
        if (id == null || id <= 0) {
            log.warn("Id de modelo inválido: {}", id);
            throw new IllegalArgumentException("El id del modelo no puede ser nulo ni menor o igual a cero");
        }
        return modeloRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Modelo no encontrado con id {}", id);
                    return new ModeloNoEncontradoException(id);
                });
    }

    private Marca buscarMarcaOFallar(Long marcaId) {
        if (marcaId == null || marcaId <= 0) {
            log.warn("Id de marca inválido: {}", marcaId);
            throw new IllegalArgumentException("El id de la marca no puede ser nulo ni menor o igual a cero");
        }
        return marcaRepository.findById(marcaId)
                .orElseThrow(() -> {
                    log.warn("Marca no encontrada con id {}", marcaId);
                    return new MarcaNoEncontradaException(marcaId);
                });
    }

    private void validarRequest(CrearModificarModeloRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("El cuerpo de la petición no puede ser nulo");
        }
        if (request.nombre() == null || request.nombre().isBlank()) {
            log.warn("Intento de guardar un modelo sin nombre");
            throw new IllegalArgumentException("El nombre del modelo no puede ser nulo ni estar vacío");
        }
        // marcaId se valida dentro de buscarMarcaOFallar
    }

    private void validarMarcaActiva(Marca marca) {
        if (Boolean.FALSE.equals(marca.getActive())) {
            log.warn("Intento de asociar un modelo a la marca inactiva {}", marca.getNombre());
            throw new IllegalArgumentException("La marca " + marca.getNombre() + " está inactiva");
        }
    }
}