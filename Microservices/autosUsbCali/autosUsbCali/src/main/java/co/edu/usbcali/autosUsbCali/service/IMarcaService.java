package co.edu.usbcali.autosUsbCali.service;

import co.edu.usbcali.autosUsbCali.dto.request.CrearMarcaRequest;
import co.edu.usbcali.autosUsbCali.dto.response.ObtenerMarcaResponse;

import java.util.List;

public interface IMarcaService {

    // Lista todas las marcas
    List<ObtenerMarcaResponse> obtenerTodas();

    // Crea una marca nueva (la usamos para tener datos de prueba)
    ObtenerMarcaResponse crearMarca(CrearMarcaRequest request);

    // Borra la fila de la tabla marcas (DELETE real).
    // Solo se permite si ningún modelo usa la marca.
    void eliminarMarcaFisicamente(Long id);
}
