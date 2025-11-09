package tp1.exceptions;

public class CommandException extends Exception{
	
	private static final long serialVersionUID = 1L; //sino añadimos esto nos sale una advertencia de que se debe declarar el campo porque estamos heredando de la interfaz 'Serializable'
	
	public CommandException() {
		super();
	}
	
	public CommandException(String message) {
		super(message);
	}
	
	public CommandException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public CommandException(Throwable cause) {
		super(cause);
	}
	
	CommandException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace){
		 super(message, cause, enableSuppression, writableStackTrace);
	}
	
}
