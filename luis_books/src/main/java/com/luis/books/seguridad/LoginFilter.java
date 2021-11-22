package com.luis.books.seguridad;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.luis.book.service.SesionManager;

@WebFilter(urlPatterns = {"*.jsp"})
public class LoginFilter implements Filter {


	@Inject
	SesionManager sesionManager;
	
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {	
					
		// para procesar los caracteres especialesre
		servletRequest.setCharacterEncoding("UTF-8");

		// obtiene los elementos basicos para el procesamiento de la solicitud web
		HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
		HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
		String contextPath = httpRequest.getContextPath();
		
		String requestResource = getRequestResource(httpRequest.getRequestURI(), contextPath);
	
		// eliminar la cache del browser
		httpResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		httpResponse.setHeader("Pragma", "no-cache");
		httpResponse.setDateHeader("Expires", 0);		
		
		String token = (String) httpRequest.getSession().getAttribute("token");
		boolean tokenValido = false;
		if (token != "") {
			 tokenValido = sesionManager.existeUsuarioSesion(token) != "" ? true : false;
		}
		
		if (requestResource.startsWith("/login.jsp")) {
			if (tokenValido) {
				httpResponse.sendRedirect("/Books/index.jsp");
			} else {
				filterChain.doFilter(httpRequest, httpResponse);
				return;
			}
		}
		
		if (requestResource.startsWith("/signup.jsp")) {
				filterChain.doFilter(httpRequest, httpResponse);
				return;
		}
		
		if(requestResource.startsWith("/resources")) {
			filterChain.doFilter(httpRequest, httpResponse);
			return;
		}		
		
		if (tokenValido) {
			if (contextPath.isEmpty() || requestResource.equals("/")) {
				httpResponse.sendRedirect("/Books/index.jsp");
			}else {
				filterChain.doFilter(httpRequest, httpResponse);	
			}			
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	
	/**
	 * Valida que la rua solicitada este dentro del conexto y luego elimina el
	 * contexto de la ruta.
	 *
	 * <p>
	 * En caso de que el recurso solicitado no esté dentro del contexto retorna
	 * un null.
	 *
	 * @param recursoURI
	 *            el ruta del recurso solicitado que debe incluir el contexto.
	 * @return la ruta del recurso sin el contexto.
	 */
	public String getRequestResource(String recursoURI, String contextPath) {
		if (recursoURI != null) {
			if (recursoURI.startsWith(contextPath)) {
				return recursoURI.substring(contextPath.length());
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
