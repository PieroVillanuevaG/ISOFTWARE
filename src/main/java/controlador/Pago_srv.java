/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

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
import modelo.Cliente;
import modelo.Fecha;
import modelo.Pago;
import modelo.Servicio;
import modelo.Usuario;

/**
 *
 * @author PIERO
 */
@WebServlet(name = "Pago_srv", urlPatterns = {"/Pago_srv", "/ListarPagos", "/EditarPagos", "/ActualizarPagos", "/EliminarPagos", "/AgregarPagos", "/BuscarCliente"})
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
                        + "                                    <td class=\"price_body\">" + pago.getMonto() + "</td>\n"
                        + "                                    <td class=\"price_body\">" + pago.getFechaPago() + "</td>\n"
                        + "                                    <td>\n"
                        + "                                        <div class=\"dropdown\">\n"
                        + "                                            <button class=\"btn btn-warning dropdown-toggle\" type=\"button\" id=\"dropdownMenuButton\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">\n"
                        + "                                                Seleccione Accion\n"
                        + "                                            </button>\n"
                        + "                                            <div class=\"dropdown-menu dropdown-menu-right\" aria-labelledby=\"dropdownMenuButton\">\n"
                        + "                                                <a id='btnEditar' class=\"dropdown-item\" href='EditarPagos?id=" + pago.getIdPago() + "' title=\"Editar\">Editar</a>\n"
                        + "                                                <a id='btnEliminar' class=\"dropdown-item\" href='EliminarPagos?id=" + pago.getIdPago() + "' title=\"Eliminar\">Eliminar</a>\n"
                        + "\n"
                        + "                                            </div>\n"
                        + "                                        </div>\n"
                        + "                                    </td>");
                out.write("</tr>");
            }
            out.write("</tbody>");
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
                    suma = pago1.getMonto()+suma;
                }
                if(suma==servicio.getTarifa()){
                    if(Serviciodao.actualizarFechas(servicio.getIdServicio())){
                        System.out.println("Se cambio la fecha");
                    }else{
                        System.out.println("No se cambio la fecha aun");
                    }
                }
                out.write("TRUE");
            } else {
                out.write("FALSE");
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
                out.write("<label for=\"nombre\" style=\"color: black\">Cliente:</label>");
                out.write("<input style=\"color: black\" type='text' disabled value='" + cliente.getNombre() + " " + cliente.getApellidoPaterno() + "'><br>");
                out.write("<label for=\"nombre\" style=\"color: black\">Periodo:</label>");
                out.write("<input style=\"color: black;width:300px\" type='text' disabled value='" + servicio.getF_inicio() + " a " + servicio.getF_vencimiento() + "'><br>");
                out.write("<label for=\"nombre\" style=\"color: black\">Monto Restante:</label>");
                out.write("<input style=\"color: black;width:300px\" type='text' disabled value='" + (servicio.getTarifa() - monto) + "  " + "Soles" + "'>");
            }else{
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
