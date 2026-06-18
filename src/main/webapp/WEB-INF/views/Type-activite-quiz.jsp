<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.util.List" %>
<%@ page import="com.denkolochi.model.Quiz" %>
    <%
        List<com.denkolochi.model.Quiz> quizzes = (List<com.denkolochi.model.Quiz>) request.getAttribute("quizzes");
        if (quizzes != null && !quizzes.isEmpty()) {
    %>


<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Quiz - DenKɔlɔsi</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/child-profile.css">
</head>
<body>

<div class="container-fluid">
<div class="row">

<!-- SIDEBAR -->
<aside class="col-lg-3 col-xl-2 sidebar p-0">
    <div class="p-4">
        <div class="logo text-center mb-5">
            <a class="navbar-brand custom-brand" href="#">
                <img src="${pageContext.request.contextPath}/assets/brand/Group 39.svg" width="60">
            </a>
            <a class="navbar-brand custom-brand" href="#">
                <img src="${pageContext.request.contextPath}/assets/brand/logoName.svg" width="120">
            </a>
        </div>
        <ul class="nav flex-column">
            <li class="nav-item mb-3"><a href="${pageContext.request.contextPath}/dashboard" class="nav-link">
                <i class="bi bi-grid"></i> Dashboard
            </a></li>
            <li class="nav-item mb-3"><a href="#" class="nav-link active">
                <i class="bi bi-people"></i> Mes enfants
            </a></li>
            <li class="nav-item mb-3"><a href="#" class="nav-link">
                <i class="bi bi-gear"></i> Paramètres
            </a></li>
            <li class="nav-item mt-5"><a href="${pageContext.request.contextPath}/deconnexion" class="nav-link text-danger">
                <i class="bi bi-box-arrow-right"></i> Déconnexion
            </a></li>
        </ul>
    </div>
</aside>

<!-- MAIN CONTENT -->
<main class="col-lg-9 col-xl-10 p-4">

<!-- HEADER -->
<div class="d-flex justify-content-between align-items-center mb-4">
    <div>
        <a href="${pageContext.request.contextPath}/enfant-activities?id=${enfant.id}" class="btn btn-success">
            <i class="bi bi-arrow-left me-2"></i> Retour aux activités
        </a>
    </div>
</div>

<!-- ENFANT -->
<div class="child-header">
    <div class="d-flex align-items-center gap-4">
        <img src="${pageContext.request.contextPath}/assets/images/image10.png" class="child-avatar">
        <div>
            <h3>${enfant.prenom} ${enfant.nom}</h3>
            <p class="text-muted mb-1">${enfant.date_naissance} • ${enfant.sexe} • Âge: ${ageEnAnnees} ans (${ageEnMois} mois)</p>
        </div>
    </div>
</div>

<!-- MENU INTERNE -->
<div class="profile-menu">
    <a href="${pageContext.request.contextPath}/child-profile?id=${enfant.id}"> Vue d'ensemble </a>
    <a href="${pageContext.request.contextPath}/enfant-activities?id=${enfant.id}" class="active"> Activités </a>
    <a href="${pageContext.request.contextPath}/enfant-croissance?id=${enfant.id}"> Croissance </a>
    <a href="${pageContext.request.contextPath}/enfant-progress?id=${enfant.id}"> Suivi & progrès </a>
    <a href="${pageContext.request.contextPath}/enfant-recommandations?id=${enfant.id}"> Recommandations </a>
</div>

<!-- LISTE DES QUIZ -->
<div class="mt-5">
    <h4 class="fw-bold mb-3">Quiz disponibles - Développement ${type}</h4>


    <div class="row g-4">
        <%
            for (com.denkolochi.model.Quiz quiz : quizzes) {
        %>
        <div class="col-md-6 col-lg-4">
            <div class="card shadow-sm border-0 h-100 quiz-card">
                <div class="card-body d-flex flex-column">
                    <h5 class="card-title fw-bold text-warning"><%=  quiz.getTitre() %></h5>
                    <p class="card-text text-muted mb-3">
                        <i class="bi bi-people"></i> Âge: <%= quiz.getAgeMin() %> - <%= quiz.getAgeMax() %> ans
                        <br>
                        <i class="bi bi-clock"></i> <%= quiz.getTempsLimitGlobal() %> min
                        <br>
                        <i class="bi bi-trophy"></i> Score max: <%= quiz.getScoreMax() %>
                    </p>
                    <p class="card-text text-dark small">
                        <%= quiz.getDescriptions() %>
                    </p>
                    <a href="${pageContext.request.contextPath}/QuizServlet?id_quiz=<%= quiz.getIdQuiz() %>"
                       class="btn btn-success mt-auto">
                        <i class="bi bi-play-fill"></i> Commencer le quiz
                    </a>
                </div>
            </div>
        </div>
        <%
            }
        %>
    </div>

    <%
        } else {
    %>

    <div class="section-card mt-4 d-flex justify-content-center align-items-center text-center">
        <i class="bi bi-emoji-frown fs-1 text-muted"></i>
        <p class="fs-4 text-muted mt-3">
            Aucun quiz disponible pour ce type de développement et l'âge de votre enfant.
        </p>
    </div>

    <%
        }
    %>
</div>

</main>
</div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>