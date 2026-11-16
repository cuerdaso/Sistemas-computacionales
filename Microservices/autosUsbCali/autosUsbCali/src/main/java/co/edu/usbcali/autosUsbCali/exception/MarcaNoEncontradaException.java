package co.edu.usbcali.autosUsbCali.exception;

public class MarcaNoEncontradaException extends RuntimeException {
    public MarcaNoEncontradaException(Long id) {
        super("No existe una marca con el id: " + id);
    }
}
