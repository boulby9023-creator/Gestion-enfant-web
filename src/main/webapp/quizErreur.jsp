<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="fr">

<head>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Quiz indisponible - DenKɔlɔsi</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
      rel="stylesheet">

<link rel="stylesheet"
      href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">

<style>

body{
    background:#F8FAFC;
    min-height:100vh;
    display:flex;
    align-items:center;
    justify-content:center;
    font-family:'Poppins',sans-serif;
}

.error-card{

    max-width:600px;
    width:100%;

    background:#FFFFFF;

    border:none;
    border-radius:24px;

    padding:40px;

    text-align:center;

    box-shadow:0 10px 40px rgba(0,0,0,.08);
}

.error-icon{

    width:100px;
    height:100px;

    margin:auto;

    background:#FFF3CD;

    border-radius:50%;

    display:flex;
    align-items:center;
    justify-content:center;
}

.error-icon i{

    font-size:50px;
    color:#FFC107;
}

.error-title{

    margin-top:25px;
    font-weight:700;
    color:#1F2937;
}

.error-text{

    color:#6B7280;
    margin-top:15px;
    margin-bottom:30px;
}

.btn-main{

    background:#2E9E3E;
    border:none;
    border-radius:12px;

    padding:12px 25px;
}

.btn-main:hover{

    background:#258734;
}

</style>

</head>

<body>

<div class="error-card">

    <div class="error-icon">
        <i class="bi bi-exclamation-triangle-fill"></i>
    </div>

    <h2 class="error-title">
        Quiz indisponible
    </h2>

    <p class="error-text">

        Désolé, aucun quiz n'est actuellement disponible pour cet enfant.

        <br><br>

        Veuillez réessayer plus tard ou contacter un administrateur si le problème persiste.

    </p>

    <div class="d-flex justify-content-center gap-3">

        <a href="${pageContext.request.contextPath}/dashboard"
           class="btn btn-secondary">

            <i class="bi bi-arrow-left me-2"></i>
            Retour

        </a>

        <a href="${pageContext.request.contextPath}/liste-enfant"
           class="btn btn-main text-white">

            <i class="bi bi-house-door me-2"></i>
            Mes enfants

        </a>

    </div>

</div>

</body>

</html>