package estoque.form;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import estoque.model.Produto;
import estoque.service.ServiceException;

public class ProdutoForm {

	private static final DecimalFormat DECIMAL_FORMAT;
	
	static {
		
		DECIMAL_FORMAT = (DecimalFormat) NumberFormat.getInstance(new Locale("pt", "BR"));
		DECIMAL_FORMAT.applyPattern("#0.00");
	
	}

	private String id;
	private String nome;
	private String precoCusto;
	private String quantidadeEstoque;

	public static ProdutoForm fromRequest(HttpServletRequest request) {
		ProdutoForm form = new ProdutoForm();
		form.setId(request.getParameter("id"));
		form.setNome(request.getParameter("nome"));
		form.setPrecoCusto(request.getParameter("precoCusto"));
		form.setQuantidadeEstoque(request.getParameter("quantidadeEstoque"));
		return form;
	}

	public Produto toProduto() throws ServiceException {
		Produto produto = new Produto();
		produto.setNome(this.getNome());
		
		try {
			
			if (this.getPrecoCusto() != null && !this.getPrecoCusto().equals("")) {
				produto.setPrecoCusto(new BigDecimal(DECIMAL_FORMAT.parse(this.getPrecoCusto()).doubleValue()));
			}
		
		} catch (ParseException e) {
			throw new ServiceException("Informe o preço de custo corretamente.");
		}
		
		try {
			if (this.getQuantidadeEstoque() != null && !this.getQuantidadeEstoque().equals("")) {
				produto.setQuantidadeEstoque(Integer.parseInt(this.getQuantidadeEstoque()));
			}
		
		} catch (NumberFormatException e) {
			throw new ServiceException("Informe a quantidade em estoque corretamente.");
		}
		return produto;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPrecoCusto() {
		return precoCusto;
	}
	
	public void setPrecoCusto(String precoCusto) {
		this.precoCusto = precoCusto;
	}
	
	public String getQuantidadeEstoque() {
		return quantidadeEstoque;
	}
	
	public void setQuantidadeEstoque(String quantidadeEstoque) {
		this.quantidadeEstoque = quantidadeEstoque;
	}
	
}
