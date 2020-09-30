/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Clientes;


/**
 *
 * @author JOSE.RODARTE
 */
public class Cliente {
    int id_cliente;
    int nId;
    String nombre;
    String apellidos;
    String celular;
    String telefono;
    String email;
    String direccion;
    String fecha;
    int credito;

    public Cliente() {
    }
    
    public Cliente(int id_cliente, int nId, String nombre, String apellidos, String celular, String telefono, String email, String direccion, String fecha) {
        this.id_cliente = id_cliente;
        this.nId = nId;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.celular = celular;
        this.telefono = telefono;
        this.email = email;
        this.direccion = direccion;
        this.fecha = fecha;

    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getCredito() {
        return credito;
    }

    public void setCredito(int credito) {
        this.credito = credito;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getnId() {
        return nId;
    }

    public void setnId(int nId) {
        this.nId = nId;
    }
    
}
