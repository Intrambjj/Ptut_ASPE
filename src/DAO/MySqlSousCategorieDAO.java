/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import metier.CategorieProduit;
import metier.Produit;
import metier.SousCategorie;
import metier.SousCategorieAccessoires;
import metier.SousCategorieBasFemme;


/**
 *
 * @author ilyassberradi
 */
public class MySqlSousCategorieDAO implements SousCategorieDAO {

    @Override
    public void insertSousCategorie(SousCategorie S_cat) {
        Connection conn = null;
        try {
            conn = MySqlDAOFactory.createConnection();
            
            PreparedStatement st = conn.prepareStatement("insert into souscategorie (id_categorie, nom_sous_categorie) values (?,?)");
            st.setInt(1, S_cat.getIDCategorieDeSousCategorie());
            st.setString(2, S_cat.getNomSousCategorie());
            st.execute();
            System.out.println(S_cat.getNomSousCategorie()+" saved into the database");
            
            
            
        } catch (SQLException e) {
            while (e != null) {
                System.out.println(e.getSQLState());
                System.out.println(e.getMessage());
                System.out.println(e.getErrorCode());
                e = e.getNextException();
            }
            System.out.println("unable to save the sous-categorie");
        } finally{       
            if (conn != null) { try { conn.close(); } catch (SQLException e) {} conn = null;} 
        }
    }

    @Override
    public List<String> findSousCategoriesByCategorie(String s_cat){
        Connection conn = null;
        try {
            conn = MySqlDAOFactory.createConnection();
            PreparedStatement st = conn.prepareStatement("select nom_sous_categorie from souscategorie sc, categorie c where c.nom = ? and c.id=sc.id_categorie");
            st.setString(1, s_cat);
            ResultSet rs =st.executeQuery();
            List<String> l_sc = new ArrayList<String>();
            
            while(rs.next()){
                String s = new String(rs.getString("nom_sous_categorie"));
                
                l_sc.add(s);
            }
            return l_sc;
        } catch (SQLException e) {
            while (e != null) {
                System.out.println(e.getSQLState());
                System.out.println(e.getMessage());
                System.out.println(e.getErrorCode());
                e = e.getNextException();
            }
            System.out.println("unable to find the product");
            return null;
        } finally{       
            if (conn != null) { try { conn.close(); } catch (SQLException e) {} conn = null;} 
        }
    }
    
    
    
}
