package com.denkolochi.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import com.denkolochi.dao.ImplUtilisateurDao;
import com.denkolochi.enumeration.RoleEnum;
import com.denkolochi.model.Utilisateur;

/**
 * Servlet implementation class ServletAjouterUtilisateurs
 */
@WebServlet("/ServletAjouterUtilisateurs")
public class ServletAjouterUtilisateurs extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	ImplUtilisateurDao util = new ImplUtilisateurDao();
	Utilisateur us = new Utilisateur();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletAjouterUtilisateurs() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/WEB-INF/views/Dashboard/ajouterUtilisateur.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String mail = request.getParameter("email");
		String tel = request.getParameter("tel");
		String mdp = request.getParameter("mdp");
		String role = request.getParameter("role");
		
		us.setNom(nom);
		us.setPrenom(prenom);
		us.setMail(mail);
		us.setTel(tel);
		us.setMotDePasse(mdp);
		us.setRole(RoleEnum.valueOf(role));
		util.save(us);
		
		
		
		//request.setAttribute("nom", nom);
		//request.setAttribute("prenom", prenom);
		//request.setAttribute("email", mail);
		//request.setAttribute("tel", tel);
		//request.setAttribute("mdp", mdp);
		//request.setAttribute("role", role);
		//util.save(us);
		
		response.sendRedirect(request.getContextPath() + "/ServletListeUtilisateur");
		
		
		
	}

}
