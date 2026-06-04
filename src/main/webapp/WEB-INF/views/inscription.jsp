<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" href="assets/images/iconedenkolochi.png" type="image/x-icon">

    <title>Inscription - DenKolosi</title>
    <style>
        body { 
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; 
            display: flex; 
            justify-content: center; 
            align-items: center; 
            min-height: 100vh; 
            margin: 0; 
            background-color: #f9f9f9; 
        }

        .conteneur-inscription { 
            display: flex; 
            background: white; 
            border-radius: 20px; 
            box-shadow: 0 4px 20px rgba(0,0,0,0.1); 
            width: 800px; 
            overflow: hidden; 
        }

        .zone-illustration { 
            flex: 1; 
            background: #f0f7f4; 
            padding: 40px; 
            display: flex; 
            flex-direction: column; 
            justify-content: center; 
            align-items: center; 
            text-align: center;
        }

        .bloc-formulaire { 
            flex: 1; 
            padding: 40px; 
        }

        .bloc-formulaire h2 { text-align: center; }

        h2 { color: #333; margin-top: 0; }
        
        label { display: block; margin-top: 15px; font-weight: 600; color: #555; }
        
        input { 
            width: 100%; 
            padding: 12px; 
            margin: 8px 0; 
            border: 1px solid #ccc; 
            border-radius: 8px; 
            box-sizing: border-box; 
        }

        .bouton-inscription { 
            width: 100%; 
            padding: 12px; 
            background: #ff9800; 
            color: white; 
            border: none; 
            border-radius: 8px; 
            cursor: pointer; 
            font-weight: bold;
            margin-top: 20px;
            transition: all .5s ease-in;
        }

        .bouton-inscription:hover { background: #e68900; }

        .lien-retour { 
            text-align: center; 
            margin-top: 15px; 
            font-size: 0.9em; 
        }

        .lien-retour a { color: #28a745; text-decoration: none; font-weight: bold; }

        .conditions { font-size: 0.75em; color: #666; margin-top: 20px; text-align: center; }
        
        .logo-denkolochi {
            display: block;
            width: 30%;
        }
    </style>
</head>
<body>
    <div class="conteneur-inscription">
        <div class="zone-illustration">
            <img src="assets/images/iconedenkolochi.png" alt="logo de Denkolochi" class="logo-denkolochi">
            <p>Rejoignez notre communauté pour un suivi intelligent.</p>
        </div>

        <div class="bloc-formulaire">
            <h2>Créer un compte</h2>
            
           <form action="inscriptionaction" method="POST">
    <label>Nom</label>
    <input type="text" name="nom" placeholder="Entrer votre nom" required>
    
    <label>Prénom</label>
    <input type="text" name="prenom" placeholder="Entrer votre prénom" required>
    
    <label>Téléphone</label>
    <input type="text" name="tel" placeholder="Entrer votre téléphone" required>
    
    <label>Email</label>
    <input type="email" name="mail" placeholder="Entrer votre email" required>
    
    <label>Mot de passe</label>
    <input type="password" name="password" placeholder="Choisir un mot de passe" required>

    <label>Profil</label>
    <select name="role" style="width:100%; padding:10px; border-radius:8px;">
        <option value="PARENT">Parent</option>
        <option value="ENSEIGNANT">Enseignant</option>
        <option value="SPECIALISTE">Spécialiste</option>
    </select>
    
    <button type="submit" class="bouton-inscription">S'inscrire</button>
</form>

            <div class="lien-retour">
                Déjà inscrit ? <a href="connexion">Connectez-vous</a>
            </div>
            
            <p class="conditions">En vous inscrivant, vous acceptez nos <a>conditions d'utilisation</a> et notre <a>politique de confidentialité</a>.</p>
        </div>
    </div>
</body>
</html>