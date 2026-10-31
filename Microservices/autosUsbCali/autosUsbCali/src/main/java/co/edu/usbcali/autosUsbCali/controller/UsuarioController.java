package co.edu.usbcali.autosUsbCali.controller;


import co.edu.usbcali.autosUsbCali.dto.response.ObtenerUsuarioResponse;
import co.edu.usbcali.autosUsbCali.service.IUsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
@AllArgsConstructor
public class UsuarioController {
    private final IUsuarioService usuarioService;

    @GetMapping
    public List<ObtenerUsuarioResponse> obtenerUsuarios() {
        return usuarioService.obtenerUsuarios();
    }

    @GetMapping("/por-id/{id}")
    public ResponseEntity<ObtenerUsuarioResponse> obtenerPorId(@PathVariable Long id) throws Exception{
       return new ResponseEntity<>(usuarioService.obtenerUsuarioPorId(id), HttpStatus.OK);
    }

}
