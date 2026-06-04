package com.denkolochi.servlet;

import java.io.IOException;
import java.util.List;

import com.denkolochi.dao.ImplParentDAO;
import com.denkolochi.model.Parent;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ServletListeUtilisateur")
public class ServletListeUtilisateur extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//private ImplParentDAO parentDAO = new ImplParentDAO();
       
    public ServletListeUtilisateur() {
        super();
        // Constructeur propre et vide.
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1. On charge les données au moment du clic utilisateur
		//List<Parent> liste = parentDAO.findAll();
		
		// 2. On envoie l'attribut AVANT le forward. 
		// "parent" correspond exactement à ta boucle 'for(Parent pa : parent)'
		//request.setAttribute("parent", liste);
		
		// 3. Enfin, on affiche la page JSP
		request.getRequestDispatcher("/WEB-INF/views/Dashboard/listeUtilisateurs.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}