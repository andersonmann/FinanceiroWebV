package br.com.sisnema.financeiroweb.domain;

public enum UsuarioPermissao {

	ROLE_USUARIO("Permissão default de um usuário"),
	ROLE_ADMINISTRADOR("Permissão de administração"),
	ROLE_VIP("Permissão de usuários vips");

	private String descricao;

	private UsuarioPermissao(String descricao) {
		this.descricao = descricao;
	}
	
	@Override
	public String toString() {
		return descricao;
	}
}
