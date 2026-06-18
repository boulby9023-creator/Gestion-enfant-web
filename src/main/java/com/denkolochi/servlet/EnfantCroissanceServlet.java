package com.denkolochi.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import com.denkolochi.dao.ImplEnfantDAO;
import com.denkolochi.dao.ImplReponseEnfantDAO;
import com.denkolochi.dao.ImplcorporelleDao;
import com.denkolochi.model.Corporelle;
import com.denkolochi.model.Enfant;
import com.denkolochi.model.Utilisateur;

/**
 * Servlet implementation class EnfantCroissanceServlet
 */
@WebServlet("/enfant-croissance")
public class EnfantCroissanceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	HttpSession session = request.getSession();
    	
    	String idStr = request.getParameter("id");
    	int idEnfant = Integer.parseInt(idStr);


    	

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

           ImplcorporelleDao corporelleDAO = new ImplcorporelleDao();
           System.out.println(idEnfant);
           List<Corporelle> corporelles = corporelleDAO.findAllByEnfant(idEnfant);
           
           

           if (corporelles != null) {
               System.out.println("✅ Corporelles récupérées : " + corporelles.size());

               
           } else {
               System.out.println("❌ Corporelles est null");
           }

           
           request.setAttribute("corporelles", corporelles);
           
           
           request.setAttribute("enfant", enfant);          
          
    	
		
		request.getRequestDispatcher(
                "/WEB-INF/views/enfant-croissance.jsp")
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
