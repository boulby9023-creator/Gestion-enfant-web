<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@page import="java.util.List"%>
<%@page import="com.denkolochi.model.Quiz"%>

<%
List<Quiz> quizzes = (List<Quiz>) request.getAttribute("quizzes");
%>

<!DOCTYPE html>
<html lang="fr">
<head>
<meta charset="UTF-8">
<title>Quiz</title>

<link rel="stylesheet" href="../../assets/css/ListQuiz.css">
</head>
<body>

	<div class="quiz-container">

		<%
		if (quizzes != null) {
			for (Quiz quiz : quizzes) {
		%>

		<div class="quiz-card">
			<h2><%=quiz.getTitre()%></h2>

			<div class="info">
				<span>Temps : <%=quiz.getTempsLimitGlobal()%> min 
				</span> <span>Score : <%=quiz.getScoreMax()%></span>
			</div>

			<button>Commencer</button>
		</div>

		<button class="btn-retour"> Retour</button>

		<%
		}
		} else {
		%>

		<h2>Aucun quiz trouvé</h2>

		<%
		}
		%>
	
</body>
</html>