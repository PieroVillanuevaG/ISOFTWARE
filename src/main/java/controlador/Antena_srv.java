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
                        + "                                                <a id='btnEditar' class=\"dropdown-item\" href='EditarAntena?id=" + antena.getIdAntena() + "' title=\"Editar\">Editar</a>\n"
                        + "                                                <a id='btnEliminar' class=\"dropdown-item\" href='EliminarAntena?id=" + antena.getIdAntena() + "' title=\"Eliminar\">Eliminar</a>\n"
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
            Antena ant = Antenadao.listarAntenasId(id);
            request.setAttribute("antena", ant);
            request.getRequestDispatcher("antena.jsp").forward(request, response);
        }
        if (path.equals("/EliminarAntena")) {
            int id = Integer.parseInt(request.getParameter("id"));
            if (Antenadao.deleteAntena(id)) {
                request.setAttribute("msg", "Eliminado");
                request.getRequestDispatcher("antena.jsp").forward(request, response);
            } else {
                request.setAttribute("msg", "No eliminado");
                request.getRequestDispatcher("antena.jsp").forward(request, response);
            }
        }
        if (path.equals("/AgregarAntena")) {
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
        if (path.equals("/ActualizarAntena")) {
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
