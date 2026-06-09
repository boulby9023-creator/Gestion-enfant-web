package com.denkolochi.servlet;

import java.awt.SystemColor;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.denkolochi.dao.ImplParentDAO;
import com.denkolochi.dao.ImplUtilisateurDao;
import com.denkolochi.model.Parent;
import com.denkolochi.model.Utilisateur;
import com.denkolochi.enumeration.RoleEnum;

@WebServlet("/inscription")
public class InscriptionActionServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private ImplUtilisateurDao utilisateurDao;
	private ImplParentDAO parentDAO;

	public InscriptionActionServlet() {
		utilisateurDao = new ImplUtilisateurDao();
		parentDAO = new ImplParentDAO();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	request.getRequestDispatcher("WEB-INF/views/inscription.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Utilisateur user = new Utilisateur();

		user.setNom(request.getParameter("nom"));
		user.setPrenom(request.getParameter("prenom"));
		user.setTel(request.getParameter("tel"));
		user.setMail(request.getParameter("mail"));
		user.setMotDePasse(request.getParameter("password"));

		String passwordConfirmation = request.getParameter("passwordConfirmation");

		// Vérification mot de passe
		if (!user.getMotDePasse().equals(passwordConfirmation)) {

			request.setAttribute("ErreurPassword", "Les mots de passe ne correspondent pas.");

			request.getRequestDispatcher("/WEB-INF/views/inscription.jsp").forward(request, response);

			return;
		}

		// Vérification email
		if (utilisateurDao.emailExiste(user.getMail())) {

			request.setAttribute("ErreurMail", "Cet email est déjà utilisé.");

			request.getRequestDispatcher("/WEB-INF/views/inscription.jsp").forward(request, response);

			return;
		}

		// Vérification téléphone
		if (utilisateurDao.telephoneExiste(user.getTel())) {

			request.setAttribute("ErreurTel", "Ce numéro de téléphone est déjà utilisé.");

			request.getRequestDispatcher("/WEB-INF/views/inscription.jsp").forward(request, response);

			return;
		}

		// Enregistrement utilisateur
		user.setRole(RoleEnum.valueOf(request.getParameter("role")));

		utilisateurDao.save(user);

		Utilisateur utilisateur = utilisateurDao.findByMail(user.getMail());

		// Enregistrement parent
		Parent parent = new Parent();

		parent.setGenre(request.getParameter("genre"));
		parent.setId_parent(utilisateur.getId());

		parentDAO.save(parent);

		response.sendRedirect("connexion");
	}
}