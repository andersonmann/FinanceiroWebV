package br.com.sisnema.financeiroweb.filter;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import br.com.sisnema.financeiroweb.util.JPAUtil;

/**
 * Classe que interceptara TODAS as requisições JSF, criando um EntityManager 
 * e uma transação para esta requisição.
 *  
 * Maiores detalhes, livro página 143 
 */
@WebFilter({ "*.jsf" })
public class OpenEntityManagerInViewFilter implements Filter {

	public void destroy() {
		JPAUtil.closeEntityManagerFactory();
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		// Obtem um EntityManager 
		EntityManager em = JPAUtil.getEntityManager();
		
		// Obtem uma transação
		EntityTransaction tx = em.getTransaction();

		try {
			// inicia a transação antes de processar o request
			tx.begin();

			// processa a requisição
			chain.doFilter(request, response);
			
			// faz commit da transação
			if (tx != null && tx.isActive()) {
				tx.commit();
			}
			
		// ou em caso de erro faz o rollback
		} catch (Exception e) {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
		} finally {
			// Finaliza o Entity Manager
			em.close();
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
