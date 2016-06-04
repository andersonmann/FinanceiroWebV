package br.com.sisnema.financeiroweb.action;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

import br.com.sisnema.financeiroweb.exception.RNException;
import br.com.sisnema.financeiroweb.negocio.IRN;

public class ActionBean<T> {
	
	protected final IRN<T> negocio;

	public ActionBean(IRN<T> negocio) {
		super();
		this.negocio = negocio;
	}
	
	protected void apresentarMensagemDeErro(RNException e) {
		apresentarMensagemDeErro(e.getMessage());
	}
	
	protected void apresentarMensagemDeErro(String msg) {
		apresentarMensagem(msg, FacesMessage.SEVERITY_ERROR);
	}
	
	protected void apresentarMensagemDeSucesso(String msg) {
		apresentarMensagem(msg, FacesMessage.SEVERITY_INFO);
	}
	
	protected void apresentarMensagem(String msg, Severity severity) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, msg,""));
	}
}
















