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
   
   
   
   
   
   
   <form>
  <div class="row mb-4">
    <div class="col">
      <div data-mdb-input-init class="form-outline">
          <label class="form-label" for="nom">Nom de l'enfant :</label>
          <input  class="form-control"  type="text" id="nom" name="nom" required>
      </div>
    </div>
    <div class="col">
      <div data-mdb-input-init class="form-outline">
        
        <label class="form-label" for="prenom">Prénom de l'enfant :</label>
    <input  class="form-control "  type="text" id="prenom" name="prenom" required>
      </div>
    </div>
  </div>

  <div data-mdb-input-init class="form-outline mb-4">
   
     <label class="form-label" for="dateNaissance">Date de naissance :</label>
    <input  class="form-control"  type="date" id="dateNaissance" name="dateNaissance" required>
  </div>


 

  <div class="form-check d-flex justify-content-center mb-4">
    <label  class="form-label">Sexe :</label>
  <div>
    <label class="form-check-label" for="garcon">Garçon</label>
    <input class="form-check-input me-2" type="checkbox" id="garcon" name="sexe" value="garcon">
  </div>
  <div>
   <input  class="form-check-input me-2" type="checkbox" id="fille" name="sexe" value="fille">
    <label class="form-check-label" for="fille">Fille</label>
  </div>
   

 
  </div>
  
  <!-- Poids -->
    <label for="poids">Poids (kg) :</label>
    <input  class="form-control"  type="number" id="poids" name="poids" step="0.1" required>

    <!-- Taille -->
    <label for="taille">Taille (cm) :</label>
    <input  class="form-control"  type="number" id="taille" name="taille" required>

    <!-- Bouton de soumission -->
    <button type="submit">Enregistrer</button>
</form>
   
   

</body>
</html>