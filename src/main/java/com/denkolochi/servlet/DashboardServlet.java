package com.denkolochi.servlet;

import java.io.IOException;

import com.denkolochi.dao.ImplHistoriqueDao;
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

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;


    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {
    	
    	HttpSession session = request.getSession();
    	
    	if (session == null || session.getAttribute("utilisateurConnecte") == null) {
            response.sendRedirect("connexion"); 
            return;
        }
    	
    	ImplParentDAO parentDao = new ImplParentDAO();
    	ImplReponseEnfantDAO reponseEnfantDao = new ImplReponseEnfantDAO();
    	
           Utilisateur utilisateurConnecte = (Utilisateur) session.getAttribute("utilisateurConnecte");
           
           Parent parent = parentDao.findById(utilisateurConnecte.getId()); 
           
           request.setAttribute("parent", parent);
           request.setAttribute("enfants", parent.getEnfants());
           request.setAttribute("nbEnfants", parent.getEnfants().size());
           request.setAttribute("nb_quiz_realises",reponseEnfantDao.getNombreQuizByParentId(utilisateurConnecte.getId()) );
          
  
    	
    	
  
         


        request.getRequestDispatcher(
                "/WEB-INF/views/dashboard.jsp")
                .forward(request, response);
    }
}


