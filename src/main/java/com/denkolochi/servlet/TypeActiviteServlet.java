package com.denkolochi.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.denkolochi.dao.ImplEnfantDAO;
import com.denkolochi.dao.ImplReponseEnfantDAO;
import com.denkolochi.model.Enfant;
import com.denkolochi.model.Utilisateur;

/**
 * Servlet implementation class TypeActiviteServlet
 */
@WebServlet("/Type-activite")
public class TypeActiviteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	HttpSession session = request.getSession();
    	
    	String idStr = request.getParameter("idEnfant");
    	String type = request.getParameter("type");
    	int idEnfant = Integer.parseInt(idStr);

    	 if (type == null || idStr == null) {
             response.sendRedirect("dashboard");
             return;
         }
    	

    	if (session == null || session.getAttribute("utilisateurConnecte") == null) {
            response.sendRedirect("connexion"); 
            return;
        }
    	
    	
	
    	ImplEnfantDAO enfantDao = new ImplEnfantDAO();
    	ImplReponseEnfantDAO reponseEnfantDao = new ImplReponseEnfantDAO();
    	
           Utilisateur utilisateurConnecte = (Utilisateur) session.getAttribute("utilisateurConnecte");
           
           Enfant enfant = enfantDao.findById(idEnfant);
           
           if (enfant.getid_parent() != utilisateurConnecte.getId()) {
        	    response.sendRedirect("dashboard");
        	    return;
        	}

           
           request.setAttribute("enfant", enfant); 
           request.setAttribute("type", type);
          
    	
           
		request.getRequestDispatcher(
                "/WEB-INF/views/Type-activite.jsp")
                .forward(request, response);
	}

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
