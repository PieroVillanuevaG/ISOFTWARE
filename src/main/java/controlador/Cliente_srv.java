/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.Clientedao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Cliente;

/**
 *
 * @author PIERO
 */
@WebServlet(name = "Cliente_srv", urlPatterns = {"/Cliente_srv", "/ListarClientes", "/ActualizarClientes", "/EditarClientes", "/EliminarClientes", "/AgregarClientes"})
public class Cliente_srv extends HttpServlet {

    Cliente cl = new Cliente();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String path = request.getServletPath();
        if (path.equals("/ListarClientes")) {
            ArrayList<Cliente> lista = Clientedao.listarClientes();
            int i = 0;
            out.write("<thead>\n"
                    + "                                <tr>\n"
                    + "                                    <th class=\"plans-list\"><h3>#</h3></th>\n"
                    + "                                    <th class=\"plans-list\"><h3>Nombre</h3></th>\n"
                    + "                                    <th class=\"plans-list\"><h3>Apellidos</h3></th>\n"
                    + "                                    <th class=\"plans-list\"><h3>Direccion</h3></th>\n"
                    + "                                    <th class=\"plans-list\"><h3>DNI</h3></th>\n"
                    + "                                    <th class=\"plans-list\"><h3>Acciones</h3></th>\n"
                    + "                                </tr>\n"
                    + "                            </thead>	");
            out.write("<tbody>");
            for (Cliente cliente : lista) {
                i++;
                out.write("<tr>\n"
                        + "                                        <td class=\"price_body\">" + i + "</td>\n"
                        + "                                        <td class=\"plan_list_title\">" + cliente.getNombre() + "</td>\n"
                        + "                                        <td class=\"price_body\">" + cliente.getApellidoPaterno() + " " + cliente.getApellidoMaterno() + "</td>\n"
                        + "                                        <td class=\"price_body\">" + cliente.getDireccion() + "</td>\n"
                        + "                                        <td class=\"price_body\">" + cliente.getDNI() + "</td>\n"
                        + "                                        <td>\n"
                        + "\n"
                        + "                                            <div class=\"dropdown\">\n"
                        + "                                                <button class=\"btn btn-warning dropdown-toggle\" type=\"button\" id=\"dropdownMenuButton\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">\n"
                        + "                                                    Seleccione Accion\n"
                        + "                                                </button>\n"
                        + "                                                <div class=\"dropdown-menu dropdown-menu-right\" aria-labelledby=\"dropdownMenuButton\">\n"
                        + "                                                    <a id='btnEditar' class=\"dropdown-item\" href='" + cliente.getIdCliente() + "' title=\"Editar\">Editar</a>\n"
                        + "                                                    <a id= 'btnEliminar' class=\"dropdown-item\" href='" + cliente.getIdCliente() + "' title=\"Eliminar\">Eliminar</a>\n"
                        + "                                                </div>\n"
                        + "                                            </div>\n"
                        + "                                        </td>\n"
                        + "                                    </tr>");
            }
            out.write("</tbody>");
        }
        if (path.equals("/EditarClientes")) {
            int id = Integer.parseInt(request.getParameter("id"));
            Cliente cliente = Clientedao.listarClienteId(id);
            out.write("<div class=\"modal-dialog\" role=\"document\">\n"
                    + "                <div class=\"modal-content\">\n"
                    + "                    <div class=\"modal-header\">\n"
                    + "                        <h5 class=\"modal-title\" id=\"exampleModalLabel\">Edicion de Cliente</h5>\n"
                    + "                        <button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-label=\"Close\" id=\"btnExit2\">\n"
                    + "                            <span aria-hidden=\"true\">&times;</span>\n"
                    + "                        </button>\n"
                    + "                    </div>\n"
                    + "                    <form  id=\"formUp\" action=\"ActualizarAntena\" method=\"POST\">\n"
                    + "                        <div class=\"modal-body\">\n"
                    + "                            <div class=\"form-group\">\n"
                    + "                                <label for=\"nombre\" style=\"color: black\">Ingrese nombre:</label>\n"
                    + "                                <input name=\"nom\" style=\"color: black\" type=\"text\" placeholder=\"Nombre\" class=\"field\" value='"+cliente.getNombre()+"' required>\n"
                    + "                                <input type=\"hidden\" name=\"idCliente\" value='"+cliente.getIdCliente()+"'>\n"
                    + "                            </div>\n"
                    + "                            <div class=\"form-group\">\n"
                    + "                                <label for=\"nombre\" style=\"color: black\">Ingrese su primer apellido:</label>\n"
                    + "                                <input name=\"apePat\" style=\"color: black\" type=\"text\" placeholder=\"Apellido Paterno\" class=\"field\" value='"+cliente.getApellidoPaterno()+"' required> \n"
                    + "                            </div>\n"
                    + "                            <div class=\"form-group\">\n"
                    + "                                <label for=\"nombre\" style=\"color: black\">ingrese su segundo apellido:</label>\n"
                    + "                                <input name=\"apeMat\" style=\"color: black\" type=\"text\" placeholder=\"Apellido Materno\" class=\"field\" value='"+cliente.getApellidoMaterno()+"' required>\n"
                    + "                            </div>\n"
                    + "                            <div class=\"form-group\">\n"
                    + "                                <label for=\"nombre\" style=\"color: black\">Ingrese su direccion:</label>\n"
                    + "                                <input name=\"dir\" type=\"text\" style=\"color: black\" placeholder=\"Direccion\" class=\"field\" value='"+cliente.getDireccion()+"' required>\n"
                    + "                            </div>\n"
                    + "                            <div class=\"form-group\">\n"
                    + "                                <label for=\"nombre\" style=\"color: black\">Ingrese su correo:</label>\n"
                    + "                                <input name=\"correo\" style=\"color: black\" type=\"email\" placeholder=\"Correo\" class=\"field\" value='"+cliente.getCorreo()+"' required><br>\n"
                    + "                            </div>\n"
                    + "                            <div class=\"form-group\">\n"
                    + "                                <label for=\"nombre\" style=\"color: black\">Ingrese su dni:</label>\n"
                    + "                                <input name=\"dni\" style=\"color: black\" type=\"text\" placeholder=\"DNI\" class=\"field\" pattern=\"[0-9]{8}\" title=\"Debe poner 8 números\" value='"+cliente.getDNI()+"' required>\n"
                    + "                            </div>\n"
                    + "                        </div>\n"
                    + "                        <div class=\"modal-footer\">\n"
                    + "                            <button type=\"button\" class=\"btn btn-primary\" data-dismiss=\"modal\" id=\"btnClose2\">Close</button>\n"
                    + "                            <button type=\"submit\" class=\"btn btn-warning\" style=\"float: right; color: white;\" id=\"btnActualizar\">Actualizar</button>\n"
                    + "                        </div>\n"
                    + "                    </form>\n"
                    + "                </div>\n"
                    + "            </div>");
        }
        if (path.equals("/EliminarClientes")) {
            int id = Integer.parseInt(request.getParameter("id"));
            if (Clientedao.deleteCliente(id)) {
                out.write("Eliminado");

            } else {
               out.write("No eliminado");
            }
        }
        if (path.equals("/ActualizarClientes")) {
            String nom_n = request.getParameter("nom").toUpperCase();
            String apePaterno_n = request.getParameter("apePat").toUpperCase();
            String apeMaterno_n = request.getParameter("apeMat").toUpperCase();
            String dir_n = request.getParameter("dir");
            String correo_n = request.getParameter("correo");
            String dni_n = request.getParameter("dni");
            int id = Integer.parseInt(request.getParameter("idCliente"));
            cl.setNombre(nom_n);
            cl.setApellidoPaterno(apePaterno_n);
            cl.setApellidoMaterno(apeMaterno_n);
            cl.setDireccion(dir_n);
            cl.setCorreo(correo_n);
            cl.setDNI(dni_n);
            cl.setIdCliente(id);
            if (Clientedao.guardarCliente(cl)) {
                out.write("TRUE");
            } else {
                out.write("FALSE");
            }
        }
        if (path.equals("/AgregarClientes")) {
            String nom = request.getParameter("nom").toUpperCase();
            String apePaterno = request.getParameter("apePat").toUpperCase();
            String apeMaterno = request.getParameter("apeMat").toUpperCase();
            String dir = request.getParameter("dir");
            String correo = request.getParameter("correo");
            String dni = request.getParameter("dni");
            cl.setNombre(nom);
            cl.setApellidoPaterno(apePaterno);
            cl.setApellidoMaterno(apeMaterno);
            cl.setDireccion(dir);
            cl.setCorreo(correo);
            cl.setDNI(dni);
            if (Clientedao.insertarCliente(cl)) {
                out.write("TRUE");
            } else {
                out.write("FALSE");
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
