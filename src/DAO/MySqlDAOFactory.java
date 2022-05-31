/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;


import java.sql.*;
import java.sql.DriverManager;

/**
 *
 * @author ilyassberradi
 */
public class MySqlDAOFactory extends DAOFactory {
    public static final String DRIVER= "com.mariadb.jdbc.Driver";
    public static final String DBURL="jdbc:mariadb://localhost:8889/ptut";
    public static final String USER="root";
    public static final String PASSWORD="root";
    
    public static Connection createConnection() throws SQLException {
        try {
            Class.forName(DRIVER);
            Connection connection = DriverManager.getConnection(DBURL, USER, PASSWORD);
            connection.setAutoCommit(true);
            return connection;
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
            return null;
        }
        
    }
    
    public ProduitDAO getProduitDAO() {
        
        return new MySqlProduitDAO();
    }
    
    public SousCategorieDAO getSousCategorieDAO() {
        
        return new MySqlSousCategorieDAO();
    }
    
    public CategorieProduitDAO getCategorieProduitDAO() {
        
        return new MySqlCategorieProduitDAO();
    }
}
