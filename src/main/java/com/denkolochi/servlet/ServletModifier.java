package com.denkolochi.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.denkolochi.dao.ImplUtilisateurDao;
import com.denkolochi.enumeration.RoleEnum;
import com.denkolochi.model.Utilisateur;

/**
 * Servlet implementation class ServletModifier
 */
@WebServlet("/ServletModifier")

public class ServletModifier extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ImplUtilisateurDao util = new ImplUtilisateurDao();
	Utilisateur us = new Utilisateur();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletModifier() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("id")) ;
		us = util.findById(id);
		request.setAttribute("us", us);
		request.getRequestDispatcher("/WEB-INF/views/Dashboard/modifier.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		us.setId(Integer.parseInt(request.getParameter("id")));
		us.setNom(request.getParameter("nom"));
		us.setPrenom(request.getParameter("prenom"));
		us.setMail(request.getParameter("email"));
		us.setTel(request.getParameter("tel"));
		
		util.update(us.getId(), us);
		
		request.setAttribute("message", "Modif reussie !!!");
		List<Utilisateur> users = util.findAll();
		request.setAttribute("users", users);
		request.getRequestDispatcher("/WEB-INF/views/Dashboard/listeUtilisateurs.jsp").forward(request, response);
		
		
		
		
	}

}
