package metier;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vde
 */
public enum SousCategorieBasFemme {
    
    SHO("Shorts Femme"),
    JUP("Jupes Femme"),
    PAN("Pantalons Femme"),
    JEA("Jeans Femme");
    
    private final String nom;

    public String getNom() {
        return nom;
    }
    
    SousCategorieBasFemme(String nom) {
        this.nom = nom;
    }
    
     // retourne la liste de tous les noms de la catégorie : "Bas Femme"
    public static List<String> getLesBas() {
        List<String> liste = new ArrayList<>();
        
        for(SousCategorieBasFemme cat: SousCategorieBasFemme.values()) {
            liste.add(cat.getNom());
        }

        return liste;
    }
}
