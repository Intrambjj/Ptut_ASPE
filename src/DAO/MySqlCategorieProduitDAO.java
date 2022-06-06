/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.util.List;
import metier.CategorieProduit;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author ilyassberradi
 */
public class MySqlCategorieProduitDAO implements CategorieProduitDAO {

    @Override
    public void insertCategorieProduit(String Cat) {
        Connection conn = null;
        PreparedStatement st =null;
        try {
            conn = MySqlDAOFactory.createConnection();
            
            st = conn.prepareStatement("insert into categorie (nom) values (?)");
            st.setString(1, Cat);
            st.execute();
            System.out.println(Cat+" saved into the database");
            
            
        } catch (SQLException e) {
            while (e != null) {
                System.out.println(e.getSQLState());
                System.out.println(e.getMessage());
                System.out.println(e.getErrorCode());
                e = e.getNextException();
            }
            System.out.println("unable to save the category");
        } finally{       
            if (st != null) { try { st.close(); } catch (SQLException e) {} st = null; } 
            if (conn != null) { try { conn.close(); } catch (SQLException e) {} conn = null; } 
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

    @Override
    public List<String> listCategorieProduit() {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            conn = MySqlDAOFactory.createConnection();
            st = conn.createStatement();
            
            rs =st.executeQuery("select nom from categorie");
            List<String> l_cp = new ArrayList<String>();
            
            while(rs.next()){
                String S;
                
                S=(rs.getString(1));
                l_cp.add(S);
            }
            return l_cp;
        } catch (SQLException e) {
            while (e != null) {
                System.out.println(e.getSQLState());
                System.out.println(e.getMessage());
                System.out.println(e.getErrorCode());
                e = e.getNextException();
            }
            return null;
        } finally{
            if (rs != null) { try { rs.close(); } catch (SQLException e) {} rs = null; }
            if (st != null) { try { st.close(); } catch (SQLException e) {} st = null; } 
            if (conn != null) { try { conn.close(); } catch (SQLException e) {} conn = null; } 
        }
    }

    @Override
    public CategorieProduit findCategorieProduit(String nom) {
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement st = null;
        try {
            conn = MySqlDAOFactory.createConnection();
            st = conn.prepareStatement("select id, nom from categorie where nom=?");
            st.setString(1, nom);
            rs =st.executeQuery();
            CategorieProduit c = new CategorieProduit();
            
            if(rs.next()){
                c.setNom(rs.getString("nom"));
                c.setIDCategorie(rs.getInt("id"));
            }
            return c;
        } catch (SQLException e) {
            while (e != null) {
                System.out.println(e.getSQLState());
                System.out.println(e.getMessage());
                System.out.println(e.getErrorCode());
                e = e.getNextException();
            }
            return null;
        } finally{       
            if (rs != null) { try { rs.close(); } catch (SQLException e) {} rs = null; }
            if (st != null) { try { st.close(); } catch (SQLException e) {} st = null; } 
            if (conn != null) { try { conn.close(); } catch (SQLException e) {} conn = null;}
        }    
        
    }

    @Override
    public int deleteCategorieProduit(String cat) {
        Connection conn= null;
        PreparedStatement pst= null;
        try{
            
            conn = MySqlDAOFactory.createConnection();
            pst = conn.prepareStatement("delete from categorie where nom=?");
            pst.setString(1, cat);
            
            
            
            int n = pst.executeUpdate();
            if(n==0){
                return 0;
            }
            else{
                System.out.println(cat+" deleted from database");
                return 1;
            }
        } catch (SQLException e){
            while (e != null) {
                System.out.println(e.getSQLState());
                System.out.println(e.getMessage());
                System.out.println(e.getErrorCode());
                e = e.getNextException();
            }
            System.out.println("unable to delete the category");
            return -1;
        } finally{
            if (conn != null) { try { conn.close(); } catch (SQLException e) {} conn = null;}
            if (pst != null) { try { pst.close(); } catch (SQLException e) {} pst = null; }
        }
    }

    @Override
    public int updateCategorieProduit(String cat, int id) {
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = MySqlDAOFactory.createConnection();
            
            st = conn.prepareStatement("update categorie set nom=? where id=?");
            
            st.setString(1, cat);
            
            st.setInt(2, id);
            
            int n = st.executeUpdate();
            if(n==0){
                return 0;
            }
            else{
                System.out.println(cat +" saved into database");
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
            if (st != null) { try { st.close(); } catch (SQLException e) {} st = null; } 
        }
    }
    
    
    
}
