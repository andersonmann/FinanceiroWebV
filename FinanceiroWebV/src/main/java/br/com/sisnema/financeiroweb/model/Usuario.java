package br.com.sisnema.financeiroweb.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.NaturalId;

import br.com.sisnema.financeiroweb.domain.UsuarioPermissao;

@Entity
public class Usuario extends BaseEntity {

	private static final long serialVersionUID = 8402638884150282595L;

	@Id
	@Column(name="cod_usuario")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer codigo;
	
	private String nome;
	
	@NaturalId
	@Column(updatable=false) 
	private String login;
	
	private String senha;
	
	private String celular;
	
	private String nascimento;
	
	private String idioma;
	
	private String email;
	
	private boolean ativo;
	
	@ElementCollection(targetClass = UsuarioPermissao.class)	     
	@JoinTable( name = "usuario_permissao", 
			    uniqueConstraints = {@UniqueConstraint(columnNames = {"usuario", "permissao" }) }, 
			    joinColumns = @JoinColumn(name = "usuario")
			   )	     
	@Enumerated(EnumType.STRING)
	@Column(name = "permissao", length = 50)
	private Set<UsuarioPermissao> permissao = new HashSet<UsuarioPermissao>();

	public Usuario() {
	}

	public Usuario(Integer codigo) {
		super();
		this.codigo = codigo;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getNascimento() {
		return nascimento;
	}

	public void setNascimento(String nascimento) {
		this.nascimento = nascimento;
	}

	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (ativo ? 1231 : 1237);
		result = prime * result + ((celular == null) ? 0 : celular.hashCode());
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((idioma == null) ? 0 : idioma.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((nascimento == null) ? 0 : nascimento.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((permissao == null) ? 0 : permissao.hashCode());
		result = prime * result + ((senha == null) ? 0 : senha.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (!(obj instanceof Usuario)) {
			return false;
		}
		Usuario other = (Usuario) obj;
		if (ativo != other.ativo) {
			return false;
		}
		if (celular == null) {
			if (other.celular != null) {
				return false;
			}
		} else if (!celular.equals(other.celular)) {
			return false;
		}
		if (codigo == null) {
			if (other.codigo != null) {
				return false;
			}
		} else if (!codigo.equals(other.codigo)) {
			return false;
		}
		if (email == null) {
			if (other.email != null) {
				return false;
			}
		} else if (!email.equals(other.email)) {
			return false;
		}
		if (idioma == null) {
			if (other.idioma != null) {
				return false;
			}
		} else if (!idioma.equals(other.idioma)) {
			return false;
		}
		if (login == null) {
			if (other.login != null) {
				return false;
			}
		} else if (!login.equals(other.login)) {
			return false;
		}
		if (nascimento == null) {
			if (other.nascimento != null) {
				return false;
			}
		} else if (!nascimento.equals(other.nascimento)) {
			return false;
		}
		if (nome == null) {
			if (other.nome != null) {
				return false;
			}
		} else if (!nome.equals(other.nome)) {
			return false;
		}
		if (permissao == null) {
			if (other.permissao != null) {
				return false;
			}
		} else if (!permissao.equals(other.permissao)) {
			return false;
		}
		if (senha == null) {
			if (other.senha != null) {
				return false;
			}
		} else if (!senha.equals(other.senha)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Usuario [codigo=" + codigo + ", nome=" + nome + ", login=" + login + ", senha=" + senha + ", celular="
				+ celular + ", nascimento=" + nascimento + ", idioma=" + idioma + ", email=" + email + ", ativo="
				+ ativo + ", permissao=" + permissao + "]";
	}

	public Set<UsuarioPermissao> getPermissao() {
		return permissao;
	}

	public void setPermissao(Set<UsuarioPermissao> permissao) {
		this.permissao = permissao;
	}

}
