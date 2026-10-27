package co.edu.usbcali.autosUsbCali.controller;

import co.edu.usbcali.autosUsbCali.dto.request.CrearModificarRolRequest;
import co.edu.usbcali.autosUsbCali.dto.response.ObtenerRolResponse;
import co.edu.usbcali.autosUsbCali.service.IRolService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
@AllArgsConstructor
public class RolController {
    private final IRolService rolService;

    @GetMapping
    public List<ObtenerRolResponse> obtenerTodos() {
        return rolService.obtenerTodos();
    }

    @GetMapping("/activos")
    public List<ObtenerRolResponse> obtenerTodosActivos(){
        return rolService.buscarRolPorEstado(true);
    }

    @GetMapping("/inactivos")
    public List<ObtenerRolResponse> obtenerTodosInactivos(){
        return rolService.buscarRolPorEstado(false);
    }

    @GetMapping("/por-nombre/{nombre}")
    public ResponseEntity<ObtenerRolResponse> obtenerPorNombre(@PathVariable String nombre){
        ObtenerRolResponse rolResponse = rolService.obtenerRolPorNombre(nombre);
        if (rolResponse != null) {
            return new ResponseEntity<>(rolResponse, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/por-id/{id}")
    public ResponseEntity<ObtenerRolResponse> ObtenerPorId(@PathVariable Long id){
        ObtenerRolResponse rolResponse = rolService.obtenerRolPorId(id);
        if (rolResponse != null) {
            return new ResponseEntity<>(rolResponse, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/guardar")
    public ResponseEntity<ObtenerRolResponse> crearRol(@RequestBody CrearModificarRolRequest crearModificarRolRequest) throws Exception {
        return new ResponseEntity<>(
                rolService.crearRol(crearModificarRolRequest),HttpStatus.CREATED);
    };

}
