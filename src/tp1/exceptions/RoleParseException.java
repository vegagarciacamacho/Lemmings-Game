package tp1.exceptions;

public class RoleParseException extends GameParseException {
	private static final long serialVersionUID = 1L; 

	public RoleParseException() {
		super();
	}
	
	public RoleParseException(String message) {
		super(message);
	}
	
	public RoleParseException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public RoleParseException(Throwable cause) {
		super(cause);
	}
	
	RoleParseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace){
		 super(message, cause, enableSuppression, writableStackTrace);
	}
}
