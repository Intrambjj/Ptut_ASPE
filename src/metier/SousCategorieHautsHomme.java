package metier;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vde
 */
public enum SousCategorieHautsHomme {
    
    CHE("Chemise"),
    PUL("Pull / SweatShirt"),
    VES("Vestes"),
    DEB("Débardeurs");
    
    private final String nom;

    public String getNom() {
        return nom;
    }
    
    SousCategorieHautsHomme(String nom) {
        this.nom = nom;
    }
    
     // retourne la liste de tous les noms de la catégorie : "Hauts Homme"
    public static List<String> getLesHautsHommes() {
        List<String> liste = new ArrayList<>();
        
        for(SousCategorieHautsHomme cat: SousCategorieHautsHomme.values()) {
            liste.add(cat.getNom());
        }

        return liste;
    }
}