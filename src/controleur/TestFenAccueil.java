package controleur;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import vue.FenAccueil;

/**
 * @author vde
 * Ce projet affiche la fenêtre d'accueil des gestions de backOffice, avec un onglet par gestion.
 * Ici seul l'onglet Catalogue Produit est traitée, avec le pattern Master / Detail pour les catégories et sous-catégories :
 * on pourra rechercher un produit sur son libellé, sa catégorie et / ou sa sous-catégorie, la liste résultat s'affichera dans le
 * panneau dessous, avec un bouton suivant pour balayer la liste.
 * Les menus pour naviguer sur les différents onglets, les classes Métier, les fenêtres de Dialogue
 */
public class TestFenAccueil {
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FenAccueil().setVisible(true);
            }
        });
        
    
    
    }
}

