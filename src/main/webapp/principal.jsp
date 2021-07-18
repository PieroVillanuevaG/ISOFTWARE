<%-- 
    Document   : principal
    Created on : 14 jul. 2021, 18:57:22
    Author     : PIERO
--%>

<%@page import="dao.TipoUsuariodao"%>
<%@page import="dao.Personaldao"%>
<%@page import="modelo.TipoUsuario"%>
<%@page import="modelo.Personal"%>
<%@page import="modelo.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>VILLA FLASH NET</title>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link href="css/style_1.css" rel="stylesheet" type="text/css" media="all"/>

        <link href='//fonts.googleapis.com/css?family=Electrolize' rel='stylesheet' type='text/css'>
        <link href='images/ordenador-portatil.ico' rel='icon' >
        <script type="text/javascript" src="js/modernizr.custom.28468.js"></script>
        <script type="text/javascript" src="js/jquery.min.js"></script>
        <script type="text/javascript">
            jQuery(document).ready(function ($) {
                $(".scroll").click(function (event) {
                    event.preventDefault();
                    $('html,body').animate({scrollTop: $(this.hash).offset().top}, 1200);
                });
            });
        </script>
        <script type="text/javascript">
            //funcion para no volver al index
            window.onload = function () {
                window.location.hash = "";
                window.location.hash = "Again-No-back-button"
                window.onhashchange = function () {
                    window.location.hash = ""
                }
            }
        </script>
        <script src="js/responsiveslides.min.js"></script>
        <script>
