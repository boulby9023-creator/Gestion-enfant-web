package com.denkolochi.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.denkolochi.dao.ImplUtilisateurDao;
import com.denkolochi.model.Utilisateur;

/**
 * Servlet implementation class LoginActionServlet
 */
@WebServlet("/loginaction")
public class LoginActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginActionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email=request.getParameter("mail");
		String password=request.getParameter("password");
		ImplUtilisateurDao implementation=new ImplUtilisateurDao();
		Utilisateur utilisateur=implementation.findByMail(email);
		if(email.equals(utilisateur.getMail() AND password.equals(utilisateur.getMotDePasse()) {}
		
		
	}

}
