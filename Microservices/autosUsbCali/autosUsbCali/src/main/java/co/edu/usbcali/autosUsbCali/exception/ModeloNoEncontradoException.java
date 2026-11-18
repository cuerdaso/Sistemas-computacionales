package co.edu.usbcali.autosUsbCali.exception;

public class ModeloNoEncontradoException extends RuntimeException{

    public ModeloNoEncontradoException(Long id){
        super("No existe un modelo con el id "+ id);
    }
}
