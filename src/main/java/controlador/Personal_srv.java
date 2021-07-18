/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.Personaldao;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Personal;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author PIERO
 */
@WebServlet(name = "Personal_srv", urlPatterns = {"/Personal_srv", "/ListarPersonal", "/EditarPersonal", "/EliminarPersonal", "/AgregarPersonal", "/ActualizarPersonal"})
public class Personal_srv extends HttpServlet {

    Personal p = new Personal();

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
        if (path.equals("/ListarPersonal")) {
            ArrayList<Personal> lista = Personaldao.listarPersonal();
            out.write("<thead>\n"
                    + "                                <tr>\n"
                    + "                                    <th class=\"plans-list\"><h3>ID</h3></th>\n"
                    + "                                    <th class=\"plans-list\"><h3>Nombre</h3></th>\n"
                    + "                                    <th class=\"plans-list\"><h3>Apellidos</h3></th>\n"
                    + "                                    <th class=\"plans-list\"><h3>DNI</h3></th>\n"
                    + "                                    <th class=\"plans-list\"><h3>Imagen</h3></th>\n"
                    + "                                    <th class=\"plans-list\"><h3>Acciones</h3></th>\n"
                    + "                                </tr>\n"
                    + "                            </thead>	");
            out.write("<tbody>");
            int i = 0;
            for (Personal personal : lista) {
                i++;
                out.write("<tr>");
                out.write("<td>" + i + "</td>");
                out.write("<td>" + personal.getNombre() + "</td>");
                out.write("<td>" + personal.getApellidoPaterno() + " " + personal.getApellidoMaterno() + "</td>");
                out.write("<td>" + personal.getDni() + "</td>");
                out.write("<td><img src=" + personal.getImg() + " width='100 px' height='100px'></td>");
                out.write("<td><div class=\"dropdown\">\n"
                        + "                                            <button class=\"btn btn-warning dropdown-toggle\" type=\"button\" id=\"dropdownMenuButton\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">\n"
                        + "                                                Seleccione Accion\n"
                        + "                                            </button>\n"
                        + "                                            <div class=\"dropdown-menu dropdown-menu-right\" aria-labelledby=\"dropdownMenuButton\">\n"
                        + "                                                <a id='btnEditar' class=\"dropdown-item\" href='EditarPersonal?id=" + personal.getIdPersonal() + "' title=\"Editar\">Editar</a>\n"
                        + "                                                <a id='btnEliminar' class=\"dropdown-item\" href='EliminarPersonal?id=" + personal.getIdPersonal() + "' title=\"Eliminar\">Eliminar</a>\n"
                        + "                                            </div>\n"
                        + "                                        </div></td>");
                out.write("</tr>");
            }
            out.write("</tbody>");

        }
        if (path.equals("/AgregarPersonal")) {
            FileItemFactory file_factory = new DiskFileItemFactory();
            ServletFileUpload sfu = new ServletFileUpload(file_factory);

            ArrayList<String> campos = new ArrayList<>();
            ArrayList<String> imgs = new ArrayList<>();

            try {
                List items = sfu.parseRequest(request);
                for (int i = 0; i < items.size(); i++) {
                    FileItem item = (FileItem) items.get(i);
                    if (!item.isFormField()) {
                        File archivo = new File("C:\\Users\\PIERO\\OneDrive\\Documentos\\NetBeansProjects\\IS_villaflashnet\\src\\main\\webapp\\imagenesPersonal\\" + item.getName());
                        item.write(archivo);
                        imgs.add("imagenesPersonal/" + item.getName());
                    } else {
                        campos.add(item.getString());
                    }
                }
            } catch (Exception e) {
            }
            p.setNombre(campos.get(0).toUpperCase());
            p.setApellidoPaterno(campos.get(1).toUpperCase());
            p.setApellidoMaterno(campos.get(2).toUpperCase());
            p.setDni(campos.get(3));
            p.setCorreo(campos.get(4));
            p.setImg(imgs.get(0));
            if (Personaldao.insertarPersonal(p)) {
                out.write("TRUE");
            } else {
                out.write("FALSE");
            }
        }
        if (path.equals("/EditarPersonal")) {
            int id = Integer.parseInt(request.getParameter("id"));
            Personal pers = Personaldao.listarPersonalXId(id);
            request.setAttribute("personal", pers);
            request.getRequestDispatcher("personal.jsp").forward(request, response);

        }
        if (path.equals("/ActualizarPersonal")) {
            int id = Integer.parseInt(request.getParameter("idPersonal"));
            String nom = request.getParameter("nombre").toUpperCase();
            String apePat = request.getParameter("apeP").toUpperCase();
            String apeMat = request.getParameter("apeM").toUpperCase();
            String dni = request.getParameter("dni");
            String correo = request.getParameter("correo");
            p.setNombre(nom);
            p.setApellidoPaterno(apePat);
            p.setApellidoMaterno(apeMat);
            p.setDni(dni);
            p.setCorreo(correo);
            p.setIdPersonal(id);
            if (Personaldao.guardarPersonal(p)) {
                out.write("TRUE");
            } else {
                out.write("FALSE");
            }
        }
        if (path.equals("/EliminarPersonal")) {
            int id = Integer.parseInt(request.getParameter("id"));
            if (Personaldao.deletePersonal(id)) {
                request.setAttribute("msg", "Eliminado");
                request.getRequestDispatcher("personal.jsp").forward(request, response);
            } else {
                request.setAttribute("msg", "No Eliminado");
                request.getRequestDispatcher("personal.jsp").forward(request, response);
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
