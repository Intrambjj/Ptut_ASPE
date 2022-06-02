package metier;

import DAO.DAOFactory;
import DAO.ProduitDAO;
import java.util.ArrayList;
import java.util.List;
import metier.CategorieProduit;

/**
 * @author vde
 */
public class Produit implements Comparable<Object> {
    DAOFactory mysqlFactory = DAOFactory.getDAOFactory();
    private static List<Produit> listeDesProduits = new ArrayList<>();
    
    private int IDProduit;//N'OUBLIE PAS DE CHANGER LES PRIMITIFS EN OBJET WRAPPER!!!
    private static int dernierID = 10;
    private String libelle;
    private String categorie;
    private String sousCategorie; // gérée sous forme de String car varie selon la catégorie
    private String description;
    private boolean lot; // si vendu en lot, par exemple 3 paires de chaussettes
    private int nombreDuLot; // ex. des chaussettes : 3
    private boolean enPromo;
    private Double prixDouble;
    private Double prixPromoDouble;
    private int quantiteEnStock;
    private int seuilAlerteStock; // quantité à partir de laquelle on avertit le gestionnaire
    private String lienPhotoString;

   public Produit(){
       
   }
    
   
    
    
    /** Le tri naturel des produits ici se fait sur le libellé
     * 
     * @param o : produit à comparer (par son libellé)
     * @return true si mêmes libellés (sans la casse)
     */
    @Override
    public int compareTo(Object o) {
        Produit prod = (Produit) o;
        return this.libelle.compareToIgnoreCase(prod.getLibelle());

    }
    
    public static List<Produit> getListeDesProduits() {
        return listeDesProduits;
    }

    public int getIDProduit() {
        return IDProduit;
    }

    public void setIDProduit(int IDProduit) {
        this.IDProduit = IDProduit;
    }

    public static int getDernierID() {
        return dernierID;
    }

    public static void setDernierID(int dernierID) {
        Produit.dernierID = dernierID;
    }

    public boolean isLot() {
        return lot;
    }

    public void setLot(boolean lot) {
        this.lot = lot;
    }

    public int getNombreDuLot() {
        return nombreDuLot;
    }

    public void setNombreDuLot(int nombreDuLot) {
        this.nombreDuLot = nombreDuLot;
    }

    public boolean isEnPromo() {
        return enPromo;
    }

    public void setEnPromo(boolean enPromo) {
        this.enPromo = enPromo;
    }

    public Double getPrixPromoDouble() {
        return prixPromoDouble;
    }

    public void setPrixPromoDouble(Double prixPromoDouble) {
        this.prixPromoDouble = prixPromoDouble;
    }

    public int getQuantiteEnStock() {
        return quantiteEnStock;
    }

    public void setQuantiteEnStock(int quantiteEnStock) {
        this.quantiteEnStock = quantiteEnStock;
    }

    public int getSeuilAlerteStock() {
        return seuilAlerteStock;
    }

    public void setSeuilAlerteStock(int seuilAlerteStock) {
        this.seuilAlerteStock = seuilAlerteStock;
    }

    public String getLienPhotoString() {
        return lienPhotoString;
    }

    public void setLienPhotoString(String lienPhotoString) {
        this.lienPhotoString = lienPhotoString;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getSousCategorie() {
        return sousCategorie;
    }

    public void setSousCategorie(String sousCategorie) {
        this.sousCategorie = sousCategorie;
    }

    public Double getPrix() {
        return prixDouble;
    }

    public void setPrix(Double prix) {
        this.prixDouble = prix;
    }

}
