/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.cClientes;

import Modelo.Clientes.ClienteDAO;
import Modelo.Clientes.Cliente;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author JOSE.RODARTE
 */
@WebServlet(name = "controlCliente", urlPatterns = {"/controlCliente"})
public class controlCliente extends HttpServlet {

    ClienteDAO clienteDAO = new ClienteDAO();
    Cliente cliente = new Cliente();

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
         if(request.getCharacterEncoding() == null)
    {
        request.setCharacterEncoding("UTF-8");
    }
        
        int ac = Integer.parseInt(request.getParameter("ac") != null ? request.getParameter("ac") : "0");
        int idCliente = Integer.parseInt(request.getParameter("id") != null ? request.getParameter("id") : "0");
        boolean ban = false;

        //1=Crear cliente   
        if (ac == 1) {
            ban = clienteDAO.crearCliente(request);
            request.getRequestDispatcher("Modules/Clientes/Clientes.jsp?cC=" + ban).forward(request, response);
        }
        //2=Modificar cliente
        if (ac == 2) {
            if (idCliente != 0) {
                cliente = clienteDAO.recuperaCliente(idCliente);
                if (!cliente.getNombre().equals("")) {
                    request.setAttribute("cliente", cliente);
                    request.getRequestDispatcher("Modules/Clientes/Clientes.jsp?idC="+cliente.getnId()).forward(request, response);
                }
            }
        }
        //3=AplicarModificacion cliente
        if (ac == 3) {
            ban = clienteDAO.modificarCliente(request);
            request.getRequestDispatcher("Modules/Clientes/Clientes.jsp?cM=" + ban).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
