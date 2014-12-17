package agenda.filter;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import agenda.util.JpaUtilThread;

public class ConnectionFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		EntityManager em = JpaUtilThread.getEntityManager();

		request.setAttribute("EntityManager", em);

		em.getTransaction().begin();
		chain.doFilter(request, response);

		try {
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
		} finally {
			em.close();

		}

	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		JpaUtilThread.getEntityManager();

	}

	@Override
	public void destroy() {
		JpaUtilThread.closeEntityManager();
		JpaUtilThread.closeEntityManagerFactory();

	}

}
