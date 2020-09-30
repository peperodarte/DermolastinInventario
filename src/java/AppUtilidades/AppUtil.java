/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AppUtilidades;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.servlet.http.HttpSession;
import org.apache.catalina.Session;

/**
 *
 * @author JOSE.RODARTE
 */
public class AppUtil {

    public AppUtil() {

    }
    private static Autentificacion autentifica=new Autentificacion();
    
    /////////////Utilidades
    
    //Inserta metadatos de materialize
    public String insertMETA(){
        String meta=" <title>Dermolastin</title>"
                + "<link rel=\"icon\" type=\"image/png\" href=\"/Sources/media/images/favico.png\" /> "
                + "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" /> "
                + "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\" />"
                + "<link href=\"https://fonts.googleapis.com/icon?family=Material+Icons\" rel=\"stylesheet\">"
                + "<link rel=\"stylesheet\" href=\"/Sources/css/style.css\"> "
                + "<link rel=\"stylesheet\" href=\"/Sources/css/materialize.min.css\"> "
                + "<script src=\"/Sources/js/materialize.min.js\"></script>"
                + "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"/>";
        return meta;
    }
    /// obtiene fecha actual
    public String fechaActual(){
        Calendario cal=new Calendario();
        return cal.getFecha();
    }

    public int validaUsuario(String usuario, String password) throws SQLException{
        return autentifica.validaUsuario(usuario, password);
    }
    
     //////////////////// Obtener datos de session
    public void cargaSession(String usuario) throws SQLException{
        ResultSet rs=null;
        AppDB appUtil=new AppDB();
        String query="Select "
                + " emp.id_emp as idEmpleado "
                + " ,emp.nombre as nombreEmpleado "
                + " ,us.id_usuario as idUsuario "
                + " ,us.id_nivel as nivelUsuario "
                + " from "
                + " tr_usr_usuarios us "
                + " ,tr_empleados emp "
                + " where "
                + " us.id_emp=emp.id_emp "
                + " and login='"+usuario.trim().toUpperCase()+"'";
        try{
             rs=appUtil.resultSet(query);
             if(rs.next()){
                 autentifica.setIdEmpleado(Integer.parseInt(rs.getString("idEmpleado")));
                 autentifica.setNombreEmpleado(rs.getString("nombreEmpleado"));
                 autentifica.setIdUsuario(Integer.parseInt(rs.getString("idUsuario")));
                 autentifica.setNivelUsuario(Integer.parseInt(rs.getString("nivelUsuario")));
             }
        }catch(SQLException sqlex){
            System.out.println("Error SQL: "+sqlex.getMessage());   
        }
        finally{
           if(rs != null){rs.close();}
        }
    }
    
}
