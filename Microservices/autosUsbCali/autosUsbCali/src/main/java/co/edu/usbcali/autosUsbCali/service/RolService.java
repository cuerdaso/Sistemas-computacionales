package co.edu.usbcali.autosUsbCali.service;

import co.edu.usbcali.autosUsbCali.domain.Rol;
import co.edu.usbcali.autosUsbCali.dto.request.CrearModificarRolRequest;
import co.edu.usbcali.autosUsbCali.dto.response.ObtenerRolResponse;
import co.edu.usbcali.autosUsbCali.mapper.RolMapper;
import co.edu.usbcali.autosUsbCali.repository.RolRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RolService implements IRolService{
    private final RolRepository rolRepository;

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
        return null;
    }

    @Override
    public ObtenerRolResponse modificacion(Long id, CrearModificarRolRequest modificarRolRequest) throws Exception {
        return null;
    }

    @Override
    public ObtenerRolResponse modificacion() {
        return null;
    }

    @Override
    public ObtenerRolResponse crearRol(CrearModificarRolRequest crearRolRequest) throws Exception {
        // validacion del objeto ScreenRolRequest
        if (crearModificarRolRequest == null){
            throw new Exception("El objeto CrearRolRequest no puede ser nulo");
        }
        if (crearRolRequest.nombre() == null || crearRolRequest.nombre().isBlank()) {
            throw new Exception("El nombre no puede ser nulo ni estar vacio");
        }
        if (crearRolRequest.descripcion() == null || crearRolRequest.descripcion().isBlank()){
            throw new Exception("La descripcion no puede ser nula ni estar vacio");
        }
        // buscar si existe un rol con el nombre que se quiere agregar

        //opcion 1 - traer el objeto
        /*Optional<Rol> rolPorNombre = rolRepository.findByNombre(crearRolRequest.nombre());
        if (rolPorNombre.isPresent()){
            throw new Exception("Ya existe un rol con el nombre"+crearRolRequest.nombre());
        } */
        // opcion 2 - usar el booleano
        if (rolRepository.existsByNombre(crearRolRequest.nombre())){
            throw new Exception("Ya existe un rol con el nombre" + crearRolRequest.nombre());
        }


        // usar mapper para convertir DTO a objeto de Domain
        Rol rol = RolMapper.crearRolRequest(crearRolRequest);

        // persistir el rol en base de datos
        rol = rolRepository.save(rol);

        // crear DTO response obtenerRolResponse
        ObtenerRolResponse rolResponse = RolMapper.rolAObtenerRolResponse(rol);

        //retornar rolResponse
        return rolResponse;

    }
}
