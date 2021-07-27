/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.Antenadao;
import dao.Clientedao;
import dao.Pagodao;
import dao.Serviciodao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Antena;
import modelo.Cliente;
import modelo.Fecha;
import modelo.Pago;
import modelo.Servicio;
import modelo.Usuario;

/**
 *
 * @author PIERO
 */
@WebServlet(name = "Pago_srv", urlPatterns = {"/Pago_srv", "/ListarPagos", "/EditarPagos", "/ActualizarPagos", "/EliminarPagos", "/AgregarPagos", "/BuscarCliente", "/DetallePago"})
public class Pago_srv extends HttpServlet {

    Pago pago = new Pago();

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
        if (path.equals("/ListarPagos")) {
            ArrayList<Pago> lista = Pagodao.listarPagos();
            out.write("<thead>\n"
                    + "                                <tr>\n"
                    + "                                    <th class=\"plans-list\"><h3>#</h3></th>\n"
                    + "                                    <th class=\"plans-list\"><h3>Cliente</h3></th>\n"
                    + "                                    <th class=\"plans-list\"><h3>Monto</h3></th>\n"
                    + "                                    <th class=\"plans-list\"><h3>FechaPago</h3></th>\n"
                    + "                                    <th class=\"plans-list\"><h3>Acciones</h3></th>\n"
                    + "\n"
                    + "                                </tr>\n"
                    + "                            </thead>");
            int i = 0;
            out.write("<tbody>");
            for (Pago pago : lista) {
                i++;
                Cliente cliente = Clientedao.listarClienteId(pago.getIdCliente());
                out.write("<tr>");
                out.write("<td class=\"plan_list_title\">" + i + "</td>\n"
                        + "                                    <td class=\"plan_list_title\">" + cliente.getNombre() + " " + cliente.getApellidoPaterno() + "</td>\n"
                        + "                                    <td class=\"price_body\">" + pago.getMonto() +" "+"soles"+ "</td>\n"
                        + "                                    <td class=\"price_body\">" + pago.getFechaPago() + "</td>\n"
                        + "                                    <td>\n"
                        + "                                        <div class=\"dropdown\">\n"
                        + "                                            <button class=\"btn btn-warning dropdown-toggle\" type=\"button\" id=\"dropdownMenuButton\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">\n"
                        + "                                                Seleccione Accion\n"
                        + "                                            </button>\n"
                        + "                                            <div class=\"dropdown-menu dropdown-menu-right\" aria-labelledby=\"dropdownMenuButton\">\n"
                        + "                                                <a id='btnDetalle' class=\"dropdown-item\" href='" + pago.getIdPago() + "' title=\"Editar\">Detalle Servicio</a>\n"
                        + "\n"
                        + "                                            </div>\n"
                        + "                                        </div>\n"
                        + "                                    </td>");
                out.write("</tr>");
            }
            out.write("</tbody>");
        }
        if (path.equals("/DetallePago")) {
            int id = Integer.parseInt(request.getParameter("id"));
            Pago pago = Pagodao.listarPagosID(id);
            Servicio servicio = Serviciodao.listarServiciosCodCliente(pago.getIdCliente());
            Cliente cliente = Clientedao.listarClienteId(servicio.getIdCliente());
            Antena antena = Antenadao.listarAntenasId(servicio.getIdAntena());
            out.write("<div class=\"modal-dialog\" role=\"document\">\n"
                    + "                <div class=\"modal-content\">\n"
                    + "                    <div class=\"modal-header\">\n"
                    + "                        <h5 class=\"modal-title\" id=\"exampleModalLabel\">Detalle de Pago</h5>\n"
                    + "                        <button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-label=\"Close\" id=\"btnExit1\">\n"
                    + "                            <span aria-hidden=\"true\">&times;</span>\n"
                    + "                        </button>\n"
                    + "                    </div>\n"
                    + "                    <form  id=\"form\" action=\"AgregarAntena\" method=\"POST\">\n"
                    + "                        <div class=\"modal-body\">\n"
                    + "                            <div class=\"form-group\">\n"
                    + "                                <label for=\"nombre\" style=\"color: black\">Cliente : " + cliente.getNombre() + " " + cliente.getApellidoPaterno() + "</label>\n"
                    + "                            </div>\n"
                    + "                            <div class=\"form-group\">\n"
                    + "                                <label for=\"nombre\" style=\"color: black\">Antena: " + antena.getNombreAntena() + "</label>\n"
                    + "                            </div>\n"
                    + "                            <div class=\"form-group\">\n"
                    + "                                <label for=\"nombre\" style=\"color: black\">Frecuencia: " + servicio.getFrecuencia() + "</label>\n"
                    + "                            </div>\n"
                    + "                            <div class=\"form-group\">\n"
                    + "                                <label for=\"nombre\" style=\"color: black\">Ancho de Banda: " + servicio.getAnchoBanda() + "</label>\n"
                    + "                            </div>\n"
                    + "                            <div class=\"form-group\">\n"
                    + "                                <label for=\"nombre\" style=\"color: black\">Condicion de la antena: " + servicio.getCondicionAntena() + "</label>\n"
                    + "                            </div>\n"
                    + "                            <div class=\"form-group\">\n"
                    + "                                <label for=\"nombre\" style=\"color: black\">Dni: " + cliente.getDNI() + "</label>\n"
                    + "                            </div>\n"
                    + " <div class=\"form-group\">\n"
                    + "                                <label for=\"nombre\" style=\"color: black\">Periodo : " + pago.getP_inicial() + " - " + pago.getP_final() + " </label>\n"
                    + "                            </div>\n"
                    + "                        <div class=\"modal-footer\">\n"
                    + "                            <button type=\"button\" class=\"btn btn-primary\" data-dismiss=\"modal\" id=\"btnClose1\">Close</button>\n"
                    + "                        </div>\n"
                    + "                    </form>\n"
                    + "                </div>\n"
                    + "            </div>");
        }
        if (path.equals("/EditarPagos")) {
            int id = Integer.parseInt(request.getParameter("id"));
            Pago pago = Pagodao.listarPagosID(id);
            request.setAttribute("pago", pago);
            request.getRequestDispatcher("pagos.jsp").forward(request, response);
        }
        if (path.equals("/ActualizarPagos")) {
            float monto = Float.parseFloat(request.getParameter("monto"));
            int idPago = Integer.parseInt(request.getParameter("idPago"));
            pago.setMonto(monto);
            pago.setIdPago(idPago);
            if (Pagodao.guardarPago(pago)) {
                out.write("TRUE");
            } else {
                out.write("FALSE");
            }
        }
        if (path.equals("/EliminarPagos")) {
            int id = Integer.parseInt(request.getParameter("id"));
            if (Pagodao.deletePago(id)) {
                request.setAttribute("msg", "Eliminado");
                request.getRequestDispatcher("pagos.jsp").forward(request, response);
            } else {
                request.setAttribute("msg", "No eliminado");
                request.getRequestDispatcher("pagos.jsp").forward(request, response);
            }
        }
        if (path.equals("/AgregarPagos")) {
            String dni = request.getParameter("dniSol");
            Servicio servicio = Serviciodao.listarServiciosCodCliente(dni);
            float monto = Float.parseFloat(request.getParameter("monto"));
            HttpSession s = request.getSession();
            Usuario us = (Usuario) s.getAttribute("us");
            if (monto <= servicio.getTarifa()) {
                pago.setIdPersonal(us.getIdPersonal());
                pago.setMonto(monto);
                pago.setP_inicial(servicio.getF_inicio());
                pago.setP_final(servicio.getF_vencimiento());
                pago.setIdCliente(servicio.getIdCliente());
                pago.setFechaPago(Fecha.fecha_actual());
                if (Pagodao.insertarPago(pago)) {
                    double suma = 0;
                    ArrayList<Pago> lista = Pagodao.PagosFechaInicio(servicio.getIdCliente(), servicio.getF_inicio());
                    for (Pago pago1 : lista) {
                        suma = pago1.getMonto() + suma;
                    }
                    if (suma == servicio.getTarifa()) {
                        if (Serviciodao.actualizarFechas(servicio.getIdServicio())) {
                            System.out.println("Se cambio la fecha");
                        } else {
                            System.out.println("No se cambio la fecha aun");
                        }
                    } 
                    out.write("TRUE");
                } else {
                    out.write("FALSE");
                }
            } else {
                out.write("MFALSE");
            }
        }
        if (path.equals("/BuscarCliente")) {
            String dni = request.getParameter("dni");
            Servicio servicio = Serviciodao.listarServiciosCodCliente(dni);
            if (servicio != null) {
                float monto = 0;
                Cliente cliente = Clientedao.listarClienteId(servicio.getIdCliente());
                ArrayList<Pago> lista = Pagodao.PagosFechaInicio(servicio.getIdCliente(), servicio.getF_inicio());
                for (Pago pago1 : lista) {
                    monto = pago1.getMonto() + monto;
                }
                out.write("<label for=\"nombre\" style=\"color: black\">Cliente: " + cliente.getNombre() + " " + cliente.getApellidoPaterno() + " " + cliente.getApellidoMaterno() + "</label><br>");
                out.write("<label for=\"nombre\" style=\"color: black\">Periodo: " + servicio.getF_inicio() + " a " + servicio.getF_vencimiento() + "</label><br>");
                out.write("<input type='hidden' id='tarifaServicio' value='" + servicio.getTarifa() + "' ");
                out.write("<label for=\"nombre\" style=\"color: black\">Monto Restante: " + (servicio.getTarifa() - monto) + "</label><br>");
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
