package co.edu.usbcali.autosUsbCali.service;

import co.edu.usbcali.autosUsbCali.domain.Rol;
import co.edu.usbcali.autosUsbCali.dto.request.ActualizarRolRequest;
import co.edu.usbcali.autosUsbCali.dto.request.CrearRolRequest;
import co.edu.usbcali.autosUsbCali.dto.request.EliminarRolRequest;
import co.edu.usbcali.autosUsbCali.dto.response.ObtenerRolResponse;
import co.edu.usbcali.autosUsbCali.mapper.RolMapper;
import co.edu.usbcali.autosUsbCali.repository.RolRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RolService {
    private final RolRepository rolRepository;

    // GET

    public List<ObtenerRolResponse> obtenerTodos() {
        return RolMapper.listaRolesAListaObtenerRolResponse(rolRepository.findAll());
    }

    public List<ObtenerRolResponse> obtenerTodosActivos() {
        return RolMapper.listaRolesAListaObtenerRolResponse(rolRepository.findByActivo(true));
    }

    public List<ObtenerRolResponse> obtenerTodosInactivos() {
        return RolMapper.listaRolesAListaObtenerRolResponse(rolRepository.findByActivo(false));
    }

    public Optional<ObtenerRolResponse> obtenerPorNombre(String nombre) {
        return rolRepository.findByNombre(nombre)
                .map(RolMapper::rolAObtenerRolResponse);
    }

    public Optional<ObtenerRolResponse> obtenerPorId(Long id) {
        return rolRepository.findById(id)
                .map(RolMapper::rolAObtenerRolResponse);
    }

    // CREATE

    @Transactional
    public ObtenerRolResponse crear(CrearRolRequest request) {
        Rol rol = new Rol();
        rol.setNombre(request.nombre());
        rol.setDescripcion(request.descripcion());
        rol.setActivo(request.activo());

        Rol rolGuardado = rolRepository.save(rol);
        return RolMapper.rolAObtenerRolResponse(rolGuardado);
    }

    // UPDATE

    @Transactional
    public Optional<ObtenerRolResponse> actualizar(Long id, ActualizarRolRequest request) {
        return rolRepository.findById(id)
                .map(rol -> {
                    rol.setNombre(request.nombre());
                    rol.setDescripcion(request.descripcion());
                    rol.setActivo(request.activo());
                    return RolMapper.rolAObtenerRolResponse(rolRepository.save(rol));
                });
    }

    // DELETE

    @Transactional
    public boolean eliminar(EliminarRolRequest request) {
        if (!rolRepository.existsById(request.id())) {
            return false;
        }
        rolRepository.deleteById(request.id());
        return true;
    }


}
