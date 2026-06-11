<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">

<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
<title>Insert title here</title>
</head>
<body class='px-6'>
 <div class=" row ">
       <div class="logo text-center mb-4 col-4">

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
    <h2 class="col-4">Ajouter un enfant</h2>
      <div class="logo text-center mb-4 col-4">

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
   </div>
   
   
   
   
   
   
 <div class="container mt-4">
  <div class="form-card p-4 shadow rounded bg-white">
    <h3 class="text-center mb-4">Ajouter un enfant</h3>

    <form action="${pageContext.request.contextPath}/ajouterEnfant" method="post">

      <div class="row mb-3">
        <div class="col-md-6">
          <label for="nom" class="form-label">Nom</label>
          <input type="text" class="form-control" id="nom" name="nom" placeholder="Entrer le nom" required>
        </div>

        <div class="col-md-6">
          <label for="date_naissance" class="form-label">Date de naissance</label>
          <input type="date" class="form-control"   max="<%= java.time.LocalDate.now() %>" id="date_naissance" name="date_naissance" required>
        </div>
      </div>

      <div class="row mb-3">
        <div class="col-md-6">
          <label for="prenom" class="form-label">Prénom</label>
          <input type="text" class="form-control" id="prenom" name="prenom" placeholder="Entrer le prénom" required>
        </div>

        <div class="col-md-6">
          <label class="form-label d-block">Sexe</label>
          <div class="d-flex align-items-center justify-content-around border rounded p-2">
            <div class="form-check">
              <input class="form-check-input" type="radio" id="garcon" name="sexe" value="Garçon" required>
              <label class="form-check-label" for="garcon">Garçon</label>
            </div>

            <div class="form-check">
              <input class="form-check-input" type="radio" id="fille" name="sexe" value="Fille" required>
              <label class="form-check-label" for="fille">Fille</label>
            </div>
          </div>
        </div>
      </div>

      <div class="row mb-4">
        <div class="col-md-6">
          <label for="poids" class="form-label">Poids <small class="text-muted">(kg)</small></label>
          <input type="number" class="form-control" id="poids" name="poids" step="0.1" placeholder="Entrer le poids de l'enfant en kg" required>
        </div>

        <div class="col-md-6">
          <label for="taille" class="form-label">Taille <small class="text-muted">(cm)</small></label>
          <input type="number" class="form-control" id="taille" name="taille" step="0.1" placeholder="Entrer la taille de l'enfant en cm" required>
        </div>
      </div>

      <div class="text-center">
        <button type="submit" class="btn btn-success px-5">Ajouter</button>
      </div>
    </form>
  </div>
</div>

</body>
</html>