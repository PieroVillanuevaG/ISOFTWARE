<%-- 
    Document   : formPassword
    Created on : 12 jul. 2021, 19:32:10
    Author     : PIERO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>VILLA FLASH NET</title>
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta name="keywords" content="Clean Login Form Responsive, Login Form Web Template, Flat Pricing Tables, Flat Drop-Downs, Sign-Up Web Templates, Flat Web Templates, Login Sign-up Responsive Web Template, Smartphone Compatible Web Template, Free Web Designs for Nokia, Samsung, LG, Sony Ericsson, Motorola Web Design" />

        <!-- css files -->
        <link href="css/font-awesome.min.css" rel="stylesheet" type="text/css" media="all">
        <link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
        <!-- /css files -->
        <link href='images/ordenador-portatil.ico' rel='icon' >
        <!-- online fonts -->
        <link href="//fonts.googleapis.com/css?family=Sirin+Stencil" rel="stylesheet">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="sweetalert2.all.min.js"></script>
    </head>
    <body>
        <script type="text/javascript">
            $(document).ready(function () {
                $("#btnRecuperar").click(function (e) {
                    e.preventDefault();
                    var data = $("#form").serialize();
                    $.ajax({
                        type: "POST",
                        url: "RecuperarContraseña",
                        data: data,
                        success: function (response) {
                            if (response == "TRUE") {
                                Swal.fire({
                                    icon: 'success',
                                    title: 'Buen trabajo',
                                    text: "Se envio un enlace de reestablecimiento",
                                })
                            } else {
                                Swal.fire({
                                    icon: 'error',
                                    title: 'Error',
                                    text: "El correo brindado no se encuentra registrado",
                                })
                            }
                        }
                    });
                });
                $("#btnRegresar").click(function (e){
                    e.preventDefault();
                    window.location="index.jsp";
                });
            });
        </script>

        <div class="container demo-1">
            <div class="content">
                <div id="large-header" class="large-header">
                    <h1><marquee>Bienvenido a Villa Flash Net</marquee></h1><br><br><br>
                    <div class="main-agileits">
                        <!--form-stars-here-->
                        <div class="form-w3-agile">
                            <h2>Recuperacion de Contraseña</h2>
                            <form id="form" action="Password_srv" method="post">
                                <div class="form-sub-w3">
                                    <input type="text" name="email" placeholder="Email" />
                                    <div class="icon-w3">
                                        <i class="fa fa-user" aria-hidden="true"></i>
                                    </div>
                                </div>
                                <div class="form-sub-w3">
                                    <div class="clear"></div>
                                    <div class="icon-w3">
                                        <i class="" aria-hidden="true"></i>
                                    </div>
                                </div>
                                <div class="clear"></div>
                                <p class="p-bottom-w3ls"><a class href="#"></a></p>
                                <p class="p-bottom-w3ls1"><a class href="#"></a></p>
                                <div class="clear"></div>
                                <div class="submit-w3l">
                                    <input id="btnRecuperar" style="float: right"type="submit" name="accion" value="Recuperar">
                                </div>
                                <div class="submit-w3l">
                                    <input id="btnRegresar" type="submit" name="accion" value="Regresar">
                                </div>
                                <div class="clear"></div>
                                <div class="clear"></div><br><br>
                            </form><br>
                        </div>
                        <!--//form-ends-here-->
                    </div><!-- copyright -->
                    <div class="copyright w3-agile">
                        <p> © 2021 VILLA FLASH NET . All rights reserved</p>
                    </div>
                    <!-- //copyright --> 
                </div>
            </div>
        </div>	
        <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    </body>
</html>
