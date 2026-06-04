package com.denkolochi.configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnexionDB {

	private static ConnexionDB instance = null;
	private Connection connection = null;

	private ConnexionDB() {
		try {
			this.connection = DriverManager.getConnection(DbConstConfig.url, DbConstConfig.utilisateur,DbConstConfig.mot_de_passe);
			System.out.println("Connexion MySQL établie avec succès.");
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
			if (this.connection == null || this.connection.isClosed()) {
				System.out.println("Connexion inexistante ou fermée. Reconnexion...");
				this.connection = DriverManager.getConnection(DbConstConfig.url, DbConstConfig.utilisateur,
						DbConstConfig.mot_de_passe);
			} 
		} catch (SQLException e) {
			System.err.println("Erreur lors de la vérification de la connexion : " + e.getMessage());
		}
		return this.connection;

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
		} finally {
			connection = null;
			instance = null; // 🌟 Nettoyé : Plus aucun caractère invisible ici !
		}
	}
}