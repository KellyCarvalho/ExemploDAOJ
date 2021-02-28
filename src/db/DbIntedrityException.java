package db;

public class DbIntedrityException extends RuntimeException{

	
	private static final long serialVersionUID = 1L;
	
	public DbIntedrityException(String msg) {
		super(msg);
	}

}
