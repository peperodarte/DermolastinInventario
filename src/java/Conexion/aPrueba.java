/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexion;

import AppUtilidades.Autentificacion;
import AppUtilidades.AppDB;
import AppUtilidades.AppDB;
import AppUtilidades.Autentificacion;


/**
 *
 * @author jose.rodarte <your.name at your.org>
 */
public class aPrueba {
      public static void main(String [] args) throws Exception{
          Autentificacion au=new Autentificacion();
          AppDB appUtil=new AppDB();
          String co="";
          int is=0;
          
          
          //Test inicio sesion 
          is=au.validaUsuario("jose.rodarte", "051288") ;
          System.out.println("#############");
          System.out.println("sesion: "+is);
          System.out.println("#############");
          
          
          //muestra password
          /*co=au.validaPass("XZ7vjrb7tE4");
          System.out.println("#############");
          System.out.println("Password: "+co);
          System.out.println("#############");
          */
          //Encripta password
          /*co=au.encriptaPass("051288");
          System.out.println("#############");
          System.out.println("Password: "+co);
          System.out.println("#############");
          co=au.validaPass(co);
          System.out.println("#############");
          System.out.println("Password: "+co);
          System.out.println("#############");
          */
          
      }
}
