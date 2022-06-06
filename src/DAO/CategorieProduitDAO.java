/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.SQLException;
import java.util.List;
import metier.CategorieProduit;


/**
 *
 * @author ilyassberradi
 */
public interface CategorieProduitDAO {
    public void insertCategorieProduit(String Cat);
    public List<String> listCategorieProduit();
    public int deleteCategorieProduit(String cat);
    public CategorieProduit findCategorieProduit(String nom);
    public int updateCategorieProduit(String cat, int id);
    //public List<CategorieProduit> selectCategorieProduit();
}
