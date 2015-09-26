package br.com.flyra.examples.simple_java_angular_jwt.servlet;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.flyra.examples.simple_java_angular_jwt.model.User;
import br.com.flyra.examples.simple_java_angular_jwt.util.JWTUtil;

import com.google.gson.Gson;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final String username = "admin";
	private static final String password = "abc123";
	private Gson gson = new Gson();

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		User userRequest = getUserFromRequest(request);
		String token = null;

		if (username.equals(userRequest.getUsername())
				&& password.equals(userRequest.getPassword())) {
			
			token = JWTUtil.createToken(username);
			
			response.setHeader("authorization", token);
			response.setStatus(HttpServletResponse.SC_OK);
			response.getWriter().println(gson.toJson(new Token(token)));
		} else {
			String jsonStr = "{\"msg\": \"Username or password incorrect.\"}";

			response.setContentType("application/json");
			response.setCharacterEncoding("utf-8");
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.getWriter().print(jsonStr);
		}

	}

	private User getUserFromRequest(HttpServletRequest request)
			throws ServletException, IOException {

		BufferedReader reader = request.getReader();		
		return gson.fromJson(reader, User.class);
	}
	
	class Token {
		final String token;
		
		public Token(String token) {
			this.token = token;
		}
	}
}
