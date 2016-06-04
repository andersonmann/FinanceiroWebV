package br.com.sisnema.financeiroweb.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

/**
 * Classe pai de todas as entidades persistiveis
 * 
 * MappedSuperclass --> Informa que os atributos serão HERDADOS por entidades
 */
@MappedSuperclass
public class BaseEntity implements Serializable {

	private static final long serialVersionUID = -948207347529643488L;

	@Version
	protected int version;
	
	
	@Column(updatable = false) // evita que esta coluna seja alterada, hibernate
							   // a desconsidera em operações de update...
	@Temporal(TemporalType.TIMESTAMP)
	protected Date dtCreated;

	@Temporal(TemporalType.TIMESTAMP)
	protected Date dtUpdated;
	
	@PrePersist
	@PreUpdate
	/**
	 * Este método será sempre chamada ao se criar ou alterar um registro
	 */
	void onPersist(){
		dtCreated = dtUpdated = new Date();
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public Date getDtCreated() {
		return dtCreated;
	}

	public void setDtCreated(Date dtCreated) {
		this.dtCreated = dtCreated;
	}

	public Date getDtUpdated() {
		return dtUpdated;
	}

	public void setDtUpdated(Date dtUpdated) {
		this.dtUpdated = dtUpdated;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dtCreated == null) ? 0 : dtCreated.hashCode());
		result = prime * result + ((dtUpdated == null) ? 0 : dtUpdated.hashCode());
		result = prime * result + version;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof BaseEntity)) {
			return false;
		}
		BaseEntity other = (BaseEntity) obj;
		if (dtCreated == null) {
			if (other.dtCreated != null) {
				return false;
			}
		} else if (!dtCreated.equals(other.dtCreated)) {
			return false;
		}
		if (dtUpdated == null) {
			if (other.dtUpdated != null) {
				return false;
			}
		} else if (!dtUpdated.equals(other.dtUpdated)) {
			return false;
		}
		if (version != other.version) {
			return false;
		}
		return true;
	}
}
