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
    public void insertProduit(Produit prod) {
        try{
            Connection conn = MySqlDAOFactory.createConnection();
            PreparedStatement st = conn.prepareStatement("insert into product (id, nom, prix, descrption, stock, seuil_alerte) values (?,?,?,?,?,?)");
            st.setInt(1, prod.getIDProduit());
            st.setString(2, prod.getLibelle());
            
            st.setDouble(3, prod.getPrix());
            st.setString(4, prod.getDescription());
            st.setInt(5, prod.getQuantiteEnStock());
            st.setInt(6, prod.getSeuilAlerteStock());
            st.execute();
            System.out.println(prod.getLibelle()+" saved into database");
        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("unable to insert the product");
        }
    }    
    @Override
    public void updateProduit(int id, String NewNom, double newPrix, String NewDescription, int newStock, int newSeuil) {
        try {
            Connection conn = MySqlDAOFactory.createConnection();
            PreparedStatement st = conn.prepareStatement("update product set nom=?, prix=?, descrption=?, stock=?, seuil_alerte=? where id=?");
            
            st.setString(1, NewNom);
            
            st.setDouble(2, newPrix);
            st.setString(3, NewDescription);
            st.setInt(4, newStock);
            st.setInt(5, newSeuil);
            st.setInt(6, id);
            st.execute();
            System.out.println(NewNom +" saved into database");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("unable to update the product");
        }
                           
    }

    @Override
    public Produit findProduitById(int id) {
        try {
            Connection conn = MySqlDAOFactory.createConnection();
            PreparedStatement st = conn.prepareStatement("select * from product where id = ?");
            st.setInt(1, id);
            ResultSet rs =st.executeQuery();
            Produit p = new Produit();
            
            while(rs.next()){
                p.setIDProduit(rs.getInt("id"));
                p.setLibelle(rs.getString("nom"));
                p.setPrix(rs.getDouble("prix"));
                p.setDescription(rs.getString("description"));
                p.setQuantiteEnStock(rs.getInt("stock"));
                p.setSeuilAlerteStock(rs.getInt("seuil_alerte"));
                
            }
            return p;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Produit> findProduitByLibelle(String Libelle) {
         try {
            Connection conn = MySqlDAOFactory.createConnection();
            PreparedStatement st = conn.prepareStatement("select * from product where nom = ?");
            st.setString(1, Libelle);
            ResultSet rs =st.executeQuery();
            List<Produit> l_p = new ArrayList<Produit>();
            Produit p = new Produit();
            
            while(rs.next()){
                p.setIDProduit(rs.getInt("id"));
                p.setLibelle(rs.getString("nom"));
                p.setPrix(rs.getDouble("prix"));
                p.setDescription(rs.getString("description"));
                p.setQuantiteEnStock(rs.getInt("stock"));
                p.setSeuilAlerteStock(rs.getInt("seuil_alerte"));
                l_p.add(p)
            }
            return p;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    
    
}
