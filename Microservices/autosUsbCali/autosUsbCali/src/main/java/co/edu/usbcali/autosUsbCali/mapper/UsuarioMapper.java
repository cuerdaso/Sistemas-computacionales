package co.edu.usbcali.autosUsbCali.mapper;

import co.edu.usbcali.autosUsbCali.domain.Rol;
import co.edu.usbcali.autosUsbCali.domain.Usuario;
import co.edu.usbcali.autosUsbCali.dto.response.ObtenerUsuarioResponse;

import java.util.List;

public class UsuarioMapper {

    public static ObtenerUsuarioResponse usuarioAObtenerUsuarioResponse(Usuario usuario){
        String[] roles= usuario.getRoles().stream().map(Rol::getNombre).toArray(String[]::new);
        return new ObtenerUsuarioResponse(
                usuario.getId(),
                usuario.getNombres().concat(" ").concat(usuario.getApellidos()),
                usuario.getEmail(), usuario.getCelular(),
                usuario.getTipoDocumento(),
                usuario.getNumeroDocumento(),
                roles
        );
    }

    public static List<ObtenerUsuarioResponse> listaUsuariosAListaObtenerUsuarioResponse(List<Usuario> usuarios) {
        return usuarios.stream().map(UsuarioMapper::usuarioAObtenerUsuarioResponse).toList();
    }
}
