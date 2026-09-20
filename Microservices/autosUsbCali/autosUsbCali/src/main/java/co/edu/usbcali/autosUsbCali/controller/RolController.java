package co.edu.usbcali.autosUsbCali.controller;

import co.edu.usbcali.autosUsbCali.domain.Rol;
import co.edu.usbcali.autosUsbCali.repository.RolRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
@AllArgsConstructor
public class RolController {
    private final RolRepository rolRepository;

    @GetMapping
    public ResponseEntity<List<Rol>> obtenerTodos() {
        return ResponseEntity.ok(rolRepository.findAll());
    }
}
