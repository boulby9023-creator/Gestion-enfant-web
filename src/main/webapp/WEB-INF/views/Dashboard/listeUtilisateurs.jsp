<%@page import="java.util.List"%>
<%@page import="com.denkolochi.model.Utilisateur"%>
<%@ page language="java" %>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard - Admin</title>
    <link rel="stylesheet" href="assets/css/dashboard-admin.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
</head>
<style>
body{
	box-sizing:border-box;
	width:100%;
	overflow-x:hidden;
}
   .table-container {
   	margin-top: 25px;
    background-color: #ffffff;
    border: 1px solid #e2e8f0;
    border-radius: 8px;
    overflow: hidden;
    display: flex;
    flex-direction: column;
    flex: 1;
}
.b{

    position: absolute;
    right: 2vw;
    top: -30px;
    display: flex;
    flex-direction: row;
    gap: 10px;
}
.ajouter{
    position: relative;
    font-family: 'Montserrat', sans-serif;
    background-color: #2E9E3E;
    width: 10vw;
    height: 8vh;
    border-radius: 10px;
}
.modifier{
    position: relative;
    font-family: 'Montserrat', sans-serif;
    background-color: #F7941D;
    width: 9vw;
    height: 8vh;
    border-radius: 10px;
}
.ajouter a {
	position: absolute;
	left: 35px;
    top: 12px;
    font-family: 'Montserrat', sans-serif;
    color: #fff;
    text-decoration: none;
}
.modifier a{
	position: absolute;
    top: 12px;
	left: 28px;
    font-family: 'Montserrat', sans-serif;
    color: #fff;
    text-decoration: none;
}
.table-wrapper {
    flex: 1;
    overflow-y: auto;
}

table {
	overflow-x: hidden;
    width: 78vw;
    height: 65vh;
    border-collapse: collapse;
    text-align: left;
    display: block;
}

thead {
    background-color: #f8fafc;
    border-bottom: 1px solid #e2e8f0;
    position: sticky;
    top: 0;
    z-index: 10;
    width:100%;
}
th {
    font-family: 'Montserrat', sans-serif;
    padding: 14px 20px;
    font-size: 13px;
    font-weight: 600;
    color: #334155;
    
}

.col-avatar { 
    font-family: 'Montserrat', sans-serif;
    width: 70px; 
    text-align: center; 
}

tbody tr {
    border-bottom: 1px solid #f1f5f9;
    width:100%;
}

tbody tr:last-child {
    border-bottom: none;
}

tbody tr:hover {
    background-color: #f8fafc;
}
td {
    font-family: 'Montserrat', sans-serif;
    padding: 10px 32px;
    font-size: 13px;
    color: #475569;
    vertical-align: middle;
}

/* Cellule Avatar */
.row-avatar {
    width: 32px;
    height: 32px;
    background-color: #f1f5f9;
    border-radius: 50%;
    display: flex;
    align-items: center;
    justify-content: center;
    color: #cbd5e1;
    font-size: 14px;
    margin: 0 auto;
}
.badge {
    display: inline-block;
    background-color: #f1f5f9;
    color: #94a3b8;
    padding: 3px 12px;
    border-radius: 9999px;
    font-size: 12px;
    font-weight: 500;
}

/* Actions (...) */
.actions-cell {
    text-align: right;
    color: #94a3b8;
    cursor: pointer;
    width: 60px;
    padding-right: 25px;
}

a{
	color: orange;
}
.page-item {
    display: inline-flex;
    align-items: center;
    justify-content: center;
    min-width: 32px;
    height: 32px;
    padding: 0 8px;
    font-size: 13px;
    font-weight: 500;
    color: #2563eb;
    text-decoration: none;
    border-radius: 4px;
    cursor: pointer;
}
.page-item:hover {
    background-color: #f1f5f9;
}

.page-item.active {
    background-color: #bfdbfe;
    color: #1d4ed8;
}

.page-dots {
    color: #94a3b8;
    padding: 0 4px;
    font-size: 13px;
}
.pagination {
    display: flex;
    justify-content: center;
    align-items: center;
    padding: 12px 0;
}

.sidebar-menu {
    width: 100%;
    list-style: none;
}

.menu-item {
   width: 100%;  
   margin-bottom: 8px;
}

.menu-link {
    font-family: 'Montserrat', sans-serif;
    display: flex;
    align-items: center;
    gap: 14px;
    padding: 14px 3px;
    color: #000000;
    text-decoration: none;
    font-size: 14px;
    font-weight: 500;
    border-radius: 10px;
    transition: background-color 0.2s ease, padding-left 0.2s ease, color 0.2s ease;
}

.menu-link i {
    font-size: 16px;
    width: 20px;
}

.menu-link:not(.active):hover {
    background-color: #2E9E3E;
    opacity: 50%;
    padding-left: 24px;
    }

