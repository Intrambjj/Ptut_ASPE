package metier;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vde
 */
public enum SousCategorieRobesFemme {
    
    SOI("Robe de Soirée"),
    COU("Robes légères courtes"),
    HAB("Robes habillées");
    
    private final String nom;

    public String getNom() {
        return nom;
    }
    
    SousCategorieRobesFemme(String nom) {
        this.nom = nom;
    }
    
     // retourne la liste de tous les noms de la catégorie : "Robes Femme"
    public static List<String> getLesRobes() {
        List<String> liste = new ArrayList<>();
        
        for(SousCategorieRobesFemme cat: SousCategorieRobesFemme.values()) {
            liste.add(cat.getNom());
        }

        return liste;
    }
}
