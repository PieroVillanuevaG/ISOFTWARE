/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.Antenadao;
import dao.Servidordao;
import dao.TipoAntenadao;
import dao.Torredao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Antena;
import modelo.Servidor;
import modelo.TipoAntena;
import modelo.Torre;

/**
 *
 * @author PIERO
 */
@WebServlet(name = "Antena_srv", urlPatterns = {"/Antena_srv", "/ListarAntena", "/EditarAntena", "/EliminarAntena", "/AgregarAntena", "/ActualizarAntena"})
public class Antena_srv extends HttpServlet {

    Antena ant = new Antena();

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
        if (path.equals("/ListarAntena")) {
            ArrayList<Antena> lista = Antenadao.listarAntenas();
            out.write("<thead>\n"
                    + "                                <tr>\n"
                    + "                                    <th class=\"plans-list\"><h3>#</h3></th>\n"
                    + "                                    <th class=\"plans-list\"><h3>Nombre Antena</h3></th>\n"
                    + "                                    <th class=\"plans-list\"><h3>Ip</h3></th>\n"
                    + "                                    <th class=\"plans-list\"><h3>Canal</h3></th>\n"
                    + "                                    <th class=\"plans-list\"><h3>Servidor</h3></th>\n"
                    + "                                    <th class=\"plans-list\"><h3>Torre</h3></th>\n"
                    + "                                    <th class=\"plans-list\"><h3>Tipo Antena</h3></th>\n"
                    + "                                    <th class=\"plans-list\"><h3>Acciones</h3></th>\n"
                    + "\n"
                    + "                                </tr>\n"
                    + "                            </thead>");
            int i = 0;
            out.write("<tbody>");
            for (Antena antena : lista) {
                i++;
                TipoAntena ta = TipoAntenadao.listarTiposId(antena.getIdTipo());
                Servidor srv = Servidordao.listarServidoresId(antena.getIdServidor());
                Torre t = Torredao.listarTorresId(antena.getIdTorre());
                out.write("<tr>");
                out.write("<td class=\"plan_list_title\">" + i + "</td>\n"
                        + "                                    <td class=\"plan_list_title\">" + antena.getNombreAntena() + "</td>\n"
                        + "                                    <td class=\"price_body\">" + antena.getIp() + "</td>\n"
                        + "                                    <td class=\"price_body\">" + antena.getCanal() + "</td>\n"
                        + "                                    <td class=\"price_body\">" + srv.getNombreServidor() + "</td>\n"
                        + "                                    <td class=\"price_body\">" + t.getNombreTorre() + "</td>\n"
                        + "                                    <td class=\"price_body\">" + ta.getTipoAntena() + "</td>\n"
                        + "                                    <td>\n"
                        + "                                        <div class=\"dropdown\">\n"
                        + "                                            <button class=\"btn btn-warning dropdown-toggle\" type=\"button\" id=\"dropdownMenuButton\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">\n"
                        + "                                                Seleccione Accion\n"
                        + "                                            </button>\n"
                        + "                                            <div class=\"dropdown-menu dropdown-menu-right\" aria-labelledby=\"dropdownMenuButton\">\n"
                        + "                                                <a id='btnEditar' class=\"dropdown-item\" href='" + antena.getIdAntena() + "' title=\"Editar\">Editar</a>\n"
                        + "                                                <a id='btnEliminar' class=\"dropdown-item\" href='" + antena.getIdAntena() + "' title=\"Eliminar\">Eliminar</a>\n"
                        + "\n"
                        + "                                            </div>\n"
                        + "                                        </div>\n"
                        + "                                    </td>");
                out.write("</tr>");
            }
            out.write("</tbody>");
        }
        if (path.equals("/EditarAntena")) {
            int id = Integer.parseInt(request.getParameter("id"));
            ArrayList<Torre> lista_torre = Torredao.listarTorres();
            ArrayList<Servidor> lista_servidor = Servidordao.listarServidores();
            ArrayList<TipoAntena> lista_tipo = TipoAntenadao.listarTipos();
            String result = null;
            Antena ant = Antenadao.listarAntenasId(id);
            out.write("<div class=\"modal-dialog\" role=\"document\">\n"
                    + "                <div class=\"modal-content\">\n"
                    + "                    <div class=\"modal-header\">\n"
                    + "                        <h5 class=\"modal-title\" id=\"exampleModalLabel\">Edicion de Antena</h5>\n"
                    + "                        <button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-label=\"Close\">\n"
                    + "                            <span aria-hidden=\"true\">&times;</span>\n"
                    + "                        </button>\n"
                    + "                    </div>\n"
                    + "	<form  id=\"formUp\" action=\"ActualizarAntena\" method=\"POST\">\n"
                    + "                        <div class=\"modal-body\">\n"
                    + "                            <div class=\"form-group\">\n"
                    + "                                <label for=\"nombre\" style=\"color: black\">Ingrese su nombre de la antena:</label>\n"
                    + "                                <input name=\"nomAntena\" style=\"color: black;\" type=\"text\" placeholder=\"Nombre Antena\" class=\"field\" required value='" + ant.getNombreAntena() + "'><br>\n"
                    + "                                <input type=\"hidden\" name=\"idAntena\" value='" + ant.getIdAntena() + "'>\n"
                    + "                            </div>\n"
                    + "                            <div class=\"form-group\">\n"
                    + "                                <label for=\"nombre\" style=\"color: black\">Ingrese su IP:</label>\n"
                    + "                                <input name=\"ip\" style=\"color: black;\" type=\"text\" placeholder=\"Ip\" class=\"field\" required value='" + ant.getIp() + "'><br>\n"
                    + "                            </div>\n"
                    + "                            <div class=\"form-group\">\n"
                    + "                                <label for=\"nombre\" style=\"color: black\">ingrese su Mac:</label>\n"
                    + "                                <input name=\"mac\" style=\"color: black;\" type=\"text\" placeholder=\"Mac\" class=\"field\" required value='" + ant.getMac() + "'><br>\n"
                    + "                            </div>\n"
                    + "                            <div class=\"form-group\">\n"
                    + "                                <label for=\"nombre\" style=\"color: black\">Ingrese su Frecuencia:</label>\n"
                    + "                                <input name=\"frecuencia\" style=\"color: black;\" type=\"text\" placeholder=\"Frecuencia\" class=\"field\" required value='" + ant.getFrecuencia() + "'><br> \n"
                    + "                            </div>\n"
                    + "                            <div class=\"form-group\">\n"
                    + "                                <label for=\"nombre\" style=\"color: black\">Ingrese su Canal:</label>\n"
                    + "                                <input name=\"canal\" style=\"color: black;\" type=\"text\" placeholder=\"Canal\" class=\"field\" required value='" + ant.getCanal() + "'><br>\n"
                    + "                            </div>\n"
                    + "                            <div class=\"form-group\">\n"
                    + "                                <label for=\"nombre\" style=\"color: black\">Ingrese su password configuracion:</label>\n"
                    + "                                <input name=\"passConfig\" style=\"color: black;\" type=\"text\" placeholder=\"Password Configuracion\" class=\"field\" value='" + ant.getPasswConfig() + "' required><br>\n"
                    + "                            </div>\n"
                    + "                            <div class=\"form-group\">\n"
                    + "                                <label for=\"nombre\" style=\"color: black\">Ingrese su password conexion:</label>\n"
                    + "                                <input name=\"passConec\" style=\"color: black;\" type=\"text\" placeholder=\"Password Conexion\" class=\"field\" value='" + ant.getPasswConeccion() + "' required><br>\n"
                    + "                            </div>");
            out.write("  <div class=\"form-group\">\n"
                    + "                                <label for=\"nombre\" style=\"color: black\">Ingrese su Servidor:</label>\n"
                    + "                                <select id=\"country\" name=\"cmbo_servidor\" onchange=\"change_country(this.value)\" class=\"frm-field required\">");
            for (Servidor servidor : lista_servidor) {
                out.write(" <option ");
                if (servidor.getIdServidor() == ant.getIdServidor()) {
                    result = "selected";
                } else {
                    result = "";
                }
                out.write(result + " " + "value=" + servidor.getIdServidor() + ">" + servidor.getNombreServidor() + "</option> ");
            }
            out.write(" </select>\n"
                    + "                            </div>");
            out.write("<div class=\"form-group\">\n"
                    + "                                <label for=\"nombre\" style=\"color: black\">Ingrese su Torre:</label>\n"
                    + "                                <select id=\"country\" name=\"cmbo_torre\" onchange=\"change_country(this.value)\" class=\"frm-field required\">");
            for (Torre torre : lista_torre) {
                out.write(" <option ");
                if (torre.getIdTorre() == ant.getIdTorre()) {
                    result = "selected";
                } else {
                    result = "";
                }
                out.write(result + " " + "value=" + torre.getIdTorre() + ">" + torre.getNombreTorre() + "</option>  ");
            }
            out.write(" </select>\n"
                    + "                            </div>");
            out.write(" <div class=\"form-group\">\n"
                    + "                                <label for=\"nombre\" style=\"color: black\">Tipo de antena:</label>\n"
                    + "                                <select id=\"country\" name=\"cmbo_tipos\" onchange=\"change_country(this.value)\" class=\"frm-field required\">");
            for (TipoAntena tipoAntena : lista_tipo) {
                out.write(" <option ");
                if (tipoAntena.getIdtipo() == ant.getIdTipo()) {
                    result = "selected";
                } else {
                    result = "";
                }
                out.write(result + " " + "value=" + tipoAntena.getIdtipo() + ">" + tipoAntena.getTipoAntena() + "</option>  ");
            }
            out.write(" </select>\n"
                    + "                            </div>\n"
                    + "                        </div>\n"
                    + "\n"
                    + "                        <div class=\"modal-footer\">\n"
                    + "                            <button type=\"button\" class=\"btn btn-primary\" data-dismiss=\"modal\">Close</button>\n"
                    + "                            <button type=\"button\" class=\"btn btn-warning\" style=\"float: right; color: white;\" id=\"btnActualizar\">Actualizar</button>\n"
                    + "                        </div>\n"
                    + "                    </form>\n"
                    + "                </div>\n"
                    + "            </div>");
        }

