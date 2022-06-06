/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import metier.Produit;
import java.sql.PreparedStatement;
import java.sql.*;
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
        PreparedStatement pst= null;
        ResultSet rs=null;
        PreparedStatement st=null;
        Statement stat=null;
        ResultSet rst=null;
        PreparedStatement psta=null;
        try{
            int id_sc=0;
            int id_c=0;
            int lastid=0;
            conn = MySqlDAOFactory.createConnection();
            pst = conn.prepareStatement("select id_sous_categorie, id_categorie from souscategorie where UPPER(nom_sous_categorie)=UPPER(?)");
            pst.setString(1, prod.getSousCategorie());
            rs = pst.executeQuery();
            if(rs.next()){
                id_sc=rs.getInt("id_sous_categorie");
                id_c=rs.getInt("id_categorie");
            }
            
            st = conn.prepareStatement("insert into product (id, nom, prix, description, stock, seuil_alerte, id_sous_categorie, marque, image1) values (?,?,?,?,?,?,?,?,?)");
            st.setInt(1, prod.getIDProduit());
            st.setString(2, prod.getModele());
            
            st.setDouble(3, prod.getPrix());
            st.setString(4, prod.getDescription());
            st.setInt(5, prod.getQuantiteEnStock());
            st.setInt(6, prod.getSeuilAlerteStock());
            st.setInt(7, id_sc);
            st.setString(8, prod.getLibelle());
            st.setString(9, "");
            st.execute();
            
            stat = conn.createStatement();
            
            rst= stat.executeQuery("select last_insert_id() from product");
            if(rst.next())               
                lastid = rst.getInt("last_insert_id()");
            
            psta = conn.prepareStatement("insert into produitcategorie (id_produit, id_categorie) values (?,?)");
            psta.setInt(1, lastid);
            psta.setInt(2, id_c);
            psta.execute();
            System.out.println(prod.getModele()+" saved into database");
        } catch (SQLException e){
            while (e != null) {
                System.out.println(e.getSQLState());
                System.out.println(e.getMessage());
                System.out.println(e.getErrorCode());
                e = e.getNextException();
            }
            System.out.println("unable to insert the product");
        } finally{
            if (rs != null) { try { rs.close(); } catch (SQLException e) {} rs = null; }
            if (st != null) { try { st.close(); } catch (SQLException e) {} st = null; } 
            if (conn != null) { try { conn.close(); } catch (SQLException e) {} conn = null;}
            if (psta != null) { try { psta.close(); } catch (SQLException e) {} psta = null; }
            if (pst != null) { try { pst.close(); } catch (SQLException e) {} pst = null; } 
            if (stat != null) { try { stat.close(); } catch (SQLException e) {} stat = null;}
            if (rst != null) { try { rst.close(); } catch (SQLException e) {} rst = null;}
        }
    }    
    @Override
    public int updateProduit(int id, String NewModele, String sous_categorie, double newPrix, String NewDescription, int newStock, int newSeuil, String newLibelle){
        Connection conn = null;
        PreparedStatement pst=null;
        ResultSet rs=null;
        PreparedStatement st=null;
        try {
            int id_sc=0;
            conn = MySqlDAOFactory.createConnection();
            pst = conn.prepareStatement("select id_sous_categorie from souscategorie where UPPER(nom_sous_categorie)=UPPER(?)");
            
            pst.setString(1, sous_categorie);
            rs = pst.executeQuery();
            if(rs.next()){
                id_sc=rs.getInt("id_sous_categorie");
            }
            st = conn.prepareStatement("update product set nom=?, prix=?, description=?, stock=?, seuil_alerte=?, id_sous_categorie=?, marque=? where id=?");
            
            st.setString(1, NewModele);
            
            st.setDouble(2, newPrix);
            st.setString(3, NewDescription);
            st.setInt(4, newStock);
            st.setInt(5, newSeuil);
            st.setInt(6, id_sc);
            st.setString(7, newLibelle);
            st.setInt(8, id);
            int n = st.executeUpdate();
            if(n==0){
                return 0;
            }
            else{
                System.out.println(NewModele +" saved into database");
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
            if (pst != null) { try { pst.close(); } catch (SQLException e) {} pst = null;}
            if (rs != null) { try { rs.close(); } catch (SQLException e) {} rs = null;}
            if (st != null) { try { st.close(); } catch (SQLException e) {} st = null;}
        }
                           
    }

    @Override
    public int deleteProduit(int id) {
        Connection conn= null;
        PreparedStatement pst=null;
        PreparedStatement st=null;
        try{
            
            conn = MySqlDAOFactory.createConnection();
            pst = conn.prepareStatement("delete from produitcategorie where id_produit=?");
            pst.setInt(1, id);
            pst.execute();
            st = conn.prepareStatement("delete from product where id=?");
            st.setInt(1, id);
            
            int n = st.executeUpdate();
            if(n==0){
                return 0;
            }
            else{
                System.out.println("Product with id="+id+" deleted from database");
                return 1;
            }
        } catch (SQLException e){
            while (e != null) {
                System.out.println(e.getSQLState());
                System.out.println(e.getMessage());
                System.out.println(e.getErrorCode());
                e = e.getNextException();
            }
            System.out.println("unable to delete the product");
            return -1;
        } finally{
            if (conn != null) { try { conn.close(); } catch (SQLException e) {} conn = null;}
            if (pst != null) { try { pst.close(); } catch (SQLException e) {} pst = null;}
            if (st != null) { try { st.close(); } catch (SQLException e) {} st = null;}
        }
    }
    
    

    @Override
    public Produit findProduitById(int id){
        Connection conn = null;
        PreparedStatement st=null;
        ResultSet rs=null;
        try {
            conn = MySqlDAOFactory.createConnection();
            st = conn.prepareStatement("select * from product p, produitcategorie, categorie c where p.id = ? and p.id=id_produit and id_categorie=c.id");
            st.setInt(1, id);
            rs =st.executeQuery();
            Produit p = new Produit();
            
            while(rs.next()){
                p.setIDProduit(rs.getInt("p.id"));
                p.setLibelle(rs.getString("p.marque"));
                p.setModele(rs.getString("p.nom"));
                p.setPrix(rs.getDouble("prix"));
                p.setCategorie(rs.getString("c.nom"));
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
            if (st != null) { try { st.close(); } catch (SQLException e) {} st = null;}
            if (rs != null) { try { rs.close(); } catch (SQLException e) {} rs = null;}
        }
    }

    @Override
    public List<Produit> findProduitByLibelle(String Libelle){
        Connection conn=null;
        PreparedStatement st=null;
        ResultSet rs=null;
        try {
            conn = MySqlDAOFactory.createConnection();
            st = conn.prepareStatement("select * from product p, produitcategorie pcat, categorie c where UPPER(p.marque) = UPPER(?) and c.id=id_categorie and id_produit=p.id");
            st.setString(1, Libelle);
            rs =st.executeQuery();
            List<Produit> l_p = new ArrayList<Produit>();
            
            while(rs.next()){
                Produit p = new Produit();
                p.setIDProduit(rs.getInt("p.id"));
                p.setLibelle(rs.getString("p.marque"));
                p.setModele(rs.getString("p.nom"));
                p.setCategorie(rs.getString("c.nom"));
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
            if (rs != null) { try { rs.close(); } catch (SQLException e) {} rs = null;}
            if (st != null) { try { st.close(); } catch (SQLException e) {} st = null;}
        }
        
    }

    @Override
    public List<Produit> findProduitBySousCategorie(String sous_cat) {
        Connection conn=null;
        PreparedStatement pst=null;
        ResultSet rst=null;
        PreparedStatement st=null;
        ResultSet rs=null;
        try {
            int id_sc=0;
            conn = MySqlDAOFactory.createConnection();
            pst = conn.prepareStatement("select id_sous_categorie from souscategorie where UPPER(nom_sous_categorie)=UPPER(?)");
            pst.setString(1, sous_cat);
            rst = pst.executeQuery();
            if(rst.next()){
                id_sc=rst.getInt("id_sous_categorie");
            }
            
            st = conn.prepareStatement("select * from product p, souscategorie sc, categorie c where p.id_sous_categorie = ? and p.id_sous_categorie=sc.id_sous_categorie and id_categorie=c.id");
            st.setInt(1, id_sc);
            rs =st.executeQuery();
            List<Produit> l_p = new ArrayList<Produit>();
            
            while(rs.next()){
                Produit p = new Produit();
                p.setIDProduit(rs.getInt("id"));
                p.setLibelle(rs.getString("marque"));
                p.setModele(rs.getString("p.nom"));
                p.setCategorie(rs.getString("c.nom"));
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
            if (pst != null) { try { pst.close(); } catch (SQLException e) {} pst = null;}
            if (rst != null) { try { rst.close(); } catch (SQLException e) {} rst = null;}
            if (st != null) { try { st.close(); } catch (SQLException e) {} st = null;}
            if (rs != null) { try { rs.close(); } catch (SQLException e) {} rs = null;}
        }
    }
    
    
    
    
    
}
