package br.com.sisnema.financeiroweb.dao;

import javax.persistence.EntityManager;

import org.hibernate.Session;

import br.com.sisnema.financeiroweb.util.JPAUtil;

/**
 * Classe abstrata que herdara os comportamentos de {@link IDAO} e conterá atributos 
 * e funcionalidades genéricas a todas as filhas 
 */
public abstract class DAO<T> implements IDAO<T> {

	/**
	 * Como todas as DAOS irão possuir uma sessao, criaremos a mesma
	 * na classe pai, sendo ela HERDADA pelas filhas....
	 */
    protected final EntityManager em;
	
	/**
	 * Método construtor de DAO para INICIALIZAR a sessao
	 * do hibernate
	 */
	public DAO() {
		em = JPAUtil.getEntityManager();
	}
	
    protected final Session getSession() {
    	return (Session) em.unwrap(Session.class);
    }
    
    protected final void commit() {
    	getSession().getTransaction().commit();
	}
    
    protected final void beginTransaction() {
    	JPAUtil.getEntityManager().getTransaction().begin();
    }
    
    protected final void rollback() {
    	JPAUtil.getEntityManager().getTransaction().rollback();
    }
}






