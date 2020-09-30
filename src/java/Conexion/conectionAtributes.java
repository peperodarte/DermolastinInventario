/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexion;

import java.sql.Connection;

/**
 *
 * @author JOSE.RODARTE
 */
public class conectionAtributes {
    
    private void estableceConeccion(){
        /////Produccion
        this.setServidor("jdbc:mysql://192.168.100.90:3306/");
        this.setBaseDatos("puntoVenta");
        this.setUsuario("dbUser");
        this.setPassword("sa.sa");
        
        /////Pruebas
        /*
        this.setServidor("jdbc:mysql://localhost:3300/");
        this.setBaseDatos("hotel");
        this.setUsuario("root");
        this.setPassword("sa.sa");
        */
    }
    private String servidor;
    private String baseDatos;
    private String usuario;
    private String password;

   

    public conectionAtributes() {
        estableceConeccion();
    }
    
   public Connection obtenerConeccion(){
       DataBase_ConectionClass dbcc=new DataBase_ConectionClass(this.getServidor(), this.getBaseDatos(), this.getUsuario(), this.getPassword());
       Connection con=dbcc.getconecction();
       return con;
   }
    
    public String getServidor() {
        return servidor;
    }

    public void setServidor(String servidor) {
        this.servidor = servidor;
    }

    public String getBaseDatos() {
        return baseDatos;
    }

    public void setBaseDatos(String baseDatos) {
        this.baseDatos = baseDatos;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
/*
 Crear usuario dbDermolastin y darle todos losatributos asi como default sheama de bdDermolastin
 * 
 * SET SQL_SAFE_UPDATES = 0; desde root
 
 */