// You can also use "$(window).load(function() {"
            $(function () {
// Slideshow 4
                $("#slider3").responsiveSlides({
                    auto: true,
                    pager: false,
                    nav: false,
                    speed: 500,
                    namespace: "callbacks",
                    before: function () {
                        $('.events').append("<li>before event fired.</li>");
                    },
                    after: function () {
                        $('.events').append("<li>after event fired.</li>");
                    }
                });
            });
        </script>
    </head>
    <body>
        <style type="text/css">
            ul.dropdown-menu {
                background-color: gray;
            }
        </style>
        <%
            HttpSession s = request.getSession();
            Usuario us = (Usuario) s.getAttribute("us");
            if (us == null) {
                s.invalidate();
                response.sendRedirect("index.jsp");
            }%><%else {
                    response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
                    Personal p = Personaldao.listarPersonalXId(us.getIdPersonal());
                    TipoUsuario tp = TipoUsuariodao.listarTiposXId(us.getIdTipoUser());
        %>
        <div class="header">
            <div class="header_top">
                <div class="wrap">		
                    <div class="logo">
                        <a href="principal.jsp"><img src="images/villa flash.png" alt="" /></a>
                    </div>	
                    <div class="menu">
                        <ul>
                            <li class="active"><a href="principal.jsp">Inicio</a></li>
                            <li><a href="personal.jsp">Personal</a></li>
                            <li><a href="usuarios.jsp">Usuarios</a></li>
                            <li class="dropdown">
                                <a class="dropdown-toggle" href="#" role="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    Registro
                                </a>
                                <ul class="dropdown-menu">
                                    <a class="dropdown-item" href="cliente.jsp">Cliente</a>
                                    <a class="dropdown-item" href="servicios.jsp">Servicio</a>
                                    <a class="dropdown-item" href="pagos.jsp">Pagos</a>
                                </ul>
                            </li>
                            <li class="dropdown">
                                <a class="dropdown-toggle" href="#" role="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    Mantenimiento
                                </a>
                                <ul class="dropdown-menu">
                                    <a class="dropdown-item" href="torre.jsp">Torre</a>
                                    <a class="dropdown-item" href="servidor.jsp">Servidor</a>
                                    <a class="dropdown-item" href="antena.jsp">Antena</a>
                                </ul>
                            </li>
                            <li class="dropdown">
                                <a class="dropdown-toggle" href="#" role="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    Reportes
                                </a>
                                <ul class="dropdown-menu">
                                    <a class="dropdown-item" href="reportesClientes.jsp">Por cliente</a>
                                    <a class="dropdown-item " href="reportesPagos.jsp">Por Pago</a>
                                    <a class="dropdown-item" href="reportesCaja.jsp">Cierre de Caja</a>
                                </ul>
                            </li>
                            <li><a href="CerrarSesion_srv?btn=true">Cerrar Sesion</a></li>
                            <div class="clear"></div>
                        </ul>
                    </div>
                    <div class="clear"></div>
                </div>
            </div>
            <div class="header_bottom">
                <div class="slider">
                    <div class="wrap">
                        <div  id="top" class="callbacks_container">
                            <div class="services_heading">
                                <h2>BIENVENIDO <%=p.getNombre()%> <%=p.getApellidoPaterno()%></h2>
                                <p><%=tp.getTipoUser()%></p>

                            </div>
                            <ul class="rslides" id="slider3">
                                <li>
                                    <div class="da-slide">
                                        <div class="slide-text">
                                            <h2><span>VISION</span></h2>
                                            <p>Ser una empresa líder en el mercado de las telecomunicaciones con innovación, servicio y dedicación a sus clientes, liderando la preferencia en la provisión de servicios de última tecnología e Internet en el país, con recursos técnicos, financieros y humanos calificados</p>
                                        </div>
                                        <div class="da-img"><img src="images/2.png" alt="image01" /></div>
                                        <div class="clear"></div>
                                    </div>
                                </li>
                                <li>
                                    <div class="da-slide">
                                        <div class="slide-text">
                                            <h2><span>MISION</span></h2>
                                            <p>Proveer del acceso a las tecnologías de la información usando infraestructura de telecomunicaciones de última generación, gestionado por personal altamente calificado para brindar un servicio acorde a las necesidades de nuestros usuarios, comprometidos al desarrollo del país y orientados a superar los desniveles culturales, económicos y sociales.</p>		

                                        </div>
                                        <div class="da-img"><img src="images/1.png" alt="image01" /></div>
                                        <div class="clear"></div>
                                    </div>
                                </li>
                                <li>
                                    <div class="da-slide">
                                        <div class="slide-text">
                                            <h2><span>PROPOSITO</span></h2>
                                            <p>Proveer del servicio de acceso a Internet, diseño e implementación de sistemas de telecomunicaciones acorde a las necesidades del cliente, con tecnología de punta y sin limitaciones geográficas.
                                                Nuestros servicios son especialmente diseñados para cubrir las necesidades de comunicación, enfocados en usuarios particulares y empresariales, con una atención personalizada que le aseguran confiabilidad y seriedad en nuestros servicios.</p>		

                                        </div>
                                        <div class="da-img"><img src="images/3.png" alt="image01" /></div>
                                        <div class="clear"></div>
                                    </div>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="clear"></div>
            </div>
            <div class="clear"></div>
        </div>
        <div class="main">
            <div class="content_top">
                <div class="wrap">
                    <div class="ptables">
                        <ul class="green pricing jcarousel-list jcarousel-list-horizontal">
                            <li>
                                <ul>
                                    <li class="title"><h3>PLAN BASICO</h3><h4>S/50<small>/mes</small></h4></li>
                                    <li>ANCHO DE BANDA</li>
                                    <li>512 Kbps</li>
                                    <li>FRECUENCIA</li>
                                    <li>2.4 GHZ</li>
                                    <li><a href="Cliente_srv?menu=Cliente&accion=Listar&banda=512 Kbps&frec=2.4 GHz">Comprar</a></li>
                                </ul>
                            </li>
                            <li>
                                <ul>
                                    <li class="title"><h3>PLAN MEDIO</h3><h4>S/75<small>/mes</small></h4></li>
                                    <li>ANCHO DE BANDA</li>
                                    <li>1 Mbps</li>
                                    <li>FRECUENCIA</li>
                                    <li>2.4 GHZ</li>
                                    <li><a href="Cliente_srv?menu=Cliente&accion=Listar&banda=1 Mbps&frec=2.4 GHz" >Comprar</a></li>
                                </ul>
                            </li>               
                            <li>
                                <ul>
                                    <li class="title"><h3>PLAN EJECUTIVO</h3><h4>S/100<small>/mes</small></h4></li>
                                    <li>ANCHO DE BANDA</li>
                                    <li>1 Mbps</li>
                                    <li>FRECUENCIA</li>
                                    <li>5.8 GHZ</li>
                                    <li><a href="Cliente_srv?menu=Cliente&accion=Listar&banda=1 Mbps&frec=5.8 GHz" >Comprar</a></li>
                                </ul>
                            </li>        
                            <li>
                                <ul>
                                    <li class="title"><h3>PLAN PREMIER</h3><h4>S/125<small>/mes</small></h4></li>
                                    <li>ANCHO DE BANDA</li>
                                    <li>2 Mbps</li>
                                    <li>FRECUENCIA</li>
                                    <li>5.8 GHZ</li>
                                    <li><a href="Cliente_srv?menu=Cliente&accion=Listar&banda=2 Mbps&frec=5.8 GHz" >Comprar</a></li>
                                </ul>
                            </li>   
                            <div class="clear"></div>          
                        </ul>
                    </div>
                </div>        
            </div>
        </div>
        <div class="copy_right">
            <p> © 2021 VILLA FLASH NET . All rights reserved |  <%=p.getNombre()%> <%=p.getApellidoPaterno()%> <a href="CerrarSesion_srv?btn=true">Salir</a></p>
        </div>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
        <%}%>
    </body>
</html>
