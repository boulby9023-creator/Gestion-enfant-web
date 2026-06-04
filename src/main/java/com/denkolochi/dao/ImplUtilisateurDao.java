package com.denkolochi.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.denkolochi.configuration.ConnexionDB;
import com.denkolochi.model.Utilisateur;
import com.denkolochi.enumeration.RoleEnum;

public class ImplUtilisateurDao implements Repository<Utilisateur, Integer> {
    Connection con = ConnexionDB.getInstance().getconnection();

    @Override
    public void save(Utilisateur entity) {
        String sql = "INSERT INTO utilisateurs (nom, prenom, tel, mail, mot_de_passe, roles) VALUES(?,?,?,?,?,?)";
        try (PreparedStatement prepare = con.prepareStatement(sql)) {
            prepare.setString(1, entity.getNom());
            prepare.setString(2, entity.getPrenom());
            prepare.setString(3, entity.getTel());
            prepare.setString(4, entity.getMail());
            prepare.setString(5, entity.getMotDePasse());
            prepare.setString(6, entity.getRole().toString());

            if (prepare.executeUpdate() > 0) {
                System.out.println("Utilisateur ajouté avec succès !");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Utilisateur findById(Integer id) {
        String sql = "SELECT * FROM utilisateurs WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet result = ps.executeQuery()) {
                if (result.next()) {
                    return mapResultSetToUtilisateur(result);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<Utilisateur> findAll() {
        List<Utilisateur> utilisateurs = new ArrayList<>();
        String sql = "SELECT * FROM utilisateurs";
        try (Statement stat = con.createStatement(); ResultSet result = stat.executeQuery(sql)) {
            while (result.next()) {
                utilisateurs.add(mapResultSetToUtilisateur(result));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return utilisateurs;
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM utilisateurs WHERE id = ?";
        try (PreparedStatement prepare = con.prepareStatement(sql)) {
            prepare.setInt(1, id);
            prepare.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Integer id, Utilisateur entity) {
        String sql = "UPDATE utilisateurs SET nom = ?, prenom = ?, tel = ?, mail = ?, mot_de_passe = ?, roles = ? WHERE id = ?";
        try (PreparedStatement prepare = con.prepareStatement(sql)) {
            prepare.setString(1, entity.getNom());
            prepare.setString(2, entity.getPrenom());
            prepare.setString(3, entity.getTel());
            prepare.setString(4, entity.getMail());
            prepare.setString(5, entity.getMotDePasse());
            prepare.setString(6, entity.getRole().toString());
            prepare.setInt(7, id);
            prepare.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Utilisateur findByMail(String mail) {
        String sql = "SELECT * FROM utilisateurs WHERE mail = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, mail);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToUtilisateur(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean seConnecter(String mail, String motDePasseSaisi) {
        Utilisateur u = findByMail(mail);
        return (u != null && u.getMotDePasse().equals(motDePasseSaisi));
    }
    private Utilisateur mapResultSetToUtilisateur(ResultSet rs) throws SQLException {
        Utilisateur u = new Utilisateur();
        u.setId(rs.getInt("id"));
        u.setNom(rs.getString("nom"));
        u.setPrenom(rs.getString("prenom"));
        u.setTel(rs.getString("tel"));
        u.setMail(rs.getString("mail"));
        u.setMotDePasse(rs.getString("mot_de_passe"));
        u.setRole(RoleEnum.valueOf(rs.getString("roles").toUpperCase()));
        return u;
    }
    
    public boolean emailExiste(String mail) {
        String sql = "SELECT COUNT(*) FROM utilisateurs WHERE mail = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, mail);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    
    public boolean telephoneExiste(String tel) {
        String sql = "SELECT COUNT(*) FROM utilisateurs WHERE tel = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, tel);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}