package br.com.sisnema.financeiroweb.exception;

public class UtilException extends Exception {

	private static final long serialVersionUID = 7522639246494274327L;

	public UtilException() {
	}

	public UtilException(String message) {
		super(message);
	}

	public UtilException(Throwable cause) {
		super(cause);
	}

	public UtilException(String message, Throwable cause) {
		super(message, cause);
	}

	public UtilException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
