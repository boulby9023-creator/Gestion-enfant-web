<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<% List<com.denkolochi.model.Enfant> enfants = (List<com.denkolochi.model.Enfant>) request.getAttribute("enfants"); %>

<!DOCTYPE html>

<html lang="fr">

<head>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="shortcut icon" href="assets/images/iconedenkolochi.png"
	type="image/x-icon">
<title>DenKɔlɔsi - Dashboard Parent</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">

<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/dashboard.css">

</head>

<body>

<div class="wrapper">

```
<!-- ================= SIDEBAR ================= -->

<aside class="sidebar">

    <div class="logo text-center mb-4">

       <div class="logo">
				<a class="navbar-brand custom-brand" href="#"> <img
					src="${pageContext.request.contextPath}/assets/brand/Group 39.svg"
					alt="" width="60" height="50">
				</a> <a class="navbar-brand custom-brand" href="#"> <img
					src="${pageContext.request.contextPath}/assets/brand/logoName.svg"
					alt="" width="90" height="60">
				</a>
			</div>

    </div>

    <ul class="nav flex-column sidebar-menu">

        <li class="nav-item">

            <a class="nav-link active" href="#">

                <i class="bi bi-grid-1x2-fill"></i>

                Dashboard

            </a>

        </li>

        <li class="nav-item">

            <a class="nav-link"
               href="${pageContext.request.contextPath}/liste-enfant">

                <i class="bi bi-people-fill"></i>

                Mes enfants

            </a>

        </li>

        <li class="nav-item">

            <a class="nav-link" href="#">

                <i class="bi bi-gear-fill"></i>

                Paramètres

            </a>

        </li>

    </ul>

    <div class="tip-card">

        <i class="bi bi-lightbulb-fill"></i>

        <h6>Conseil du jour</h6>

        <p>
            Encouragez votre enfant à pratiquer
            une activité éducative chaque jour.
        </p>

    </div>

    <div class="logout">

        <a href="<%=request.getContextPath()%>/deconnexion">

            <i class="bi bi-box-arrow-right"></i>

            Déconnexion

        </a>

    </div>

</aside>

<!-- ================= MAIN CONTENT ================= -->

<main class="main-content">

    <!-- HEADER -->

    <div class="topbar">

        <div>

            <h2>Bonjour ${parent.nom} ${parent.prenom } </h2>

            <p>
                Bienvenue sur votre espace de suivi.
            </p>

        </div>

       

    </div>

    <!-- KPI -->

    <div class="row g-4">

        <div class="col-lg-3">

            <div class="kpi-card">

                <i class="bi bi-people-fill"></i>

                <h3>${nbEnfants}</h3>

                <p>Enfants suivis</p>

            </div>

        </div>

        <div class="col-lg-3">

            <div class="kpi-card">

                <i class="bi bi-clipboard-check-fill"></i>

                <h3>${nb_quiz_realises}</h3>

                <p>Activités réalisées</p>

            </div>

        </div>

        <div class="col-lg-3">

            <div class="kpi-card">

                <i class="bi bi-bar-chart-fill"></i>

                <h3>85%</h3>

                <p>Moyenne globale</p>

            </div>

        </div>

        <div class="col-lg-3">

            <div class="kpi-card">

                <i class="bi bi-award-fill"></i>

                <h3>12</h3>

                <p>Objectifs atteints</p>

            </div>

        </div>

    </div>

   <% if(enfants == null | enfants.isEmpty())  {%>
    <div class="section-card mt-4 d-flex justify-content-center fs-2">
   Vous n'avez pas encore ajouter d'enfants 
    
    </div>
    
    <% }   else {%>
    
     <div class="section-card mt-4">


        <div class="section-header">

            <div>

                <h4>Mes enfants</h4>

                <p>
                    Sélectionnez un enfant pour accéder
                    à son profil détaillé.
                </p>

            </div>

             <a href="<%=request.getContextPath()%>/liste-enfant" class="underline">
                            Voir plus
                        </a>

        </div>

        <div class="row g-4 mt-2">
        
        <%
   
    if (enfants != null) {
    	int count = 0;
        for (com.denkolochi.model.Enfant e : enfants) {
        	 if (count >= 2) break;
%>
            <div class="col-lg-6">
                <div class="child-card">
                    <div class="child-info">
                        <img src="<%=request.getContextPath()%>/assets/images/girl.png" alt="">
                        <div>
                            <h5><%= e.getPrenom() %> <%= e.getNom() %></h5>
                            <p><%= e.getDate_naissance() %> • <%= e.getSexe() %></p>
                        </div>
                    </div>
                    <div class="child-actions">
                        <a href="<%=request.getContextPath()%>/child-profile?id=<%= e.getId() %>" class="btn btn-success">
                            Voir le profil
                        </a>
                        <button class="btn btn-light"><i class="bi bi-pencil"></i></button>
                        <button class="btn btn-light"><i class="bi bi-three-dots-vertical"></i></button>
                    </div>
                </div>
            </div>
<%
 count++;
        }
    }
    
   
%>

        
      


        </div>

    </div>
    <%} %>


   
    <!-- RECOMMANDATIONS -->

    <div class="section-card mt-4">

        <h4 class="mb-4">
            Recommandations générales
        </h4>

        <div class="recommendation success">

            <i class="bi bi-check-circle-fill"></i>

            <div>

                <h6>Lecture quotidienne</h6>

                <p>
                    Encouragez 15 minutes de lecture
                    chaque jour.
                </p>

            </div>

        </div>

        <div class="recommendation warning">

            <i class="bi bi-lightbulb-fill"></i>

            <div>

                <h6>Développement cognitif</h6>

                <p>
                    Renforcez la logique avec des jeux
                    adaptés à l'âge.
                </p>

            </div>

        </div>

    </div>

</main>
```

</div>

<script
src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js">
</script>

</body>
</html>
