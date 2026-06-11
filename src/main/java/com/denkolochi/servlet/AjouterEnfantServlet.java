package com.denkolochi.servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.denkolochi.dao.ImplEnfantDAO;
import com.denkolochi.dao.ImplcorporelleDao;
import com.denkolochi.model.Corporelle;
import com.denkolochi.model.Enfant;
import com.denkolochi.model.Utilisateur;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/ajouterEnfant")
public class AjouterEnfantServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    
    
    
    private final ImplEnfantDAO enfantDAO = new ImplEnfantDAO();
    
    
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	request.getRequestDispatcher("WEB-INF/views/ajouterEnfant.jsp").forward(request, response);
	}

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        // Vérification session
        if (session == null || session.getAttribute("utilisateurConnecte") == null) {
            response.sendRedirect("connexion");
            return;
        }

        Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateurConnecte");

        try {
            // Récupération des données du formulaire
        	
            String nom = request.getParameter("nom");
            String prenom = request.getParameter("prenom");
            String dateNaissanceStr = request.getParameter("date_naissance");
            String sexe = request.getParameter("sexe");
            String poidsStr = request.getParameter("poids");
            String tailleStr = request.getParameter("taille");

            LocalDate dateNaissance = LocalDate.parse(dateNaissanceStr, DateTimeFormatter.ISO_LOCAL_DATE);
            double poids = Double.parseDouble(poidsStr);
            double taille = Double.parseDouble(tailleStr);

            Enfant enfant = new Enfant();
            enfant.setNom(nom);
            enfant.setPrenom(prenom);
            enfant.setDate_naissance(dateNaissance);
            enfant.setSexe(sexe);
            enfant.setParent(utilisateur.getId());        

            boolean success =  enfantDAO.saveEnfant(enfant);
            
            if (success) {

                Corporelle corp = new Corporelle();
                corp.setId_enfant(enfant.getId());
                corp.setPoids(poids);
                corp.setTaille(taille);

                double tailleMetres = taille > 3 ? taille / 100.0 : taille;
                corp.setImc(poids / (tailleMetres * tailleMetres));

                ImplcorporelleDao corpDao = new ImplcorporelleDao();
                corpDao.save(corp);

                response.sendRedirect("liste-enfant");
                return;
            
            } else {
                request.setAttribute("errorMessage", "Erreur lors de l'ajout de l'enfant.");
                request.getRequestDispatcher("/WEB-INF/views/ajouterEnfant").forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Erreur de saisie. Veuillez vérifier les informations.");
            request.getRequestDispatcher("/WEB-INF/views/ajouterEnfant.jsp").forward(request, response);
        }
    }
}