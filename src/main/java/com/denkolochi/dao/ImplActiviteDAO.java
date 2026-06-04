package com.denkolochi.dao;
import com.denkolochi.servlet.Ajout_Activite;
import java.sql.Connection;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.denkolochi.configuration.ConnexionDB;
import com.denkolochi.model.Activite;

public class ImplActiviteDAO implements Repository<Activite, Integer> {
	Connection con = ConnexionDB.getInstance().getconnection();    
    @Override
    public void save(Activite entity) {
        String sql = "INSERT INTO activites VALUES (?,?,?,?,?,?,?,?)";
        try (java.sql.PreparedStatement pont = con.prepareStatement(sql)) {
            pont.setNull(1,java.sql.Types.INTEGER);
            pont.setString(2, entity.getTitre());
            pont.setString(3, entity.getDescriptions());
            pont.setInt(4, entity.getAgeMin());
            pont.setInt(5, entity.getAgeMax());
            pont.setObject(6, entity.getDateCreation());
            pont.setInt(7, entity.getIdCapacite() );
            pont.setString(8, entity.getTypeActivites());
            int b =pont.executeUpdate();
            if(b > 0){
//                System.err.println("Activite inserer avec succès");
//
//                System.out.println("Nombre de lignes insérées = " + b);
            }

            con.close();
            
        } catch (java.sql.SQLException e) {
        	
//        	 System.out.println("Erreur SQL");
//        	    System.out.println("Code : " + e.getErrorCode());
//        	    System.out.println("Etat : " + e.getSQLState());
//        	    System.out.println("Message : " + e.getMessage());
//        	    
            e.printStackTrace();

    }

    
}

	@Override
	public Activite findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Activite> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Integer id, Activite entity) {
		// TODO Auto-generated method stub
	
		
	}


}

