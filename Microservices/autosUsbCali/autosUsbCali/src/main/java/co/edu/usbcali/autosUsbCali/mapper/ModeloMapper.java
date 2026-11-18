package co.edu.usbcali.autosUsbCali.mapper;

import co.edu.usbcali.autosUsbCali.domain.Marca;
import co.edu.usbcali.autosUsbCali.domain.Modelo;
import co.edu.usbcali.autosUsbCali.dto.request.CrearModificarModeloRequest;
import co.edu.usbcali.autosUsbCali.dto.response.ObtenerModeloResponse;

import java.util.List;

public class ModeloMapper {

    public static ObtenerModeloResponse modeloAObtenerModeloResponse(Modelo modelo) {
        return new ObtenerModeloResponse(
                modelo.getId(),
                modelo.getNombre(),
                modelo.getMarca().getId(),
                modelo.getMarca().getNombre());
    }

    public static List<ObtenerModeloResponse> listaModelosAListaObtenerModeloResponse(List<Modelo> modelos) {
        return modelos.stream().map(ModeloMapper::modeloAObtenerModeloResponse).toList();
    }

    public static Modelo crearModificarModeloRequestAModelo(CrearModificarModeloRequest request, Marca marca) {
        return new Modelo(null, marca, request.nombre());
    }

}
