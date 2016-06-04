package br.com.sisnema.financeiroweb.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * Classe utilitária para disponibilizar as conexões com o banco...
 * Conterá uma Fábrica de Entity Manager (que conterá a sessao do Hibernate)
 */
public class JPAUtil {

	// Nome da persistence-unit registrada no arquivo persistence.xml --> Livro pg 29...
	private static final String PERSISTENCE_UNIT_NAME = "financeiro-persistence";

	// Criamos uma Thread para conter a EntityManager da requisição
	private static ThreadLocal<EntityManager> manager = new ThreadLocal<EntityManager>();

	private static EntityManagerFactory factory;
	
	static {
		// Variável estática inicializada APENAS UMA VEZ que disponibilizará instancias do EntityManager
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	}


	public static boolean isEntityManagerOpen(){
		return JPAUtil.manager.get() != null && JPAUtil.manager.get().isOpen();
	}
	
	/**
	 * Disponibiliza um EntityManager utilizando a fábrica criada anteriormente
	 */
	public static EntityManager getEntityManager() {
		EntityManager em = JPAUtil.manager.get();
		if (em == null || !em.isOpen()) {
			em = factory.createEntityManager();
			manager.set(em);
		}
		return em;
	}
	
	/**
	 * Que finaliza o EntityManager comitando a transação caso esteja ativa
	 */
	public static void closeEntityManager() {
		EntityManager em = JPAUtil.manager.get();
		if (em != null) {
			EntityTransaction tx = em.getTransaction();
			if (tx.isActive()) { 
				tx.commit();
			}
			em.close();
			JPAUtil.manager.set(null);
		}
	}
	
	/**
	 * Ao finalizar a aplicação no container deverá se chamar este método para
	 * fechar a fábrica de EntityManager
	 */
	public static void closeEntityManagerFactory(){
		closeEntityManager();
		JPAUtil.factory.close();
	}
}