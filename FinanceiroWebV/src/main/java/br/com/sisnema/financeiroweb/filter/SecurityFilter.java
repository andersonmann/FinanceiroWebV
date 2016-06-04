package br.com.sisnema.financeiroweb.filter;

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

import org.apache.commons.lang3.StringUtils;

import br.com.sisnema.financeiroweb.action.LoginBean;
import br.com.sisnema.financeiroweb.domain.UsuarioPermissao;
import br.com.sisnema.financeiroweb.model.Usuario;

@WebFilter(urlPatterns="*" )
public class SecurityFilter implements Filter {

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		String path = StringUtils.substringAfter(req.getRequestURI(),req.getContextPath()+"/");
		path = StringUtils.substringBefore(path, "/");
		
		if(!path.equals("javax.faces.resource")){
			
			// Caso a tentativa seja para um recurso não publico deve estar logado
			if(!path.startsWith("publico")){
				
				Usuario usuarioLogado = (Usuario) req.getSession().getAttribute(LoginBean.USUARIO_LOGADO);
				
				if(usuarioLogado == null){
					String login = req.getContextPath()+"/"+"publico/login.jsf";
					((HttpServletResponse) response).sendRedirect(login);
					return;
				}
				
				// Caso esteja logado, verifica se possui permissão
				if(path.startsWith("admin") && 
						!usuarioLogado.getPermissao().contains(UsuarioPermissao.ROLE_ADMINISTRADOR)){
					((HttpServletResponse) response).sendRedirect(req.getContextPath()+"/publico/acessoInvalido.jsf");
					return;
				}
			}
		}
		
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
