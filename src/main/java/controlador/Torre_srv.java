/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.Torredao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Torre;

/**
 *
 * @author PIERO
 */
@WebServlet(name = "Torre_srv", urlPatterns = {"/Torre_srv", "/ListarTorres", "/EditarTorres", "/ActualizarTorres", "/EliminarTorres", "/AgregarTorres"})
public class Torre_srv extends HttpServlet {

    Torre t = new Torre();

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
        if (path.equals("/ListarTorres")) {
            ArrayList<Torre> lista = Torredao.listarTorres();
            int i = 0;
            out.write("<thead>\n"
                    + "                                <tr>\n"
                    + "                                    <th class=\"plans-list\"><h3>#</h3></th>\n"
                    + "                                    <th class=\"plans-list\"><h3>Nombre Torre</h3></th>\n"
                    + "                                    <th class=\"plans-list\"><h3>Direccion </h3></th>\n"
                    + "                                    <th class=\"plans-list\"><h3>Dueño Local</h3></th>\n"
                    + "                                    <th class=\"plans-list\"><h3>Telefono</h3></th>\n"
                    + "                                    <th class=\"plans-list\"><h3>Acciones</h3></th>\n"
                    + "\n"
                    + "                                </tr>\n"
                    + "                            </thead>");
            out.write("<tbody>");
            for (Torre torre : lista) {
                i++;
                out.write("<tr>\n"
                        + "                                        <td class=\"plan_list_title\">" + i + "</td>\n"
                        + "                                        <td class=\"plan_list_title\">" + torre.getNombreTorre() + "</td>\n"
                        + "                                        <td class=\"price_body\">" + torre.getDireccion() + "</td>\n"
                        + "                                        <td class=\"price_body\">" + torre.getDueñoLocal() + "</td>\n"
                        + "                                        <td class=\"price_body\">" + torre.getTelefono() + "</td>\n"
                        + "                                        <td>\n"
                        + "                                            <div class=\"dropdown\">\n"
                        + "                                                <button class=\"btn btn-warning dropdown-toggle\" type=\"button\" id=\"dropdownMenuButton\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">\n"
                        + "                                                    Seleccione Accion\n"
                        + "                                                </button>\n"
                        + "                                                <div class=\"dropdown-menu dropdown-menu-right\" aria-labelledby=\"dropdownMenuButton\">\n"
                        + "                                                    <a id='btnEditar' class=\"dropdown-item\" href='" + torre.getIdTorre() + "' title=\"Editar\">Editar</a>\n"
                        + "                                                    <a id='btnEliminar' class=\"dropdown-item\" href='" + torre.getIdTorre() + "' title=\"Eliminar\">Eliminar</a>\n"
                        + "\n"
                        + "                                                </div>\n"
                        + "                                            </div>\n"
                        + "                                        </td>\n"
                        + "                                    </tr>");
            }
            out.write("</tbody>");
        }
        if (path.equals("/EditarTorres")) {
            int id = Integer.parseInt(request.getParameter("id"));
            Torre torre = Torredao.listarTorresId(id);
            out.write("<div class=\"modal-dialog\" role=\"document\">\n" +
"                <div class=\"modal-content\">\n" +
"                    <div class=\"modal-header\">\n" +
"                        <h5 class=\"modal-title\" id=\"exampleModalLabel\">Edicion de Torre</h5>\n" +
"                        <button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-label=\"Close\">\n" +
"                            <span aria-hidden=\"true\">&times;</span>\n" +
"                        </button>\n" +
"                    </div>\n" +
"                    <form  id=\"formUp\" action=\"ActualizarAntena\" method=\"POST\">\n" +
"                        <div class=\"modal-body\">\n" +
"                            <div class=\"form-group\">\n" +
"                                <label for=\"nombre\" style=\"color: black\">Ingrese nombre de la torre:</label>\n" +
"                                <input name=\"nomTorre\" style=\"color: black\" type=\"text\" placeholder=\"Nombre Torre\" value='"+torre.getNombreTorre()+"' class=\"field\" required>\n" +
"                                <input type=\"hidden\" name=\"idTorre\" value='"+torre.getIdTorre()+"'>\n" +
"                            </div>\n" +
"                            <div class=\"form-group\">\n" +
"                                <label for=\"nombre\" style=\"color: black\">Ingrese su direccion:</label>\n" +
"                                <input name=\"dirTorre\" style=\"color: black\" type=\"text\" placeholder=\"Direccion\" value='"+torre.getDireccion()+"' class=\"field\" required> \n" +
"                            </div>\n" +
"                            <div class=\"form-group\">\n" +
"                                <label for=\"nombre\" style=\"color: black\">ingrese nombre del dueño:</label>\n" +
"                                <input name=\"dueLocal\" style=\"color: black\" type=\"text\" placeholder=\"Dueño Local\" value='"+torre.getDueñoLocal()+"' class=\"field\" required>\n" +
"                            </div>\n" +
"                            <div class=\"form-group\">\n" +
"                                <label for=\"nombre\" style=\"color: black\">Ingrese telefono de referencia:</label>\n" +
"                                <input name=\"telefono\" style=\"color: black\" type=\"text\" placeholder=\"Telefono\" value='"+torre.getTelefono()+"' class=\"field\" required>\n" +
"                            </div>\n" +
"                        </div>\n" +
"                        <div class=\"modal-footer\">\n" +
"                            <button type=\"button\" class=\"btn btn-primary\" data-dismiss=\"modal\">Close</button>\n" +
"                            <button type=\"button\" class=\"btn btn-warning\" style=\"float: right; color: white;\" id=\"btnActualizar\">Actualizar</button>\n" +
"                        </div>\n" +
"                    </form>\n" +
"                </div>\n" +
"            </div>");
        }
        if (path.equals("/ActualizarTorres")) {
            int id = Integer.parseInt(request.getParameter("idTorre"));
            String nomTorre_n = request.getParameter("nomTorre").toUpperCase();
            String dirTorre_n = request.getParameter("dirTorre").toUpperCase();
            String dueñoLocal_n = request.getParameter("dueLocal").toUpperCase();
            String telefono_n = request.getParameter("telefono");
            t.setNombreTorre(nomTorre_n);
            t.setDueñoLocal(dueñoLocal_n);
            t.setDireccion(dirTorre_n);
            t.setTelefono(telefono_n);
            t.setIdTorre(id);
            if(Torredao.guardarTorre(t)){
                out.write("TRUE");
            }else{
                out.write("FALSE");
            }
        }
        if (path.equals("/EliminarTorres")) {
            int id = Integer.parseInt(request.getParameter("id"));
            if (Torredao.deleteTorre(id)) {
                out.write("Eliminado");
            } else {
                 out.write("No Eliminado");
            }
        }
        if (path.equals("/AgregarTorres")) {
            String nomTorre = request.getParameter("nomTorre").toUpperCase();
            String dirTorre = request.getParameter("dirTorre").toUpperCase();
            String dueñoLocal = request.getParameter("dueLocal").toUpperCase();
            String telefono = request.getParameter("telefono");
            t.setNombreTorre(nomTorre);
            t.setDueñoLocal(dueñoLocal);
            t.setDireccion(dirTorre);
            t.setTelefono(telefono);
            if (Torredao.insertarTorre(t)) {
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
