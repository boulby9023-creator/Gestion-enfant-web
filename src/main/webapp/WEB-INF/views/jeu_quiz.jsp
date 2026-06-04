<%@page import="com.denkolochi.model.Option"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.denkolochi.model.Quiz" %>
<%@ page import="com.denkolochi.model.Question" %>
<%@ page import="java.util.List" %>
<%
    // Récupération des données de session
    Quiz quiz = (Quiz) session.getAttribute("quiz");
    Integer currentIndex = (Integer) session.getAttribute("currentIndex");
    Integer score = (Integer) session.getAttribute("score");
    Integer bonneReponses = (Integer) session.getAttribute("bonneReponses");
    List<Integer> reponsesUtilisateur = (List<Integer>) session.getAttribute("reponsesUtilisateur");

    if (currentIndex == null) currentIndex = 0;
    if (score == null) score = 0;
%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="jeu_quiz.css">
    <title><%= (quiz != null) ? quiz.getTitre() : "Page quiz" %></title>
    
    <script>
		window.addEventListener('load', function() {
		    const questionActuelle = document.getElementById('actuel');
		
		    if (questionActuelle) {
		        questionActuelle.scrollIntoView({
		            behavior: 'smooth',
		            block: 'center'
		        });
		    }
		});
</script>
</head>
<body>
    <div class="container">
        <header>
            <div class="retour">
                <a href="#">Quitter le quiz</a>
            </div>
            <div class="btn-gauche">
                <a href="#">Meilleur Score</a>
                <a href="${pageContext.request.contextPath}/QuizServlet?action=restart">Recommencer</a>
            </div>
        </header>

        <main>
            <% if (quiz != null && quiz.getQuestions() != null && !quiz.getQuestions().isEmpty()) { 
                Question questionActuelle = quiz.getQuestions().get(currentIndex);
                int userSelection = reponsesUtilisateur.get(currentIndex);
                int totalQuestions = quiz.getQuestions().size();
                int progressionPourcentage = ((currentIndex +1) * 100) / totalQuestions;
            %>
            <form action="${pageContext.request.contextPath}/QuizServlet" method="post" class="main-gauche">
                
                <div class="progression">
                    <h2>Progression : <%= quiz.getTitre() %> </h2>
                    <div class="progress">
                        <div class="progress-bar" style="width: <%= (currentIndex == totalQuestions - 1) ? "100" : progressionPourcentage %>%"></div>
                    </div>
                </div>

                <div class="question-reponse">
                    <p id="affiche-question">
                        Question : <%= (currentIndex + 1) %>/<%= totalQuestions %> : 
                        <strong><%= questionActuelle.getEnonce() %></strong>
                    </p>
                    <p>
                        <span>Délai imparti : <%= questionActuelle.getDelai_max() %>s</span> | 
                        
                    </p>
                    <p><strong>Veuillez choisir une réponse : </strong></p>
                    
                    <div class="propositions">
			                        <%
			List<Option> options = questionActuelle.getOptions();
			
			for (int i = 0; i < options.size(); i++) {
			    Option opt = options.get(i);
			%>
			
			<label class="reponse <%= (userSelection == i) ? "selectionnee" : "" %>">
			
			    <input type="radio"
			           name="reponse"
			           value="<%= i %>"
			           <%= (userSelection == i) ? "checked" : "" %>
			           
			           ">
			
			    <span><%= opt.getTexte() %></span>
			
			</label>
			
			<%
			}
			%>
                    </div>
                </div>

                <div class="bouton-bas">
                    <button type="submit" name="nav" value="precedent" class="precedent" <%= (currentIndex == 0) ? "disabled" : "" %>>Précédent</button>
                    <button onclick="window.scrollTo({top:10px, behavior:'smooth'})" type="submit" name="nav" value="suivant" class="suivant" <%= (currentIndex == totalQuestions - 1) ? "disabled" : ""  %>>Suivant</button>
                </div>

            </form>
            <% } else { %>
                <div class="main-gauche">
                    <p>Aucun quiz en cours. Veuillez cliquer sur Recommencer.</p>
                </div>
            <% } %>

            <div class="main-droite">
                <div class="score">
                    <h2><%= score %></h2>
                    <p>Votre score</p>
                </div>

                <div class="list-question">
                    <% 
                        if (quiz != null && quiz.getQuestions() != null) {
                            for (int i = 0; i < quiz.getQuestions().size(); i++) {
                                int rep = reponsesUtilisateur.get(i);
                                String idAttr = "id='no-answer'";
                                
                                if (i == currentIndex) {
                                    idAttr = "id='actuel'";
                                } else if (rep != -1 && rep == 1) { 
                                    idAttr = "id='vrai'";
                                } else if (rep != -1 && rep != 1) {
                                    idAttr = "id='faux'";
                                }
                    %>
                        <div class="question" <%= idAttr %>>Question <%= (i + 1) %></div>
                    <% 
                            }
                        } 
                    %>
                </div>
            </div>
        </main>
    </div>
</body>
</html>