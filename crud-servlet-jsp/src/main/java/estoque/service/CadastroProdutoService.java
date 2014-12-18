package estoque.service;

import java.math.BigDecimal;

import estoque.model.Produto;
import estoque.repository.ProdutoRepository;

public class CadastroProdutoService {

	private ProdutoRepository repository;

	public CadastroProdutoService(ProdutoRepository repository) {
		this.repository = repository;
	}
	
	public void cadastrar(Produto produto) throws ServiceException{
		validarProduto(produto);
		this.repository.adicionar(produto);
	}
    
	public void atualiza(Long id, String nome, Integer quantidadeEstoque, BigDecimal precoCusto) throws ServiceException{

		this.repository.atualizar(id,nome,quantidadeEstoque,precoCusto);
	}
	
	private void validarProduto(Produto produto) throws ServiceException {
		if (produto.getNome() == null || produto.getNome().equals("")) {
			throw new ServiceException("Nome deve ser informado.");
		
		} else if (produto.getPrecoCusto() == null) {
			throw new ServiceException("Preço de custo deve ser informado.");
		
		} else if (produto.getQuantidadeEstoque() == null) {
			throw new ServiceException("Quantidade em estoque deve ser informada.");
		
		} else if (produto.getPrecoCusto().compareTo(BigDecimal.ZERO) < 0) {
			throw new ServiceException("Preço de custo deve ser maior que zero.");
		
		} else if (produto.getQuantidadeEstoque() < 0) {
			throw new ServiceException("Quantidade em estoque deve ser maior que zero.");
		}
	}
}
