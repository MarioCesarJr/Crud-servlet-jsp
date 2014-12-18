package estoque.repository;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import estoque.model.Produto;

public class ProdutoRepository {

	private EntityManager manager;

	public ProdutoRepository(EntityManager manager) {
		this.manager = manager;
	}

	public void adicionar(Produto produto) {
		this.manager.persist(produto);
	}

	@SuppressWarnings("unchecked")
	public List<Produto> todos() {
		return manager.createQuery("from Produto").getResultList();
	}

	public List<Produto> porNomeNaoExato(String nome) {
		TypedQuery<Produto> query = manager.createQuery(
				"from Produto where upper(nome) like upper(:nome)",
				Produto.class);
		query.setParameter("nome", "%" + (nome == null ? "" : nome) + "%");
		return query.getResultList();
	}
	
	public void remove(Long id){
		Produto produto = manager.find(Produto.class, id);
		manager.remove(produto);
	}
	
	public Produto buscar(Long id){
		Produto produto = manager.find(Produto.class, id);
		return produto;
	}
	
	public void atualizar(Long id, String nome, Integer quantidadeEstoque, BigDecimal precoCusto){
		Produto produto = manager.find(Produto.class, id);
		produto.setNome(nome);
		produto.setQuantidadeEstoque(quantidadeEstoque);
		produto.setPrecoCusto(precoCusto);
	}
}
