/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package metier;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ilyassberradi
 */
public class CategorieProduit {
    private int id;
    private String nom;
    public int getIDCategorie(){return this.id;}
    public String getNom(){return this.nom;}
    
    private static List<CategorieProduit> l_categorie = new ArrayList<CategorieProduit>();
    
    
    
    public static List<String> getLesCategoriesProduit(){
        List<String> l= new ArrayList<String>();
        for(CategorieProduit c :l_categorie){
            l.add(c.nom);
        }
        return l;
    }
    
    public static CategorieProduit getCategorieFromString(String s) {
        
        for (CategorieProduit b : l_categorie) {
            if (b.getNom().equalsIgnoreCase(s)) {
                return b;
            }
        }
        return null; 
    }
}
