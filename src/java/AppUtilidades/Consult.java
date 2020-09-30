/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AppUtilidades;
import Conexion.Conexion;
import Modelo.Clientes.Cliente;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

/**
 *
 * @author AJPDHN
 */
public class Consult extends Conexion{
    private QueryRunner QR = new QueryRunner();
    public List<Cliente> clientes(){
        List<Cliente> cliente = new ArrayList();
        try {
            cliente = (List<Cliente>) QR.query(getConn(), "SELECT * FROM tclientes",
                    new BeanListHandler(Cliente.class));
        } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, "Error : " + ex);
        }
        return cliente;
    }
}