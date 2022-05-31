package metier;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vde
 */
public enum SousCategorieBasHomme {
    
    SHO("Shorts Homme"),
    PAN("Pantalons Homme"),
    JEA("Jeans Homme");
    
    private final String nom;

    public String getNom() {
        return nom;
    }
    
    SousCategorieBasHomme(String nom) {
        this.nom = nom;
    }
    
     // retourne la liste de tous les noms de la catégorie : "Bas Homme"
    public static List<String> getLesBas() {
        List<String> liste = new ArrayList<>();
        
        for(SousCategorieBasHomme cat: SousCategorieBasHomme.values()) {
            liste.add(cat.getNom());
        }

        return liste;
    }
}
