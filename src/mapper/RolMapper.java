package co.edu.usbcali.autosUsbCali.mapper;

import co.edu.usbcali.autosUsbCali.domain.Rol;
import co.edu.usbcali.autosUsbCali.dto.request.CrearModificarRolRequest;
import co.edu.usbcali.autosUsbCali.dto.response.ObtenerRolResponse;

import java.util.List;

public class RolMapper {
    public static ObtenerRolResponse rolAObtenerRolResponse(Rol rol){
        ObtenerRolResponse obtenerRolResponse =
                new ObtenerRolResponse(rol.getId(), rol.getNombre());
        return obtenerRolResponse;
    }

    public static List<ObtenerRolResponse> listaRolesAListaObtenerRolResponse(List<Rol> roles){
        return roles.stream().map(RolMapper::rolAObtenerRolResponse).toList();
    }

    // metodo que convierte un Request de crear rol a un Rol (Entidad de dominio)
    public static Rol CrearModificarRolRequestARol(CrearModificarRolRequest crearModificarRolRequest) {
        // Forma 1 usando el constructor
        return new Rol(null,
                crearModificarRolRequest.nombre(),
                crearModificarRolRequest.descripcion(),
                true);

        // Forma 2 usando el constructor vacío
        /*Rol rol = new Rol();
        rol.setNombre(crearModificarRolRequest.nombre());
        rol.setDescripcion(crearModificarRolRequest.descripcion());
        rol.setActivo(true);
        return rol;*/
    }
}
