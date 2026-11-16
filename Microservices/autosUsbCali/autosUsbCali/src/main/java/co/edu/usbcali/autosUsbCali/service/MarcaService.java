package co.edu.usbcali.autosUsbCali.service;

import co.edu.usbcali.autosUsbCali.domain.Marca;
import co.edu.usbcali.autosUsbCali.dto.request.CrearMarcaRequest;
import co.edu.usbcali.autosUsbCali.dto.response.ObtenerMarcaResponse;
import co.edu.usbcali.autosUsbCali.exception.MarcaEnUsoException;
import co.edu.usbcali.autosUsbCali.exception.MarcaNoEncontradaException;
import co.edu.usbcali.autosUsbCali.mapper.MarcaMapper;
import co.edu.usbcali.autosUsbCali.repository.MarcaRepository;
import co.edu.usbcali.autosUsbCali.repository.ModeloRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class MarcaService implements IMarcaService{

    private final MarcaRepository marcaRepository;
    private final ModeloRepository modeloRepository;

    @Override
    public List<ObtenerMarcaResponse> obtenerTodas() {
        return MarcaMapper.listaMarcasAListaObtenerMarcaResponse(marcaRepository.findAll());
    }

    @Override
    @Transactional
    public ObtenerMarcaResponse crearMarca(CrearMarcaRequest request) {
        log.debug("Solicitud de creación de marca: {}", request);

        // Validación: el nombre es obligatorio (error del cliente → 400)
        if (request == null || request.nombre() == null || request.nombre().isBlank()) {
            log.warn("Intento de crear una marca sin nombre");
            throw new IllegalArgumentException("El nombre de la marca no puede ser nulo ni estar vacío");
        }

        // Validación: no se permiten nombres repetidos
        if (marcaRepository.existsByNombre(request.nombre())) {
            log.warn("Intento de crear una marca duplicada: {}", request.nombre());
            throw new IllegalArgumentException("Ya existe una marca con el nombre " + request.nombre());
        }

        Marca marca = marcaRepository.save(MarcaMapper.crearMarcaRequestAMarca(request));

        log.info("Marca creada: id={}, nombre={}", marca.getId(), marca.getNombre());
        return MarcaMapper.marcaAObtenerMarcaResponse(marca);
    }

    @Override
    @Transactional
    public void eliminarMarcaFisicamente(Long id) {
        // 1. Entrada: detalle técnico → DEBUG
        log.debug("Solicitud de eliminación física de la marca con id {}", id);

        // 2. Validar el id (error del cliente → 400)
        if (id == null || id <= 0) {
            log.warn("Id inválido para eliminación física de marca: {}", id);
            throw new IllegalArgumentException("El id no puede ser nulo ni menor o igual a cero");
        }

        // 3. La marca debe existir (si no → 404)
        Marca marca = marcaRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Intento de eliminar una marca inexistente con id {}", id);
                    return new MarcaNoEncontradaException(id);
                });

        // 4. No puede tener modelos asociados (si los tiene → 409, nada de cascada)
        if (modeloRepository.existsByMarca_Id(id)) {
            log.warn("No se elimina la marca {} ({}): tiene modelos asociados", id, marca.getNombre());
            throw new MarcaEnUsoException("La marca " + marca.getNombre() + " tiene modelos asociados");
        }

        // 5. Eliminación física + flush para que el DELETE se ejecute YA
        marcaRepository.delete(marca);
        marcaRepository.flush();

        // 6. Auditoría: los datos ya no existen en la BD, quedan solo en este log → INFO
        log.info("AUDITORIA - Marca eliminada físicamente: id={}, nombre={}, activo={}",
                marca.getId(), marca.getNombre(), marca.getActive());
    }
}
