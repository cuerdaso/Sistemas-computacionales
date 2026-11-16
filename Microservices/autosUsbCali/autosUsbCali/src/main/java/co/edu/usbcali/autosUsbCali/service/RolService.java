package co.edu.usbcali.autosUsbCali.service;

import co.edu.usbcali.autosUsbCali.domain.Rol;
import co.edu.usbcali.autosUsbCali.dto.request.CrearModificarRolRequest;
import co.edu.usbcali.autosUsbCali.dto.response.ObtenerRolResponse;
import co.edu.usbcali.autosUsbCali.mapper.RolMapper;
import co.edu.usbcali.autosUsbCali.repository.RolRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import co.edu.usbcali.autosUsbCali.domain.Rol;
import co.edu.usbcali.autosUsbCali.exception.RolEnUsoException;
import co.edu.usbcali.autosUsbCali.exception.RolNoEncontradoException;
import co.edu.usbcali.autosUsbCali.repository.RolPermisoRepository;
import co.edu.usbcali.autosUsbCali.repository.RolRepository;
import co.edu.usbcali.autosUsbCali.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class RolService implements IRolService{
    private final RolRepository rolRepository;
    private final UsuarioRepository usuarioRepository;
    private final RolPermisoRepository rolPermisoRepository;

    @Override
    public List<ObtenerRolResponse> obtenerTodos() {
        return RolMapper.listaRolesAListaObtenerRolResponse(rolRepository.findAll());
    }

    @Override
    public List<ObtenerRolResponse> buscarRolPorEstado(Boolean activo) {
        List<Rol> rolesPorEstado = rolRepository.findByActivo(activo);
        return RolMapper.listaRolesAListaObtenerRolResponse(rolesPorEstado);
    }

    @Override
    public ObtenerRolResponse obtenerRolPorNombre(String nombre) {
        Rol rolPorNombre = rolRepository.findByNombre(nombre)
                .orElseThrow(() -> new RuntimeException("No se ha encontrado el rol por nombre: "+nombre));
        return RolMapper.rolAObtenerRolResponse(rolPorNombre);
    }

    @Override
    public ObtenerRolResponse obtenerRolPorId(Long id) {
        Rol rolPorId = rolRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se ha encontrado el rol por id "+id));
        return RolMapper.rolAObtenerRolResponse(rolPorId);
    }

    @Override
    public ObtenerRolResponse crearRol(CrearModificarRolRequest crearModificarRolRequest) throws Exception {
        // validacion del objeto CrearModificarRolRequest
        if (crearModificarRolRequest == null){
            throw new Exception("El objeto CrearModificarRolRequest no puede ser nulo");
        }
        if (crearModificarRolRequest.nombre() == null || crearModificarRolRequest.nombre().isBlank()) {
            throw new Exception("El nombre no puede ser nulo ni estar vacio");
        }
        if (crearModificarRolRequest.descripcion() == null || crearModificarRolRequest.descripcion().isBlank()){
            throw new Exception("La descripcion no puede ser nula ni estar vacio");
        }
        // Buscar si existe un rol con el nombre que se quiere agregar Opción 1 traer el objeto
        /*
        Optional<Rol> rolPorNombre = rolRepository.findByNombre(crearModificarRolRequest.nombre());
        if (rolPorNombre.isPresent()) {
            throw new Exception("Ya existe un rol con el nombre "+crearModificarRolRequest.nombre());
        }*/

        // Buscar si existe un rol con el nombre que se quiere agregar Opción 2 buscar boolean
        if (rolRepository.existsByNombre(crearModificarRolRequest.nombre())) {
            throw new Exception("Ya existe un rol con el nombre "+crearModificarRolRequest.nombre());
        }

        // Usar mapper para convertir DTO a objeto de Domain
        Rol rol = RolMapper.CrearModificarRolRequestARol(crearModificarRolRequest);

        // Persistir el Rol en base de datos
        rol = rolRepository.save(rol);

        // Crear DTO response obtenerRolResponse
        ObtenerRolResponse rolResponse = RolMapper.rolAObtenerRolResponse(rol);

        // Retornar rolResponse
        return rolResponse;

    }

    @Override
    public ObtenerRolResponse modificarRol(Long id, CrearModificarRolRequest modificarRolRequest) throws Exception {
        if (id == null || id <= 0) {
            throw new Exception("El id no puede ser nulo ni menor o igual a cero");
        }
        if (modificarRolRequest == null) {
            throw new Exception("El objeto CrearModificarRolRequest no puede ser nulo");
        }
        if (modificarRolRequest.nombre() == null || modificarRolRequest.nombre().isBlank()) {
            throw new Exception("El nombre no puede ser nulo ni estar vacio");
        }
        if (modificarRolRequest.descripcion() == null || modificarRolRequest.descripcion().isBlank()) {
            throw new Exception("La descripcion no puede ser nula ni estar vacia");
        }

        // El rol debe existir
        Rol rol = rolRepository.findById(id)
                .orElseThrow(() -> new Exception("No existe un rol con el id " + id));

        // El nombre no puede pertenecer a OTRO rol
        if (rolRepository.existsByNombreAndIdNot(modificarRolRequest.nombre(), id)) {
            throw new Exception("Ya existe otro rol con el nombre " + modificarRolRequest.nombre());
        }

        rol.setNombre(modificarRolRequest.nombre());
        rol.setDescripcion(modificarRolRequest.descripcion());
        rol = rolRepository.save(rol);

        return RolMapper.rolAObtenerRolResponse(rol);
    }

    @Override
    public void eliminarRol(Long id) throws Exception {
        if (id == null || id <= 0) {
            throw new Exception("El id no puede ser nulo ni menor o igual a cero");
        }
        Rol rol = rolRepository.findById(id)
                .orElseThrow(() -> new Exception("No existe un rol con el id " + id));

        if (!rol.getActivo()) {
            throw new Exception("El rol con id " + id + " ya se encuentra inactivo");
        }

        rol.setActivo(false);
        rolRepository.save(rol);
    }

    @Override
    @Transactional
    public void eliminarRolFisicamente (Long id){
        log.debug("Solicitud de eliminacion fisica del rol con id {}", id);

        // se valida el id (si el id es 0 o null, entonces error 400
        if (id == null || id <= 0){
            log.warn("Id invalido para eliminacion fisica de rol: {}", id);
            throw new IllegalArgumentException("El id no puede ser nulo ni menor o igual a cero");
        }

        // el rol debe existir (en caso de que no, da error 404
        Rol rol = rolRepository.findById(id).orElseThrow(()->{log.warn("Intento de eliminar un rol inexistente con id {}", id); return new RolNoEncontradoException((id));});

        //si el rol esta siendo usado arroja 409
        if (usuarioRepository.existsByRoles_Id(id)){
            log.warn("No se elimina el rol {} ({}): tiene un usuario asignado", id, rol.getNombre());
            throw new RolEnUsoException("El rol " + rol.getNombre() + "Tiene usuarios asignados");

        }
        if (rolPermisoRepository.existsByRol_Id(id)){
            log.warn("No se elimina el rol {} ({}): Tiene permisos asignados", id, rol.getNombre());
            throw new RolEnUsoException("El rol "+ rol.getNombre()+ "tiene permisos asignados");
        }

        // eliminacion fisica + flush para que el delete se ejecute ya
        rolRepository.delete(rol);
        rolRepository.flush();

        // despues de esto, los datos ta no existen en la BD y aparece INFOR
        log.info("AUDITORIA - Rol eliminado fisicamente: id={}, nombre = {}, activo ={}", rol.getId(), rol.getNombre(), rol.getActivo());
    }
}
