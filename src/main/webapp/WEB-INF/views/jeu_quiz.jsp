<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.denkolochi.model.Quiz" %>
<%@ page import="com.denkolochi.model.Question" %>
<%@ page import="com.denkolochi.model.Option" %>
<%@ page import="java.util.List" %>

<%
    Quiz quiz = (Quiz) session.getAttribute("quiz");
    Integer currentIndex = (Integer) session.getAttribute("currentIndex");
    Integer score = (Integer) session.getAttribute("score");
    List<Integer> reponsesUtilisateur = (List<Integer>) session.getAttribute("reponsesUtilisateur");

    if (currentIndex == null) currentIndex = 0;
    if (score == null) score = 0;

    if (quiz == null || quiz.getQuestions() == null) {
        response.sendRedirect(request.getContextPath() + "/parent/dashboard.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jeu_quiz.css">
    <title><%= quiz.getTitre() %></title>
    
    <style>
        .question-list .question { 
            cursor: pointer; 
            margin: 8px 0; 
            padding: 12px; 
            border-radius: 8px;
            background: #f3f4f6;
        }
        .question-list .current { background: #4f46e5; color: white; }
        .question-list .answered { background: #10b981; color: white; }
        .btn-terminer { 
            background: #dc2626; 
            color: white; 
            font-weight: bold; 
            padding: 12px 24px; 
        }
    </style>
</head>
<body>
    <div class="container">
        <header>
            <div class="retour">
                <a href="${pageContext.request.contextPath}/parent/dashboard.jsp">← Quitter le quiz</a>
            </div>
            <div>
                <a href="${pageContext.request.contextPath}/QuizServlet?action=restart&id_quiz=<%= quiz.getIdQuiz() %>" 
                   style="text-decoration: none; padding: 8px 16px; background: #eab308; color: black; border-radius: 6px;">
                    Recommencer
                </a>
            </div>
        </header>

        <main>
            <%
                Question questionActuelle = quiz.getQuestions().get(currentIndex);
                int totalQuestions = quiz.getQuestions().size();
                int progression = ((currentIndex + 1) * 100) / totalQuestions;
            %>
            
            <form action="${pageContext.request.contextPath}/QuizServlet" method="post" class="main-gauche">
                
                <div class="progression">
                    <h2><%= quiz.getTitre() %> — Question <%= currentIndex + 1 %> / <%= totalQuestions %></h2>
                    <div class="progress">
                        <div class="progress-bar" style="width: <%= progression %>%"></div>
                    </div>
                </div>

                <div class="question-reponse">
                    <p class="enonce">
                        <strong><%= questionActuelle.getEnonce() %></strong>
                    </p>
                    
                    <p class="delai">
                        Délai imparti : <strong><%= questionActuelle.getDelai_max() %></strong> secondes
                    </p>

                    <p><strong>Choisissez une réponse :</strong></p>
                    
                    <div class="propositions">
                        <%
                        List<Option> options = questionActuelle.getOptions();
                        int userSelection = (reponsesUtilisateur != null && reponsesUtilisateur.size() > currentIndex) 
                                            ? reponsesUtilisateur.get(currentIndex) : -1;
                        
                        for (int i = 0; i < options.size(); i++) {
                            Option opt = options.get(i);
                        %>
                            <label class="reponse <%= (userSelection == i) ? "selectionnee" : "" %>">
                                <input type="radio" 
                                       name="reponse" 
                                       value="<%= i %>" 
                                       <%= (userSelection == i) ? "checked" : "" %> >
                                <span><%= opt.getTexte() %></span>
                            </label>
                        <% } %>
                    </div>
                </div>

                <div class="bouton-bas">
                    <button type="submit" name="nav" value="precedent" 
                            class="precedent" <%= (currentIndex == 0) ? "disabled" : "" %>>
                        Précédent
                    </button>
                    
                    <% if (currentIndex < totalQuestions - 1) { %>
                        <button type="submit" name="nav" value="suivant" class="suivant">
                            Suivant
                        </button>
                    <% } else { %>
                        <button type="submit" name="nav" value="terminer" class="btn-terminer">
                            Terminer le Quiz
                        </button>
                    <% } %>
                </div>
            </form>

            <!-- Panneau droit -->
            <div class="main-droite">
                <div class="score">
                    <h2><%= score %></h2>
                    <p>Score actuel</p>
                </div>

                <div class="list-question">
                    <h3>Progression des questions</h3>
                    <div class="question-list">
                        <% for (int i = 0; i < totalQuestions; i++) { 
                            String cssClass = "";
                            if (i == currentIndex) {
                                cssClass = "current";
                            } else if (reponsesUtilisateur.get(i) != -1) {
                                cssClass = "answered";
                            }
                        %>
                            <div class="question <%= cssClass %>">
                                Question <%= i + 1 %>
                            </div>
                        <% } %>
                    </div>
                </div>
            </div>
        </main>
    </div>
</body>
</html>