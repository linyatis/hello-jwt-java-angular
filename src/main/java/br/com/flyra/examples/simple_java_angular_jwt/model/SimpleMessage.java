package br.com.flyra.examples.simple_java_angular_jwt.model;

public class SimpleMessage {

	private String msg;

	public SimpleMessage(String msg) {
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}

	@Override
	public String toString() {
		return "SimpleMessage [msg=" + msg + "]";
	}

}
