package co.edu.usbcali.autosUsbCali.mapper;

import co.edu.usbcali.autosUsbCali.domain.Rol;
import co.edu.usbcali.autosUsbCali.dto.response.ObtenerRolResponse;
import tools.jackson.core.tree.ObjectTreeNode;

import java.util.ArrayList;
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
}
