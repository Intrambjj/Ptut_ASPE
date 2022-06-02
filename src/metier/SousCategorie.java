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
public class SousCategorie {
    private String nom_SC; 
    private int id_categorie, id_sous_categorie;
    public static List<SousCategorie> l_SousCategorie= new ArrayList<SousCategorie>();
    
    
    
    public String getNomSousCategorie(){return nom_SC;}
    public int getIDSousCategorie(){return id_sous_categorie;}
    public int getIDCategorieDeSousCategorie(){return id_categorie;}
    public void setNomSousCategorie(String nom){
        nom_SC=nom;
    }
    public void setIDSousCategorie(int n){
        id_sous_categorie=n;
    }
    public void setIDCategorieDeSousCategorie(int n){
        id_categorie=n;
    }
    
    /*public static List<String> getLesSousCategories(String categorie){
        List<String> l_SousCategorie_Categorie = new ArrayList<String>();
        
        for(SousCategorie Sc :l_SousCategorie){
            if(Sc.categorie.equals(categorie)){
                l_SousCategorie_Categorie.add(Sc.nom_SC);
                
            }
            
        }
        return l_SousCategorie_Categorie;}*/
    
    
    
}
