/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import metier.CategorieProduit;

/**
 *
 * @author ilyassberradi
 */
public class MySqlCategorieProduitDAO implements CategorieProduitDAO {

    @Override
    public void insertCategorieProduit() {
        try {
            Connection conn = MySqlDAOFactory.createConnection();
            for(String s : CategorieProduit.getLesCategoriesProduit()){
                PreparedStatement st = conn.prepareStatement("insert into categorie (nom) values (?)");
                st.setString(1, s);
                st.execute();
                System.out.println(s+" saved into the database");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("unable to save the category");
        }
    }

    /*@Override
    public CategorieProduit findCategorieProduit(String nom) {
        try {
            Connection conn = MySqlDAOFactory.createConnection();
            PreparedStatement st = conn.prepareStatement("select * from categorie where nom= ?");
            st.setString(1, nom);
            ResultSet rs = st.executeQuery();
            CategorieProduit cp = CategorieProduit.getCategorieFromString(nom);
        } catch (Exception e) {
        }
    }*/
    
    
    
}
