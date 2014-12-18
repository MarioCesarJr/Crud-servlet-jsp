package estoque.servlet;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import estoque.model.Produto;
import estoque.repository.ProdutoRepository;
import estoque.util.JpaUtil;

@WebServlet("/atualiza-produto")
public class AtualizaProdutoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		EntityManager em = JpaUtil.getEntityManager();
		ProdutoRepository repository = new ProdutoRepository(em);

		Long id = Long.parseLong(req.getParameter("id"));

		try {

			Produto produto = repository.buscar(id);

			req.setAttribute("form", produto);

			RequestDispatcher dispatcher = req.getRequestDispatcher("cadastro-produto");

			dispatcher.forward(req, resp);

		} finally {
             em.close();
		}
	}

}
