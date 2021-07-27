/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.Antenadao;
import dao.Clientedao;
import dao.EstadoClientedao;
import dao.MarcaAntenadao;
import dao.Personaldao;
import dao.Serviciodao;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Antena;
import modelo.Cliente;
import modelo.EstadoCliente;
import modelo.Fecha;
import modelo.MarcaAntena;
import modelo.Personal;
import modelo.Servicio;
import modelo.Usuario;

/**
 *
 * @author PIERO
 */
@WebServlet(name = "Servicios_srv", urlPatterns = {"/Servicios_srv", "/AgregarServicios", "/ListarServicios", "/ActualizarServicios", "/EliminarServicios", "/EditarServicios", "/BuscarServicios", "/DetalleServicio"})
public class Servicios_srv extends HttpServlet {

    Servicio ser = new Servicio();

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
        if (path.equals("/ListarServicios")) {
            ArrayList<Servicio> lista = Serviciodao.listarServicios();
            int i = 0;
            String fechaHoy = Fecha.fecha_actual();
            String style = null;
            out.write("<thead>\n"
                    + "                                <tr>\n"
                    + "                                    <th class=\"plans-list\"><h3>#</h3></th>\n"
                    + "                                    <th class=\"plans-list\"><h3>Cliente</h3></th>\n"
                    + "                                    <th class=\"plans-list\"><h3>Antena</h3></th>\n"
                    + "                                    <th class=\"plans-list\"><h3>Tarifa</h3></th>\n"
                    + "                                    <th class=\"plans-list\"><h3>Frecuencia</h3></th>\n"
                    + "                                    <th class=\"plans-list\"><h3>Ancho Banda</h3></th>\n"
                    + "                                    <th class=\"plans-list\"><h3>Estado</h3></th>\n"
                    + "                                    <th class=\"plans-list\"><h3>Acciones</h3></th>\n"
                    + "                                </tr>\n"
                    + "                            </thead>	");
            out.write("<tbody>");
            for (Servicio servicio : lista) {

                i++;
                if (fechaHoy.equals(servicio.getF_vencimiento())) {
                    Serviciodao.actualizarEstados(2, servicio.getIdServicio());
                    style = "style='background:green;color:white'";
                } else if (fechaHoy.equals(servicio.getF_corte())) {
                    Serviciodao.actualizarEstados(3, servicio.getIdServicio());
                    style = "style='background:red;text-color:white'";
                } else {
                    style = "style=' background-image: url(\"images/background.png\");	'";
                }

                Cliente cl = Clientedao.listarClienteId(servicio.getIdCliente());
                Antena an = Antenadao.listarAntenasId(servicio.getIdAntena());
                EstadoCliente est = EstadoClientedao.listarEstadoClienteId(servicio.getIdEstado());

                out.write("<tr " + style + ">\n"
                        + "                                    \n"
                        + "                                    <td class=\"plan_list_title\">" + i + "</td>\n"
                        + "                                    <td class=\"plan_list_title\">" + cl.getNombre() + " " + cl.getApellidoPaterno() + "</td>\n"
                        + "                                    <td class=\"plan_list_title\">" + an.getNombreAntena() + "</td>\n"
                        + "                                    <td class=\"plan_list_title\">" + servicio.getTarifa() +" "+"soles"+ "</td>\n"
                        + "                                    <td class=\"plan_list_title\">" + servicio.getFrecuencia() + "</td>\n"
                        + "                                    <td class=\"plan_list_title\">" + servicio.getAnchoBanda() + "</td>\n"
                        + "                                    <td class=\"plan_list_title\">" + est.getDescripcion() + "</td>\n"
                        + "\n"
                        + "                                    <td>\n"
                        + "                                        <div class=\"dropdown\">\n"
                        + "                                            <button class=\"btn btn-warning dropdown-toggle\" type=\"button\" id=\"dropdownMenuButton\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">\n"
                        + "                                                Seleccione Accion\n"
                        + "                                            </button>\n"
                        + "                                            <div class=\"dropdown-menu dropdown-menu-right\" aria-labelledby=\"dropdownMenuButton\">\n"
                        + "                                                <a class=\"dropdown-item\" id='btnDetalle' href='" + servicio.getIdServicio() + "' title=\"Editar\">Detalle de Servicio</a>\n"
                        + "                                                <a class=\"dropdown-item\" id='btnEditar' href='" + servicio.getIdServicio() + "' title=\"Eliminar\">Editar</a>\n"
                        + "                                                <a class=\"dropdown-item\" id='btnEliminar' href='" + servicio.getIdServicio() + "' title=\"Eliminar\">Eliminar</a>\n"
                        + "                                            </div>\n"
                        + "                                        </div>\n"
                        + "                                    </td>\n"
                        + "                                </tr>");
            }
            out.write("</tbody>");
        }
        if (path.equals("/DetalleServicio")) {
            int id = Integer.parseInt(request.getParameter("id"));
            Servicio servicio = Serviciodao.listarServiciosId(id);
            Cliente cliente = Clientedao.listarClienteId(servicio.getIdCliente());
            MarcaAntena marca = MarcaAntenadao.listarMarcaAntenaId(servicio.getIdMarca());
            Personal p = Personaldao.listarPersonalXId(servicio.getIdPersonal());
            out.write("<div class=\"modal-dialog\" role=\"document\">\n"
                    + "                <div class=\"modal-content\">\n"
                    + "                    <div class=\"modal-header\">\n"
                    + "                        <h5 class=\"modal-title\" id=\"exampleModalLabel\">Detalle de servicio</h5>\n"
                    + "                        <button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-label=\"Close\" id=\"btnExit1\">\n"
                    + "                            <span aria-hidden=\"true\">&times;</span>\n"
                    + "                        </button>\n"
                    + "                    </div>\n"
                    + "                    <form  id=\"form\" action=\"AgregarAntena\" method=\"POST\">\n"
                    + "                        <div class=\"modal-body\">\n"
                    + "                            <div class=\"form-group\">\n"
                    + "                                <label for=\"nombre\" style=\"color: black\">Cliente: " + cliente.getNombre() + " " + cliente.getApellidoPaterno() + "</label>\n"
                    + "                            </div>\n"
                    + "                            <div class=\"form-group\">\n"
                    + "                                <label for=\"nombre\" style=\"color: black\">Dni: " + cliente.getDNI() + "</label>\n"
                    + "                            </div>\n"
                    + "                            <div class=\"form-group\">\n"
                    + "                                <label for=\"nombre\" style=\"color: black\">Fecha de Inicio: " + servicio.getF_inicio() + "</label>\n"
                    + "                            </div>\n"
                    + "                            <div class=\"form-group\">\n"
                    + "                                <label for=\"nombre\" style=\"color: black\">Fecha de Vencimiento: " + servicio.getF_vencimiento() + "</label>\n"
                    + "                            </div>\n"
                    + "                            <div class=\"form-group\">\n"
                    + "                                <label for=\"nombre\" style=\"color: black\">Fecha de Corte: " + servicio.getF_corte() + "</label>\n"
                    + "                            </div>\n"
                    + "                            <div class=\"form-group\">\n"
                    + "                                <label for=\"nombre\" style=\"color: black\">Condicion de Antena: " + servicio.getCondicionAntena() + "</label>\n"
                    + "                            </div>\n"
                    + "                            <div class=\"form-group\">\n"
                    + "                                <label for=\"nombre\" style=\"color: black\">Marca de antena: " + marca.getMarca() + "</label>\n"
                    + "                            </div>\n"
                    + "                        <div class=\"modal-footer\">\n"
                    + "                            <button type=\"button\" class=\"btn btn-primary\" data-dismiss=\"modal\" id=\"btnClose1\">Close</button>\n"
                    + "                        </div>\n"
                    + "                    </form>\n"
                    + "                </div>\n"
                    + "            </div>");
        }
        if (path.equals("/ActualizarServicios")) {

            String f_inicio_add_n = request.getParameter("fechaInicio");
            String f_fin_add_n = request.getParameter("fechaFin");
            String f_corte_add_n = request.getParameter("fechaCorte");
            String ip_n = request.getParameter("cmbo_ip");
            String mac_n = request.getParameter("cmbo_mac");
            String frec_n = request.getParameter("cmbo_frecuencia");
            String anchoBanda_n = request.getParameter("cmbo_anchoBanda");
            int idServicio = Integer.parseInt(request.getParameter("idServicio"));
            int idAntena_n = Integer.parseInt(request.getParameter("cmbo_antena"));
            int marca_n = Integer.parseInt(request.getParameter("cmbo_marca"));
            String condicion_n = request.getParameter("cmbo_condicion");
            int tarifa_n = 0;
            if (anchoBanda_n.equals("512 Kbps") && frec_n.equals("2.4 GHz")) {
                tarifa_n = 50;
            } else if (anchoBanda_n.equals("1 Mbps") && frec_n.equals("2.4 GHz")) {
                tarifa_n = 75;
            } else if (anchoBanda_n.equals("1 Mbps") && frec_n.equals("5.8 GHz")) {
                tarifa_n = 100;
            } else if (anchoBanda_n.equals("2 Mbps") && frec_n.equals("5.8 GHz")) {
                tarifa_n = 125;
            } else if (anchoBanda_n.equals("2 Mbps") && frec_n.equals("2.4 GHz")) {
                tarifa_n = 115;
            } else if (anchoBanda_n.equals("512 Kbps") && frec_n.equals("5.8 GHz")) {
                tarifa_n = 105;
            }
            ser.setF_vencimiento(f_fin_add_n);
            ser.setF_inicio(f_inicio_add_n);
            ser.setF_corte(f_corte_add_n);
            ser.setCondicionAntena(condicion_n);
            ser.setMac(mac_n);
            ser.setIp(ip_n);
            ser.setFrecuencia(frec_n);
            ser.setAnchoBanda(anchoBanda_n);
            ser.setIdMarca(marca_n);
            ser.setTarifa(tarifa_n);
            ser.setIdAntena(idAntena_n);
            Servicio servi = Serviciodao.listarServiciosId(idServicio);
            ser.setIdCliente(servi.getIdCliente());
            HttpSession s = request.getSession();
            Usuario us = (Usuario) s.getAttribute("us");
            ser.setIdPersonal(us.getIdPersonal());
            ser.setIdServicio(idServicio);
            if (Serviciodao.guardar_srv(ser)) {
                out.write("TRUE");
            } else {
                out.write("FALSE");
            }
        }
        if (path.equals("/EditarServicios")) {
            int id = Integer.parseInt(request.getParameter("id"));
            Servicio servicio = Serviciodao.listarServiciosId(id);
            Cliente cliente = Clientedao.listarClienteId(servicio.getIdCliente());
            String result = null;
            ArrayList<Antena> lista_antena = Antenadao.listarAntenas();
            ArrayList<MarcaAntena> lista_marcas = MarcaAntenadao.listarMarcaAntena();
            ArrayList<String> lista_bandas = new ArrayList<>();
            lista_bandas.add("512 Kbps");
            lista_bandas.add("1 Mbps");
            lista_bandas.add("2 Mbps");
            ArrayList<String> lista_frecuencia = new ArrayList<>();
            lista_frecuencia.add("2.4 GHz");
            lista_frecuencia.add("5.8 GHz");
            ArrayList<String> lista_mac = new ArrayList<>();
            lista_mac.add("C0:3F:D5:4B:E3:E");
            lista_mac.add("00:23:CD:20:0E:4");
            lista_mac.add("F8:D1:11:7D:A0:C");
            lista_mac.add("64:66:B3:7A:F8:D");
            ArrayList<String> lista_ip = new ArrayList<>();
            for (int i = 10; i <= 30; i++) {
                lista_ip.add("192.168." + i);
            }
            out.write("<div class=\"modal-dialog\" role=\"document\">\n"
                    + "                <div class=\"modal-content\">\n"
                    + "                    <div class=\"modal-header\">\n"
                    + "                        <h5 class=\"modal-title\" id=\"exampleModalLabel\">Edicion de Servicio</h5>\n"
                    + "                        <button type=\"button\" class=\"close\" id=\"btnExit2\" data-dismiss=\"modal\" aria-label=\"Close\">\n"
                    + "                            <span aria-hidden=\"true\">&times;</span>\n"
                    + "                        </button>\n"
                    + "                    </div>\n"
                    + "                    <<form  id=\"formUp\" action=\"ActualizarAntena\" method=\"POST\">\n"
                    + "                        <div class=\"modal-body\">\n"
                    + "                            <div class=\"form-group\">\n"
                    + "                                <label for=\"nombre\" style=\"color: black\">Cliente: " + cliente.getNombre() + " " + cliente.getApellidoPaterno() + "</label>\n"
                    + "                                <input type=\"hidden\" name=\"idServicio\" value='" + servicio.getIdServicio() + "'>\n"
                    + "                            </div>\n"
                    + "                            <div class=\"form-group\">\n"
                    + "                                <label for=\"nombre\" style=\"color: black\">Fecha de Inicio:</label>\n"
                    + "                                <input name=\"fechaInicio\" style=\"color: black\" type=\"date\"  value='" + servicio.getF_inicio() + "' class=\"field\" >\n"
                    + "                            </div>\n"
                    + "                            <div class=\"form-group\">\n"
                    + "                                <label for=\"nombre\" style=\"color: black\">Fecha de Vencimiento:</label>\n"
                    + "                                <input name=\"fechaFin\" style=\"color: black\" type=\"date\"  value='" + servicio.getF_vencimiento() + "'  class=\"field\" >\n"
                    + "                            </div>\n"
                    + "                            <div class=\"form-group\">\n"
                    + "                                <label for=\"nombre\" style=\"color: black\">Fecha de Corte:</label>\n"
                    + "                                <input name=\"fechaCorte\" style=\"color: black\" type=\"date\"  value='" + servicio.getF_corte() + "' class=\"field\" >\n"
                    + "                            </div>");

            out.write(" <div class=\"form-group\">\n"
                    + "                                <label for=\"nombre\" style=\"color: black\">Seleccion IP:</label>\n"
                    + "                                <select id=\"country\" name=\"cmbo_ip\" onchange=\"change_country(this.value)\" class=\"frm-field required\">");
            for (int i = 0; i < lista_ip.size(); i++) {
                out.write("<option ");
                if (servicio.getIp().equals(lista_ip.get(i))) {
                    result = "selected";
                } else {
                    result = "";
                }
                out.write(result + " " + "value='" + lista_ip.get(i) + "'>" + lista_ip.get(i) + "</option>  ");
            }
            out.write(" </select> </div>");
            out.write(" <div class=\"form-group\">\n"
                    + "                                <label for=\"nombre\" style=\"color: black\">Seleccion MAC:</label>\n"
                    + "                                <select id=\"country\" name=\"cmbo_mac\" onchange=\"change_country(this.value)\" class=\"frm-field required\">");
            for (int i = 0; i < lista_mac.size(); i++) {
                out.write("<option ");
                if (servicio.getMac().equals(lista_mac.get(i))) {
                    result = "selected";
                } else {
                    result = "";
                }
                out.write(result + " " + "value='" + lista_mac.get(i) + "'>" + lista_mac.get(i) + "</option>  ");
            }
            out.write(" </select> </div>");
            out.write("  <div class=\"form-group\">\n"
                    + "                                <label for=\"nombre\" style=\"color: black\">Seleccion Frecuencia:</label>\n"
                    + "                                <select id=\"country\" name=\"cmbo_frecuencia\" onchange=\"change_country(this.value)\" class=\"frm-field required\">");
            for (int i = 0; i < lista_frecuencia.size(); i++) {
                out.write("<option ");
                if (servicio.getFrecuencia().equals(lista_frecuencia.get(i))) {
                    result = "selected";
                } else {
                    result = "";
                }
                out.write(result + " " + "value='" + lista_frecuencia.get(i) + "'>" + lista_frecuencia.get(i) + "</option>  ");
            }
            out.write(" </select>\n"
                    + "                            </div>");
            out.write("   <div class=\"form-group\">\n"
                    + "                                <label for=\"nombre\" style=\"color: black\">Seleccione Ancho de Banda:</label>\n"
                    + "                                <select id=\"country\" name=\"cmbo_anchoBanda\" onchange=\"change_country(this.value)\" class=\"frm-field required\">");
            for (int i = 0; i < lista_bandas.size(); i++) {
                out.write("<option ");
                if (servicio.getAnchoBanda().equals(lista_bandas.get(i))) {
                    result = "selected";
                } else {
                    result = "";
                }
                out.write(result + " " + "value='" + lista_bandas.get(i) + "'>" + lista_bandas.get(i) + "</option>  ");
            }
            out.write(" </select>\n"
                    + "                            </div>");
            out.write("<div class=\"form-group\">\n"
                    + "                                <label for=\"nombre\" style=\"color: black\">Seleccione Antena:</label>\n"
                    + "                                <select id=\"country\" name=\"cmbo_antena\" onchange=\"change_country(this.value)\" class=\"frm-field required\">");
            for (Antena antena : lista_antena) {
                out.write("<option ");
                if (servicio.getIdAntena() == antena.getIdAntena()) {
                    result = "selected";
                } else {
                    result = "";
                }
                out.write(result + " " + "value=" + antena.getIdAntena() + ">" + antena.getNombreAntena() + "</option>");
            }
            out.write(" </select>\n"
                    + "                            </div>");
            out.write("<div class=\"form-group\">\n"
                    + "                                <label for=\"nombre\" style=\"color: black\">Seleccione Marca de Antena:</label>\n"
                    + "                                <select id=\"country\" name=\"cmbo_marca\" onchange=\"change_country(this.value)\" class=\"frm-field required\">");
            for (MarcaAntena lista_marca : lista_marcas) {
                out.write("<option ");
                if (servicio.getIdMarca() == lista_marca.getIdMarca()) {
                    result = "selected";
                } else {
                    result = "";
                }
                out.write(result + " " + "value=" + lista_marca.getIdMarca() + ">" + lista_marca.getMarca() + "</option>");
            }
            out.write(" </select>\n"
                    + "                            </div>");
            out.write(" <div class=\"form-group\">\n"
                    + "                                <label for=\"nombre\" style=\"color: black\">Seleccione Condicion de Antena:</label>\n"
                    + "                                <select id=\"country\" name=\"cmbo_condicion\" onchange=\"change_country(this.value)\" class=\"frm-field required\">\n"
                    + "                                    <option value=\"Propia\">Propia</option>  \n"
                    + "                                    <option value=\"Alquilada\">Alquilada</option>     \n"
                    + "                                </select> \n"
                    + "                            </div>\n"
                    + "                        </div>\n"
                    + "                        <div class=\"modal-footer\">\n"
                    + "                            <button type=\"button\" class=\"btn btn-primary\" data-dismiss=\"modal\" id=\"btnClose2\">Close</button>\n"
                    + "                            <button type=\"button\" class=\"btn btn-warning\" style=\"float: right; color: white;\" id=\"btnActualizar\">Actualizar</button>\n"
                    + "                        </div>\n"
                    + "                    </form>\n"
                    + "                </div>\n"
                    + "            </div>");
        }
        if (path.equals("/EliminarServicios")) {
            int id = Integer.parseInt(request.getParameter("id"));
            if (Serviciodao.delete_srv(id)) {
                out.write("Eliminado");
            } else {
                out.write("No Eliminado");
            }
        }
        if (path.equals("/AgregarServicios")) {
            String dniAdd = request.getParameter("dniSol");
            Cliente clienteAdd = Clientedao.listarClienteDni(dniAdd);
            String f_inicio_add = request.getParameter("fechaInicio");
            String f_fin_add = request.getParameter("fechaFin");
            String f_corte_add = request.getParameter("fechaCorte");
            String ip = request.getParameter("cmbo_ip");
            String mac = request.getParameter("cmbo_mac");
            String frec = request.getParameter("cmbo_frecuencia");
            String anchoBanda = request.getParameter("cmbo_anchoBanda");
            int idAntena = Integer.parseInt(request.getParameter("cmbo_antena"));
            int marca = Integer.parseInt(request.getParameter("cmbo_marca"));
            String condicion = request.getParameter("cmbo_condicion");
            int tarifa = 0;
            if (anchoBanda.equals("512 Kbps") && frec.equals("2.4 GHz")) {
                tarifa = 50;
            } else if (anchoBanda.equals("1 Mbps") && frec.equals("2.4 GHz")) {
                tarifa = 75;
            } else if (anchoBanda.equals("1 Mbps") && frec.equals("5.8 GHz")) {
                tarifa = 100;
            } else if (anchoBanda.equals("2 Mbps") && frec.equals("5.8 GHz")) {
                tarifa = 125;
            } else if (anchoBanda.equals("2 Mbps") && frec.equals("2.4 GHz")) {
                tarifa = 115;
            } else if (anchoBanda.equals("512 Kbps") && frec.equals("5.8 GHz")) {
                tarifa = 105;
            }
            ser.setF_vencimiento(f_fin_add);
            ser.setF_inicio(f_inicio_add);
            ser.setF_corte(f_corte_add);
            ser.setCondicionAntena(condicion);
            ser.setMac(mac);
            ser.setIp(ip);
            ser.setFrecuencia(frec);
            ser.setAnchoBanda(anchoBanda);
            ser.setIdMarca(marca);
            ser.setTarifa(tarifa);
            ser.setIdAntena(idAntena);
            ser.setIdCliente(clienteAdd.getIdCliente());
            HttpSession s = request.getSession();
            Usuario us = (Usuario) s.getAttribute("us");
            ser.setIdPersonal(us.getIdPersonal());
            if (Serviciodao.insertar_srv(ser)) {
                out.write("TRUE");
            } else {
                out.write("FALSE");
            }
        }
        if (path.equals("/BuscarServicios")) {
            String dni = request.getParameter("dni");
            Cliente cliente = Clientedao.buscar(dni);
            Servicio servicio = Serviciodao.listarServiciosCodCliente(dni);
            if (servicio != null) {
                out.write("FALSE");
            } else if (servicio == null && cliente != null) {
                out.write(" <label for=\"nombre\" style=\"color: black\">Cliente: " + cliente.getNombre() + " " + cliente.getApellidoPaterno() + "</label>");
            } else {
                out.write("");
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
