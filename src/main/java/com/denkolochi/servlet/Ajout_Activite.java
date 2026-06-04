package com.denkolochi.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;

import com.denkolochi.dao.ImplActiviteDAO;
import com.denkolochi.model.Activite;

/**
 * Servlet implementation class Ajout_Activite
 */
@WebServlet("/Ajout_Activite")
@MultipartConfig

public class Ajout_Activite extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static int nombre ;

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
		doGet(request,response);
		String titre = request.getParameter("titre_Activite");
		String description = request.getParameter("description");
		
		int agemin = Integer.parseInt(request.getParameter("agemin")) ;
		int agemax = Integer.parseInt(request.getParameter("agemax"));
		
		String type = request.getParameter("type_activite");
		String capacite = request.getParameter("capacite");
		
		
		if(capacite.equals("Memoire")){
			 nombre = 8 ;
			
		}
		else if(capacite.equals("Attention")) {
			nombre = 9 ;
		}
		else if(capacite.equals("Logique")) {
		    nombre = 10 ;
		}
		else if(capacite.equals("Resolution")) {
			 nombre = 11 ;
		}
		else if(capacite.equals("Concentration")) {
			 nombre = 12 ;
		}
		else if(capacite.equals("Emotionnelle")){
			 nombre = 13 ;
		}
		
		
		
		Date date = Date.valueOf(request.getParameter("date_creation"));
		
//		Part imagePart = request.getPart("image"); 
//		
//		String imageName =
//	                imagePart.getSubmittedFileName();
//		
		
		Activite activite = new Activite();
		
		activite.setTitre(titre);
		activite.setDescriptions(description);
		activite.setAgeMin(agemin);
		activite.setAgeMax(agemax);
		activite.setTypeActivites(type);
		activite.setDateCreation(date);
		activite.setIdCapacite(nombre);
		
		
		
		
		ImplActiviteDAO actidao = new ImplActiviteDAO();
		try {
			//System.out.println("les valeur sont "+activite.getTitre()+" "+activite.getDescriptions()+" "+activite.getAgeMin()+" "+activite.getAgeMax()+" "+activite.getTypeActivites()+" "+activite.getDateCreation());
		   actidao.save(activite);

		    request.setAttribute(
		        "message",
		        "Activité enregistrée avec succès"
		    );

		} catch (Exception e) {

		    request.setAttribute(
		        "message",
		        "Erreur lors de l'enregistrement"
		    );

		    e.printStackTrace();
		    
		    request.getRequestDispatcher(
		            "/WEB-INF/views/Ajout_Activite.jsp")
		            .forward(request, response);
		}
		
		
		
		
		

		
	}

}