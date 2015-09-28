package br.com.flyra.examples.simple_java_angular_jwt.model;

public class SimpleToken {

	private String token;

	public SimpleToken(String token) {
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	@Override
	public String toString() {
		return "SimpleToken [token=" + token + "]";
	}

}
