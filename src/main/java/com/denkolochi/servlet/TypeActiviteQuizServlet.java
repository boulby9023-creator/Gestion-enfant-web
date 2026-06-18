package com.denkolochi.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import com.denkolochi.dao.ImplEnfantDAO;
import com.denkolochi.dao.QuizDao;
import com.denkolochi.model.Enfant;
import com.denkolochi.model.Quiz;
import com.denkolochi.model.Utilisateur;

/**
 * Servlet implementation class TypeActiviteQuizServlet
 */
@WebServlet("/Type-activite-quiz")
public class TypeActiviteQuizServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	 protected void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {

	        HttpSession session = request.getSession();

	        if (session == null || session.getAttribute("utilisateurConnecte") == null) {
	            response.sendRedirect(request.getContextPath() + "/connexion");
	            return;
	        }

	        String typeParam = request.getParameter("type");
	        String idEnfantParam = request.getParameter("id");

	        if (typeParam == null || idEnfantParam == null) {
	            response.sendRedirect(request.getContextPath() + "/enfant-activities");
	            return;
	        }

	        String typeCapacite = typeParam; // "Cognitif" ou "Intellectuel"
	        int idEnfant = Integer.parseInt(idEnfantParam);

	        ImplEnfantDAO enfantDao = new ImplEnfantDAO();
	        Enfant enfant = enfantDao.findById(idEnfant);

	        if (enfant == null) {
	            response.sendRedirect(request.getContextPath() + "/liste-enfant");
	            return;
	        }

	        Utilisateur utilisateurConnecte = (Utilisateur) session.getAttribute("utilisateurConnecte");
	        if (enfant.getid_parent() != utilisateurConnecte.getId()) {
	            response.sendRedirect(request.getContextPath() + "/dashboard");
	            return;
	        }
	     // Calcul de l'âge en mois et en années
	        int ageEnMois = 0;
	        int ageEnAnnees = 0;

	        if (enfant.getDate_naissance() != null) {
	            LocalDate dateNaissance = enfant.getDate_naissance();
	            LocalDate dateActuelle = LocalDate.now();
	            Period period = Period.between(dateNaissance, dateActuelle);
	            ageEnMois = period.getYears() * 12 + period.getMonths();
	            ageEnAnnees = period.getYears(); // Utilise directly les années complètes
	        }

	        System.out.println("========================================");
	        System.out.println("Âge de l'enfant: " + ageEnMois + " mois = " + ageEnAnnees + " ans");
	        System.out.println("========================================");

	        // Récupération des quiz filtrés
	        QuizDao quizDao = new QuizDao();
	        List<Quiz> quizzes = quizDao.findQuizzesByTypeEtAge(typeCapacite, ageEnAnnees);

	        // Affichage dans la console
	        if (quizzes != null && !quizzes.isEmpty()) {
	            System.out.println("✅ Quiz trouvés: " + quizzes.size());
	            for (Quiz quiz : quizzes) {
	                System.out.println("----------------------------------------");
	                System.out.println("ID: " + quiz.getIdQuiz() + " | Titre: " + quiz.getTitre());
	                System.out.println("Âge: " + quiz.getAgeMin() + " - " + quiz.getAgeMax() + " ans");
	                System.out.println("----------------------------------------");
	            }
	        } else {
	            System.out.println("❌ Aucun quiz trouvé pour type: " + typeCapacite + ", âge: " + ageEnAnnees + " ans");
	        }

	        request.setAttribute("quizzes", quizzes);
	        request.setAttribute("enfant", enfant);
	        request.setAttribute("type", typeCapacite);
	        request.setAttribute("ageEnMois", ageEnMois);
	        request.setAttribute("ageEnAnnees", ageEnAnnees);

	        

	        request.getRequestDispatcher("/WEB-INF/views/Type-activite-quiz.jsp")
	                .forward(request, response);
	    }

	    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        doGet(request, response);
	    }
}




