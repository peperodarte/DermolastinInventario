/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Clientes;

import AppUtilidades.AppDB;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author JOSE.RODARTE
 */
public class ClienteDAO {

    AppDB appDB = new AppDB();
    String qry = "";
    PreparedStatement ps;
    ResultSet rs;
    boolean cC = false;
    int mC=0;
    public boolean crearCliente(HttpServletRequest request) throws SQLException {
        
        qry = "INSERT INTO tclientes (Nombre, Apellidos, Email, Direccion, Telefono,Celular, Fecha, Credito) "
                + "VALUES "
                + "(?,?,?,?,?,?,?,?)";
        //+ "( 'Jose Rodarte Lopez', 'jose.rodarte@outlook.com', 'Rio tiber 442 colinas del rio', '4493770219','44938', '14/mar./2020', 0);";
        try {
            mC=1;
            appDB.conexion().setAutoCommit(false);
            ps = (PreparedStatement) appDB.conexion().prepareStatement(qry);
            ps.setString(1, request.getParameter("nombreC"));
            ps.setString(2, request.getParameter("apellidosC"));
            ps.setString(3, request.getParameter("emailC"));
            ps.setString(4, request.getParameter("direccionC"));
            ps.setString(5, request.getParameter("telC"));
            ps.setString(6, request.getParameter("celC"));
            ps.setString(7, request.getParameter("fecha"));
            ps.setInt(8, Integer.parseInt(request.getParameter("credito")));

            mC = ps.executeUpdate();
            appDB.conexion().commit();
            if(mC==1){cC=true;}else{cC=false;}
        } catch (Exception e) {
            e.getMessage();
            appDB.conexion().rollback();
            cC = false;
        }finally{
            
        }
        return cC;
    }

    public Cliente recuperaCliente(int id) throws SQLException {
        Cliente cliente = new Cliente();
        qry = "select id, Nombre, Apellidos, Email, Direccion, Telefono,Celular, Fecha, Credito from tclientes where id= " + id;
        rs = appDB.resultSet(qry);
        while (rs.next()) {
            cliente.setnId(rs.getInt("id"));
            cliente.setNombre(rs.getString("Nombre"));
            cliente.setApellidos(rs.getString("Apellidos"));
            cliente.setEmail(rs.getString("Email"));
            cliente.setDireccion(rs.getString("Direccion"));
            cliente.setTelefono(rs.getString("Telefono"));
            cliente.setCelular(rs.getString("Celular"));
            cliente.setCelular(rs.getString("Celular"));
        }
        return cliente;
    }
    public boolean modificarCliente(HttpServletRequest request) throws SQLException{
         appDB.conexion().setAutoCommit(false);
        qry = "update tclientes set Nombre=?, Apellidos=?, Email=?, Direccion=?, Telefono=?, Celular=? where id=?";
        //+ "( 'Jose Rodarte Lopez', 'jose.rodarte@outlook.com', 'Rio tiber 442 colinas del rio', '4493770219','44938', '14/mar./2020', 0);";
        try {
            ps = (PreparedStatement) appDB.conexion().prepareStatement(qry);
            ps.setString(1, request.getParameter("nombreC"));
            ps.setString(2, request.getParameter("apellidosC"));
            ps.setString(3, request.getParameter("emailC"));
            ps.setString(4, request.getParameter("direccionC"));
            ps.setString(5, request.getParameter("telC"));
            ps.setString(6, request.getParameter("celC"));
            ps.setInt(7, Integer.parseInt(request.getParameter("id")));
            mC = ps.executeUpdate();
            appDB.conexion().commit();
            if(mC==0){cC=false;}else{cC=true;}
        } catch (Exception e) {
            e.getMessage();
            appDB.conexion().rollback();
            cC = false;
        }finally{
            
        }
        return cC;
    }
}
