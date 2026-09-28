package co.edu.usbcali.autosUsbCali.controller;

import co.edu.usbcali.autosUsbCali.dto.request.ActualizarRolRequest;
import co.edu.usbcali.autosUsbCali.dto.request.CrearRolRequest;
import co.edu.usbcali.autosUsbCali.dto.request.EliminarRolRequest;
import co.edu.usbcali.autosUsbCali.dto.response.ObtenerRolResponse;
import co.edu.usbcali.autosUsbCali.service.RolService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/roles")
@AllArgsConstructor
public class RolController {
    private final RolService rolService;

    @GetMapping
    public List<ObtenerRolResponse> obtenerTodos() {
        return rolService.obtenerTodos();
    }

    @GetMapping("/activos")
    public List<ObtenerRolResponse> obtenerTodosActivos(){
        return rolService.obtenerTodosActivos();
    }

    @GetMapping("/inactivos")
    public List<ObtenerRolResponse> obtenerTodosInactivos(){
        return rolService.obtenerTodosInactivos();
    }

    @GetMapping("/por-nombre/{nombre}")
    public ResponseEntity<ObtenerRolResponse> obtenerPorNombre(@PathVariable String nombre){
        return rolService.obtenerPorNombre(nombre).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ObtenerRolResponse> ObtenerPorId(@PathVariable Long id){
        return rolService.obtenerPorId(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ObtenerRolResponse> crear(@RequestBody CrearRolRequest request) {
        return new ResponseEntity<>(rolService.crear(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ObtenerRolResponse> actualizar(@PathVariable Long id,
                                                         @RequestBody ActualizarRolRequest request) {
        return rolService.actualizar(id, request)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        boolean eliminado = rolService.eliminar(new EliminarRolRequest(id));
        return eliminado ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
