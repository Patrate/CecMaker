package main;

public class MissingParamException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7987575270025459670L;
	
	public MissingParamException() {
		super("Missing parameters");
	}
}
