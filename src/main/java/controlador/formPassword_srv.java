/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.Personaldao;
import dao.Usuariodao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.MD5;
import modelo.Personal;
import modelo.Usuario;

/**
 *
 * @author PIERO
 */
@WebServlet(name = "formPassword_srv", urlPatterns = {"/formPassword_srv", "/RecuperarContraseña", "/ReestablecerContraseña"})
public class formPassword_srv extends HttpServlet {

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
        String context = request.getContextPath();
        if (path.equals("/RecuperarContraseña")) {
            String email = request.getParameter("email").toUpperCase();
            String asunto = "Formulario de Reestablecimiento de contraseña";
            Personal p = Personaldao.validarCorreo(email);
            Usuario us = Usuariodao.listarUsuarioXIdPersonal(p.getIdPersonal());
            if (us != null) {
                String contenido = "Su contraseña es:" + us.getPassword();
                String result;
                String de = "thepieritoxp255@gmail.com";
                Properties properties = System.getProperties();
                properties.put("mail.smtp.host", "smtp.gmail.com");
                properties.put("mail.smtp.user", "thepieritoxp255@gmail.com");
                properties.put("mail.smtp.clave", "pierovg159");
                properties.put("mail.smtp.auth", "true");
                properties.put("mail.smtp.socketFactory.port", "587");
                properties.put("mail.smtp.socketFactory.fallback", "false");
                properties.put("mail.smtp.starttls.enable", "true");
                properties.put("mail.smtp.debug", "true");
                properties.put("mail.smtp.port", "587");
                properties.put("mail.smtp.ssl.trust", "*");
                Session mailSession = Session.getDefaultInstance(properties, null);
                MimeMessage message = new MimeMessage(mailSession);
                try {
                    message.setFrom(new InternetAddress(de));
                    message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
                    String messageHTML = "<h1>HOLA</h1>";
                    message.setSubject(asunto);
                    message.setContent(messageHTML, "text/html; charset=utf-8");
                    Transport transport = mailSession.getTransport("smtp");
                    transport.connect("smtp.gmail.com", de, "pierovg159");
                    transport.sendMessage(message, message.getAllRecipients());
                    transport.close();
                    result = "Mensaje enviado";
                    out.write("TRUE");

                } catch (MessagingException ex) {
                    ex.printStackTrace();
                    result = "Error";
                    out.write("FALSE 1" + ex.getMessage() + p.getNombre());
                }
            } else {
                out.write("FALSE 2");
            }
        }
        if (path.equals("/ReestablecerContraseña")) {
            int id = Integer.parseInt(request.getParameter("id"));
            String pass = request.getParameter("pas1");
            if (Usuariodao.reestablecerContraseña(MD5.getMD5(pass), id)) {
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
