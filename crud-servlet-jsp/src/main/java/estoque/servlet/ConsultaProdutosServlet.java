package estoque.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.List;

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

@WebServlet("/consulta-produtos")
public class ConsultaProdutosServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		EntityManager em = JpaUtil.getEntityManager();
		ProdutoRepository repository = new ProdutoRepository(em);

		String nome = req.getParameter("nome");
		
		try {
			
			List<Produto> todosProdutos = repository.porNomeNaoExato(nome);
			
			req.setAttribute("produtos", todosProdutos);
			
			RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/paginas/consulta-produtos.jsp");
			dispatcher.forward(req, resp);

			
		} finally {
			em.close();
		}
		
		req.setAttribute("agora", new Date());
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	
	      this.doGet(req, resp);
	}
}
