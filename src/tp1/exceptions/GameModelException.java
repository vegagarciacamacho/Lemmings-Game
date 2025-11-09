package tp1.exceptions;

public class GameModelException extends Exception {
private static final long serialVersionUID = 1L; //sino añadimos esto nos sale una advertencia de que se debe declarar el campo porque estamos heredando de la interfaz 'Serializable'
	
	public GameModelException() {
		super();
	}
	
	public GameModelException(String message) {
		super(message);
	}
	
	public GameModelException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public GameModelException(Throwable cause) {
		super(cause);
	}
	
	GameModelException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace){
		 super(message, cause, enableSuppression, writableStackTrace);
	}
	
}
