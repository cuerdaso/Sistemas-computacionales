package co.edu.usbcali.autosUsbCali.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ManejadorGlobalExcepciones {
    // El servicio ya registró el WARN; aquí no lo repetimos para no duplicar logs.
    @ExceptionHandler(RolNoEncontradoException.class)
    public ProblemDetail manejarNoEncontrado(RolNoEncontradoException ex) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());   // 404
    }

    @ExceptionHandler(RolEnUsoException.class)
    public ProblemDetail manejarEnUso(RolEnUsoException ex) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, ex.getMessage());    // 409
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ProblemDetail manejarArgumentoInvalido(IllegalArgumentException ex) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage()); // 400
    }

    // Respaldo: por si alguna FK que no validamos bloquea el DELETE
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ProblemDetail manejarIntegridad(DataIntegrityViolationException ex) {
        log.warn("Violación de integridad en la base de datos", ex);   // excepción al final
        return ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT,
                "El registro tiene datos asociados y no puede eliminarse");
    }

    @ExceptionHandler(MarcaNoEncontradaException.class)
    public ProblemDetail manejarMarcaNoEncontrada(MarcaNoEncontradaException ex) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());   // 404
    }

    @ExceptionHandler(MarcaEnUsoException.class)
    public ProblemDetail manejarMarcaEnUso(MarcaEnUsoException ex) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, ex.getMessage());    // 409
    }

    @ExceptionHandler(ModeloNoEncontradoException.class)
    public ProblemDetail manejarModeloNoEncontrado(ModeloNoEncontradoException ex) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());   // 404
    }

    @ExceptionHandler(ModeloEnUsoException.class)
    public ProblemDetail manejarModeloEnUso(ModeloEnUsoException ex) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, ex.getMessage());    // 409
    }
}
