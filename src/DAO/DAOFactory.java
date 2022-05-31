/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;



/**
 *
 * @author ilyassberradi
 */
public abstract class DAOFactory {
    public static final int MYSQL = 1;
    public static final int ORACLE = 2;
    public static final int XML = 3;
    
    public abstract ProduitDAO getProduitDAO();
    public abstract CategorieProduitDAO getCategorieProduitDAO();
    public abstract SousCategorieDAO getSousCategorieDAO();
    
    public static DAOFactory getDAOFactory(){
        return new MySqlDAOFactory();
    }
}
