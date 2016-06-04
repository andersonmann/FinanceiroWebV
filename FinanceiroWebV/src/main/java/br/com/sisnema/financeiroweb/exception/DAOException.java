package br.com.sisnema.financeiroweb.exception;

public class DAOException extends Exception {

	private static final long serialVersionUID = -2724043612748351087L;

	public DAOException() {
	}

	public DAOException(String arg0) {
		super(arg0);
	}

	public DAOException(Throwable arg0) {
		super(arg0);
	}

	public DAOException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public DAOException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

}
