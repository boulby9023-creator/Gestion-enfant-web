package com.denkolochi.servlet;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

import com.denkolochi.dao.ImplEnfantDAO;
import com.denkolochi.dao.ImplParentDAO;
import com.denkolochi.dao.ImplReponseEnfantDAO;
import com.denkolochi.dao.ImplcorporelleDao;
import com.denkolochi.model.Corporelle;
import com.denkolochi.model.Enfant;
import com.denkolochi.model.Parent;
import com.denkolochi.model.Utilisateur;
import com.denkolochi.util.CalculDeveloppementCorporelleEnfant;

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
    	ImplcorporelleDao corporelleDAO = new ImplcorporelleDao();
    	
           Utilisateur utilisateurConnecte = (Utilisateur) session.getAttribute("utilisateurConnecte");
           
           Enfant enfant = enfantDao.findById(idEnfant);
           
           if (enfant.getid_parent() != utilisateurConnecte.getId()) {
        	    response.sendRedirect("dashboard");
        	    return;
        	}
           
//        // ✅ Affichage des données de l'enfant dans la console pour vérification
//           System.out.println("=== VERIFICATION ENFANT ===");
//           System.out.println("ID Enfant: " + enfant.getId());
//           System.out.println("Nom: " + enfant.getNom());
//           System.out.println("Prénom: " + enfant.getPrenom());
//           System.out.println("Date de naissance: " + enfant.getDate_naissance());
//           System.out.println("Sexe: " + enfant.getSexe());
//           System.out.println("ID Parent: " + enfant.getid_parent());
//           System.out.println("ID Parent (Connecté): " + utilisateurConnecte.getId());
//           System.out.println("Sécurité OK: " + (enfant.getid_parent() == utilisateurConnecte.getId()));
//           System.out.println("age moi " + enfant.getAgeEnMois());
//
//           
//           System.out.println("=== FIN VERIFICATION ===");
           
           
           List<Corporelle> mesure = corporelleDAO.getMesuresByEnfantId(enfant.getId()); 

           double score = CalculDeveloppementCorporelleEnfant.calculerScoreDevelopment(mesure, enfant.getAgeEnMois());
           String appreciation = CalculDeveloppementCorporelleEnfant.interpreterScore(score);
           String categorie = CalculDeveloppementCorporelleEnfant.getCategorie(score);
           
          


           DecimalFormat d= new DecimalFormat("#");

           request.setAttribute("enfant", enfant);   
           request.setAttribute("idEnfant", idEnfant);  
           request.setAttribute("score", d.format(score));  
           request.setAttribute("appreciation", appreciation);  
           request.setAttribute("categorie", categorie);  
          
    	

        request.getRequestDispatcher(
                "/WEB-INF/views/child-profile.jsp")
                .forward(request, response);
    }
}