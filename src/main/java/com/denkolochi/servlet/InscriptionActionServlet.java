package com.denkolochi.servlet;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.denkolochi.dao.ImplUtilisateurDao;
import com.denkolochi.model.Utilisateur;
import com.denkolochi.enumeration.RoleEnum;

@WebServlet("/inscriptionaction")
public class InscriptionActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ImplUtilisateurDao utilisateurDao = new ImplUtilisateurDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
                Utilisateur user = new Utilisateur();
        user.setNom(request.getParameter("nom"));
        user.setPrenom(request.getParameter("prenom"));
        user.setTel(request.getParameter("tel"));
        user.setMail(request.getParameter("mail"));
        user.setMotDePasse(request.getParameter("password"));
        user.setRole(RoleEnum.valueOf(request.getParameter("role")));
        utilisateurDao.save(user);
        response.sendRedirect("login.jsp");
    }
}