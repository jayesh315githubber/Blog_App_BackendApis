package com.blog.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

	/*
	 * By default,the BasicAuthenticationEntryPoint provisioned by Spring Security
	 * returns a full page for a 401 Unauthorized response back to the client. This
	 * HTML representation of the error renders well in a browser.Conversely, it's
	 * not well suited for other scenarios, such as a REST API where a json
	 * representation may be preferred. we took control of the exact error message
	 * format, moving from the standard HTML error page to a custom text or JSON
	 * format.
	 */
	
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {

		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Access Denined !!");

	}

}
