package co.edu.usbcali.autosUsbCali.service;

import co.edu.usbcali.autosUsbCali.dto.request.CrearModificarModeloRequest;
import co.edu.usbcali.autosUsbCali.dto.response.ObtenerModeloResponse;

import java.util.List;

public interface IModeloService {

    List<ObtenerModeloResponse> obtenerTodos();

    ObtenerModeloResponse obtenerModeloPorId(Long id);

    // Todos los modelos de una marca (consulta usando la FK)
    List<ObtenerModeloResponse> obtenerModelosPorMarca(Long marcaId);

    ObtenerModeloResponse crearModelo(CrearModificarModeloRequest request);

    // Permite cambiar el nombre y también la marca (cambiar la FK)
    ObtenerModeloResponse modificarModelo(Long id, CrearModificarModeloRequest request);

    // DELETE real; solo si ningún vehículo usa el modelo
    void eliminarModeloFisicamente(Long id);
}
