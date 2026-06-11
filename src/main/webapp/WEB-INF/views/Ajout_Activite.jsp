<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ajouter une activité</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/Ajout_Activite.css">
    <link rel="stylesheet"  href="https://fonts.googleapis.com/css2?family=Montserrat:wght@100;300;400;500;600;700;800&display=swap">
</head>
<body>
    <div class="container">
        <!-- sidebar -->
          <aside class="sidebar">
            <div class="logo_content">
                <img class="logo"  src="${pageContext.request.contextPath}/assets/images/logo_den.png" alt="Logo">
            </div>
            <div class="menu">
                
                    <div class="icon"><img src="${pageContext.request.contextPath}/assets/images/icon_dashboard.png" alt="icon pour home"><a href="${pageContext.request.contextPath}/servlet">Dashboard</a></div>
                    <br>
                    <div class="icon"><img src="${pageContext.request.contextPath}/assets/images/icon_exercice.png" alt="icon pour exercice"><a href="${pageContext.request.contextPath}/ServletExercice">Gestion des exercices</a></div>
                    <br>
                    <div class="icon"><img src="${pageContext.request.contextPath}/assets/images/icon_quiz.png" alt="icon pour quiz"><a href="${pageContext.request.contextPath}/ServletQuiz">Gestion des quiz</a></div>
                    <br>
                    <div class="icon"><img src="${pageContext.request.contextPath}/assets/images/icon_jeux.png" alt="icon pour jeux"><a href="${pageContext.request.contextPath}/ServletJeu">Gestion des jeux</a></div>
                    <br>
                    <div class="icon"><img src="${pageContext.request.contextPath}/assets/images/icon_user.png" alt="icon pour utilisateur"><a href="${pageContext.request.contextPath}/ServletAjouterUtilisateurs">Gestion des utilisateurs</a></div>
                    <br>
                    <br>
                    <div class="icon_para"><img src="${pageContext.request.contextPath}/assets/images/icon_para.png" alt="icon pour paramètre"><a href="#">Paramètres</a></div>
                    <div class="icon_deconn"><img src="${pageContext.request.contextPath}/assets/images/icon_out.png" alt="icon pour deconnexion"><a href="${pageContext.request.contextPath}/Home">Déconnexion</a></div>
            </div>
          </aside>
          <!-- contenu -->
          <main class="contenu">
            <header class="header">
              <div></div>
              <div class="admin">
                <img src="${pageContext.request.contextPath}/assets/images/user.png" alt="icon pour profil administrateur" class="notif">
                <div class="profil">
                    <strong>Admin</strong>
                    <span>Administrateur</span>
                </div>
              </div>
            </header>
            <section class="section_ajout">
               <div class="icon_ajout">+</div>
               <div>
                  <h1>Ajouter une nouvelle activité</h1>
                  <p>Remplissez les informations de l'activité</p>
               </div>
            </section>
             <p class="champ">* : Champs obligatoires</p>
            <div class="formulaire">
               <div class="cote_gauche">
               
                  <form action="${pageContext.request.contextPath}/Ajout_Activite" method="post" enctype="multipart/form-data" >
                     <div class="form">
                     <label for="" class="label_gauche">Titre de l'activité *</label>
                     <input type="text" name = "titre_Activite" placeholder="Ex: Jeu de mémoire" required>
                  </div>
                  <div class="form">
                     <label for="" class="label_gauche">Description *</label>
                     <textarea  name = "description" placeholder="Décrivez l’activité en détail..." required></textarea>
                  </div>
                  <div class="form_ligne">
                      <div class="form">
                          <label for="" class="label_gauche">Âge minimum *</label>
                          <input type="number" name = "agemin" placeholder="Ex: 5" required>
                      </div>
                      <div class="form">
                          <div class="form_ligne_age">
                          <label for="" class="label_gauche">Âge maximum *</label>
                          <input type="number" name = "agemax" placeholder="Ex: 10" required>
                          </div>
                      </div>
                  </div>
                  <div class="form_ligne">
                      <div class="form">
                        <label for="" class="label_gauche">Type d'activité *</label>
                        <select id="select" name="type_activite" required>
                          <option value="">Sélectionnez un type</option>
                          <option value="Cognitif">Cognitif</option>
                          <option value="Intellectuel">Intellectuel</option>
                        </select>
                      </div>
                      <div class="form">
                        <div class="form_ligne_capacite">
                        <label for="" class="label_gauche">Capacité *</label>
                        <select name="capacite" id="" required>
                          <option value="">Sélectionnez une capacité</option>
                          <option value="Memoire">Mémoire</option>
                          <option value="Attention">Attention</option>
                          <option value="Logique">Logique</option>
                          <option value="Resolution">Résolution de problèmes</option>
                          <option value="Concentration">Concentration</option>
                        </select>
                        </div>
                      </div>
                  </div>
               </div>
               <div class="cote_droite">
                 <label class="label_droite">Image d’activité</label>
                  <div class="upload">
                    <img src="${pageContext.request.contextPath}/assets/images/cloud.png" class="image" alt="icon pour upload">
                    <br>
                    <label for="fileUpload">Glissez-déposez une image ici</label>
                    <br>
                    <input type="file" name = "image" id="fileUpload" accept=".jpg, .png" hidden>
                    <strong>ou cliquer pour parcourir</strong>
                    <br>
                    <small> Format acceptés: JPG, PNG (Max 2Mo)</small>
                  </div>
                  <div class="form">
                    <section id="date_section">
                    <label for="fileUpload" class="label_droite">Date de création *</label>
                    <input type="date" name = "date_creation" id="dateCreation" required>
                    </section>
                  </div>
                  <div class="info">
                    <img id="information" src="${pageContext.request.contextPath}/assets/images/info.png" alt="icon pour information">
                      Une activité bien décrite aide à <br>
                      mieux suivre les progrés des enfants
                  </div>
               </div>
            </div>
              <div class="bouton">
                  <button type = "reset" class="annuler">Annuler</button>
                  <button type = "submit" class="enregistrer">Enregistrer l'activité</button>
              </div>
               </form>  
          </main>
        </div>
    </div>
</body>
</html>