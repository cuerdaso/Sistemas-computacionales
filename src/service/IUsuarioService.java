package co.edu.usbcali.autosUsbCali.service;

import co.edu.usbcali.autosUsbCali.dto.response.ObtenerUsuarioResponse;

import java.util.List;

public interface IUsuarioService {
    ObtenerUsuarioResponse obtenerUsuarioPorId(Long id) throws Exception;
    List<ObtenerUsuarioResponse> obtenerUsuarios();
}
