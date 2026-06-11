package com.denkolochi.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.denkolochi.dao.ImplEnfantDAO;
import com.denkolochi.dao.ImplReponseEnfantDAO;
import com.denkolochi.model.Enfant;
import com.denkolochi.model.Utilisateur;

/**
 * Servlet implementation class DeconnexionServlet
 */
@WebServlet("/delete")
public class DeleteEnfantServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String idStr = request.getParameter("id");
    	int idEnfant = Integer.parseInt(idStr);


    
    	
    	ImplEnfantDAO enfantDao = new ImplEnfantDAO();
    	
           
          enfantDao.delete(idEnfant);
           
           
         response.sendRedirect("ListeEnfantServlet");
        	    
     

//           
//            request.getRequestDispatcher(
//	                "/WEB-INF/views/delete.jsp")
//	                .forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
