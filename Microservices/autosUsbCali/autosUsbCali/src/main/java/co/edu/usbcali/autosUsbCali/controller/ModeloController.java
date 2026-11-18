package co.edu.usbcali.autosUsbCali.controller;

import co.edu.usbcali.autosUsbCali.dto.request.CrearModificarModeloRequest;
import co.edu.usbcali.autosUsbCali.dto.response.ObtenerModeloResponse;
import co.edu.usbcali.autosUsbCali.service.IModeloService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/modelos")
@AllArgsConstructor
public class ModeloController {

    private final IModeloService modeloService;

    // GET /api/modelos
    @GetMapping
    public List<ObtenerModeloResponse> obtenerTodos() {
        return modeloService.obtenerTodos();
    }

    // GET /api/modelos/por-id/5
    @GetMapping("/por-id/{id}")
    public ResponseEntity<ObtenerModeloResponse> obtenerPorId(@PathVariable Long id) {
        return new ResponseEntity<>(modeloService.obtenerModeloPorId(id), HttpStatus.OK);
    }

    // GET /api/modelos/por-marca/1  → todos los modelos de la marca 1
    @GetMapping("/por-marca/{marcaId}")
    public List<ObtenerModeloResponse> obtenerPorMarca(@PathVariable Long marcaId) {
        return modeloService.obtenerModelosPorMarca(marcaId);
    }

    // POST /api/modelos/guardar
    @PostMapping("/guardar")
    public ResponseEntity<ObtenerModeloResponse> crearModelo(@RequestBody CrearModificarModeloRequest request) {
        return new ResponseEntity<>(modeloService.crearModelo(request), HttpStatus.CREATED);
    }

    // PUT /api/modelos/modificar/5
    @PutMapping("/modificar/{id}")
    public ResponseEntity<ObtenerModeloResponse> modificarModelo(@PathVariable Long id,
                                                                 @RequestBody CrearModificarModeloRequest request) {
        return new ResponseEntity<>(modeloService.modificarModelo(id, request), HttpStatus.OK);
    }

    // DELETE /api/modelos/eliminar-fisico/5
    @DeleteMapping("/eliminar-fisico/{id}")
    public ResponseEntity<Void> eliminarModeloFisicamente(@PathVariable Long id) {
        modeloService.eliminarModeloFisicamente(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}