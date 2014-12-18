package estoque.servlet;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import estoque.repository.ProdutoRepository;
import estoque.util.JpaUtil;

@WebServlet("/remove-produto")
public class RemoveProdutoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Long id = Long.parseLong(request.getParameter("id"));

		EntityManager manager = JpaUtil.getEntityManager();

		EntityTransaction trx = manager.getTransaction();

		try {

			trx.begin();

			ProdutoRepository repository = new ProdutoRepository(manager);

			repository.remove(id);

			trx.commit();

		} finally {
			if (trx.isActive())
				trx.rollback();
			manager.close();
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("consulta-produtos");
		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

				
	}

}
