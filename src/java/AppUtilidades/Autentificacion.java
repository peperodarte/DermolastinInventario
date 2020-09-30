/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AppUtilidades;

import AppUtilidades.AppDB;
import AppUtilidades.AppUtil;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.apache.tomcat.util.codec.binary.Base64;

public class Autentificacion {
    ///////////////////Variables de SESSION
    private int idEmpleado;
    private String nombreEmpleado;
    private int idUsuario;
    private int nivelUsuario;

    public Autentificacion() {
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getNivelUsuario() {
        return nivelUsuario;
    }

    public void setNivelUsuario(int nivelUsuario) {
        this.nivelUsuario = nivelUsuario;
    }

    
    
   
    
    ////////////////////Validacion de inicio de Session
    public int validaUsuario(String usuario, String password) throws SQLException{
        int correcto=0;
        ResultSet rs=null;
        AppDB appUtil=new AppDB();
        AppUtil app=new AppUtil();
        String contrasena=Autentificacion.Encriptar(password.trim());
        try{
             rs=appUtil.resultSet("SELECT LOGIN FROM TR_USR_USUARIOS WHERE upper(LOGIN)='"+usuario.trim().toUpperCase()+"'");
             if(rs.next()){
                 rs.close();
                 rs=appUtil.resultSet("SELECT LOGIN, PASSWORD FROM TR_USR_USUARIOS WHERE LOGIN='"+usuario.trim()+"' AND PASSWORD ='"+contrasena+"'");
                 if(rs.next()){
                      rs.close();
                      rs=appUtil.resultSet("SELECT ACTIVO FROM TR_USR_USUARIOS WHERE LOGIN='"+usuario.trim()+"' AND PASSWORD ='"+contrasena+"'");
                      if(rs.next()){
                          if(rs.getString("ACTIVO").equals("1")==true){
                              correcto=0;
                              app.cargaSession(usuario);
                          }else{
                              correcto=3;//Usuario inactivo
                          }
                      }    
                 }else{
                     correcto=2;//loguin y password incorrectos
                 }
             }else{
                 correcto=1;//cuenta inexistente
             }
        }catch(SQLException sqlex){
            System.out.println("Error SQL: "+sqlex.getMessage());   
        }
        finally{
           if(rs != null){rs.close();}
        }
        return correcto;
    }
    
    
    //////////////////////////Encriptacion de Contraseñas
    private static String Encriptar(String texto) {
     
        
        String secretKey = "Dermolastin"; //llave para encriptar datos
        String base64EncryptedString = "";

        try {

            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-8"));
            byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);

            SecretKey key = new SecretKeySpec(keyBytes, "DESede");
            Cipher cipher = Cipher.getInstance("DESede");
            cipher.init(Cipher.ENCRYPT_MODE, key);

            byte[] plainTextBytes = texto.getBytes("utf-8");
            byte[] buf = cipher.doFinal(plainTextBytes);
            byte[] base64Bytes = Base64.encodeBase64(buf);
            base64EncryptedString = new String(base64Bytes);

        } catch (NoSuchAlgorithmException ex) { }
        catch(UnsupportedEncodingException uee){}
        catch(NoSuchPaddingException nspe1){}
        catch(InvalidKeyException ike){}
        catch(IllegalBlockSizeException ibse){}
        catch(BadPaddingException bpe){}
        return base64EncryptedString;
    }
    private static String Desencriptar(String textoEncriptado) throws Exception {
         
        String secretKey = "Dermolastin"; //llave para encriptar datos
        String base64EncryptedString = "";
        
        try {
            //byte[] message=null;
           // byte[] message=Base64.decodeB(textoEncriptado, secretKey) ;
            byte[] message = Base64.decodeBase64(textoEncriptado.getBytes("utf-8"));
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-8"));
            byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
            SecretKey key = new SecretKeySpec(keyBytes, "DESede");

            Cipher decipher = Cipher.getInstance("DESede");
            decipher.init(Cipher.DECRYPT_MODE, key);

            byte[] plainText = decipher.doFinal(message);

            base64EncryptedString = new String(plainText, "UTF-8");

        } catch (NoSuchAlgorithmException ex) { }
        catch(UnsupportedEncodingException uee){}
        catch(NoSuchPaddingException nspe1){}
        catch(InvalidKeyException ike){}
        catch(IllegalBlockSizeException ibse){}
        catch(BadPaddingException bpe){}
        return base64EncryptedString;
    
    }
    public String validaPass(String password) throws Exception{
        String pass=Desencriptar(password);
        return pass;
    }
    public String encriptaPass(String password) throws Exception{
        String pass=Encriptar(password);
        return pass;
    }
}
