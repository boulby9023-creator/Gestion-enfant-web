package com.denkolochi.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.denkolochi.dao.ImplQuestionDAO;
import com.denkolochi.dao.QuizDao;
import com.denkolochi.model.Option;
import com.denkolochi.model.Question;
import com.denkolochi.model.Quiz;

/**
 * Servlet implementation class ServletQuiz
 */
@WebServlet("/ServletQuiz")
public class ServletQuiz extends HttpServlet {
	 private static final long serialVersionUID = 1L;

	    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {

	        HttpSession session = request.getSession();
	        String action = request.getParameter("action");
	        String idQuizParam = request.getParameter("id_quiz");

	        if ("restart".equals(action) || session.getAttribute("quiz") == null) {
	            if (idQuizParam == null) {
	                response.sendRedirect(request.getContextPath() + "/Type-activite-quiz");
	                return;
	            }

	            int idQuiz = Integer.parseInt(idQuizParam);
	            Quiz quiz = chargerQuizComplet(idQuiz);

	            if (quiz == null) {
	                response.sendRedirect(request.getContextPath() + "/Type-activite-quiz");
	                return;
	            }

	            session.setAttribute("quiz", quiz);
	            session.setAttribute("currentIndex", 0);
	            session.setAttribute("score", 0);
	            session.setAttribute("reponsesUtilisateur", initialiserReponses(quiz.getQuestions().size()));
	        }

	        request.getRequestDispatcher("/WEB-INF/views/jeu_quiz.jsp").forward(request, response);
	    }

	    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {

	        HttpSession session = request.getSession();
	        Quiz quiz = (Quiz) session.getAttribute("quiz");
	        Integer currentIndex = (Integer) session.getAttribute("currentIndex");
	        List<Integer> reponsesUtilisateur = (List<Integer>) session.getAttribute("reponsesUtilisateur");

	        if (quiz == null || currentIndex == null) {
	            response.sendRedirect(request.getContextPath() + "/QuizServlet");
	            return;
	        }

	        // 1. Enregistrer la réponse si présente
	        String reponseStr = request.getParameter("reponse");
	        if (reponseStr != null) {
	            int choix = Integer.parseInt(reponseStr);
	            reponsesUtilisateur.set(currentIndex, choix);
	        }

	        // 2. Navigation
	        String nav = request.getParameter("nav");
	        int total = quiz.getQuestions().size();

	        if ("suivant".equals(nav) && currentIndex < total - 1) {
	            currentIndex++;
	        } else if ("precedent".equals(nav) && currentIndex > 0) {
	            currentIndex--;
	        } else if ("terminer".equals(nav)) {
	            int scoreFinal = calculerScoreFinal(quiz, reponsesUtilisateur);
	            session.setAttribute("score", scoreFinal);
	            session.setAttribute("quizTermine", true);
	            response.sendRedirect(request.getContextPath() + "/ResultatQuizServlet");
	            return;
	        }

	        session.setAttribute("currentIndex", currentIndex);
	        session.setAttribute("reponsesUtilisateur", reponsesUtilisateur);

	        response.sendRedirect(request.getContextPath() + "/QuizServlet");
	    }

	    private int calculerScoreFinal(Quiz quiz, List<Integer> reponses) {
	        int score = 0;
	        List<Question> questions = quiz.getQuestions();

	        for (int i = 0; i < questions.size(); i++) {
	            int indexReponse = reponses.get(i);
	            if (indexReponse != -1) {
	                Option option = questions.get(i).getOptions().get(indexReponse);
	                if (option.isEstCorrecte()) {
	                    score += 10;
	                }
	            }
	        }
	        return score;
	    }

	    private List<Integer> initialiserReponses(int size) {
	        List<Integer> list = new ArrayList()<>();
	        for (int i = 0; i < size; i++) list.add(-1);
	        return list;
	    }

	    private Quiz chargerQuizComplet(int idQuiz) {
	        QuizDao quizDao = new QuizDao();
	        ImplQuestionDAO questionDao = new ImplQuestionDAO();

	        Quiz quiz = quizDao.findById(idQuiz);
	        if (quiz != null) {
	            quiz.setQuestions(questionDao.recuperationQuestionsByIdQuiz(idQuiz));
	        }
	        return quiz;
	    }
}
