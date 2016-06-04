package br.com.sisnema.financeiroweb.negocio;

import java.util.List;

import br.com.sisnema.financeiroweb.dao.UsuarioDAO;
import br.com.sisnema.financeiroweb.domain.UsuarioPermissao;
import br.com.sisnema.financeiroweb.exception.DAOException;
import br.com.sisnema.financeiroweb.exception.RNException;
import br.com.sisnema.financeiroweb.model.Usuario;

public class UsuarioRN extends RN<Usuario> {

	public UsuarioRN() {
		super(new UsuarioDAO());
	}
	
	public void salvar(Usuario usuarioDaTela) throws RNException {
		
		if(usuarioDaTela.getCodigo() != null){
			try {
				UsuarioDAO uDAO = (UsuarioDAO) dao;
				
				uDAO.alterar(usuarioDaTela);
			} catch (DAOException e) {
				throw new RNException(e.getMessage(),e);
			}
			
		} else {
			
			try {
				Usuario userExistente = buscarUsuarioPorLogin(usuarioDaTela.getLogin());
				
				if(userExistente != null){
					throw new RNException("Já existe um usuário com o login informado");
				}
				
				usuarioDaTela.getPermissao().add(UsuarioPermissao.ROLE_USUARIO);
				
				dao.salvar(usuarioDaTela);
			} catch (DAOException e) {
				throw new RNException(e.getMessage(),e);
			}
		}
	}

	public void excluir(Usuario model) throws RNException {
		try {
			dao.excluir(model);
		} catch (DAOException e) {
			throw new RNException(e.getMessage(), e);
		}
	}

	public Usuario obterPorId(Usuario filtro) {
		return dao.obterPorId(filtro);
	}

	public List<Usuario> pesquisar(Usuario filtros) {
		return dao.pesquisar(filtros);
	}

	public Usuario buscarUsuarioPorLoginESenha(String login, String senha){
		return ((UsuarioDAO) dao).buscarUsuarioPorLoginESenha(login, senha);
	}
	
	public Usuario buscarUsuarioPorLogin(String login){
		return ((UsuarioDAO) dao).buscarUsuarioPorLogin(login);
	}
	
}

















