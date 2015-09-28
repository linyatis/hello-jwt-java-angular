package br.com.flyra.examples.simple_java_angular_jwt.exception;

/**
 * Exception used when a token has not validated correctly. For example: the
 * token has expired or the token is not trusted.
 *
 */
public class TokenException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5150525338999551014L;

	public TokenException(Throwable cause) {
		super(cause);
	}

	public TokenException(String message, Throwable cause) {
		super(message, cause);
	}

}
