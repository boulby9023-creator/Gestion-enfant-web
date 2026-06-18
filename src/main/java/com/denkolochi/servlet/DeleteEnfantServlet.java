package com.denkolochi.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.denkolochi.dao.ImplEnfantDAO;
import com.denkolochi.model.Enfant;
import com.denkolochi.model.Utilisateur;

@WebServlet("/delete")
public class DeleteEnfantServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id");
        if (idStr == null) {
            response.sendRedirect(request.getContextPath() + "/liste-enfant");
            return;
        }

        int idEnfant = Integer.parseInt(idStr);

        ImplEnfantDAO enfantDao = new ImplEnfantDAO();
        enfantDao.delete(idEnfant);

        // Redirection vers la liste avec chemin absolu
        response.sendRedirect(request.getContextPath() + "/liste-enfant");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}