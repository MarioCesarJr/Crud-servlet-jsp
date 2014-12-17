package agenda.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import agenda.model.Contato;
import agenda.util.JpaUtilThread;

public class ContatoDAO {

	EntityManager em;
	
	public ContatoDAO(){
		this.em = JpaUtilThread.getEntityManager();
		 
	}
	
	public void salvar(Contato contato){
	
		em.persist(contato);
	    
	}
	
	public void atualizar(Integer codigo, String nome, String email, String endereco, Date dataNascimento){
		Contato contato = em.find(Contato.class, codigo);
		contato.setNome(nome);
		contato.setEmail(email);
		contato.setEndereco(endereco);
		contato.setDataNascimento(dataNascimento);
		
	}
	
	public void remover(Integer codigo){
		Contato contato = em.find(Contato.class, codigo);
		em.remove(contato);
		
	}
	
	public Contato buscarContato(Integer codigo){
		Contato contato = em.find(Contato.class, codigo);
		if(contato != null){
          return  contato; 
		}
		
		return null;
	}
	
    @SuppressWarnings("unchecked")
    public List<Contato> listarTodos(){
	   return em.createQuery("FROM Contato").getResultList();
   }

}