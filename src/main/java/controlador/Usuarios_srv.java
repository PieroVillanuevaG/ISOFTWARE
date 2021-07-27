/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.Personaldao;
import dao.TipoUsuariodao;
import dao.Usuariodao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import static modelo.MD5.getMD5;
import modelo.Personal;
import modelo.TipoUsuario;
import modelo.Usuario;

/**
 *
 * @author PIERO
 */
@WebServlet(name = "Usuarios_srv", urlPatterns = {"/Usuarios_srv", "/ListarUsuarios", "/EditarUsuarios", "/EliminarUsuarios", "/AgregarUsuarios", "/ActualizarUsuarios", "/EstadoUsuarios"})
public class Usuarios_srv extends HttpServlet {
    
    Usuario us = new Usuario();

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
        if (path.equals("/ListarUsuarios")) {
            ArrayList<Usuario> lista = Usuariodao.listarUsuarios();
            HttpSession s = request.getSession();
            Usuario us = (Usuario) s.getAttribute("us");
            int i = 0;
            out.write("<thead>\n"
                    + "                                <tr>\n"
                    + "                                    <th class=\"plans-list\"><h3>#</h3></th>\n"
                    + "                                    <th class=\"plans-list\"><h3>Nombre</h3></th>\n"
                    + "                                    <th class=\"plans-list\"><h3>Tipo</h3></th>\n"
                    + "                                    <th class=\"plans-list\"><h3>Estado</h3></th>\n"
                    + "                                    <th class=\"plans-list\"><h3>Acciones</h3></th>\n"
                    + "\n"
                    + "                                </tr>\n"
                    + "                            </thead>");
            out.write("<tbody>");
            for (Usuario usuario : lista) {
                i++;
                TipoUsuario tp = TipoUsuariodao.listarTiposXId(usuario.getIdTipoUser());
                String estado = usuario.getEstado();
                String nomEst = null;
                if (estado.equals("A")) {
                    nomEst = "ACTIVO";
                } else if (estado.equals("I")) {
                    nomEst = "INACTIVO";
                }
                
                out.write("<tr>\n"
                        + "<td>" + i + "</td>\n"
                        + "<td >" + usuario.getUser() + "</td>\n"
                        + " <td >" + tp.getTipoUser().toUpperCase() + "</td>\n"
                        + "<td >" + nomEst + "</td>");
                out.write("<td>\n"
                        + "<div class=\"dropdown\">\n"
                        + " <button class=\"btn btn-warning dropdown-toggle\" type=\"button\" id=\"dropdownMenuButton\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">\n"
                        + "Seleccione Accion\n"
                        + "</button>\n"
                        + "<div class=\"dropdown-menu dropdown-menu-right\" aria-labelledby=\"dropdownMenuButton\">");
                if (usuario.getIdUsuario() == us.getIdUsuario()) {
                    out.write("<a id = 'btnFormEdit' class=\"dropdown-item\" href='" + usuario.getIdUsuario() + "' title=\"Editar\">Editar</a>");
                } else {
                    out.write("<a  id ='btnEstado' class=\"dropdown-item\" href='" + usuario.getIdUsuario() + "' title=\"Cambiar Estado\">Cambiar Estado</a>\n"
                            + "<a id = 'btnFormEdit' class=\"dropdown-item\" href='" + usuario.getIdUsuario() + "' title=\"Editar\">Editar</a>\n"
                            + " <a id='btnEliminar' class=\"dropdown-item\" href='" + usuario.getIdUsuario() + "' title=\"Eliminar\">Eliminar</a>");
                }
                out.write(" </div>\n"
                        + " </div>\n"
                        + "</td>\n"
                        + "</tr>");
            }
            out.write("<tbody>");
        }
        if (path.equals("/EditarUsuarios")) {
            int id = Integer.parseInt(request.getParameter("id"));
            String result = null;
            ArrayList<TipoUsuario> lista_tipos = TipoUsuariodao.listarTipos();
            ArrayList<Personal> lista_personal = Personaldao.listarPersonal();
            Usuario us = Usuariodao.listarUsuarioXId(id);
            out.write("<div class=\"modal-dialog\" role=\"document\">\n"
                    + "                <div class=\"modal-content\">\n"
                    + "                    <div class=\"modal-header\">\n"
                    + "                        <h5 class=\"modal-title\" id=\"exampleModalLabel\">Edicion de Usuario</h5>\n"
                    + "                        <button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-label=\"Close\" id=\"btnExit2\">\n"
                    + "                            <span aria-hidden=\"true\">&times;</span>\n"
                    + "                        </button>\n"
                    + "                    </div>\n"
                    + "                    <form  id=\"formUp\" action=\"ActualizarUsuarios\" method=\"POST\">\n"
                    + "                        <div class=\"modal-body\">\n"
                    + "                            <div class=\"form-group\">\n"
                    + "                                <label for=\"nombre\" style=\"color: black\">Ingrese su usuario:</label>\n"
                    + "                                <input name=\"us\" type=\"text\" style=\"color: black;\" placeholder=\"Username\" class=\"field\" value='" + us.getUser() + "' required>\n"
                    + "                                <input type=\"hidden\" name=\"idUsuario\" value='"+us.getIdUsuario()+"'>\n"
                    + "                            </div>\n"
                    + "                            <div class=\"form-group\">\n"
                    + "                                <label for=\"nombre\" style=\"color: black\">Ingrese su password:</label>\n"
                    + "                                <input name=\"pas\" style=\"color: black;\" type=\"password\" placeholder=\"Password\" class=\"field\" value='" + us.getPassword() + "' required> \n"
                    + "                            </div>");
            out.write("<div class=\"form-group\">");
            out.write("<label for=\"nombre\" style=\"color: black\">ingrese su tipo de usuario:</label>");
            out.write("<select id=\"country\" name=\"cmbo_tps\" onchange=\"change_country(this.value)\" class=\"frm-field required\">");
            for (TipoUsuario tipos : lista_tipos) {
                out.write("<option");
                if (us.getIdTipoUser() == tipos.getIdTipoUser()) {
                    result = "selected";
                } else {
                    result = "";
                }
                out.write(" " + result + " " + "value=" + tipos.getIdTipoUser() + ">" + tipos.getTipoUser() + "</option> ");
            }
            out.write("</select></div>");
            out.write("<div class=\"form-group\">");
            out.write(" <label for=\"nombre\" style=\"color: black\">Ingrese su nombre:</label>");
            out.write("<select id=\"country\" name=\"cmbo_prs\" onchange=\"change_country(this.value)\" class=\"frm-field required\">");
            for (Personal personal : lista_personal) {
                out.write("<option");
                if (us.getIdPersonal() == personal.getIdPersonal()) {
                    result = "selected";
                } else {
                    result = "";
                }
                out.write(" " + result + " " + "value=" + personal.getIdPersonal() + ">" + personal.getNombre() + " " + personal.getApellidoPaterno() + "</option> ");
            }
            out.write("</select></div>");
            out.write("</div>\n"
                    + "                        <div class=\"modal-footer\">\n"
                    + "                            <button type=\"button\" class=\"btn btn-primary\" data-dismiss=\"modal\" id=\"btnClose2\">Close</button>\n"
                    + "                            <button type=\"button\" class=\"btn btn-warning\" style=\"float: right; color: white;\" id=\"btnActualizar\">Actualizar</button>\n"
                    + "                        </div>\n"
                    + "                    </form>\n"
                    + "                </div>\n"
                    + "            </div>");
        }
        if (path.equals("/EliminarUsuarios")) {
            int id = Integer.parseInt(request.getParameter("id"));
            if (Usuariodao.deleteUsuario(id)) {
                out.write("Eliminado");
            } else {
                out.write("No se puede eliminar");
            }
        }
        if (path.equals("/ActualizarUsuarios")) {
            
            String user_n = request.getParameter("us").toUpperCase();
            String pass_n = request.getParameter("pas");
            int id_tps_n = Integer.parseInt(request.getParameter("cmbo_tps"));
            int id_prs_n = Integer.parseInt(request.getParameter("cmbo_prs"));
            int id = Integer.parseInt(request.getParameter("idUsuario"));
            us.setUser(user_n);
            us.setIdTipoUser(id_tps_n);
            us.setIdPersonal(id_prs_n);
            us.setPassword(getMD5(pass_n));
            us.setIdUsuario(id);
            if (Usuariodao.guardarUsuario(us)) {
                out.write("TRUE");
            } else {
                out.write("FALSE");
            }
        }
        if (path.equals("/AgregarUsuarios")) {
            String user = request.getParameter("us").toUpperCase();
            String pass = request.getParameter("pas");
            int id_prs = Integer.parseInt(request.getParameter("cmbo_prs"));
            int id_tps = Integer.parseInt(request.getParameter("cmbo_tps"));
            Personal p = Personaldao.listarPersonalXId(id_prs);
            String codRecuperacion = p.getNombre().substring(0, 3) + p.getDni() + p.getApellidoPaterno().substring(0, 3);
            us.setUser(user);
            us.setPassword(getMD5(pass));
            us.setIdPersonal(id_prs);
            us.setIdTipoUser(id_tps);
            us.setCodRecuperacion(codRecuperacion);
            if (Usuariodao.insertarUsuario(us)) {
                out.write("TRUE");
            } else {
                out.write("FALSE");
            }
        }
        if (path.equals("/EstadoUsuarios")) {
            int id = Integer.parseInt(request.getParameter("id"));
            Usuario user = Usuariodao.listarUsuarioXId(id);
            String estado = user.getEstado();
            if (estado.equals("A")) {
                if (Usuariodao.desconectarUsuario(id)) {
                    out.write("Desconectado");
                }
            } else if (estado.equals("I")) {
                if (Usuariodao.conectarUsuario(id)) {
                    out.write("Conectado");
                }
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
