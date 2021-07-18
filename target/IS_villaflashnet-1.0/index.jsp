<%-- 
    Document   : index
    Created on : 12 jul. 2021, 19:31:56
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
        <!-- online fonts -->
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <script src="sweetalert2/dist/sweetalert2.all.min.js"></script>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    </head>
    <body>
        <script type="text/javascript">
            $(document).ready(function () {
                $("#btnIngresar").click(function (e) {
                    e.preventDefault();
                    var data = $("#form").serialize();
                    $.ajax({
                        type: "POST",
                        url: "validarUsuario",
                        data: data,
                        success: function (response) {
                            if (response == "TRUE") {
                                Swal.fire({
                                    icon: 'success',
                                    title: 'Buen trabajo',
                                    text: "Usuario logueado correctamente",
                                }).then((result) => {
                                    if (result.isConfirmed) {
                                        window.location = "principal.jsp";
                                    }
                                })
                            } else {
                                Swal.fire({
                                    icon: 'error',
                                    title: 'ERROR',
                                    text: "Usuario no esta activo",
                                })
                            }
                        }
                    });
                });
            });

        </script>
        <div class="container demo-1">
            <div class="content">
                <div id="large-header" class="large-header">
                    <h1><marquee>Bienvenido a Villa Flash Net</marquee></h1><br><br> 
                    <div class="main-agileits">
                        <!--form-stars-here-->
                        <div class="form-w3-agile">
                            <h2>Iniciar Sesion</h2>
                            <form id="form" action="Login_srv" method="post">
                                <div class="form-sub-w3">
                                    <input type="text" name="user" placeholder="Username " required="" />
                                    <div class="icon-w3">
                                        <i class="fa fa-user" aria-hidden="true"></i>
                                    </div>
                                </div>
                                <div class="form-sub-w3">
                                    <input id = "pas" type="password" name="pass" placeholder="Password" required="" />
                                    <div class="icon-w3">
                                        <i class="fa fa-unlock-alt" aria-hidden="true"></i>
                                    </div>
                                </div>

                                <div class="clear"></div>
                                <p class="p-bottom-w3ls">¿Has olvidado tu contraseña?<a class href="formPassword.jsp">  Click Aqui</a></p>
                                <p class="p-bottom-w3ls1"><a class href="#"></a></p>
                                <div class="clear"></div>
                                <div class="submit-w3l">
                                    <input  id="btnIngresar" type="submit" name="accion" value="Login">
                                </div>
                                <div class="clear"></div>
                            </form>
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
    </body>
</html>
