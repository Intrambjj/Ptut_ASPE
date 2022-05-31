package metier;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vde
 */
public enum SousCategorieAccessoires {
    
    SAC("Sacs"),
    SET("Chaussettes"),
    BAS("Baskets / Chaussures"),
    CEI("Ceintures"),
    CHA("Chapeaux / Casquettes");
    
    private final String nom;

    public String getNom() {
        return nom;
    }
    
    SousCategorieAccessoires(String nom) {
        this.nom = nom;
    }
    
     // retourne la liste de tous les noms de la catégorie : "accessoires"
    public static List<String> getLesAccessoires() {
        List<String> liste = new ArrayList<>();
        
        for(SousCategorieAccessoires cat: SousCategorieAccessoires.values()) {
            liste.add(cat.getNom());
        }

        return liste;
    }
}
