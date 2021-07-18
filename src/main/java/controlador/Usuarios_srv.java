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
                        + "                                    \n"
                        + "                                    <td>" + i + "</td>\n"
                        + "                                    <td >" + usuario.getUser() + "</td>\n"
                        + "                                    <td >" + tp.getTipoUser().toUpperCase() + "</td>\n"
                        + "                                    <td >" + nomEst + "</td>\n"
                        + "                                    <td>\n"
                        + "                                        <div class=\"dropdown\">\n"
                        + "                                            <button class=\"btn btn-warning dropdown-toggle\" type=\"button\" id=\"dropdownMenuButton\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">\n"
                        + "                                                Seleccione Accion\n"
                        + "                                            </button>\n"
                        + "                                            <div class=\"dropdown-menu dropdown-menu-right\" aria-labelledby=\"dropdownMenuButton\">\n"
                        + "                                                <a class=\"dropdown-item\" href=\"EstadoUsuarios?id=" + usuario.getIdUsuario() + "\" title=\"Cambiar Estado\">Cambiar Estado</a>\n"
                        + "                                                <a class=\"dropdown-item\" href=\"EditarUsuarios?id=" + usuario.getIdUsuario() + "\" title=\"Editar\">Editar</a>\n"
                        + "                                                <a class=\"dropdown-item\" href=\"EliminarUsuarios?id=" + usuario.getIdUsuario() + "\" title=\"Eliminar\">Eliminar</a>\n"
                        + "                                            </div>\n"
                        + "                                        </div>\n"
                        + "                                    </td>\n"
                        + "                                </tr>");
            }
            out.write("<tbody>");
        }
        if (path.equals("/EditarUsuarios")) {
            int id = Integer.parseInt(request.getParameter("id"));
            Usuario us = Usuariodao.listarUsuarioXId(id);
            request.setAttribute("usuario", us);
            request.getRequestDispatcher("usuarios.jsp").forward(request, response);
        }
        if (path.equals("/EliminarUsuarios")) {
            int id = Integer.parseInt(request.getParameter("id"));
            if (Usuariodao.deleteUsuario(id)) {
                request.setAttribute("msg", "Eliminado");
                request.getRequestDispatcher("usuarios.jsp").forward(request, response);
            } else {
                request.setAttribute("msg", "No Eliminado");
                request.getRequestDispatcher("usuarios.jsp").forward(request, response);
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
                    request.getRequestDispatcher("usuarios.jsp").forward(request, response);
                }
            } else if (estado.equals("I")) {
                if (Usuariodao.conectarUsuario(id)) {
                    request.getRequestDispatcher("usuarios.jsp").forward(request, response);
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
