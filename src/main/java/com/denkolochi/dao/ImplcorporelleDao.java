package com.denkolochi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import com.denkolochi.configuration.ConnexionDB;
import com.denkolochi.model.Corporelle;

public class ImplcorporelleDao implements Repository<Corporelle, Integer> {
	Connection con = ConnexionDB.getInstance().getconnection();



    @Override
    public void save(Corporelle entity) {
        String sql = "INSERT INTO corporelles (id_enfant, poids, taille, imc, date_enregistrement) "
                   + "VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, entity.getId_enfant());
            ps.setDouble(2, entity.getPoids());
            ps.setDouble(3, entity.getTaille());
            ps.setDouble(4, entity.getImc());
            ps.setDate(5, entity.getDate_mesure() != null 
                            ? new java.sql.Date(entity.getDate_mesure().getTime()) 
                            : new java.sql.Date(System.currentTimeMillis()));

            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("✅ Mesure corporelle insérée avec succès");
            }

        } catch (SQLException e) {
            System.err.println("❌ Erreur lors de l'insertion des mesures corporelles");
            System.err.println("SQL State : " + e.getSQLState());
            System.err.println("Message   : " + e.getMessage());
            e.printStackTrace();
        }
    }

	@Override
	public Corporelle findById(Integer id) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'findById'");
	}

	@Override
	public List<Corporelle> findAll() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'findAll'");
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'delete'");
	}

	@Override
	public void update(Integer id, Corporelle entity) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

}