/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.util.List;
import metier.CategorieProduit;
import metier.SousCategorie;


/**
 *
 * @author ilyassberradi
 */
public interface SousCategorieDAO {
    public void insertSousCategorie();
    //public boolean deleteSousCategorie();
    public List<SousCategorie> findSousCategoriesByCategorie(CategorieProduit cat);
    //public boolean updateSousCategorie();
    //public List<SousCategorie> selectSousCategorie();
}