        if (path.equals(
                "/EliminarAntena")) {
            int id = Integer.parseInt(request.getParameter("id"));
            if (Antenadao.deleteAntena(id)) {
                out.write("Eliminado");
            } else {
                out.write("No eliminado");
            }
        }

        if (path.equals(
                "/AgregarAntena")) {
            String nomAntena = request.getParameter("nomAntena").toUpperCase();
            String ip = request.getParameter("ip");
            String mac = request.getParameter("mac");
            String frecuencia = request.getParameter("frecuencia");
            String canal = request.getParameter("canal");
            String passConfig = request.getParameter("passConfig");
            String passConec = request.getParameter("passConec");
            int idServidor = Integer.parseInt(request.getParameter("cmbo_servidor"));
            int idTorre = Integer.parseInt(request.getParameter("cmbo_torre"));
            int idTipo = Integer.parseInt(request.getParameter("cmbo_tipos"));
            ant.setNombreAntena(nomAntena);
            ant.setIp(ip);
            ant.setMac(mac);
            ant.setFrecuencia(frecuencia);
            ant.setCanal(canal);
            ant.setPasswConfig(passConfig);
            ant.setPasswConeccion(passConec);
            ant.setIdServidor(idServidor);
            ant.setIdTorre(idTorre);
            ant.setIdTipo(idTipo);
            if (Antenadao.insertarAntena(ant)) {
                out.write("TRUE");
            } else {
                out.write("FALSE");
            }
        }

        if (path.equals(
                "/ActualizarAntena")) {
            int id = Integer.parseInt(request.getParameter("idAntena"));
            String nomAntena = request.getParameter("nomAntena").toUpperCase();
            String ip = request.getParameter("ip");
            String mac = request.getParameter("mac");
            String frecuencia = request.getParameter("frecuencia");
            String canal = request.getParameter("canal");
            String passConfig = request.getParameter("passConfig");
            String passConec = request.getParameter("passConec");
            int idServidor_n = Integer.parseInt(request.getParameter("cmbo_servidor"));
            int idTorre_n = Integer.parseInt(request.getParameter("cmbo_torre"));
            int idTipo_n = Integer.parseInt(request.getParameter("cmbo_tipos"));
            ant.setNombreAntena(nomAntena);
            ant.setIp(ip);
            ant.setMac(mac);
            ant.setFrecuencia(frecuencia);
            ant.setCanal(canal);
            ant.setPasswConfig(passConfig);
            ant.setPasswConeccion(passConec);
            ant.setIdAntena(id);
            ant.setIdServidor(idServidor_n);
            ant.setIdTorre(idTorre_n);
            ant.setIdTipo(idTipo_n);
            if (Antenadao.guardarAntena(ant)) {
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
