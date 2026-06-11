package com.denkolochi.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.denkolochi.dao.ImplcorporelleDao;
import com.denkolochi.model.Corporelle;

/**
 * Servlet implementation class AjoutCoporelleServlet
 */
@WebServlet("/ajoutercorpolle")
public class AjoutCoporelleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 String idEnfant = request.getParameter("idEnfant");
		 
		 System.out.println("QueryString = " + request.getQueryString());
		 System.out.println("idEnfant = " + request.getParameter("idEnfant"));
		 
		 request.setAttribute("idEnfant", idEnfant);
		 
		request.getRequestDispatcher(
                "/WEB-INF/views/ajoutCorporelle.jsp")
                .forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);

        // Vérification session
        if (session == null || session.getAttribute("utilisateurConnecte") == null) {
            response.sendRedirect("connexion");
            return;
        }
        
        try {
        	String idEnfant = request.getParameter("idEnfant");
        	 String poidsStr = request.getParameter("poids");
             String tailleStr = request.getParameter("taille");
             
             
             double poids = Double.parseDouble(poidsStr);
             double taille = Double.parseDouble(tailleStr);
             
             Corporelle corp = new Corporelle();
             corp.setId_enfant(Integer.parseInt(idEnfant));
             corp.setPoids(poids);
             corp.setTaille(taille);

             double tailleMetres =  taille / 100.0;
             corp.setImc(poids / (tailleMetres * tailleMetres));

             ImplcorporelleDao corpDao = new ImplcorporelleDao();
             corpDao.save(corp);
             
             response.sendRedirect(request.getContextPath() + "/enfant-croissance?id=" + idEnfant );
        }
        catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Erreur de saisie. Veuillez vérifier les informations.");
            request.getRequestDispatcher("/WEB-INF/views/ajoutCorporelle.jsp").forward(request, response);
        }
	}

}
