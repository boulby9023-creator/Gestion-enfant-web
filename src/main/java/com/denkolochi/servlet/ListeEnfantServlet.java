package com.denkolochi.servlet;

import java.io.IOException;

import com.denkolochi.dao.ImplParentDAO;
import com.denkolochi.dao.ImplReponseEnfantDAO;
import com.denkolochi.model.Parent;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/liste-enfant")
public class ListeEnfantServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {
    	
    	  ImplParentDAO parentDao = new ImplParentDAO();
    	  ImplReponseEnfantDAO reponseEnfantDao = new ImplReponseEnfantDAO();
          Parent parent = parentDao.getParentById(3); 
  
          request.setAttribute("parent", parent);
          request.setAttribute("enfants", parent.getEnfants());
          request.setAttribute("nbEnfants", parent.getEnfants().size());
          request.setAttribute("nb_quiz_realises",reponseEnfantDao.getNombreQuizByParentId(3) );

        request.getRequestDispatcher(
                "/WEB-INF/views/liste-enfant.jsp")
                .forward(request, response);
    }
}