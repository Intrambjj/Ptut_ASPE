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
    public void insertCategorieProduit(CategorieProduit Cat) {
        Connection conn = null;
        try {
            conn = MySqlDAOFactory.createConnection();
            
            PreparedStatement st = conn.prepareStatement("insert into categorie (nom) values (?)");
            st.setString(1, Cat.getNom());
            st.execute();
            System.out.println(Cat.getNom()+" saved into the database");
            
            
        } catch (SQLException e) {
            while (e != null) {
                System.out.println(e.getSQLState());
                System.out.println(e.getMessage());
                System.out.println(e.getErrorCode());
                e = e.getNextException();
            }
            System.out.println("unable to save the category");
        } finally{       
            if (conn != null) { try { conn.close(); } catch (SQLException e) {} conn = null;} 
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
        try {
            conn = MySqlDAOFactory.createConnection();
            Statement st = conn.createStatement();
            
            ResultSet rs =st.executeQuery("select nom from categorie");
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
            if (conn != null) { try { conn.close(); } catch (SQLException e) {} conn = null;} 
        }
    }
    
    
    
}
