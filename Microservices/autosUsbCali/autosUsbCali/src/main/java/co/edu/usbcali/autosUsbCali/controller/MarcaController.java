package co.edu.usbcali.autosUsbCali.controller;

import co.edu.usbcali.autosUsbCali.dto.request.CrearMarcaRequest;
import co.edu.usbcali.autosUsbCali.dto.response.ObtenerMarcaResponse;
import co.edu.usbcali.autosUsbCali.service.IMarcaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/marcas")
@AllArgsConstructor
public class MarcaController {

    private final IMarcaService marcaService;

    // GET http://localhost:8080/api/marcas
    @GetMapping
    public List<ObtenerMarcaResponse> obtenerTodas() {
        return marcaService.obtenerTodas();
    }

    // POST http://localhost:8080/api/marcas/guardar
    @PostMapping("/guardar")
    public ResponseEntity<ObtenerMarcaResponse> crearMarca(@RequestBody CrearMarcaRequest request) {
        return new ResponseEntity<>(marcaService.crearMarca(request), HttpStatus.CREATED);   // 201
    }

    // DELETE http://localhost:8080/api/marcas/eliminar-fisico/{id}
    @DeleteMapping("/eliminar-fisico/{id}")
    public ResponseEntity<Void> eliminarMarcaFisicamente(@PathVariable Long id) {
        marcaService.eliminarMarcaFisicamente(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);   // 204
    }
}
