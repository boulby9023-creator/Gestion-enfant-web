package com.denkolochi.servlet;

import java.io.IOException;
import java.util.List;

import com.denkolochi.dao.ImplUtilisateurDao;
import com.denkolochi.model.Utilisateur;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ServletListeUtilisateur")
public class ServletListeUtilisateur extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	ImplUtilisateurDao util = new ImplUtilisateurDao();
	Utilisateur us = new Utilisateur();
	
	//private ImplParentDAO parentDAO = new ImplParentDAO();
       
    public ServletListeUtilisateur() {
        super();
        // Constructeur propre et vide.
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Utilisateur> users = util.findAll();
		request.setAttribute("users", users);
		request.getRequestDispatcher("/WEB-INF/views/Dashboard/listeUtilisateurs.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}