<%-- 
    Document   : formReestablecimiento
    Created on : 14 jul. 2021, 23:32:18
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
        <% int id = Integer.parseInt(request.getParameter("idUsuario"));%>
        <script type="text/javascript">
            $(document).ready(function () {
                $("#btnReestablecer").click(function (e) {
                    e.preventDefault();
                    var cn1 = $("#cn1").val();
                    var cn2 = $("#cn2").val();
                    if (cn1 == cn2) {
                        var data = $("#form").serialize();
                        $.ajax({
                            type: "POST",
                            url: "ReestablecerContraseña",
                            data: data,
                            success: function (response) {
                                if (response == "TRUE") {
                                    Swal.fire({
                                        icon: 'success',
                                        title: 'Buen trabajo',
                                        text: "Contraseña reestablecida correctamente",
                                    }).then((result) => {
                                        if (result.isConfirmed) {
                                            window.location = "index.jsp";
                                        }
                                    })
                                } else {
                                    Swal.fire({
                                        icon: 'error',
                                        title: 'Error',
                                        text: "No se puede reestablecer la contraseña",
                                    })
                                }

                            }
                        });
                    } else {
                        Swal.fire({
                            icon: 'error',
                            title: 'Error',
                            text: "Contraseñas diferentes",
                        })
                    }
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
                            <h2>Reestablecimiento de Contraseña</h2>
                            <form id="form" action="Password_srv" method="post">
                                <div class="form-sub-w3">
                                    <input id="cn1" name="pas1" type="text" placeholder="Ingrese la nueva contraseña"><br>
                                    <div class="icon-w3">
                                        <i class="fa fa-user" aria-hidden="true"></i>
                                    </div>
                                </div>
                                <div class="form-sub-w3">
                                    <input id="cn2" name="pas2" type="text" placeholder="Repita la contraseña"><br>
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
                                    <input id="btnReestablecer" style="float: right"type="submit" name="accion" value="Reestablecer">
                                    <input type="hidden" name="id" value="<%=id%>">
                                </div>
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