.logout-container {
    margin-top: auto;
    width: 100%;
    padding-left: 30px;
}

.logout-btn {
    font-family: 'Montserrat', sans-serif;
    display: flex;
    align-items: center;
    margin-top: 60px;
    margin-right: 15px;
    gap: 10px;
    color: red;
    text-decoration: none;
    font-size: 13px;
    font-weight: 600;
    letter-spacing: 0.8px;
    transition: opacity 0.2s ease;
}

.logout-btn:hover {
    opacity: 0.8;
}
        
.logout-btn i {
    transition: transform 0.2s ease;
}
        
.logout-btn:hover i {
    transform: scale(1.1);
}

.search {
    position: relative;
    width: 280px;
        }

.search input {
    position: absolute;
    right: -250%;
    top: -60px;
    width: 60%;
    padding: 10px 20px;
    padding-right: 45px;
    background-color: #e2e8f0;
    border: 1px solid transparent;
    border-radius: 24px;
    font-size: 14px;
    outline: none;
    transition: border-color 0.2s ease, background-color 0.2s ease;
}

 .search input:hover {
    background-color: #cbd5e1;
}

.search input:focus {
    background-color: #ffffff;
    border-color: #94a3b8;
}

.search i {
    position: absolute;
    left: 74vw;
    bottom: 4.5vh;
    transform: translateY(-50%);
    color: #94a3b8;
    font-size: 14px;
    transition: color 0.2s ease;
}
        
.search input:focus + i {
   color: #475569;
}

</style>
<body>
    <header>
          <section>
            <div class="section-div">
                <div><img src="assets/images/logo.png" alt="" class="logo"></div>
                <ul class="sidebar-menu">
                <li class="menu-item">
                    <a href="${pageContext.request.contextPath}/ServletDashboardAdmin" class="menu-link">
                        <i class="fa-solid fa-house"></i> Dashboard
                    </a>
                </li>
                <li class="menu-item">
                    <a href="${pageContext.request.contextPath}/ServletExercice" class="menu-link">
                        <i class="fa-solid fa-thumbtack"></i> Gestion des Exercicces
                    </a>
                </li>
                <li class="menu-item">
                    <a href="${pageContext.request.contextPath}/ServletQuiz" class="menu-link">
                        <i class="fa-regular fa-square-check"></i> Gestion des Quiz
                    </a>
                </li>
                <li class="menu-item">
                    <a href="${pageContext.request.contextPath}/ServletJeu" class="menu-link">
                        <i class="fa-solid fa-gamepad"></i> Gestion des Jeux
                    </a>
                </li>
                <li class="menu-item">
                    <a href="${pageContext.request.contextPath}/ServletListeUtilisateur" class="menu-link">
                        <i class="fa-solid fa-user-plus"></i> Gestion des Utilisateurs
                    </a>
                </li>
            </ul>
            <div class="logout-container">
                <a href="#" class="logout-btn">
                    <i class="fa-solid fa-power-off"></i> DECONNEXION
                </a>
            </div>
            </div>
        </section>
        <nav>
        <div><img src="assets/images/logo.png" class="logo" alt="Logo"></div>
           <div class="search">
                <input type="text" placeholder="Rechercher">
                <i class="fa-solid fa-magnifying-glass"></i>
            </div>
        </nav> 
    </header>
    <main>
    	<div class="b">
            <div class="ajouter"><a href="${pageContext.request.contextPath}/ServletAjouterUtilisateurs" class="lien">Ajouter</a></div>
        </div>
        <div class="table-container">
                <div class="table-wrapper">
                    <table>
                        <thead>
                        
                            <tr>
                                <th class="col-avatar"></th>
                                <th>Prenom</th>
                                <th>Nom</th>
                                <th>Email</th>
                                <th>Telephone</th>
                                <th>Role</th>
                                <th class="actions-cell"></th>
                            </tr>
                        </thead>
                        <tbody>
                        <% List<Utilisateur> users=(List<Utilisateur>)request.getAttribute("users"); 
                        for( Utilisateur listeutil : users){ %>
                        <tr>
                           <td class="col-avatar">
                              <div class="row-avatar"><i class="fa-regular fa-user"></i></div>
                           	</td>
                          	<td><%= listeutil.getPrenom() %></td>
							<td><%= listeutil.getNom() %></td>
							<td><%= listeutil.getMail()  %></td>
							<td><%= listeutil.getTel() %></td>
							<td><span class="badge"><% out.println( listeutil.getRole()); %></span></td>
                            <td class="actions-cell">
                            	<a href="${pageContext.request.contextPath}/ServletModifier?id=<%= listeutil.getId()  %>">
                            		<i class="fa-solid fa-pen-to-square"></i>
                            	</a>
                            </td>
                       </tr>
                       <% } %>
                        </tbody>
                    </table>
                </div>
            </div>
    </main>
</body>

</html>