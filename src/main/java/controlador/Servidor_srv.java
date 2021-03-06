/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.Servidordao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Servidor;

/**
 *
 * @author PIERO
 */
@WebServlet(name = "Servidor_srv", urlPatterns = {"/Servidor_srv", "/ListarServidores", "/AgregarServidores", "/EditarServidores", "/ActualizarServidores", "/EliminarServidores"})
public class Servidor_srv extends HttpServlet {

    Servidor s = new Servidor();

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
        if (path.equals("/ListarServidores")) {
            ArrayList<Servidor> lista = Servidordao.listarServidores();
            int i = 0;
            out.write("<thead>\n"
                    + "                                <tr>\n"
                    + "                                    <th class=\"plans-list\"><h3>#</h3></th>\n"
                    + "                                    <th class=\"plans-list\"><h3>Nombre Servidor</h3></th>\n"
                    + "                                    <th class=\"plans-list\"><h3>IP Entrada</h3></th>\n"
                    + "                                    <th class=\"plans-list\"><h3>IP Salida</h3></th>\n"
                    + "                                    <th class=\"plans-list\"><h3>Acciones</h3></th>\n"
                    + "\n"
                    + "                                </tr>\n"
                    + "                            </thead>");
            out.write("<tbody>");
            for (Servidor servidor : lista) {
                i++;
                out.write("<tr>\n"
                        + "                                        <td class=\"plan_list_title\">" + i + "</td>\n"
                        + "                                        <td class=\"plan_list_title\">" + servidor.getNombreServidor() + "</td>\n"
                        + "                                        <td class=\"price_body\">" + servidor.getIpEntrada() + "</td>\n"
                        + "                                        <td class=\"price_body\">" + servidor.getIpSalida() + "</td>\n"
                        + "                                        <td>\n"
                        + "                                            <div class=\"dropdown\">\n"
                        + "                                                <button class=\"btn btn-warning dropdown-toggle\" type=\"button\" id=\"dropdownMenuButton\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">\n"
                        + "                                                    Seleccione Accion\n"
                        + "                                                </button>\n"
                        + "                                                <div class=\"dropdown-menu dropdown-menu-right\" aria-labelledby=\"dropdownMenuButton\">\n"
                        + "                                                    <a id='btnEditar' class=\"dropdown-item\" href='" + servidor.getIdServidor() + "' title=\"Editar\">Editar</a>\n"
                        + "                                                    <a id ='btnEliminar' class=\"dropdown-item\" href='" + servidor.getIdServidor() + "' title=\"Eliminar\">Eliminar</a>\n"
                        + "\n"
                        + "                                                </div>\n"
                        + "                                            </div>\n"
                        + "                                        </td>\n"
                        + "                                    </tr>");
            }
            out.write("</tbody>");
        }
        if (path.equals("/ActualizarServidores")) {
            int id = Integer.parseInt(request.getParameter("idServidor"));
            String nomServidor_n = request.getParameter("nomServidor").toUpperCase();
            String ipEntrada_n = request.getParameter("ipEntrada");
            String ipSalida_n = request.getParameter("ipSalida");
            s.setNombreServidor(nomServidor_n);
            s.setIpEntrada(ipEntrada_n);
            s.setIpSalida(ipSalida_n);
            s.setIdServidor(id);
            if(Servidordao.guardarServidor(s)){
                out.write("TRUE");
            }else{
                out.write("FALSE");
            }
        }
        if (path.equals("/EditarServidores")) {
            int id = Integer.parseInt(request.getParameter("id"));
            Servidor servidor = Servidordao.listarServidoresId(id);
            out.write("<div class=\"modal-dialog\" role=\"document\">\n" +
"                <div class=\"modal-content\">\n" +
"                    <div class=\"modal-header\">\n" +
"                        <h5 class=\"modal-title\" id=\"exampleModalLabel\">Edicion de Servidor</h5>\n" +
"                        <button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-label=\"Close\">\n" +
"                            <span aria-hidden=\"true\">&times;</span>\n" +
"                        </button>\n" +
"                    </div>\n" +
"                    <form  id=\"formUp\" action=\"ActualizarAntena\" method=\"POST\">\n" +
"                        <div class=\"modal-body\">\n" +
"                            <div class=\"form-group\">\n" +
"                                <label for=\"nombre\" style=\"color: black\">Ingrese nombre de servidor:</label>\n" +
"                                <input name=\"nomServidor\" style=\"color: black\" type=\"text\" placeholder=\"Nombre Servidor\" value='"+servidor.getNombreServidor()+"' class=\"field\" required><br>\n" +
"                                <input type=\"hidden\" name=\"idServidor\" value='"+servidor.getIdServidor()+"'>\n" +
"                            </div>\n" +
"                            <div class=\"form-group\">\n" +
"                                <label for=\"nombre\" style=\"color: black\">Ingrese ip de entrada:</label>\n" +
"                                <input name=\"ipEntrada\"  style=\"color: black\" type=\"text\" placeholder=\"IP Entrada\" value='"+servidor.getIpEntrada()+"' class=\"field\" required><br>\n" +
"                            </div>\n" +
"                            <div class=\"form-group\">\n" +
"                                <label for=\"nombre\" style=\"color: black\">ingrese ip de salida:</label>\n" +
"                                <input name=\"ipSalida\" style=\"color: black\" type=\"text\" placeholder=\"IP Salida\" value='"+servidor.getIpSalida()+"' class=\"field\" required><br>\n" +
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
        if (path.equals("/EliminarServidores")) {
            int id = Integer.parseInt(request.getParameter("id"));
            if (Servidordao.deleteServidor(id)) {
                out.write("Eliminado");
            } else {
                out.write("No eliminado");
            }
        }
        if (path.equals("/AgregarServidores")) {
            String nomServidor = request.getParameter("nomServidor").toUpperCase();
            String ipEntrada = request.getParameter("ipEntrada");
            String ipSalida = request.getParameter("ipSalida");
            s.setNombreServidor(nomServidor);
            s.setIpEntrada(ipEntrada);
            s.setIpSalida(ipSalida);
            if (Servidordao.insertarServidor(s)) {
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
