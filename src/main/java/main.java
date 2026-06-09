package main.java;

import main.java.Modele.Admins;
import main.java.Service.UtilisateurImplService;
import main.java.Service.interfaces.UtilisateurInService;
import main.java.enumeration.RoleEnum;

public class main {
    System.gc();
   public static void main(String[] args) {
    UtilisateurInService utilisateurInService = new  UtilisateurImplService();
    /*   Admins utl = new Admins();


       utl.setNom("MED");
       utl.setPrenom("Big noss");
       utl.setTel("82000");
       utl.setMotDePasse("0DH0");
       utl.setMail("D@zDyyaY");
       utl.setRole(RoleEnum.PARENT);

       utilisateurInService.inscriptionAdmin(utl);

       System.out.println(utl.toString());

*/

    /*try {
            Connection con = ConnexionDB.getConexion();
            if (con != null) {
                Statement pont = con.createStatement();
                String sql = "INSERT INTO capacite VALUES (NULL, 'Emotionnelle')";
                int lignesAffectees = pont.executeUpdate(sql);

                System.out.println("Insertion réussie, lignes modifiées : " + lignesAffectees);
                pont.close();
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
}
}
