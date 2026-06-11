package com.denkolochi.configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnexionDB {

	private static ConnexionDB instance = null;
	private Connection connection = null;

	private ConnexionDB() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			this.connection = DriverManager.getConnection(DbConstConfig.url, DbConstConfig.utilisateur,
					DbConstConfig.mot_de_passe);
			System.out.println("Connexion MySQL établie avec succès.");
		} catch (ClassNotFoundException e) {
			System.err.println("Le driver MySQL est introuvable (JAR manquant) : " + e.getMessage());
		} catch (SQLException e) {
			System.err.println("Erreur de connexion à la base de données.");
			System.err.println("Code SQL  : " + e.getErrorCode());
			System.err.println("Message   : " + e.getMessage());
		}
	}

	public static ConnexionDB getInstance() {
		try {
			if (instance == null) {
				instance = new ConnexionDB();
			}
		} catch (Exception e) {
			System.err.println("probleme de connection : " + e.getMessage());
		}

		return instance;
	}

	public Connection getconnection() {
		try {
			// On vérifie d'abord si connection est null avant d'appeler isClosed()
			if (instance == null || this.connection == null || this.connection.isClosed()) {
				System.out.println("Reconnexion en cours...");
				instance = new ConnexionDB();
			}
		} catch (SQLException e) {
			System.err.println("Erreur lors de la vérification de la connexion : " + e.getMessage());
		}

		return (instance != null) ? instance.connection : null;
	}

	public void fermer() {
		try {
			if (connection != null && !connection.isClosed()) {
				connection.close();
				instance = null;
				System.out.println("Connexion fermée.");
			}
		} catch (SQLException e) {
			System.err.println("Erreur lors de la fermeture : " + e.getMessage());
		}
	}

}
