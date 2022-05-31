package metier;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vde
 */
public enum SousCategorieHautsFemme {
    
    CHE("Chemisiers / Blouses"),
    PUL("Pull / SweatShirt"),
    VES("Vestes / Boléros"),
    DEB("Débardeurs");
    
    private final String nom;

    public String getNom() {
        return nom;
    }
    
    SousCategorieHautsFemme(String nom) {
        this.nom = nom;
    }
    
     // retourne la liste de tous les noms de la catégorie : "Hauts Femme"
    public static List<String> getLesHautsFemmes() {
        List<String> liste = new ArrayList<>();
        
        for(SousCategorieHautsFemme cat: SousCategorieHautsFemme.values()) {
            liste.add(cat.getNom());
        }

        return liste;
    }
}
