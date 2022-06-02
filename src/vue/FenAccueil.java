/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vue;

import DAO.CategorieProduitDAO;
import DAO.DAOFactory;
import DAO.MySqlDAOFactory;
import DAO.MySqlProduitDAO;
import DAO.ProduitDAO;
import DAO.SousCategorieDAO;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import metier.Produit;
import metier.SousCategorieAccessoires;
import metier.SousCategorieBasFemme;
import metier.SousCategorieBasHomme;
import metier.SousCategorieHautsFemme;
import metier.SousCategorieHautsHomme;
import metier.SousCategorieRobesFemme;
import java.lang.String;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.ListIterator;
import javax.swing.JButton;
import metier.CategorieProduit;
import javax.swing.JComboBox;
//import static metier.CategorieProduit.getCategorieFromString;
import metier.SousCategorie;
import java.sql.SQLException;

/**
 *
 * @author ilyassberradi
 */
public class FenAccueil extends javax.swing.JFrame {

    /**
     * Creates new form FenAccueil
     */
    private ComboBoxModel<String> modeleDesSousCategories;
    private ComboBoxModel<String> modeleDesCategories;
    private ComboBoxModel<String> modeleDesCategories2;
    private static List<Produit> lesProduits;
    private List<Produit> lesProduitsTrouves;
    private ListIterator<Produit> it;
    DAOFactory mysqlFactory = DAOFactory.getDAOFactory();
    
        
    public FenAccueil() {
        CategorieProduitDAO CatDAO = mysqlFactory.getCategorieProduitDAO();
        
        
        
        
        Object[] tabCategories = CatDAO.listCategorieProduit().toArray();
        modeleDesCategories=new DefaultComboBoxModel(tabCategories);
        modeleDesSousCategories = new DefaultComboBoxModel<String>();
        modeleDesCategories2=new DefaultComboBoxModel(tabCategories);
        
        //Q2) On voit afficher <User Code>
    //Q3)L'un de ses events change et ne reste plus <none>
        initComponents();
        butt_suivant.setVisible(false);
        butt_precedent.setVisible(false);
        /*Produit p1 = new Produit("Tunique blanche à fleurs manches longues",
                CategorieProduit.HFE, "Chemisiers / Blouses",
                "Tissu coton bio ajouré avec dentelles", false, 1, false, 42.50, 35.00,
                50, 10, "blouse-femme-blanche.jpg");

        Produit p2 = new Produit("Pantalon shino beige",
                CategorieProduit.BHO, "Pantalons Homme",
                "Tissu coton bio Colombie", false, 1, false, 98.20, 55.00,
                90, 20, "pantalons-homme-beige.png");

        Produit p3 = new Produit("Chaussettes laine grise",
                CategorieProduit.ACC, "Chaussettes",
                "Laine bio d'Auvergne", true, 3, true, 38.90, 25.00,
                100, 30, "");*/
        lesProduits = Produit.getListeDesProduits();
        Collections.sort(lesProduits);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    }

    
    private void affecterDetailCBBox(String ch, JComboBox cbb) {
        String tabNoms[];
        String Item_categorie=(String) cbb_categorie.getSelectedItem();
        switch(Item_categorie){
            case "Accessoires":
                tabNoms=new String[SousCategorie.getLesSousCategories("Accessoires").size()];
                tabNoms= SousCategorie.getLesSousCategories("Accessoires").toArray(tabNoms);
                break;
                
            case "Vetements":
                tabNoms=new String[SousCategorie.getLesSousCategories("Vetements").size()];
                tabNoms= SousCategorie.getLesSousCategories("Vetements").toArray(tabNoms);
                break;
                
            case "Chaussures":
                tabNoms=new String[SousCategorie.getLesSousCategories("Chaussures").size()];
                tabNoms= SousCategorie.getLesSousCategories("Chaussures").toArray(tabNoms);
                
                break;
                
            default:
                System.out.println("Choix incorrect");
                tabNoms=null;
                break;
        }
        modeleDesSousCategories= new DefaultComboBoxModel(tabNoms);
        cbb_sousCategorie.setModel(modeleDesSousCategories);
        /*
        // cette méthode affecte la bonne liste de sous-catégories à la CBBox
        Object[] tabNoms;
        boolean flagErreurSousCategorie = false;

        CategorieProduit cat = CategorieProduit.getCategorieFromString(ch);

        switch (cat) {
            case ACC:
                tabNoms = SousCategorieAccessoires.getLesAccessoires().toArray();
                cbBoxSousCategorieModele = new DefaultComboBoxModel(tabNoms);
                // cbBoxSousCategorieModele = new DefaultComboBoxModel(SousCategorieAccessoires.values());
                // (renvoie les VALEURS de l'énumeration)
                break;
            case HHO:
                tabNoms = SousCategorieHautsHomme.getLesHautsHommes().toArray();
                cbBoxSousCategorieModele = new DefaultComboBoxModel(tabNoms);
                break;
            case HFE:
                tabNoms = SousCategorieHautsFemme.getLesHautsFemmes().toArray();
                cbBoxSousCategorieModele = new DefaultComboBoxModel(tabNoms);
                break;
            case BFE:
                tabNoms = SousCategorieBasFemme.getLesBas().toArray();
                cbBoxSousCategorieModele = new DefaultComboBoxModel(tabNoms);
                break;
            case RFE:
                tabNoms = SousCategorieRobesFemme.getLesRobes().toArray();
                cbBoxSousCategorieModele = new DefaultComboBoxModel(tabNoms);
                break;
            case BHO:
                tabNoms = SousCategorieBasHomme.getLesBas().toArray();
                cbBoxSousCategorieModele = new DefaultComboBoxModel(tabNoms);
                break;
            default:
                flagErreurSousCategorie = true;
                tabNoms = new Object[]{"", "", "", "", ""};
                cbBoxSousCategorieModele = new DefaultComboBoxModel(tabNoms);
                break;
        }
        cbb.setModel(cbBoxSousCategorieModele);

        if (!flagErreurSousCategorie) {
            cbb.setVisible(true);
        } else {
            cbb.setVisible(false);
        }*/
    }
    
    
    /*private void resultatRecherche(int i){
        if(!(tf_id_rech.getText().equals("")) && lesProduits.get(i).getIDProduit()==Integer.parseInt(tf_id_rech.getText())){
                tf_id_pd.setText(tf_id_rech.getText());
                cbb_categorie_pd.setSelectedItem(lesProduits.get(i).getCategorie());
                tf_libelle_pd.setText(lesProduits.get(i).getLibelle());
                ta_description.setText(lesProduits.get(i).getDescription());
                tf_prix.setText(Double.toString(lesProduits.get(i).getPrix()));
                tf_prix_reduit.setText(Double.toString(lesProduits.get(i).getPrixPromoDouble()));
                if(lesProduits.get(i).isEnPromo())
                    tf_promotion.setText("Oui");
                else
                    tf_promotion.setText("Non");
                tf_qte.setText(Integer.toString(lesProduits.get(i).getQuantiteEnStock()));
                tf_seuil.setText(Integer.toString(lesProduits.get(i).getSeuilAlerteStock()));
                i++;
            }
            else if(tf_libelle.getText().equalsIgnoreCase(lesProduits.get(i).getLibelle())) {                 
                tf_id_pd.setText(Integer.toString(lesProduits.get(i).getIDProduit()));
                cbb_categorie_pd.setSelectedItem(lesProduits.get(i).getCategorie());
                tf_libelle_pd.setText(lesProduits.get(i).getLibelle());
                ta_description.setText(lesProduits.get(i).getDescription());
                tf_prix.setText(Double.toString(lesProduits.get(i).getPrix()));
                tf_prix_reduit.setText(Double.toString(lesProduits.get(i).getPrixPromoDouble()));
                if(lesProduits.get(i).isEnPromo())
                    tf_promotion.setText("Oui");
                else
                    tf_promotion.setText("Non");
                tf_qte.setText(Integer.toString(lesProduits.get(i).getQuantiteEnStock()));
                tf_seuil.setText(Integer.toString(lesProduits.get(i).getSeuilAlerteStock()));
                i++;
            }
            else
                System.out.println("Exception");
    }*/

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    
   
    
   
     
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        pannProduits = new javax.swing.JPanel();
        pannRechercheProduit = new javax.swing.JPanel();
        lbl_libelle = new javax.swing.JLabel();
        tf_libelle = new javax.swing.JTextField();
        lbl_categorie = new javax.swing.JLabel();
        cbb_categorie = new javax.swing.JComboBox<>();
        cbb_sousCategorie = new javax.swing.JComboBox<>();
        bt_effacer = new javax.swing.JButton();
        bt_rechercher = new javax.swing.JButton();
        jLabel35 = new javax.swing.JLabel();
        tf_id_rech = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tf_id_pd = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        cbb_categorie_pd = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        cbb_sousCategorie_pd = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        tf_libelle_pd = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ta_description = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        tf_prix = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        tf_prix_reduit = new javax.swing.JTextField();
        tf_promotion = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        tf_seuil = new javax.swing.JTextField();
        tf_qte = new javax.swing.JTextField();
        butt_modifier = new javax.swing.JButton();
        butt_ajouter = new javax.swing.JButton();
        butt_precedent = new javax.swing.JButton();
        butt_suivant = new javax.swing.JButton();
        pannPromotions = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jTextField11 = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jTextField12 = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jComboBox5 = new javax.swing.JComboBox<>();
        jLabel19 = new javax.swing.JLabel();
        jComboBox6 = new javax.swing.JComboBox<>();
        jComboBox7 = new javax.swing.JComboBox<>();
        jLabel20 = new javax.swing.JLabel();
        jComboBox8 = new javax.swing.JComboBox<>();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jComboBox16 = new javax.swing.JComboBox<>();
        jComboBox17 = new javax.swing.JComboBox<>();
        jPanel6 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jComboBox9 = new javax.swing.JComboBox<>();
        jTextField13 = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jComboBox10 = new javax.swing.JComboBox<>();
        jLabel24 = new javax.swing.JLabel();
        jComboBox11 = new javax.swing.JComboBox<>();
        jComboBox12 = new javax.swing.JComboBox<>();
        jComboBox13 = new javax.swing.JComboBox<>();
        jComboBox14 = new javax.swing.JComboBox<>();
        jComboBox15 = new javax.swing.JComboBox<>();
        jLabel25 = new javax.swing.JLabel();
        jTextField14 = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jTextField15 = new javax.swing.JTextField();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        pannCategories = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jTextField10 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        jComboBox4 = new javax.swing.JComboBox<>();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        pannClients = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jTextField16 = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jTextField17 = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jTextField18 = new javax.swing.JTextField();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jTextField19 = new javax.swing.JTextField();
        jTextField20 = new javax.swing.JTextField();
        jTextField21 = new javax.swing.JTextField();
        jTextField22 = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        jTextField23 = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        menu_app = new javax.swing.JMenuBar();
        menu_fichier = new javax.swing.JMenu();
        gestion_categories = new javax.swing.JMenuItem();
        gestion_promotions = new javax.swing.JMenuItem();
        gestion_clients = new javax.swing.JMenuItem();
        charger = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        sauvegarder = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        quitter = new javax.swing.JMenuItem();
        menu_APropos = new javax.swing.JMenu();
        version = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(810, 671));

        jTabbedPane1.setName(""); // NOI18N
        jTabbedPane1.setPreferredSize(new java.awt.Dimension(400, 400));

        pannProduits.setPreferredSize(new java.awt.Dimension(896, 671));

        pannRechercheProduit.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Rechercher un Produit", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Symbol", 2, 8))); // NOI18N
        pannRechercheProduit.setPreferredSize(new java.awt.Dimension(400, 100));

        lbl_libelle.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        lbl_libelle.setText("Libellé");
        lbl_libelle.setPreferredSize(new java.awt.Dimension(30, 15));

        tf_libelle.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N

        lbl_categorie.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        lbl_categorie.setText("Catégorie");
        lbl_categorie.setPreferredSize(new java.awt.Dimension(45, 15));

        cbb_categorie.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        cbb_categorie.setModel(modeleDesCategories);
        cbb_categorie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbb_categorieActionPerformed(evt);
            }
        });

        cbb_sousCategorie.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        cbb_sousCategorie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbb_sousCategorieActionPerformed(evt);
            }
        });

        bt_effacer.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        bt_effacer.setText("Effacer");
        bt_effacer.setMargin(new java.awt.Insets(2, 8, 2, 8));
        bt_effacer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_effacerActionPerformed(evt);
            }
        });

        bt_rechercher.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        bt_rechercher.setText("Rechercher");
        bt_rechercher.setMargin(new java.awt.Insets(2, 8, 2, 8));
        bt_rechercher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_rechercherActionPerformed(evt);
            }
        });

        jLabel35.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel35.setText("ID:");

        tf_id_rech.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        tf_id_rech.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_id_rechActionPerformed(evt);
            }
        });

        jLabel36.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel36.setText("Sous-Catégorie");

        javax.swing.GroupLayout pannRechercheProduitLayout = new javax.swing.GroupLayout(pannRechercheProduit);
        pannRechercheProduit.setLayout(pannRechercheProduitLayout);
        pannRechercheProduitLayout.setHorizontalGroup(
            pannRechercheProduitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pannRechercheProduitLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pannRechercheProduitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pannRechercheProduitLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(bt_effacer)
                        .addGap(27, 27, 27)
                        .addComponent(bt_rechercher)
                        .addGap(30, 30, 30))
                    .addGroup(pannRechercheProduitLayout.createSequentialGroup()
                        .addGroup(pannRechercheProduitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pannRechercheProduitLayout.createSequentialGroup()
                                .addComponent(lbl_categorie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbb_categorie, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 118, Short.MAX_VALUE)
                                .addComponent(jLabel36)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbb_sousCategorie, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pannRechercheProduitLayout.createSequentialGroup()
                                .addComponent(jLabel35)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tf_id_rech, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lbl_libelle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tf_libelle, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(335, 335, 335))))
        );
        pannRechercheProduitLayout.setVerticalGroup(
            pannRechercheProduitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pannRechercheProduitLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pannRechercheProduitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_libelle)
                    .addComponent(jLabel35)
                    .addComponent(tf_id_rech, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_libelle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(pannRechercheProduitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbb_sousCategorie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbb_categorie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_categorie, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel36))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pannRechercheProduitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_effacer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bt_rechercher, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(55, 55, 55))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 255)));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel1.setText("ID:");
        jLabel1.setPreferredSize(new java.awt.Dimension(13, 15));

        tf_id_pd.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        tf_id_pd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_id_pdActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel2.setText("Catégorie :");
        jLabel2.setPreferredSize(new java.awt.Dimension(51, 15));
        jLabel2.setSize(new java.awt.Dimension(42, 21));

        cbb_categorie_pd.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        cbb_categorie_pd.setModel(modeleDesCategories2);
        cbb_categorie_pd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbb_categorie_pdActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel3.setText("Sous-Catégorie :");
        jLabel3.setPreferredSize(new java.awt.Dimension(78, 15));
        jLabel3.setSize(new java.awt.Dimension(42, 21));

        cbb_sousCategorie_pd.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel4.setText("Libellé Produit");
        jLabel4.setPreferredSize(new java.awt.Dimension(66, 15));

        tf_libelle_pd.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        tf_libelle_pd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_libelle_pdActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tf_id_pd, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(tf_libelle_pd, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbb_categorie_pd, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(cbb_sousCategorie_pd, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_id_pd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbb_categorie_pd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbb_sousCategorie_pd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_libelle_pd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 255)));
        jPanel2.setFont(new java.awt.Font("Symbol", 0, 10)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel5.setText("Description");
        jLabel5.setPreferredSize(new java.awt.Dimension(51, 15));

        ta_description.setColumns(20);
        ta_description.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        ta_description.setRows(5);
        jScrollPane1.setViewportView(ta_description);

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel6.setText("Prix");
        jLabel6.setPreferredSize(new java.awt.Dimension(17, 15));

        tf_prix.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel7.setText("En promotion ?");
        jLabel7.setPreferredSize(new java.awt.Dimension(69, 15));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel8.setText("Prix réduit");
        jLabel8.setPreferredSize(new java.awt.Dimension(46, 15));

        tf_prix_reduit.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        tf_prix_reduit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_prix_reduitActionPerformed(evt);
            }
        });

        tf_promotion.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Symbol", 0, 10)); // NOI18N
        jLabel9.setText("Quantité en stock");
        jLabel9.setPreferredSize(new java.awt.Dimension(82, 15));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel10.setText("Seuil Alerte");
        jLabel10.setMaximumSize(new java.awt.Dimension(53, 30));
        jLabel10.setMinimumSize(new java.awt.Dimension(53, 30));
        jLabel10.setPreferredSize(new java.awt.Dimension(53, 30));
        jLabel10.setSize(new java.awt.Dimension(49, 30));

        tf_seuil.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        tf_seuil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_seuilActionPerformed(evt);
            }
        });

        tf_qte.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tf_prix, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
                            .addComponent(tf_promotion))
                        .addGap(15, 15, 15)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tf_prix_reduit, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(tf_seuil))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(tf_qte, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tf_prix, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_promotion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_prix_reduit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_qte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tf_seuil)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(38, Short.MAX_VALUE))
        );

        butt_modifier.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        butt_modifier.setText("Modifier");
        butt_modifier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butt_modifierActionPerformed(evt);
            }
        });

        butt_ajouter.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        butt_ajouter.setText("Ajouter");
        butt_ajouter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butt_ajouterActionPerformed(evt);
            }
        });

        butt_precedent.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        butt_precedent.setText("<<");
        butt_precedent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butt_precedentActionPerformed(evt);
            }
        });

        butt_suivant.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        butt_suivant.setText(">>");
        butt_suivant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butt_suivantActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pannProduitsLayout = new javax.swing.GroupLayout(pannProduits);
        pannProduits.setLayout(pannProduitsLayout);
        pannProduitsLayout.setHorizontalGroup(
            pannProduitsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pannProduitsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pannProduitsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pannRechercheProduit, javax.swing.GroupLayout.DEFAULT_SIZE, 884, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pannProduitsLayout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(39, 39, 39))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(pannProduitsLayout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(butt_precedent, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(butt_modifier)
                .addGap(128, 128, 128)
                .addComponent(butt_ajouter)
                .addGap(210, 210, 210)
                .addComponent(butt_suivant, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60))
        );
        pannProduitsLayout.setVerticalGroup(
            pannProduitsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pannProduitsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pannRechercheProduit, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addGroup(pannProduitsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(butt_modifier)
                    .addComponent(butt_ajouter)
                    .addComponent(butt_precedent)
                    .addComponent(butt_suivant))
                .addGap(88, 88, 88))
        );

        jTabbedPane1.addTab("Produits", pannProduits);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Rechercher une promotion", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Symbol", 2, 8))); // NOI18N

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel16.setText("ID :");

        jTextField11.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jTextField11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField11ActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel17.setText("Nom de produit en promo :");

        jTextField12.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jTextField12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField12ActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel18.setText("Date de début :");

        jComboBox5.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N

        jLabel19.setFont(new java.awt.Font("Symbol", 0, 10)); // NOI18N
        jLabel19.setText("/");

        jComboBox6.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N

        jComboBox7.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jComboBox7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox7ActionPerformed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel20.setText("Date de fin :");

        jComboBox8.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N

        jButton7.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jButton7.setText("Effacer");

        jButton8.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jButton8.setText("Rechercher");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jComboBox16.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N

        jComboBox17.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox7, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 376, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel17)
                    .addComponent(jLabel20))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(64, 64, 64))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jComboBox8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox16, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jComboBox17, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton7)
                .addGap(35, 35, 35)
                .addComponent(jButton8)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBox7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBox8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBox16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBox17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton7)
                    .addComponent(jButton8))
                .addGap(16, 16, 16))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 204)));

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel21.setText("ID :");

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel22.setText("Liste des Produits en promo :");

        jComboBox9.setFont(new java.awt.Font("Symbol", 0, 10)); // NOI18N

        jTextField13.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jTextField13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField13ActionPerformed(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel23.setText("Date de début :");

        jComboBox10.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel24.setText("Date de fin :");

        jComboBox11.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N

        jComboBox12.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N

        jComboBox13.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N

        jComboBox14.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N

        jComboBox15.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N

        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel25.setText("Ancien prix :");

        jTextField14.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N

        jLabel26.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel26.setText("Nouveau prix :");

        jTextField15.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel22)
                    .addComponent(jLabel21)
                    .addComponent(jLabel23))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jComboBox10, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox11, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox12, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox9, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel26)
                    .addComponent(jLabel25)
                    .addComponent(jLabel24))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jComboBox13, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox14, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox15, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jTextField15, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField14, javax.swing.GroupLayout.Alignment.LEADING)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25)
                    .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26)
                    .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton9.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jButton9.setText("Modifier");

        jButton10.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jButton10.setText("Ajouter");

        javax.swing.GroupLayout pannPromotionsLayout = new javax.swing.GroupLayout(pannPromotions);
        pannPromotions.setLayout(pannPromotionsLayout);
        pannPromotionsLayout.setHorizontalGroup(
            pannPromotionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pannPromotionsLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton9)
                .addGap(54, 54, 54)
                .addComponent(jButton10)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pannPromotionsLayout.createSequentialGroup()
                .addGroup(pannPromotionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pannPromotionsLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pannPromotionsLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pannPromotionsLayout.setVerticalGroup(
            pannPromotionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pannPromotionsLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(pannPromotionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton10)
                    .addComponent(jButton9))
                .addContainerGap(281, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Promotions", pannPromotions);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Rechercher une catégorie", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 2, 8))); // NOI18N
        jPanel3.setFont(new java.awt.Font("Tahoma", 2, 10)); // NOI18N
        jPanel3.setPreferredSize(new java.awt.Dimension(718, 110));

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel11.setText("Nom");

        jTextField8.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jTextField8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField8ActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel12.setText("Sous-catégorie");

        jTextField9.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jButton1.setText("Effacer");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jButton2.setText("Rechercher");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addGap(35, 35, 35)
                        .addComponent(jButton2))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 93, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jButton2))
                        .addGap(19, 19, 19))))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 255)));
        jPanel4.setPreferredSize(new java.awt.Dimension(677, 73));

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel13.setText("Catégorie :");

        jTextField10.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jTextField10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField10ActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel14.setText("Sous-catégorie :");

        jComboBox3.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel15.setText("Liste de Produits");

        jComboBox4.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 341, Short.MAX_VALUE)
                        .addComponent(jLabel14)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jButton5.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jButton5.setText("Modifier");

        jButton6.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jButton6.setText("Ajouter");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pannCategoriesLayout = new javax.swing.GroupLayout(pannCategories);
        pannCategories.setLayout(pannCategoriesLayout);
        pannCategoriesLayout.setHorizontalGroup(
            pannCategoriesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pannCategoriesLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(pannCategoriesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 872, Short.MAX_VALUE)
                    .addGroup(pannCategoriesLayout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 824, Short.MAX_VALUE)
                        .addGap(48, 48, 48)))
                .addContainerGap())
            .addGroup(pannCategoriesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton6)
                .addGap(93, 93, 93)
                .addComponent(jButton5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pannCategoriesLayout.setVerticalGroup(
            pannCategoriesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pannCategoriesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(pannCategoriesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton6)
                    .addComponent(jButton5))
                .addContainerGap(294, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Catégories", pannCategories);

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Rechercher un client", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 2, 8))); // NOI18N
        jPanel7.setPreferredSize(new java.awt.Dimension(616, 138));

        jLabel27.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel27.setText("ID :");

        jTextField16.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N

        jLabel28.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel28.setText("Nom :");

        jTextField17.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N

        jLabel29.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel29.setText("Prénom :");

        jButton11.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jButton11.setText("Effacer");

        jButton12.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jButton12.setText("Rechercher");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel27)
                        .addGap(26, 26, 26)
                        .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel28)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(jButton11)
                                .addGap(29, 29, 29)
                                .addComponent(jButton12))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel29)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29)
                    .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 24, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton11)
                    .addComponent(jButton12))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 204)));

        jLabel30.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel30.setText("ID :");

        jLabel31.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel31.setText("Nom :");

        jLabel32.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel32.setText("Prénom :");

        jTextField20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField20ActionPerformed(evt);
            }
        });

        jTextField22.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N

        jLabel33.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel33.setText("Adresse email :");

        jTextField23.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N

        jLabel34.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel34.setText("Mot de passe :");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel32)
                    .addComponent(jLabel31, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel30, javax.swing.GroupLayout.Alignment.LEADING))
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField19, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField21)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 408, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel33)
                    .addComponent(jLabel34))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField22, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField23, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(jTextField19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel33))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel34)
                    .addComponent(jTextField23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(jTextField21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(38, Short.MAX_VALUE))
        );

        jButton13.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jButton13.setText("Modifier");

        jButton14.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jButton14.setText("Ajouter");

        javax.swing.GroupLayout pannClientsLayout = new javax.swing.GroupLayout(pannClients);
        pannClients.setLayout(pannClientsLayout);
        pannClientsLayout.setHorizontalGroup(
            pannClientsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pannClientsLayout.createSequentialGroup()
                .addGroup(pannClientsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pannClientsLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton13)
                        .addGap(59, 59, 59)
                        .addComponent(jButton14)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pannClientsLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(pannClientsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, 880, Short.MAX_VALUE)
                            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        pannClientsLayout.setVerticalGroup(
            pannClientsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pannClientsLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(pannClientsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton13)
                    .addComponent(jButton14))
                .addContainerGap(244, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Clients", pannClients);

        getContentPane().add(jTabbedPane1, java.awt.BorderLayout.CENTER);

        menu_fichier.setText("Fichier");

        gestion_categories.setText("Gestion Catégories");
        gestion_categories.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gestion_categoriesActionPerformed(evt);
            }
        });
        menu_fichier.add(gestion_categories);

        gestion_promotions.setText("Gestion des Promotions");
        gestion_promotions.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gestion_promotionsActionPerformed(evt);
            }
        });
        menu_fichier.add(gestion_promotions);

        gestion_clients.setText("Gestion des Clients");
        gestion_clients.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gestion_clientsActionPerformed(evt);
            }
        });
        menu_fichier.add(gestion_clients);

        charger.setText("Charger...");
        menu_fichier.add(charger);
        menu_fichier.add(jSeparator1);

        sauvegarder.setText("Sauvegarder...");
        sauvegarder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sauvegarderActionPerformed(evt);
            }
        });
        menu_fichier.add(sauvegarder);
        menu_fichier.add(jSeparator2);

        quitter.setText("Quitter");
        quitter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quitterActionPerformed(evt);
            }
        });
        menu_fichier.add(quitter);

        menu_app.add(menu_fichier);

        menu_APropos.setText("A propos...");

        version.setText("Version");
        version.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                versionActionPerformed(evt);
            }
        });
        menu_APropos.add(version);

        menu_app.add(menu_APropos);

        setJMenuBar(menu_app);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbb_categorieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbb_categorieActionPerformed
        // TODO add your handling code here:
        SousCategorieDAO SousCatDAO = mysqlFactory.getSousCategorieDAO();
        String laCategorie = (String) cbb_categorie.getSelectedItem();
        
        Object[] tabNoms;
        tabNoms = SousCatDAO.findSousCategoriesByCategorie(laCategorie).toArray();
        modeleDesSousCategories = new DefaultComboBoxModel(tabNoms);
        
        
        
        cbb_sousCategorie.setModel(modeleDesSousCategories);
        //this.affecterDetailCBBox(laCategorie, cbb_sousCategorie);
        /*Q5) on va utiliser
        une fonction qui renvoie la combobox des sous-categories en foction de la categorie choisie*/
    }//GEN-LAST:event_cbb_categorieActionPerformed

    private void bt_effacerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_effacerActionPerformed
        // TODO add your handling code here:
        tf_id_rech.setText(null);
        tf_libelle.setText(null);
        cbb_categorie.setSelectedIndex(0);
        cbb_sousCategorie.removeAllItems();
        cbb_categorie_pd.setSelectedIndex(0);
        cbb_sousCategorie_pd.removeAllItems();
        tf_libelle_pd.setText(null);
        tf_id_pd.setText(null);
        tf_prix.setText(null);
        tf_prix_reduit.setText(null);
        tf_promotion.setText(null);
        tf_qte.setText(null);
        tf_seuil.setText(null);
        ta_description.setText(null);
        butt_suivant.setVisible(false);
        butt_precedent.setVisible(false);
    }//GEN-LAST:event_bt_effacerActionPerformed

    private void gestion_promotionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gestion_promotionsActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedComponent(pannPromotions);
    }//GEN-LAST:event_gestion_promotionsActionPerformed

    private void gestion_clientsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gestion_clientsActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedComponent(pannClients);
    }//GEN-LAST:event_gestion_clientsActionPerformed

    private void sauvegarderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sauvegarderActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sauvegarderActionPerformed

    private void quitterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quitterActionPerformed
        // TODO add your handling code here:
        int reply=JOptionPane.showConfirmDialog(this, "Voulez-vous vraiment quitter?", "Confirm Dialog", JOptionPane.OK_CANCEL_OPTION);
        if(reply==JOptionPane.YES_OPTION)
            System.exit(0);
        //Q6)Implementation par classe anonyme
    }//GEN-LAST:event_quitterActionPerformed

    private void gestion_categoriesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gestion_categoriesActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedComponent(pannCategories);
    }//GEN-LAST:event_gestion_categoriesActionPerformed

    private void versionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_versionActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(this, "Information sur les auteurs et la version: ", "Message Dialog", JOptionPane.PLAIN_MESSAGE);
    }//GEN-LAST:event_versionActionPerformed

    private void cbb_sousCategorieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbb_sousCategorieActionPerformed
        // TODO add your handling code here:
        
       
    }//GEN-LAST:event_cbb_sousCategorieActionPerformed

    private void tf_id_pdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_id_pdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_id_pdActionPerformed

    private void tf_libelle_pdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_libelle_pdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_libelle_pdActionPerformed

    private void tf_prix_reduitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_prix_reduitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_prix_reduitActionPerformed

    private void tf_seuilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_seuilActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_seuilActionPerformed

    private void cbb_categorie_pdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbb_categorie_pdActionPerformed
        // TODO add your handling code here:
        /*String laCategorie = (String) cbb_categorie_pd.getSelectedItem();
        this.affecterDetailCBBox(laCategorie, cbb_sousCategorie_pd);
        SousCategorieDAO SousCatDAO = mysqlFactory.getSousCategorieDAO();
        String laCategorie = (String) cbb_categorie_pd.getSelectedItem();
        SousCatDAO.findSousCategoriesByCategorie(laCategorie);*/
        SousCategorieDAO SousCatDAO = mysqlFactory.getSousCategorieDAO();
        String laCategorie = (String) cbb_categorie_pd.getSelectedItem();
        
        Object[] tabNoms;
        tabNoms = SousCatDAO.findSousCategoriesByCategorie(laCategorie).toArray();
        modeleDesSousCategories = new DefaultComboBoxModel(tabNoms);
        cbb_sousCategorie_pd.setModel(modeleDesSousCategories);
    }//GEN-LAST:event_cbb_categorie_pdActionPerformed

    private void jTextField8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField8ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField10ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jTextField11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField11ActionPerformed

    private void jComboBox7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jTextField13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField13ActionPerformed

    private void jTextField12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField12ActionPerformed

    private void jTextField20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField20ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField20ActionPerformed

    private void tf_id_rechActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_id_rechActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_id_rechActionPerformed

    private void butt_ajouterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butt_ajouterActionPerformed
        // TODO add your handling code here:
        
        /*if(tf_promotion.getText().equalsIgnoreCase("oui")){
            
            produit= new Produit(tf_libelle_pd.getText(), getCategorieFromString(cbb_categorie_pd.getSelectedItem().toString()), cbb_sousCategorie_pd.getSelectedItem().toString(), ta_description.getText(),
                    false, 1, true, Double.parseDouble(tf_prix.getText()), Double.parseDouble(tf_prix_reduit.getText()), Integer.parseInt(tf_qte.getText()), Integer.parseInt(tf_seuil.getText()), "");
            
            System.out.println(produit.getLibelle());
        }
        else if(tf_promotion.getText().equalsIgnoreCase("non")){
            produit= new Produit(tf_libelle_pd.getName(), getCategorieFromString(cbb_categorie_pd.getSelectedItem().toString()), cbb_sousCategorie_pd.getSelectedItem().toString(), ta_description.getText(),
                    false, 1, false, Double.parseDouble(tf_prix.getText()), Double.parseDouble(tf_prix_reduit.getText()), Integer.parseInt(tf_qte.getText()), Integer.parseInt(tf_seuil.getText()), "");
        }
        else
            System.out.println("Exception");*/
        Produit p= new Produit();
        p.setLibelle(tf_libelle_pd.getText());
        p.setPrix(Double.parseDouble(tf_prix.getText()));
        p.setDescription(ta_description.getText());
        p.setQuantiteEnStock(Integer.parseInt(tf_qte.getText()));
        p.setSeuilAlerteStock(Integer.parseInt(tf_seuil.getText()));
        ProduitDAO prodDAO = mysqlFactory.getProduitDAO();
        prodDAO.insertProduit(p);
    }//GEN-LAST:event_butt_ajouterActionPerformed
    
    
    private void bt_rechercherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_rechercherActionPerformed
        // TODO add your handling code here:
        if(tf_id_rech.getText()!=null && !(tf_id_rech.getText().equals(""))){
            
            ProduitDAO prodDAO = mysqlFactory.getProduitDAO();
            Produit p = prodDAO.findProduitById(Integer.parseInt(tf_id_rech.getText()));
            tf_id_pd.setText(tf_id_rech.getText());
            //cbb_categorie_pd.setSelectedItem(p.getCategorie());
            tf_libelle_pd.setText(p.getLibelle());
            ta_description.setText(p.getDescription());
            tf_prix.setText(Double.toString(p.getPrix()));
            //tf_prix_reduit.setText(Double.toString(p.getPrixPromoDouble()));
            if(p.isEnPromo())
                tf_promotion.setText("Oui");
            else
                tf_promotion.setText("Non");
            tf_qte.setText(Integer.toString(p.getQuantiteEnStock()));
            tf_seuil.setText(Integer.toString(p.getSeuilAlerteStock()));
        }
        
        else if(tf_libelle.getText()!=null && !(tf_libelle.getText().equals(""))){
            ProduitDAO prodDAO = mysqlFactory.getProduitDAO();
            it= prodDAO.findProduitByLibelle(tf_libelle.getText()).listIterator();
            Produit p = it.next();
            tf_id_pd.setText(Integer.toString(p.getIDProduit()));
            //cbb_categorie_pd.setSelectedItem(p.getCategorie());
            tf_libelle_pd.setText(p.getLibelle());
            ta_description.setText(p.getDescription());
            tf_prix.setText(Double.toString(p.getPrix()));
            //tf_prix_reduit.setText(Double.toString(it.next().getPrixPromoDouble()));
            if(p.isEnPromo())
                tf_promotion.setText("Oui");
            else
                tf_promotion.setText("Non");
            tf_qte.setText(Integer.toString(p.getQuantiteEnStock()));
            tf_seuil.setText(Integer.toString(p.getSeuilAlerteStock()));
            if(prodDAO.findProduitByLibelle(tf_libelle.getText()).size()>1){
                butt_suivant.setVisible(true);
                butt_precedent.setVisible(true);
            }                    
                
        }
        
        else
            System.out.println("Exception");
            
        /*int i=0;
        while(i<lesProduits.size() && tf_id_pd.getText().equals("")){
            
            if(!(tf_id_rech.getText().equals("")) && lesProduits.get(i).getIDProduit()==Integer.parseInt(tf_id_rech.getText())){
                tf_id_pd.setText(tf_id_rech.getText());
                cbb_categorie_pd.setSelectedItem(lesProduits.get(i).getCategorie());
                tf_libelle_pd.setText(lesProduits.get(i).getLibelle());
                ta_description.setText(lesProduits.get(i).getDescription());
                tf_prix.setText(Double.toString(lesProduits.get(i).getPrix()));
                tf_prix_reduit.setText(Double.toString(lesProduits.get(i).getPrixPromoDouble()));
                if(lesProduits.get(i).isEnPromo())
                    tf_promotion.setText("Oui");
                else
                    tf_promotion.setText("Non");
                tf_qte.setText(Integer.toString(lesProduits.get(i).getQuantiteEnStock()));
                tf_seuil.setText(Integer.toString(lesProduits.get(i).getSeuilAlerteStock()));
                i++;
            }
            else if(tf_libelle.getText().equalsIgnoreCase(lesProduits.get(i).getLibelle())) {                 
                tf_id_pd.setText(Integer.toString(lesProduits.get(i).getIDProduit()));
                cbb_categorie_pd.setSelectedItem(lesProduits.get(i).getCategorie());
                tf_libelle_pd.setText(lesProduits.get(i).getLibelle());
                ta_description.setText(lesProduits.get(i).getDescription());
                tf_prix.setText(Double.toString(lesProduits.get(i).getPrix()));
                tf_prix_reduit.setText(Double.toString(lesProduits.get(i).getPrixPromoDouble()));
                if(lesProduits.get(i).isEnPromo())
                    tf_promotion.setText("Oui");
                else
                    tf_promotion.setText("Non");
                tf_qte.setText(Integer.toString(lesProduits.get(i).getQuantiteEnStock()));
                tf_seuil.setText(Integer.toString(lesProduits.get(i).getSeuilAlerteStock()));
                i++;
            }
            else
                System.out.println("Exception");
        }*/
    }//GEN-LAST:event_bt_rechercherActionPerformed

    private void butt_suivantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butt_suivantActionPerformed
        // TODO add your handling code here:
        if(it.hasNext()){
            
            Produit p = it.next();
            if(!(it.hasNext()))
                it.previous();
            tf_id_pd.setText(Integer.toString(p.getIDProduit()));
            //cbb_categorie_pd.setSelectedItem(p.getCategorie());
            tf_libelle_pd.setText(p.getLibelle());
            ta_description.setText(p.getDescription());
            tf_prix.setText(Double.toString(p.getPrix()));
            //tf_prix_reduit.setText(Double.toString(it.next().getPrixPromoDouble()));
            if(p.isEnPromo())
                tf_promotion.setText("Oui");
            else
                tf_promotion.setText("Non");
            tf_qte.setText(Integer.toString(p.getQuantiteEnStock()));
            tf_seuil.setText(Integer.toString(p.getSeuilAlerteStock()));
            
        }
    }//GEN-LAST:event_butt_suivantActionPerformed

    private void butt_precedentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butt_precedentActionPerformed
        // TODO add your handling code here:
        if(it.hasPrevious()){
            
            Produit p= it.previous();
            if(!(it.hasPrevious()))
                it.next();
            tf_id_pd.setText(Integer.toString(p.getIDProduit()));
            //cbb_categorie_pd.setSelectedItem(p.getCategorie());
            tf_libelle_pd.setText(p.getLibelle());
            ta_description.setText(p.getDescription());
            tf_prix.setText(Double.toString(p.getPrix()));
            //tf_prix_reduit.setText(Double.toString(it.next().getPrixPromoDouble()));
            if(p.isEnPromo())
                tf_promotion.setText("Oui");
            else
                tf_promotion.setText("Non");
            tf_qte.setText(Integer.toString(p.getQuantiteEnStock()));
            tf_seuil.setText(Integer.toString(p.getSeuilAlerteStock()));
            
        }
    }//GEN-LAST:event_butt_precedentActionPerformed

    private void butt_modifierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butt_modifierActionPerformed
        // TODO add your handling code here:
        ProduitDAO prodDAO = mysqlFactory.getProduitDAO();
        if(prodDAO.findProduitById(Integer.parseInt(tf_id_pd.getText()))==null)
            System.out.println("Produit non existant");
        else{
            prodDAO.updateProduit(Integer.parseInt(tf_id_pd.getText()), tf_libelle_pd.getText()
                    , Double.parseDouble(tf_prix.getText()), ta_description.getText(), Integer.parseInt(tf_qte.getText()), Integer.parseInt(tf_seuil.getText()));
        }
    }//GEN-LAST:event_butt_modifierActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]){
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FenAccueil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FenAccueil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FenAccueil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FenAccueil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FenAccueil().setVisible(true);
            }
        });
        
        
    }

    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_effacer;
    private javax.swing.JButton bt_rechercher;
    private javax.swing.JButton butt_ajouter;
    private javax.swing.JButton butt_modifier;
    private javax.swing.JButton butt_precedent;
    private javax.swing.JButton butt_suivant;
    private javax.swing.JComboBox<String> cbb_categorie;
    private javax.swing.JComboBox<String> cbb_categorie_pd;
    private javax.swing.JComboBox<String> cbb_sousCategorie;
    private javax.swing.JComboBox<String> cbb_sousCategorie_pd;
    private javax.swing.JMenuItem charger;
    private javax.swing.JMenuItem gestion_categories;
    private javax.swing.JMenuItem gestion_clients;
    private javax.swing.JMenuItem gestion_promotions;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox10;
    private javax.swing.JComboBox<String> jComboBox11;
    private javax.swing.JComboBox<String> jComboBox12;
    private javax.swing.JComboBox<String> jComboBox13;
    private javax.swing.JComboBox<String> jComboBox14;
    private javax.swing.JComboBox<String> jComboBox15;
    private javax.swing.JComboBox<String> jComboBox16;
    private javax.swing.JComboBox<String> jComboBox17;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JComboBox<String> jComboBox6;
    private javax.swing.JComboBox<String> jComboBox7;
    private javax.swing.JComboBox<String> jComboBox8;
    private javax.swing.JComboBox<String> jComboBox9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JTextField jTextField18;
    private javax.swing.JTextField jTextField19;
    private javax.swing.JTextField jTextField20;
    private javax.swing.JTextField jTextField21;
    private javax.swing.JTextField jTextField22;
    private javax.swing.JTextField jTextField23;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JLabel lbl_categorie;
    private javax.swing.JLabel lbl_libelle;
    private javax.swing.JMenu menu_APropos;
    private javax.swing.JMenuBar menu_app;
    private javax.swing.JMenu menu_fichier;
    private javax.swing.JPanel pannCategories;
    private javax.swing.JPanel pannClients;
    private javax.swing.JPanel pannProduits;
    private javax.swing.JPanel pannPromotions;
    private javax.swing.JPanel pannRechercheProduit;
    private javax.swing.JMenuItem quitter;
    private javax.swing.JMenuItem sauvegarder;
    private javax.swing.JTextArea ta_description;
    private javax.swing.JTextField tf_id_pd;
    private javax.swing.JTextField tf_id_rech;
    private javax.swing.JTextField tf_libelle;
    private javax.swing.JTextField tf_libelle_pd;
    private javax.swing.JTextField tf_prix;
    private javax.swing.JTextField tf_prix_reduit;
    private javax.swing.JTextField tf_promotion;
    private javax.swing.JTextField tf_qte;
    private javax.swing.JTextField tf_seuil;
    private javax.swing.JMenuItem version;
    // End of variables declaration//GEN-END:variables
}
