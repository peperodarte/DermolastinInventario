/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JOSE.RODARTE
 */


public final class DataBase_ConectionClass {

    public DataBase_ConectionClass() {
    }

    public DataBase_ConectionClass(String server, String db, String user, String pass) {
        this.setServer(server);
        this.setDb(db);
        this.setUser(user);
        this.setPass(pass);
    }
    
    private String server;
    private String db;
    private String user;
    private String pass;
    
     public  Connection getconecction(){
        Connection con = null;
        try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                con = DriverManager.getConnection(this.getServer()+this.getDb(),this.getUser(),this.getPass());
            } catch (Exception ex) {
                Logger.getLogger(DataBase_ConectionClass.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("No se pudo establecer la conección a la base de datos: "+ex.getMessage());
            }
 
        return con;
    }
 
   
    
    public static void closeConnection(Connection con)
    {
        try{
            con.close();
            con = null;
        }catch (SQLException ex){
                Logger.getLogger(DataBase_ConectionClass.class.getName()).log(Level.SEVERE, null, ex);
    }
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getDb() {
        return db;
    }

    public void setDb(String db) {
        this.db = db;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
    
   
}
