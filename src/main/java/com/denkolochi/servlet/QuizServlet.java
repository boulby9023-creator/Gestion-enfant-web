package com.denkolochi.servlet;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

//package com.denkolochi.servlet;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

import com.denkolochi.model.Quiz;
import com.denkolochi.dao.ImplQuestionDAO;
import com.denkolochi.dao.QuizDao;
import com.denkolochi.model.Option;
import com.denkolochi.model.Question;

@WebServlet("/QuizServlet")
public class QuizServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        String action = request.getParameter("action");
        String idParam = request.getParameter("id_quiz");

        boolean restart = "restart".equals(action);

        Quiz quiz = (Quiz) session.getAttribute("quiz");

        if (idParam != null || quiz == null || restart) {

            int idQuiz = (idParam != null) ? Integer.parseInt(idParam) : 0;

            Quiz newQuiz = chargerQuizDepuisDonnees(idQuiz);

            if (newQuiz == null) {
                response.sendRedirect(request.getContextPath() + "/test.jsp");
                return;
            }

            session.setAttribute("quiz", newQuiz);
            session.setAttribute("currentIndex", 0);
            session.setAttribute("score", 0);
            session.setAttribute("bonneReponses", 0);

            List<Integer> reponses = new ArrayList<>();
            for (int i = 0; i < newQuiz.getQuestions().size(); i++) {
                reponses.add(-1);
            }

            session.setAttribute("reponsesUtilisateur", reponses);
        }

        request.getRequestDispatcher("/jeu_quiz.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Quiz quiz = (Quiz) session.getAttribute("quiz");
        int currentIndex = (int) session.getAttribute("currentIndex");
        int bonneReponses = (int) session.getAttribute("bonneReponses");
        List<Integer> reponses = (List<Integer>) session.getAttribute("reponsesUtilisateur");

        if (quiz == null) {
            response.sendRedirect(request.getContextPath() + "/QuizServlet");
            return;
        }

        String reponseChoisie = request.getParameter("reponse");
        if (reponseChoisie != null) {
            reponses.set(currentIndex, Integer.parseInt(reponseChoisie));
        }

        String action = request.getParameter("nav");
        if ("suivant".equals(action) && currentIndex < quiz.getQuestions().size() - 1) {
            currentIndex++;
        } else if ("precedent".equals(action) && currentIndex > 0) {
            currentIndex--;
        }

        int scoreActuel = 0;
        List<Question> listeQuestions = quiz.getQuestions();
        for (int i = 0; i < listeQuestions.size(); i++) {

            int reponseUtilisateur = reponses.get(i);

            System.out.println("Question " + i);
            System.out.println("Réponse utilisateur = " + reponseUtilisateur);

            if (reponseUtilisateur != -1) {

                Option optionChoisie =
                        listeQuestions.get(i)
                                      .getOptions()
                                      .get(reponseUtilisateur);

                System.out.println("Option choisie = "
                        + optionChoisie.getTexte());

                System.out.println("Correcte ? "
                        + optionChoisie.isEstCorrecte());

                if (optionChoisie.isEstCorrecte()) {

                    scoreActuel += listeQuestions.get(i).getScore();
                    bonneReponses ++;
                }
            }
        }
        System.out.println("Score actuel = " + scoreActuel);
        System.out.println("Score max = " + quiz.getScoreMax());

        int scorePourcentage =
                (quiz.getScoreMax() > 0)
                ? (scoreActuel * 100) / quiz.getScoreMax()
                : 0;

        System.out.println("Pourcentage = " + scorePourcentage);
        /*for (int i = 0; i < listeQuestions.size(); i++) {

            int reponseUtilisateur = reponses.get(i);

            if (reponseUtilisateur != -1) {

                if (listeQuestions.get(i)
                        .getOptions()
                        .get(reponseUtilisateur)
                        .isEstCorrecte()) {

                    scoreActuel += listeQuestions.get(i).getScore();
                }
            }
        }*/
        
       
         scorePourcentage = (quiz.getScoreMax() > 0) ? (scoreActuel * 100) / quiz.getScoreMax() : 0;
        //scorePourcentage = (quiz.getScoreMax() > 0) ? (bonneReponses * 100) / quiz.getScoreMax() : 0;

        
        session.setAttribute("currentIndex", currentIndex);
        session.setAttribute("bonneReponses", bonneReponses);
        session.setAttribute("reponsesUtilisateur", reponses);
        session.setAttribute("score", scorePourcentage);

        response.sendRedirect(request.getContextPath() + "/QuizServlet");
    }

    
    private Quiz chargerQuizDepuisDonnees(int idQuiz) {
        QuizDao quizDao = new QuizDao();
        ImplQuestionDAO questionDao = new ImplQuestionDAO();

        Quiz quiz = quizDao.findById(idQuiz);

        if (quiz == null) {
            return null;
        }

        List<Question> liste = questionDao.recuperationQuestionsByIdQuiz(idQuiz);
        quiz.setQuestions(liste);

        return quiz;
    }

}