/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import metier.CategorieProduit;
import metier.Produit;
import metier.SousCategorieAccessoires;
import metier.SousCategorieBasFemme;


/**
 *
 * @author ilyassberradi
 */
public class MySqlSousCategorieDAO implements SousCategorieDAO {

    @Override
    public void insertSousCategorie() {
        try {
            Connection conn = MySqlDAOFactory.createConnection();
            for(String s : SousCategorieAccessoires.getLesAccessoires()){
                PreparedStatement st = conn.prepareStatement("insert into souscategorie (id_categorie, nom_sous_categorie) values (?,?)");
                st.setInt(1, 1);
                st.setString(2, s);
                st.execute();
                System.out.println(s+" saved into the database");
            }
            for(String s : SousCategorieBasFemme.getLesBas()){
                PreparedStatement st = conn.prepareStatement("insert into souscategorie (id_categorie, nom_sous_categorie) values (?,?)");
                st.setInt(1, 2);
                st.setString(2, s);
                st.execute();
                System.out.println(s+" saved into the database");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("unable to save the sous-categorie");
        }
    }

    @Override
    public void findSousCategorie(CategorieProduit cat) {
        try {
            Connection conn = MySqlDAOFactory.createConnection();
            PreparedStatement st = conn.prepareStatement("select * from souscategorie where id_categorie = ?");
            st.setInt(1, getIdCategorie());
            ResultSet rs =st.executeQuery();
            
            while(rs.next()){
                System.out.printf("%d | %d | %s\n", rs.getInt("id_sous_categorie"), rs.getInt("id_categorie"), rs.getString("nom_sous_categorie"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("unable to find the product");
        }
    }
    
    
    
}
