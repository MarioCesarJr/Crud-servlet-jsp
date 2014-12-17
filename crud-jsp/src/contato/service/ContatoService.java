package contato.service;

import java.util.Date;

import agenda.dao.ContatoDAO;
import agenda.model.Contato;

public class ContatoService {


	public void salvar(Contato contato) throws ServiceException {
		ContatoDAO contatoDAO = new ContatoDAO();
		
		validarContato(contato);
		
		contatoDAO.salvar(contato);
	}
	
	public void atualizar(Integer codigo, String nome, String email, String endereco, Date dataNascimento) throws ServiceException {
		ContatoDAO contatoDAO = new ContatoDAO();
		
		contatoDAO.atualizar(codigo,nome,email,endereco,dataNascimento);
	}

	private void validarContato(Contato contato) throws ServiceException {
		if (contato.getNome() == null || contato.getNome().equals("")) {
			throw new ServiceException("Nome obrigatório");

		} else if (contato.getEmail() == null || contato.getEmail().equals("")) {
			throw new ServiceException("Email obrigatório");

		} else if (contato.getEndereco() == null|| contato.getEndereco().equals("")) {
			throw new ServiceException("Endereço obrigatório");
		
		} else if (contato.getDataNascimento() == null || contato.getDataNascimento().equals("")){
			throw new ServiceException("Data de nascimento obrigatório");
		}
	}

}