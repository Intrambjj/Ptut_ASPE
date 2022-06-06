/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.SQLException;
import java.util.List;
import metier.CategorieProduit;
import metier.SousCategorie;


/**
 *
 * @author ilyassberradi
 */
public interface SousCategorieDAO {
    public int insertSousCategorie(String S_cat, String cat);
    public int deleteSousCategorie(String S_cat, String cat);
    public SousCategorie findSousCategorie(String S_cat);
    //public boolean deleteSousCategorie();
    public List<String> findSousCategoriesByCategorie(String s_cat);
    public int updateSousCategorie(String s, int id);
    //public List<SousCategorie> selectSousCategorie();
}
