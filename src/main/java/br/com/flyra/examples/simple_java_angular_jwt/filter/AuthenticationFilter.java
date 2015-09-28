package br.com.flyra.examples.simple_java_angular_jwt.filter;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import br.com.flyra.examples.simple_java_angular_jwt.util.JWTUtil;

@WebFilter(urlPatterns = "/api/*")
public class AuthenticationFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(final ServletRequest request,
			final ServletResponse response, final FilterChain chain)
			throws ServletException, IOException {
		final HttpServletRequest httpRequest = (HttpServletRequest) request;
		final HttpServletResponse httpResponse = (HttpServletResponse) response;
		// System.out.println(((HttpServletRequest)
		// request).getHeader("authorization"));
		HttpServletRequestWrapper wrapper = new HttpServletRequestWrapper(
				httpRequest) {
			@Override
			public String getHeader(String name) {
				final String value = request.getParameter(name);
				if (value != null) {
					return value;
				}
				return super.getHeader(name);
			}
		};

		String token = wrapper.getHeader("authorization");

		if (token != null) {
			Map<String, Object> decode = JWTUtil.decode(token);
			decode.forEach((k, v) -> System.out.printf(k, v));
			chain.doFilter(wrapper, response);	
		} else {
			sendError(httpResponse);
		}

	}

	@Override
	public void destroy() {
	}

	private void sendError(HttpServletResponse response)
			throws ServletException, IOException {
		sendError(response, "Unauthorized");
	}

	private void sendError(HttpServletResponse response, String msg)
			throws ServletException, IOException {
		String jsonStr = "{\"msg\": \"" + msg + "\"}";

		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.getWriter().print(jsonStr);
	}
}
