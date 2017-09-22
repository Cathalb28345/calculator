public class DivideByZeroException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Defines the error as attempting to divide by 0
	 */

	public DivideByZeroException() {

		super("Attempted to Divide by Zero This is  not allowed please try again");

	}

	public DivideByZeroException(String Alert) {
		super(Alert);

	}
}