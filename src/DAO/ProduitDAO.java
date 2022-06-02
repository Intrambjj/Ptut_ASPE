/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.SQLException;
import java.util.List;
import metier.Produit;

/**
 *
 * @author ilyassberradi
 */
public interface ProduitDAO {
    
    public void insertProduit(Produit produit);
    //public boolean deleteProduit();
    public Produit findProduitById(int id);
    public List<Produit> findProduitByLibelle(String Libelle);
    public void updateProduit(int id, String NewNom, double newPrix, String NewDescription, int newStock, int newSeuil);
    public List<Produit> findProduitBySousCategorie(String sous_cat);
    
    
}
