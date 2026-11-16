package co.edu.usbcali.autosUsbCali.mapper;

import co.edu.usbcali.autosUsbCali.domain.Marca;
import co.edu.usbcali.autosUsbCali.dto.request.CrearMarcaRequest;
import co.edu.usbcali.autosUsbCali.dto.response.ObtenerMarcaResponse;

import java.util.List;

public class MarcaMapper {

    // Entidad DTO de respuesta
    public static ObtenerMarcaResponse marcaAObtenerMarcaResponse(Marca marca) {
        return new ObtenerMarcaResponse(marca.getId(), marca.getNombre(), marca.getActive());
    }

    // Lista de entidades -> lista de DTOs
    public static List<ObtenerMarcaResponse> listaMarcasAListaObtenerMarcaResponse(List<Marca> marcas) {
        return marcas.stream().map(MarcaMapper::marcaAObtenerMarcaResponse).toList();
    }

    // DTO de creación -> entidad nueva
    public static Marca crearMarcaRequestAMarca(CrearMarcaRequest request) {
        return new Marca(null, request.nombre(), true);
    }
}
