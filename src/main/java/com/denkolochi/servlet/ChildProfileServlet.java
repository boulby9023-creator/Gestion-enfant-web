package com.denkolochi.servlet;

import java.io.IOException;

import com.denkolochi.dao.ImplEnfantDAO;
import com.denkolochi.dao.ImplParentDAO;
import com.denkolochi.dao.ImplReponseEnfantDAO;
import com.denkolochi.model.Enfant;
import com.denkolochi.model.Parent;
import com.denkolochi.model.Utilisateur;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/child-profile")
public class ChildProfileServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {
    	
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

           
           request.setAttribute("enfant", enfant);          
          
    	

        request.getRequestDispatcher(
                "/WEB-INF/views/child-profile.jsp")
                .forward(request, response);
    }
}