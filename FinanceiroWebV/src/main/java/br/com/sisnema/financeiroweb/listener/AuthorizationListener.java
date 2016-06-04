package br.com.sisnema.financeiroweb.listener;

import java.io.IOException;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpSession;

import br.com.sisnema.financeiroweb.action.LoginBean;
import br.com.sisnema.financeiroweb.domain.UsuarioPermissao;
import br.com.sisnema.financeiroweb.model.Usuario;

/**
 * Classe que intercepta todas as requisições JSF
 */
public class AuthorizationListener implements PhaseListener {

	private static final long serialVersionUID = -260659705084048964L;

	public void afterPhase(PhaseEvent  event) {
		FacesContext facesContext = event.getFacesContext();
		String currentPage = facesContext.getViewRoot().getViewId();
		
		// Esta tentando acessar uma página INTERNA que necessita de login
		if(!currentPage.startsWith("/publico")){
			
			HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
			
			if(facesContext.getResponseComplete()){
				return;
			}
			
			Usuario usuarioLogado = (Usuario) session.getAttribute(LoginBean.USUARIO_LOGADO);
			
			if(usuarioLogado == null){
				renderResponse("publico/login.jsf");
				
			} else {
				// Validar permissões do usuário
				if(currentPage.startsWith("/admin") && 
						!usuarioLogado.getPermissao().contains(UsuarioPermissao.ROLE_ADMINISTRADOR)){
					renderResponse("/publico/acessoInvalido.jsf");
				}
			}
		}
	}

	protected void renderResponse(String destino) {
		try {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			if(facesContext.getResponseComplete()){
				return;
			}
			
			String context = facesContext.getExternalContext().getRequestContextPath();
			
			facesContext.getExternalContext().redirect(context+destino);
			facesContext.responseComplete();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void beforePhase(PhaseEvent event) {
	}

	public PhaseId getPhaseId() {
		// Informamos que só efetuara validações para o ciclo de vida 
		// referente a invocação da pagina
		return PhaseId.INVOKE_APPLICATION;
	}
}














