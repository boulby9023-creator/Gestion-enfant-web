<%@page import="com.denkolochi.model.Historique"%>
<%@page import="java.util.List"%>


<%
List<Historique> historiques = (List<Historique>) request.getAttribute("historiques");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Historique</title>
<link rel="stylesheet" href="../../assets/css/historique.css">
</head>
<body>
<div class="contenaire">
	
	<div class="droite">

		<h2 class="titre"> Historique de l'enfant</h2>

		<%
		if (historiques == null || historiques.isEmpty()) {
		%>

		<div class="historique-card">Aucun historique disponible.</div>

		<%
		} else {
		%>

		<%
		for (Historique h : historiques) {
		%>

		<div class="historique-card">

			<div class="header-card">

				<div class="action">
					<%=h.getAction()%>
				</div>

				<div class="date">
					<%=h.getDateAction()%>
				</div>

			</div>

			<div class="details">
				<%=h.getDetails()%>
			</div>

			<div class="badge">Suivi Enfant</div>

		</div>

		<%
		}
		%>

		<%
		}
		%>

	</div>
	</div>

</body>
</html>