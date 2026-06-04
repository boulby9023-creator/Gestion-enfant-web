package com.denkolochi.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.sql.Date;

import com.denkolochi.enumeration.TypeActivitesEnum;
import com.denkolochi.model.Activite;
import com.denkolochi.model.Capacites;

/**
 * Servlet implementation class Ajout_Activite
 */
@WebServlet("/Ajout_Activite")
public class Ajout_Activite extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Ajout_Activite() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		request.getRequestDispatcher("WEB-INF/views/Ajout_Activite.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		String titre = request.getParameter("titre_activite");
		String description = request.getParameter("description");
		
		int agemin = Integer.parseInt(request.getParameter("agemin")) ;
		int agemax = Integer.parseInt(request.getParameter("agmax"));
		
		String type = request.getParameter("type_activite");
		String capacite = request.getParameter("capacite");
		
		Date date = Date.valueOf(request.getParameter("date_creation"));
		
		Part imagePart = request.getPart("image"); 
		
		String imageName =
	                imagePart.getSubmittedFileName();
		
		
		Activite activite = new Activite();
		Capacites capacites= new Capacites();
		
		activite.setTitre(titre);
		activite.setDescriptions(description);
		activite.setAgeMin(agemin);
		activite.setAgeMax(agemax);
		activite.setTypeActivites(type);
		activite.setDateCreation(date);
		
		capacites.setType_capacite(capacite);
		
		
		

		
	}

}
