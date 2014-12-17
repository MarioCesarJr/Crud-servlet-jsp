package agenda.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import contato.service.ContatoService;
import contato.service.ServiceException;
import agenda.dao.ContatoDAO;
import agenda.model.Contato;


/**
 * Servlet implementation class AdicionaContatoServlet
 */

/**
 * 
 * @author Mário junior
 *
 */

@WebServlet("/app/adicionaContato")
public class AdicionaContatoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String INSERIR_OU_EDITAR = "/app/salvarContato.jsp";
    private static String LISTAR_CONTATOS = "/app/listar_contatos.jsp";
           

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
		String id = request.getParameter("idContato");
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String endereco = request.getParameter("endereco");
		String dataEmTexto = request.getParameter("dataNascimento");
		
		   String mensagem = null;   
		
		   Date date = null;
			
		   try {
				date = new SimpleDateFormat("dd/MM/yyyy").parse(dataEmTexto);
			} catch (ParseException e) {
				e.printStackTrace();
			}
	    
	
		Contato contato = new Contato();
		
		contato.setNome(nome);
		contato.setEmail(email);
		contato.setEndereco(endereco);
		contato.setDataNascimento(date);

		ContatoService contatoDAO = new ContatoService();
		
		if(id == null || id.isEmpty()){
			
			try {
				contatoDAO.salvar(contato);
				mensagem = "Contato cadastrado com sucesso!";
			} catch (ServiceException e) {
		        mensagem = e.getMessage();
			}
            		      
		}else{
			
		    try {
		    	
		    	Integer codigo = Integer.parseInt(id);
		    	
				contatoDAO.atualizar(codigo,nome,email,endereco,date);
				
				mensagem = "alteração realizada com sucesso";
			} catch (ServiceException e) {
				mensagem = e.getMessage();
			}
		}	
		
		request.setAttribute("mensagem", mensagem);
		
		RequestDispatcher view = request.getRequestDispatcher(INSERIR_OU_EDITAR);
        view.forward(request, response);
		
	}
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    	String visualizar="";
        String action = request.getParameter("action");

        ContatoDAO dao = new ContatoDAO();
        
        if (action.equalsIgnoreCase("deletar")){
            int Id = Integer.parseInt(request.getParameter("Id"));
            dao.remover(Id);
            visualizar = LISTAR_CONTATOS;
            request.setAttribute("listar", dao.listarTodos());    
        
        } else if (action.equalsIgnoreCase("editar")){
            visualizar = INSERIR_OU_EDITAR;
            int ContatoId = Integer.parseInt(request.getParameter("Id"));
            Contato contato = dao.buscarContato(ContatoId);
            request.setAttribute("contato", contato);
        
        } else if (action.equalsIgnoreCase("listar_contatos")){
            visualizar = LISTAR_CONTATOS;
            request.setAttribute("listar", dao.listarTodos());
        
        } else {
            visualizar = INSERIR_OU_EDITAR;
        }

        RequestDispatcher view = request.getRequestDispatcher(visualizar);
        view.forward(request, response);
    }      

}


