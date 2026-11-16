package co.edu.usbcali.autosUsbCali.exception;

// Se lanza cuando el rol tiene usuarios o permisos asignados → 409 Conflict
public class RolEnUsoException extends  RuntimeException{
    public RolEnUsoException(String mensaje) {
        super(mensaje);
    }
}
