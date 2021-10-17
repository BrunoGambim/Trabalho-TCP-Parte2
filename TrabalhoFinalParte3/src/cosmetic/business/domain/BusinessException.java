package cosmetic.business.domain;

public class BusinessException extends Exception {

	private String[] args;
	
	public BusinessException() {
		this.args = new String[0];
	}
	
	public BusinessException(final String message) {
		super(message);
		this.args = new String[0];
	}
	
	public BusinessException(final String message, final String arg) {
		super(message);
		this.args = new String[] { arg };
	}
	
	public BusinessException(final String message, final String arg,
			final Throwable cause) {
		super(message, cause);
		this.args = new String[] { arg };
	}
	
	public BusinessException(final String message, final String[] args) {
		super(message);
		this.args = args;
	}
	
	public BusinessException(final String message, final String[] args,
			final Throwable cause) {
		super(message, cause);
		this.args = args;
	}
	
	public BusinessException(final Throwable cause) {
		super(cause);
	}

	public String[] getArgs() {
		return args;
	}
	
}
