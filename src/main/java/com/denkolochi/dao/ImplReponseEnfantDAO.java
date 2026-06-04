package com.denkolochi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.denkolochi.configuration.ConnexionDB;
import com.denkolochi.model.Admins;
import com.denkolochi.model.Enfant;
import com.denkolochi.model.Parent;

public class ImplReponseEnfantDAO  implements Repository<Admins, Integer>  {
	Connection con = ConnexionDB.getInstance().getconnection();


	@Override
	public void save(Admins entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Admins findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Admins> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Integer id, Admins entity) {
		// TODO Auto-generated method stub
		
	}

	
	
	public int getNombreQuizByParentId(int idParent) {
	    int total = 0;
	    try {
	        String sql = "SELECT COUNT(DISTINCT qq.id_quiz) AS nb_quiz_realises " +
	                     "FROM reponses_enfants r " +
	                     "JOIN question_quiz qq ON r.id_questions = qq.id_questions " +
	                     "JOIN enfants e ON r.id_enfants = e.id_enfants " +
	                     "WHERE e.id_parent = ?";
	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setInt(1, idParent);
	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            total = rs.getInt("nb_quiz_realises");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return total;
	}
	

}
