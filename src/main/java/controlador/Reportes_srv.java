/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.Clientedao;
import dao.Pagodao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Cliente;
import modelo.Pago;

/**
 *
 * @author PIERO
 */
@WebServlet(name = "Reportes_srv", urlPatterns = {"/Reportes_srv", "/ListarReportesCliente", "/ListarReportesCaja", "/ListarReportesPagosMes", "/ListarReportesPagosAño"})
public class Reportes_srv extends HttpServlet {

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

        if (path.equals("/ListarReportesCliente")) {
            String dni = request.getParameter("dniSol");
            int i = 0;
            Cliente cliente = Clientedao.listarClienteDni(dni);
            ArrayList<Pago> lista = Pagodao.listarPagosXId(cliente.getIdCliente());
            out.write("\"<thead>\\n\"\n"
                    + "                    + \"                                <tr>\\n\"\n"
                    + "                    + \"                                    <th class=\\\"plans-list\\\"><h3>#</h3></th>\\n\"\n"
                    + "                    + \"                                    <th class=\\\"plans-list\\\"><h3>Cliente</h3></th>\\n\"\n"
                    + "                    + \"                                    <th class=\\\"plans-list\\\"><h3>Monto</h3></th>\\n\"\n"
                    + "                    + \"                                    <th class=\\\"plans-list\\\"><h3>FechaPago</h3></th>\\n\"\n"
                    + "                    + \"\\n\"\n"
                    + "                    + \"                                </tr>\\n\"\n"
                    + "                    + \"                            </thead>\"");

            out.write("<tbody>");
            for (Pago pago : lista) {
                i++;
                out.write("<tr>");
                out.write("<td class=\"plan_list_title\">" + i + "</td>\n"
                        + "                                    <td class=\"plan_list_title\">" + cliente.getNombre() + " " + cliente.getApellidoPaterno() + "</td>\n"
                        + "                                    <td class=\"price_body\">" + pago.getMonto() + "</td>\n"
                        + "                                    <td class=\"price_body\">" + pago.getFechaPago() + "</td>\n");
                out.write("</tr>");
            }
            out.write("</tbody>");
            
        }
        if (path.equals("/ListarReportesCaja")) {
            String fecha = request.getParameter("fecha");
            ArrayList<Pago> lista = Pagodao.listarPagosXFecha(fecha);
            int i = 0;
            out.write("\"<thead>\\n\"\n"
                    + "                    + \"                                <tr>\\n\"\n"
                    + "                    + \"                                    <th class=\\\"plans-list\\\"><h3>#</h3></th>\\n\"\n"
                    + "                    + \"                                    <th class=\\\"plans-list\\\"><h3>Mes</h3></th>\\n\"\n"
                    + "                    + \"                                    <th class=\\\"plans-list\\\"><h3>Monto</h3></th>\\n\"\n"
                    + "                    + \"                                    <th class=\\\"plans-list\\\"><h3>Cliente</h3></th>\\n\"\n"
                    + "                    + \"\\n\"\n"
                    + "                    + \"                                </tr>\\n\"\n"
                    + "                    + \"                            </thead>\"");
            out.write("<tbody>");
            for (Pago pago : lista) {
                i++;
                Cliente cliente = Clientedao.listarClienteId(pago.getIdCliente());
                out.write("<tr>");
                out.write("<td class=\"plan_list_title\">" + i + "</td>\n"
                        + "                                    <td class=\"plan_list_title\">" + fecha + "</td>\n"
                        + "                                    <td class=\"price_body\">" + pago.getMonto() + "</td>\n"
                        + "                                    <td class=\"price_body\">" + cliente.getNombre()+" "+cliente.getApellidoPaterno()+ "</td>\n");
                out.write("</tr>");
            }
            out.write("</tbody>");
        }
        if (path.equals("/ListarReportesPagosAño")) {
            int año = Integer.parseInt(request.getParameter("año"));
            ArrayList<Pago> lista = Pagodao.listarPagosXAño(año);
            int i = 0;
            out.write("\"<thead>\\n\"\n"
                    + "                    + \"                                <tr>\\n\"\n"
                    + "                    + \"                                    <th class=\\\"plans-list\\\"><h3>#</h3></th>\\n\"\n"
                    + "                    + \"                                    <th class=\\\"plans-list\\\"><h3>Mes</h3></th>\\n\"\n"
                    + "                    + \"                                    <th class=\\\"plans-list\\\"><h3>Monto</h3></th>\\n\"\n"
                    + "                    + \"                                    <th class=\\\"plans-list\\\"><h3>FechaPago</h3></th>\\n\"\n"
                    + "                    + \"\\n\"\n"
                    + "                    + \"                                </tr>\\n\"\n"
                    + "                    + \"                            </thead>\"");
            out.write("<tbody>");
            for (Pago pago : lista) {
                i++;
                out.write("<tr>");
                out.write("<td class=\"plan_list_title\">" + i + "</td>\n"
                        + "                                    <td class=\"plan_list_title\">" + año + "</td>\n"
                        + "                                    <td class=\"price_body\">" + pago.getMonto() + "</td>\n"
                        + "                                    <td class=\"price_body\">" + pago.getFechaPago() + "</td>\n");
                out.write("</tr>");
            }
            out.write("</tbody>");
        }
        if (path.equals("/ListarReportesPagosMes")) {
            int mes = Integer.parseInt(request.getParameter("mes"));
            String result = null;
            switch (mes) {
                case 1:
                    result = "Enero";
                    break;
                case 2:
                    result = "Febrero";
                    break;
                case 3:
                    result = "Marzo";
                    break;
                case 4:
                    result = "Abril";
                    break;
                case 5:
                    result = "Mayo";
                    break;
                case 6:
                    result = "Junio";
                    break;
                case 7:
                    result = "Julio";
                    break;
                case 8:
                    result = "Agosto";
                    break;
                case 9:
                    result = "Septiembre";
                    break;
                case 10:
                    result = "Octubre";
                    break;
                case 11:
                    result = "Noviembre";
                    break;
                case 12:
                    result = "Diciembre";
                    break;
            }
            ArrayList<Pago> lista = Pagodao.listarPagosXMes(mes);
            int i = 0;
            out.write("\"<thead>\\n\"\n"
                    + "                    + \"                                <tr>\\n\"\n"
                    + "                    + \"                                    <th class=\\\"plans-list\\\"><h3>#</h3></th>\\n\"\n"
                    + "                    + \"                                    <th class=\\\"plans-list\\\"><h3>Mes</h3></th>\\n\"\n"
                    + "                    + \"                                    <th class=\\\"plans-list\\\"><h3>Monto</h3></th>\\n\"\n"
                    + "                    + \"                                    <th class=\\\"plans-list\\\"><h3>FechaPago</h3></th>\\n\"\n"
                    + "                    + \"\\n\"\n"
                    + "                    + \"                                </tr>\\n\"\n"
                    + "                    + \"                            </thead>\"");
            out.write("<tbody>");
            for (Pago pago : lista) {
                i++;
                out.write("<tr>");
                out.write("<td class=\"plan_list_title\">" + i + "</td>\n"
                        + "                                    <td class=\"plan_list_title\">" + result + "</td>\n"
                        + "                                    <td class=\"price_body\">" + pago.getMonto() + "</td>\n"
                        + "                                    <td class=\"price_body\">" + pago.getFechaPago() + "</td>\n");
                out.write("</tr>");
            }
            out.write("</tbody>");
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
