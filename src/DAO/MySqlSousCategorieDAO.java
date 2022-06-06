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



/**
 *
 * @author ilyassberradi
 */
public class MySqlSousCategorieDAO implements SousCategorieDAO {

    @Override
    public int insertSousCategorie(String S_cat, String cat) {
        Connection conn = null;
        try {
            int id_cat=0;
            conn = MySqlDAOFactory.createConnection();
            PreparedStatement pst = conn.prepareStatement("select id from categorie where nom=?");
            pst.setString(1, cat);
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                id_cat=rs.getInt("id");
            }
            PreparedStatement st = conn.prepareStatement("insert into souscategorie (id_categorie, nom_sous_categorie) values (?,?)");
            st.setInt(1, id_cat);
            st.setString(2, S_cat);
            st.execute();
            System.out.println(S_cat+" saved into the database");
            return id_cat;
            
            
        } catch (SQLException e) {
            while (e != null) {
                System.out.println(e.getSQLState());
                System.out.println(e.getMessage());
                System.out.println(e.getErrorCode());
                e = e.getNextException();
            }
            System.out.println("unable to save the sous-categorie");
            return -1;
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

    @Override
    public int deleteSousCategorie(String S_cat, String cat) {
        Connection conn= null;
        try{
            
            conn = MySqlDAOFactory.createConnection();
            PreparedStatement pst = conn.prepareStatement("delete from souscategorie where nom_sous_categorie=?");
            pst.setString(1, S_cat);
            
            
            
            int n = pst.executeUpdate();
            if(n==0){
                return 0;
            }
            else{
                System.out.println(S_cat+" deleted from database");
                return 1;
            }
        } catch (SQLException e){
            while (e != null) {
                System.out.println(e.getSQLState());
                System.out.println(e.getMessage());
                System.out.println(e.getErrorCode());
                e = e.getNextException();
            }
            System.out.println("unable to delete the sous-categorie");
            return -1;
        } finally{
            if (conn != null) { try { conn.close(); } catch (SQLException e) {} conn = null;}
            
        }
        
        
    }

    @Override
    public SousCategorie findSousCategorie(String S_cat) {
        Connection conn = null;
        try {
            conn = MySqlDAOFactory.createConnection();
            PreparedStatement st = conn.prepareStatement("select id_sous_categorie, nom_sous_categorie from souscategorie where nom_sous_categorie=?");
            st.setString(1, S_cat);
            ResultSet rs =st.executeQuery();
            SousCategorie s = new SousCategorie();
            
            if(rs.next()){
                s.setNomSousCategorie(rs.getString("nom_sous_categorie"));
                s.setIDSousCategorie(rs.getInt("id_sous_categorie"));
            }
            return s;
        } catch (SQLException e) {
            while (e != null) {
                System.out.println(e.getSQLState());
                System.out.println(e.getMessage());
                System.out.println(e.getErrorCode());
                e = e.getNextException();
            }
            return null;
        } finally{       
            if (conn != null) { try { conn.close(); } catch (SQLException e) {} conn = null;}
            
        }
    }

    @Override
    public int updateSousCategorie(String s, int id) {
        Connection conn = null;
        try {
            conn = MySqlDAOFactory.createConnection();
            
            PreparedStatement st = conn.prepareStatement("update souscategorie set nom_sous_categorie=? where id_sous_categorie=?");
            
            st.setString(1, s);
            
            st.setInt(2, id);
            
            int n = st.executeUpdate();
            if(n==0){
                return 0;
            }
            else{
                System.out.println(s +" saved into database");
                return 1;
            }
        } catch (SQLException e) {
            while (e != null) {
                System.out.println(e.getSQLState());
                System.out.println(e.getMessage());
                System.out.println(e.getErrorCode());
                e = e.getNextException();
            }
            System.out.println("unable to update the product");
            return -1;
        } finally{       
            if (conn != null) { try { conn.close(); } catch (SQLException e) {} conn = null;}
        }
    }
    
    
    
}
