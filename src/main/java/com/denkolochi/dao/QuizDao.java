package com.denkolochi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.denkolochi.configuration.ConnexionDB;
import com.denkolochi.model.Quiz;

public class QuizDao implements Repository<Quiz, Integer> {
    Connection con = ConnexionDB.getInstance().getconnection();

    @Override
    public void save(Quiz entity) {
        String sql = "INSERT INTO quiz (titre, temps_limite, score_max) VALUES (?, ?, ?)";
        try (PreparedStatement prepare = con.prepareStatement(sql)) {
            prepare.setString(1, entity.getTitre());
            prepare.setInt(2, entity.getTempsLimitGlobal());
            prepare.setInt(3, entity.getScoreMax());

            int row = prepare.executeUpdate();
            if (row == 1) {
                System.out.println("✅ Insertion réussie");
            } else {
                System.out.println("❌ Il y'a un problème");
            }

        } catch (SQLException e) {
            System.out.println("❌ Erreur au niveau de SQL : " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public Quiz findById(Integer id) {
        String sql = """
            SELECT q.id_quiz, q.titre, q.temps_limite, q.score_max,
                   a.id_activites, a.descriptions, a.age_min, a.age_max, 
                   a.date_creation, a.id_capacite, a.type_activites
            FROM quiz q
            INNER JOIN activites a ON q.id_quiz = a.id_activites
            WHERE q.id_quiz = ?
        """;

        try (PreparedStatement prepare = con.prepareStatement(sql)) {
            prepare.setInt(1, id);

            try (ResultSet rs = prepare.executeQuery()) {
                if (rs.next()) {
                    Quiz quiz = new Quiz();
                    
                    // attributs Quiz
                    quiz.setIdQuiz(rs.getInt("id_quiz"));
                    quiz.setTitre(rs.getString("titre"));
                    quiz.setTempsLimitGlobal(rs.getInt("temps_limite"));
                    quiz.setScoreMax(rs.getInt("score_max"));
                    
                    // attributs Activite (parent)
                    quiz.setIdActivite(rs.getInt("id_activites"));
                    quiz.setDescriptions(rs.getString("descriptions"));
                    quiz.setAgeMin(rs.getInt("age_min"));
                    quiz.setAgeMax(rs.getInt("age_max"));
                    quiz.setDateCreation(rs.getDate("date_creation"));
                    quiz.setIdCapacite(rs.getInt("id_capacite"));
                    quiz.setTypeActivites(rs.getString("type_activites"));
                    
                    return quiz;
                } else {
                    System.out.println("❌ Aucun quiz trouvé avec l'id " + id);
                    return null;
                }
            }
        } catch (SQLException e) {
            System.out.println("❌ Erreur au niveau de SQL : " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Quiz> findAll() {
        String sql = """
            SELECT q.id_quiz, q.titre, q.temps_limite, q.score_max,
                   a.id_activites, a.descriptions, a.age_min, a.age_max, 
                   a.date_creation, a.id_capacite, a.type_activites
            FROM quiz q
            INNER JOIN activites a ON q.id_quiz = a.id_activites
        """;

        List<Quiz> quizzes = new ArrayList<Quiz>();

        try (PreparedStatement prepare = con.prepareStatement(sql)) {
            ResultSet rs = prepare.executeQuery();

            while (rs.next()) {
                Quiz quiz = new Quiz();

                // attributs Quiz
                quiz.setIdQuiz(rs.getInt("id_quiz"));
                quiz.setTitre(rs.getString("titre"));
                quiz.setTempsLimitGlobal(rs.getInt("temps_limite"));
                quiz.setScoreMax(rs.getInt("score_max"));

                // attributs Activite
                quiz.setIdActivite(rs.getInt("id_activites"));
                quiz.setDescriptions(rs.getString("descriptions"));
                quiz.setAgeMin(rs.getInt("age_min"));
                quiz.setAgeMax(rs.getInt("age_max"));
                quiz.setDateCreation(rs.getDate("date_creation"));
                quiz.setIdCapacite(rs.getInt("id_capacite"));
                quiz.setTypeActivites(rs.getString("type_activites"));

                quizzes.add(quiz);
            }

        } catch (SQLException e) {
            System.out.println("❌ Erreur au niveau de SQL : " + e.getMessage());
            e.printStackTrace();
            return null;
        }

        return quizzes;
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM quiz WHERE id_quiz = ?";
        try (PreparedStatement prepare = con.prepareStatement(sql)) {
            prepare.setInt(1, id);

            int row = prepare.executeUpdate();
            if (row == 1) {
                System.out.println("✅ Suppression réussie");
            } else {
                System.out.println("❌ Il y'a un problème");
            }

        } catch (SQLException e) {
            System.out.println("❌ Erreur au niveau de SQL : " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void update(Integer id, Quiz entity) {
        String sql = "UPDATE quiz SET titre = ?, temps_limite = ?, score_max = ? WHERE id_quiz = ?";
        try (PreparedStatement prepare = con.prepareStatement(sql)) {
            prepare.setString(1, entity.getTitre());
            prepare.setInt(2, entity.getTempsLimitGlobal());
            prepare.setInt(3, entity.getScoreMax());
            prepare.setInt(4, id);

            int row = prepare.executeUpdate();
            if (row == 1) {
                System.out.println("✅ Modification réussie");
            } else {
                System.out.println("❌ Il y'a un problème");
            }

        } catch (SQLException e) {
            System.out.println("❌ Erreur au niveau de SQL : " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Récupère tous les quiz filtrés par type de capacité et âge de l'enfant
     */
    public List<Quiz> findQuizzesByTypeEtAge(String typeCapacite, int ageEnAnnes) {
        String sql = """
		
				SELECT q.id_quiz, q.titre, q.temps_limite, q.score_max,
				       a.id_activites, a.descriptions, a.age_min, a.age_max, 
				       a.date_creation, a.id_capacite, a.type_activites
				FROM quiz q
				INNER JOIN activites a ON q.id_quiz = a.id_activites
				WHERE a.type_activites = ?
				  AND a.age_min <=  ?
				  AND a.age_max >= ?
				ORDER BY q.id_quiz;
				    """;
			
			    List<Quiz> quizzes = new ArrayList<Quiz>();

        try (PreparedStatement prepare = con.prepareStatement(sql)) {
        	 prepare.setString(1, typeCapacite);
            prepare.setInt(2, ageEnAnnes);
            prepare.setInt(3, ageEnAnnes);

            
            

            ResultSet rs = prepare.executeQuery();

            while (rs.next()) {
                Quiz quiz = new Quiz();

                // attributs Quiz
                quiz.setIdQuiz(rs.getInt("id_quiz"));
                quiz.setTitre(rs.getString("titre"));
                quiz.setTempsLimitGlobal(rs.getInt("temps_limite"));
                quiz.setScoreMax(rs.getInt("score_max"));

                // attributs Activite
                quiz.setIdActivite(rs.getInt("id_activites"));
                quiz.setDescriptions(rs.getString("descriptions"));
                quiz.setAgeMin(rs.getInt("age_min"));
                quiz.setAgeMax(rs.getInt("age_max"));
                quiz.setDateCreation(rs.getDate("date_creation"));
                quiz.setIdCapacite(rs.getInt("id_capacite"));
                quiz.setTypeActivites(rs.getString("type_activites"));

                quizzes.add(quiz);
            }

        } catch (SQLException e) {
            System.out.println("❌ Erreur au niveau de SQL : " + e.getMessage());
            e.printStackTrace();
            return null;
        }

        return quizzes;
    }
}