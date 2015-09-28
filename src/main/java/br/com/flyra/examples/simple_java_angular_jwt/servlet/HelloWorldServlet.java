package br.com.flyra.examples.simple_java_angular_jwt.servlet;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.flyra.examples.simple_java_angular_jwt.model.SimpleMessage;

import com.google.gson.Gson;

@WebServlet("/api/hello")
public class HelloWorldServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6319303353130827752L;

	private Gson gson = new Gson();

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");

		SimpleMessage sm = new SimpleMessage("Olá! Você está logado.");

		response.getWriter().print(gson.toJson(sm));
	}
}
