<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <link rel="icon" href="assets/images/iconedenkolochi.png">
    <link rel="stylesheet" href="accueil.css">
    <title>Connexion - DenKolosi</title>
    <style>
      
        body { font-family: sans-serif; display: flex; justify-content: center; align-items: center;  background-color: #f9f9f9; }

.login-container { display: flex; background: white; border-radius: 20px; box-shadow: 0 4px 20px rgba(0,0,0,0.1); width: 90vw;height: 95vh;overflow: hidden; }

.illustration {position: relative; background: #f0f7f4; padding: 20px 0 20px 20px; display: flex; flex-flow: column nowrap;}

.btn-back {
    position: absolute; /* Positionne le bouton en haut à gauche */
    top: 20px;
    left: 20px;
    text-decoration: none;
    color: #666;
    font-weight: bold;
    font-size: 2em;
    display: flex;
    align-items: center;
    gap: 5px;
    border: 1px solid #28a745;
    border-radius:10px 10px;
}

.btn-back:hover {
    color: white;
    background-color:#28a745;
}


.login-box {  padding: 40px; }

input { width: 100%; padding: 12px; margin: 10px 0; border: 1px solid #ccc; border-radius: 8px; box-sizing: border-box; }

.btn-connect { width: 100%; padding: 12px; background: #28a745; color: white; border: none; border-radius: 8px; cursor: pointer; }

.btn-register { width: 100%; padding: 12px; background: transparent; border: 1px solid #ff9800; color: #ff9800; border-radius: 8px; cursor: pointer; }

.form-options { display: flex; justify-content: space-between; font-size: 0.9em; margin: 10px 0; }

.divider { display: flex; justify-content: center;align-items: center; margin: 5vh 0;}

.terms { font-size: 0.75em; color: #666; margin-top: 20px; text-align: center; }
.logo{
    width: 10vw;
    position: absolute;
    top:5em;
    /* margin: auto; */
}
.main-img{
    width: 60%;
    align-self: flex-end;
}
h2,h2+p{
    text-align: center;
}
.tiret{
    background-color: #666;
    width: 45%;
    height: .2vw;
}
[type=checkbox]{
    width: auto;
}
h2{
    font-size:large ;
}

        body { font-family: sans-serif; display: flex; justify-content: center; align-items: center; background-color: #f9f9f9; }

        .conteneur-connexion { display: flex; background: white; border-radius: 20px; box-shadow: 0 4px 20px rgba(0,0,0,0.1); width: 90vw; height: 95vh; overflow: hidden; }

        .zone-illustration { background: #f0f7f4; padding: 20px 0 20px 20px; display: flex; flex-flow: column nowrap; }

        .bloc-formulaire { padding: 40px; }

        input { width: 100%; padding: 12px; margin: 10px 0; border: 1px solid #ccc; border-radius: 8px; box-sizing: border-box; }

        .bouton-connexion { width: 100%; padding: 12px; background: #28a745; color: white; border: none; border-radius: 8px; cursor: pointer; }

        .bouton-inscription { width: 100%; padding: 12px; background: transparent; border: 1px solid #ff9800; color: #ff9800; border-radius: 8px; cursor: pointer; }

        .options-formulaire { display: flex; justify-content: space-between; font-size: 0.9em; margin: 10px 0; }

        .separateur { display: flex; justify-content: center; align-items: center; margin: 5vh 0; }

        .conditions { font-size: 0.75em; color: #666; margin-top: 20px; text-align: center; }

        .logo-denkolochi { width: 10vw; }

        .image-principale { width: 60%; align-self: flex-end; }

        h2, h2 + p { text-align: center; }

        .tiret { background-color: #666; width: 45%; height: .2vw; }

        input[type=checkbox] { width: auto; }

        h2 { font-size: large; }
      main
    </style>
</head>
<body>
    <div class="conteneur-connexion">
        <div class="zone-illustration">
            <img src="assets/images/iconedenkolochi.png" alt="Logo DenKolosi" class="logo-denkolochi">
            <img src="assets/images/Photo de fond.png" alt="Parent et enfant" class="image-principale">

        </div>

        <div class="bloc-formulaire">
            <h2>Bienvenue !</h2>
            <p>Connectez-vous pour accéder au suivi intelligent de votre enfant.</p>
            <% if (request.getAttribute("erreur") != null) { %>
   				 <p style="color: red;"><%= request.getAttribute("erreur") %></p>
			<% } %>
            
            <form action="loginaction" method="POST">
                <label>Email</label>
                <input type="email" placeholder="Entrer votre email" name="mail" required>
                
                <label>Mot de passe</label>
                <input type="password" placeholder="Entrer votre mot de passe" name="password" required>
                
                <div class="options-formulaire">
                    <label><input type="checkbox" value="souvenir" name="souvenir"> Se souvenir de moi</label>
                    <a href="#">Mot de passe oublié ?</a>
                </div>
               
                <button type="submit" class="bouton-connexion">Se connecter</button>
            </form>

            <div class="separateur">
              <div class="tiret"></div>
              OU
              <div class="tiret"></div>
            </div>
            
            <button class="bouton-inscription" onclick="naviguer()">Créer un compte</button>
            
            <p class="conditions">En vous connectant, vous acceptez nos <a>conditions d'utilisation</a> et notre <a>politique de confidentialité</a>.</p>
        </div>
    </div>
    <script>

    const naviguerVersInscription = () => {
        window.location.href = "inscription";
    };
    document.querySelector('form').addEventListener('submit', function(event) {
        const email = document.querySelector('input[type="email"]').value;
        const password = document.querySelector('input[type="password"]').value;

        if (email === "" || password === "") {
            event.preventDefault(); 
            alert("Veuillez remplir tous les champs !");
        }
    });
</script>
</body>
</html>