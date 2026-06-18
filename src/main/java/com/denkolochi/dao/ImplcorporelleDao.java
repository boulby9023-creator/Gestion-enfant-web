package com.denkolochi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import com.denkolochi.configuration.ConnexionDB;
import com.denkolochi.model.Corporelle;
import com.denkolochi.model.Question;

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
            java.sql.Date dateSQL = entity.getDate_mesure() != null 
                    ? java.sql.Date.valueOf(entity.getDate_mesure()) 
                    : java.sql.Date.valueOf(java.time.LocalDate.now());
            
            ps.setDate(5, dateSQL);

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
		throw new UnsupportedOperationException("Unimplemented method 'findById'");

	
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
	
	public List<Corporelle> findAllByEnfant(Integer id) {
	    List<Corporelle> corporelles = new ArrayList<>();
	    String sql = "SELECT * FROM corporelles WHERE id_enfant = ? ORDER BY date_enregistrement DESC, id DESC";

	    try (PreparedStatement pont = con.prepareStatement(sql)) {
	        pont.setInt(1, id);

	        try (ResultSet rs = pont.executeQuery()) {
	            while (rs.next()) {
	                Corporelle corporelle = new Corporelle();
	                corporelle.setId(rs.getInt("id"));
	                corporelle.setId_enfant(rs.getInt("id_enfant"));
	                corporelle.setPoids(rs.getFloat("poids"));
	                corporelle.setTaille(rs.getFloat("taille"));
	                corporelle.setImc(rs.getFloat("imc"));
	                corporelle.setDate_mesure(LocalDate.parse(rs.getString("date_enregistrement")));
	                corporelles.add(corporelle);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return corporelles;
	}
	
	  public List<Corporelle> getMesuresByEnfantId(int idEnfant) {
	        List<Corporelle> mesures = new ArrayList<>();
	        String sql = "SELECT * FROM corporelles WHERE id_enfant = ? ORDER BY date_enregistrement ASC";
	        
	        try {
	            PreparedStatement stmt = con.prepareStatement(sql);
	            stmt.setInt(1, idEnfant);
	            ResultSet rs = stmt.executeQuery();
	            
	            while (rs.next()) {
	            	Corporelle mesure = new Corporelle();
	                mesure.setId(rs.getInt("id"));
	                mesure.setId_enfant(rs.getInt("id_enfant"));
	                mesure.setPoids(rs.getFloat("poids"));
	                mesure.setTaille(rs.getFloat("taille"));
	                mesure.setImc(rs.getFloat("imc"));
	                mesure.setDate_mesure(LocalDate.parse(rs.getString("date_enregistrement")));;
	                
	                mesures.add(mesure);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        
	        return mesures;
	    }
	  
	  public Corporelle getLastMesure(int idEnfant) {
	        String sql = "SELECT * FROM corporelles WHERE id_enfant = ? ORDER BY date_enregistrement DESC LIMIT 1";
	        
	        try {
	            PreparedStatement stmt = con.prepareStatement(sql);
	            stmt.setInt(1, idEnfant);
	            ResultSet rs = stmt.executeQuery();
	            
	            if (rs.next()) {
	            	Corporelle mesure = new Corporelle();
	                mesure.setId(rs.getInt("id"));
	                mesure.setId_enfant(rs.getInt("id_enfant"));
	                mesure.setPoids(rs.getFloat("poids"));
	                mesure.setTaille(rs.getFloat("taille"));
	                mesure.setImc(rs.getFloat("imc"));
	                mesure.setDate_mesure(LocalDate.parse(rs.getString("date_enregistrement")));
	                
	                return mesure;
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        
	        return null;
	    }
	    
	

}