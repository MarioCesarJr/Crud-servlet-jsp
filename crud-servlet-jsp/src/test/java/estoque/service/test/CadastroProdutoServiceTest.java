package estoque.service.test;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

import estoque.model.Produto;
import estoque.service.CadastroProdutoService;
import estoque.service.ServiceException;

public class CadastroProdutoServiceTest {

	@Test
	public void deve_Retornar_Verdadeiro_cadastro() throws ServiceException{

		Produto produto = new Produto();
		CadastroProdutoService service = new CadastroProdutoService();
		produto.setNome("Arroz");
		produto.setPrecoCusto(new BigDecimal(12));
		produto.setQuantidadeEstoque(20);
		service.validarProduto(produto);
		
		Assert.assertTrue(true);
		
	}
	
	@Test(expected=ServiceException.class)
	public void deve_Retornar_exception_cadastro() throws ServiceException{

		Produto produto = new Produto();
		CadastroProdutoService service = new CadastroProdutoService();
		produto.setNome("");
		produto.setPrecoCusto(new BigDecimal(12));
		produto.setQuantidadeEstoque(20);
		service.validarProduto(produto);
		
	}
}
