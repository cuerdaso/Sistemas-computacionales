package co.edu.usbcali.autosUsbCali.exception;

// RuntimeException (no verificada): así @Transactional hace rollback automático
// y no necesitas "throws" en la interfaz ni en el controlador.
public class RolNoEncontradoException extends RuntimeException{
    public RolNoEncontradoException(Long id) {
        super("No existe un rol con el id " + id);
    }
}
