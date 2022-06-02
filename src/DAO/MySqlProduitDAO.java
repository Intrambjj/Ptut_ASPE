/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import metier.Produit;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author ilyassberradi
 */
public class MySqlProduitDAO implements ProduitDAO {

    @Override
    public void insertProduit(Produit prod){
        Connection conn= null;
        try{
            conn = MySqlDAOFactory.createConnection();
            PreparedStatement st = conn.prepareStatement("insert into product (id, nom, prix, description, stock, seuil_alerte, image1, id_sous_categorie) values (?,?,?,?,?,?,?,?)");
            st.setInt(1, prod.getIDProduit());
            st.setString(2, prod.getLibelle());
            
            st.setDouble(3, prod.getPrix());
            st.setString(4, prod.getDescription());
            st.setInt(5, prod.getQuantiteEnStock());
            st.setInt(6, prod.getSeuilAlerteStock());
            st.setString(7, prod.getLienPhotoString());
            st.setString(8, prod.getSousCategorie());
            st.execute();
            System.out.println(prod.getLibelle()+" saved into database");
        } catch (SQLException e){
            while (e != null) {
                System.out.println(e.getSQLState());
                System.out.println(e.getMessage());
                System.out.println(e.getErrorCode());
                e = e.getNextException();
            }
            System.out.println("unable to insert the product");
        } finally{
            if (conn != null) { try { conn.close(); } catch (SQLException e) {} conn = null;}
            
        }
    }    
    @Override
    public void updateProduit(int id, String NewNom, double newPrix, String NewDescription, int newStock, int newSeuil){
        Connection conn = null;
        try {
            conn = MySqlDAOFactory.createConnection();
            PreparedStatement st = conn.prepareStatement("update product set nom=?, prix=?, description=?, stock=?, seuil_alerte=? where id=?");
            
            st.setString(1, NewNom);
            
            st.setDouble(2, newPrix);
            st.setString(3, NewDescription);
            st.setInt(4, newStock);
            st.setInt(5, newSeuil);
            st.setInt(6, id);
            st.execute();
            System.out.println(NewNom +" saved into database");
        } catch (SQLException e) {
            while (e != null) {
                System.out.println(e.getSQLState());
                System.out.println(e.getMessage());
                System.out.println(e.getErrorCode());
                e = e.getNextException();
            }
            System.out.println("unable to update the product");
        } finally{       
            if (conn != null) { try { conn.close(); } catch (SQLException e) {} conn = null;}
        }
                           
    }

    @Override
    public Produit findProduitById(int id){
        Connection conn = null;
        try {
            conn = MySqlDAOFactory.createConnection();
            PreparedStatement st = conn.prepareStatement("select * from product p, produitcategorie, categorie c where p.id = ? and p.id=id_produit and id_categorie=c.id");
            st.setInt(1, id);
            ResultSet rs =st.executeQuery();
            Produit p = new Produit();
            
            while(rs.next()){
                p.setIDProduit(rs.getInt("p.id"));
                p.setLibelle(rs.getString("p.nom"));
                p.setPrix(rs.getDouble("prix"));
                p.setCategorie(rs.getString("c.id"));
                p.setDescription(rs.getString("description"));
                p.setQuantiteEnStock(rs.getInt("stock"));
                p.setSeuilAlerteStock(rs.getInt("seuil_alerte"));
                
            }
            return p;
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
    public List<Produit> findProduitByLibelle(String Libelle){
        Connection conn=null;
        try {
            conn = MySqlDAOFactory.createConnection();
            PreparedStatement st = conn.prepareStatement("select * from product where UPPER(nom) = UPPER(?)");
            st.setString(1, Libelle);
            ResultSet rs =st.executeQuery();
            List<Produit> l_p = new ArrayList<Produit>();
            
            while(rs.next()){
                Produit p = new Produit();
                p.setIDProduit(rs.getInt("id"));
                p.setLibelle(rs.getString("nom"));
                p.setPrix(rs.getDouble("prix"));
                p.setDescription(rs.getString("description"));
                p.setQuantiteEnStock(rs.getInt("stock"));
                p.setSeuilAlerteStock(rs.getInt("seuil_alerte"));
                l_p.add(p);
            }
            return l_p;
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
    public List<Produit> findProduitBySousCategorie(String sous_cat) {
        Connection conn=null;
        try {
            conn = MySqlDAOFactory.createConnection();
            PreparedStatement st = conn.prepareStatement("select * from product p, souscategorie sc, categorie c where UPPER(nom_sous_categorie) = UPPER(?) and p.id_sous_categorie=sc.id_sous_categorie");
            st.setString(1, sous_cat);
            ResultSet rs =st.executeQuery();
            List<Produit> l_p = new ArrayList<Produit>();
            
            while(rs.next()){
                Produit p = new Produit();
                p.setIDProduit(rs.getInt("id"));
                p.setLibelle(rs.getString("nom"));
                p.setCategorie(rs.getString(sous_cat));
                p.setPrix(rs.getDouble("prix"));
                p.setDescription(rs.getString("description"));
                p.setQuantiteEnStock(rs.getInt("stock"));
                p.setSeuilAlerteStock(rs.getInt("seuil_alerte"));
                l_p.add(p);
            }
            return l_p;
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
