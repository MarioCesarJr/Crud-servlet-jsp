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

import estoque.form.ProdutoForm;
import estoque.model.Produto;
import estoque.repository.ProdutoRepository;
import estoque.service.CadastroProdutoService;
import estoque.service.ServiceException;
import estoque.util.JpaUtil;

@WebServlet("/cadastro-produto")
public class CadastroProdutoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/paginas/cadastro-produto.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EntityManager manager = JpaUtil.getEntityManager();
		
		EntityTransaction trx = manager.getTransaction();
		
		ProdutoForm form = null;
		String mensagem = null;
		
		try {
			
			trx.begin();
			
			form = ProdutoForm.fromRequest(request);
			
			Produto produto = form.toProduto();
			
			CadastroProdutoService servico = new CadastroProdutoService(new ProdutoRepository(manager));
			
			String id = request.getParameter("id");
			
			if(id == null || id.isEmpty()){
			
				servico.cadastrar(produto);
			
			} else {
				Long codigo = Long.parseLong(id);
				servico.atualiza(codigo, produto.getNome(), produto.getQuantidadeEstoque(), produto.getPrecoCusto());
			}
			
			form = null;
			mensagem = "Produto salvo com sucesso!";
			
			trx.commit();
		} catch (ServiceException e) {
			mensagem = e.getMessage();
		} finally {
			if (trx.isActive())
				trx.rollback();
			manager.close();
		}
		
		request.setAttribute("mensagem", mensagem);
		request.setAttribute("form", form);
		
		doGet(request, response);
	}
}