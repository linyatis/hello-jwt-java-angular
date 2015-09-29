package br.com.flyra.examples.simple_java_angular_jwt.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.flyra.examples.simple_java_angular_jwt.exception.TokenException;
import br.com.flyra.examples.simple_java_angular_jwt.model.SimpleMessage;
import br.com.flyra.examples.simple_java_angular_jwt.util.JWTUtil;

import com.google.gson.Gson;

@WebFilter(urlPatterns = "/api/*")
public class AuthenticationFilter implements Filter {

	private Gson gson = new Gson();
	private HttpServletRequest httpRequest;
	private HttpServletResponse httpResponse;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws ServletException, IOException {
		httpRequest = (HttpServletRequest) request;
		httpResponse = (HttpServletResponse) response;

		String path = httpRequest.getRequestURI();

		if (path.endsWith("login")) {
			chain.doFilter(httpRequest, response);
		} else {
			String token = httpRequest.getHeader("authorization");

			if (token != null) {
				try {
					JWTUtil.decode(token);
					chain.doFilter(httpRequest, httpResponse);
				} catch (TokenException e) {
					sendError(e.getLocalizedMessage());
				}

			} else {
				sendError();
			}
		}
	}

	@Override
	public void destroy() {
	}

	private void sendError() throws ServletException, IOException {
		sendError("Unauthorized");
	}

	private void sendError(String msg) throws ServletException, IOException {
		SimpleMessage sm = new SimpleMessage(msg);

		httpResponse.setContentType("application/json");
		httpResponse.setCharacterEncoding("utf-8");
		httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		httpResponse.getWriter().print(gson.toJson(sm));
	}
}
