package br.com.sisnema.financeiroweb.exception;

public class LockException extends DAOException {

	private static final long serialVersionUID = 5494867574528933435L;

	public LockException() {
		super();
	}

	public LockException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public LockException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public LockException(String arg0) {
		super(arg0);
	}

	public LockException(Throwable arg0) {
		super(arg0);
	}

}
