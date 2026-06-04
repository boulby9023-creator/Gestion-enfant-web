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
	<h1>Le Quiz n'existe pas !!!!</h1>
	<a href="${pageContext.request.contextPath}/QuizServlet?action=restart">Recommencer</a>
       
    </div>
</body>
</html>