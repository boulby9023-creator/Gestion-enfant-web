package com.denkolochi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.denkolochi.configuration.ConnexionDB;
import com.denkolochi.model.Question;

public class ImplQuestionDAO implements Repository<Question, Integer> {

    Connection con = ConnexionDB.getInstance().getconnection();

	@Override
	public void save(Question entity) {

		String sql = "INSERT INTO question VALUES (?,?,?,?)";
		try (PreparedStatement pont = con.prepareStatement(sql)) {
			pont.setNull(1, java.sql.Types.INTEGER);
			pont.setString(2, entity.getEnonce());
			pont.setInt(3, entity.getDelai_max());
			pont.setInt(4, entity.getId_capacite());

			int b = pont.executeUpdate();
			if (b > 0) {
				System.err.println("Question inserer avec succès");
			}

			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public Question findById(Integer id) {

		String sql = "SELECT * FROM question WHERE id_question = ?";
		try (PreparedStatement pont = con.prepareStatement(sql)) {
			pont.setInt(1, id);
			ResultSet rs = pont.executeQuery();
			if (rs.next()) {
				Question question = new Question();
				question.setId_questions(rs.getInt("id_question"));
				question.setEnonce(rs.getString("enonce"));
				question.setDelai_max(rs.getInt("delai_max"));
				question.setId_capacite(rs.getInt("id_capacite"));
				return question;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	@Override
	public List<Question> findAll() {
		List<Question> questions = new ArrayList<>();
		String sql = "SELECT * FROM question";
		try (PreparedStatement pont = con.prepareStatement(sql)) {
			ResultSet rs = pont.executeQuery();
			while (rs.next()) {
				Question question = new Question();
				question.setId_questions(rs.getInt("id_question"));
				question.setEnonce(rs.getString("enonce"));
				question.setDelai_max(rs.getInt("delai_max"));
				question.setId_capacite(rs.getInt("id_capacite"));
				questions.add(question);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return questions;
	}

	@Override
	public void delete(Integer id) {
		String sql = "DELETE FROM question WHERE id_question = ?";
		try (PreparedStatement pont = con.prepareStatement(sql)) {
			pont.setInt(1, id);
			int b = pont.executeUpdate();
			if (b > 0) {
				System.err.println("Question supprimer avec succès");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Integer id, Question entity) {
		throw new UnsupportedOperationException("Not supported yet.");

	}

	public List<Question> recuperationQuestionsByIdQuiz(Integer id) {

		String sql = " SELECT Q.id_questions, Q.enonce, Q.delai_max, Q.id_capacite FROM questions Q JOIN question_quiz QZ ON Q.id_questions = QZ.id_questions WHERE QZ.id_quiz = ? ";
		List<Question> Questions = new ArrayList<Question>();
		try (PreparedStatement pont = con.prepareStatement(sql)) {
			pont.setInt(1, id);
			ResultSet rs = pont.executeQuery();
			while (rs.next()) {
				Question question = new Question();
				question.setDelai_max(rs.getInt("delai_max"));
				question.setEnonce(rs.getString("enonce"));
				question.setId_capacite(rs.getInt("id_capacite"));
				question.setId_questions(rs.getInt("id_questions"));
				Questions.add(question);
			}

		} catch (SQLException e) {
			System.out.println("Erreur au niveau de sql " + e.getMessage());
		}
		return Questions;
	}

}
