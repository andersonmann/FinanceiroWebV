package br.com.sisnema.financeiroweb.action;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.apache.commons.lang3.StringUtils;

import br.com.sisnema.financeiroweb.domain.UsuarioPermissao;
import br.com.sisnema.financeiroweb.exception.LockException;
import br.com.sisnema.financeiroweb.exception.RNException;
import br.com.sisnema.financeiroweb.model.Usuario;
import br.com.sisnema.financeiroweb.negocio.UsuarioRN;

@ManagedBean
@RequestScoped
public class UsuarioBean extends ActionBean<Usuario> {

	private Usuario usuario = new Usuario();
	private String confirmaSenha;
	private List<Usuario> lista;
	private String destinoSalvar;
	
	public UsuarioBean() {
		super(new UsuarioRN());
	}
	
	public String novo(){
		usuario = new Usuario();
		usuario.setAtivo(true);
		destinoSalvar = "/publico/usuarioSucesso";
		return "usuario";
	}
	
	public String editar(){
		destinoSalvar = "/admin/usuarioAdm";
		confirmaSenha = usuario.getSenha();
		return "/publico/usuario";
	}
	
	public String excluir(){
		try {
			negocio.excluir(usuario);
			apresentarMensagemDeSucesso("Usuário excluído com sucesso!");
			lista = null;
			
		} catch (RNException e) {
			apresentarMensagemDeErro(e);
		}
		return null;
	}
	
	public String salvar(){
		try {
			if(!StringUtils.equals(usuario.getSenha(), confirmaSenha)){
				apresentarMensagemDeErro("Senhas diferentes");
				return null;
			}
			
			boolean isInsert = (usuario.getCodigo() == null);
			
			negocio.salvar(usuario);
			
			apresentarMensagemDeSucesso("Usuário "+(isInsert ? "inserido" : "alterado") + " com sucesso!");
			lista = null;
			
			return destinoSalvar;
			
		} catch (RNException e) {
			apresentarMensagemDeErro(e);
			
			if(e.getCause() instanceof LockException){
				return destinoSalvar;
			}
		}
		return null;
	}

	public String ativar(){
		try {
			usuario.setAtivo(!usuario.isAtivo());
			negocio.salvar(usuario);
			
			apresentarMensagemDeSucesso("Usuário "+
										(usuario.isAtivo() ? "ativado" : "inativado")+
										" com sucesso!");
			
		} catch (RNException e) {
			apresentarMensagemDeErro(e);
		}
		
		return null;
	} 
	
	public void atribuiPermissao(Usuario usuario, String permissao){
		UsuarioPermissao up = UsuarioPermissao.valueOf(permissao);
		
		if(usuario.getPermissao().contains(up)){
			usuario.getPermissao().remove(up);
		} else {
			usuario.getPermissao().add(up);
		}
	}
	
	public List<Usuario> getLista() {
		if(lista == null){
			lista = negocio.pesquisar(new Usuario());
		}
		
		return lista;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getConfirmaSenha() {
		return confirmaSenha;
	}

	public void setConfirmaSenha(String confirmaSenha) {
		this.confirmaSenha = confirmaSenha;
	}

	public void setLista(List<Usuario> lista) {
		this.lista = lista;
	}

	public String getDestinoSalvar() {
		return destinoSalvar;
	}

	public void setDestinoSalvar(String destinoSalvar) {
		this.destinoSalvar = destinoSalvar;
	}
}
