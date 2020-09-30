/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AppUtilidades;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author JOSE.RODARTE
 */
public class AppDB {

    public AppDB() {
    }
    conectionAtributes ca=new conectionAtributes();
    Connection con=ca.obtenerConeccion();
    /////////////Explotar Base de Datos
    public Connection conexion(){
        return con;
    }

    public ResultSet resultSet (String Query) throws SQLException{
        Statement st=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs=null;
        try{
             rs=st.executeQuery(Query);
             return rs;
        }catch(SQLException sqlex){
            System.out.println("Error SQL: "+sqlex.getMessage());
            System.out.println("---------------------------------------------");
            System.out.println("Query: "+Query);
            System.out.println("---------------------------------------------");          
        }finally{
        }       
        return rs; 
    } 
    public String imprimeTablaReporte(String Query, String funcionJS, String campoEnvio) throws SQLException{
        String tabla="<table class=\"highlight striped centered responsive-table\"><thead><tr>";
        String envio="";
        ResultSet rs=null;
        ResultSetMetaData md=null;
        int columnas=0;
        int i=0;
        Boolean cantRegistros=false;
        
        try{
             rs=resultSet(Query);
             md=rs.getMetaData();
             columnas=md.getColumnCount();
             
             for (i=1; i<=columnas;i++){
                 tabla=tabla+"<th class=\"\">"+md.getColumnLabel(i).toUpperCase()+"</th>";
                 if(md.getColumnLabel(i).equalsIgnoreCase(campoEnvio)){
                     envio=md.getColumnLabel(i);
                 }
             }
             tabla=tabla+"</tr></thead><tbody>";
             while(rs.next()){
                 cantRegistros=true;
                 tabla=tabla+"<tr class=\"\" style=\"cursor: pointer; \" onclick=\""+funcionJS+"('"+rs.getString(envio)+"')\">";
                 for (i=1; i<=columnas;i++){
                    tabla=tabla+"<td>"+rs.getString(md.getColumnLabel(i))+"</td>";
                 }
                 tabla=tabla+"</tr>";
             }
        }catch(SQLException sqlex){
            System.out.println("Error SQL: "+sqlex.getMessage());System.out.println("--------------------QUERY--------------------");System.out.println("Query: "+Query);System.out.println("---------------------------------------------");          
        }
        finally{
           if(rs != null){rs.close();}
           
        }
        tabla=tabla+"</tbody></table>";
        if(!cantRegistros){
            tabla="<script>M.toast({html: 'No hay registros.'})</script>";
        }
        return tabla;
    }
    public String imprimeCampo(String Query) throws SQLException{
        String campo;
        campo = "";
        try{
            ResultSet rs = resultSet(Query);
             while(rs.next()){
                 campo=rs.getString(1);
             }
        }catch(SQLException sqlex){
            System.out.println("Error SQL: "+sqlex.getMessage());System.out.println("--------------------QUERY--------------------");System.out.println("Query: "+Query);System.out.println("---------------------------------------------");          
        }
        return campo;
    }
    public void insertaCampo(String Query)throws  SQLException{
        Statement statement= con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        try{
            con.setAutoCommit(false);
            statement.executeQuery(Query);
            con.commit();
        }catch(SQLException sqlex){
            System.out.println("Error SQL: "+sqlex.getMessage());System.out.println("--------------------QUERY--------------------");System.out.println("Query: "+Query);System.out.println("---------------------------------------------");          
            con.rollback();
        }finally{}
    }

    private Object[][] ResultSetToObject(ResultSet rs){
        Object obj[][]=null;
        try
        {
        rs.last();
        ResultSetMetaData rsmd = rs.getMetaData();
        int numCols = rsmd.getColumnCount();
        int numFils =rs.getRow();
        obj=new Object[numFils][numCols];
        int j = 0;
        rs.beforeFirst();
        while (rs.next())
        {
            for (int i=0;i<numCols;i++)
            {
                obj[j][i]=rs.getObject(i+1);
            }
            j++;
        }
        }
        catch(Exception e)
        {
        }
        return obj;
    }
    
    
    
}



