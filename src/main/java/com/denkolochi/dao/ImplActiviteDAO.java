package main.java.DAO;

import java.sql.Connection;
import main.java.BD.ConnexionDB;
import main.java.Modele.Activite;

public class ImplActiviteDAO implements Repository<Activite, Integer> {
    Connection con = ConnexionDB.getConexion();
    @Override
    public void save(Activite entity) {
        String sql = "INSERT INTO activite VALUES (?,?,?,?)";
        try (java.sql.PreparedStatement pont = con.prepareStatement(sql)) {
            pont.setNull(1,java.sql.Types.INTEGER);
            pont.setString(2, entity.getTitre());
            pont.setString(3, entity.getDescriptions());
            pont.setInt(4, entity.getAgeMin());
            pont.setInt(5, entity.getAgeMax());
            pont.setObject(6, entity.getDateCreation());
            pont.setString(7, entity.getTypeActivites());
            int b =pont.executeUpdate();
            if(b > 0){
                System.err.println("Activite inserer avec succès");
            }

            con.close();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();

    }

    
}

}

