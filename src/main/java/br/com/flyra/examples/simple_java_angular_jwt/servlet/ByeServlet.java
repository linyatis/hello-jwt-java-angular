package br.com.flyra.examples.simple_java_angular_jwt.servlet;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/bye")
public class ByeServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6319303353130827752L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");

		String jsonStr = "{\"msg\": \"bye!\"}";

		response.getWriter().print(jsonStr);
	}
}
