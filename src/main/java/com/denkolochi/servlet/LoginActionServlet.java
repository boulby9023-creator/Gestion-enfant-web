package com.denkolochi.servlet;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.denkolochi.dao.ImplUtilisateurDao;
import com.denkolochi.model.Utilisateur;

@WebServlet("/loginaction")
public class LoginActionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ImplUtilisateurDao utilisateurDao = new ImplUtilisateurDao();
    
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("WEB-INF/views/connexion.jsp").forward(request, response);
	}

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String mail = request.getParameter("mail");
        String password = request.getParameter("password");
        
        if (utilisateurDao.seConnecter(mail, password)) {
            Utilisateur user = utilisateurDao.findByMail(mail);
            HttpSession session = request.getSession();
            session.setAttribute("utilisateurConnecte", user);
            response.sendRedirect("dashboard"); 
        } else {
            request.setAttribute("erreur", "Email ou mot de passe incorrect.");
            request.getRequestDispatcher("/WEB-INF/views/connexion.jsp").forward(request, response);
        }
    }
}