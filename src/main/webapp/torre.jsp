<%-- 
    Document   : torre
    Created on : 14 jul. 2021, 17:53:26
    Author     : PIERO
--%>

<%@page import="modelo.TipoUsuario"%>
<%@page import="dao.Personaldao"%>
<%@page import="dao.TipoUsuariodao"%>
<%@page import="modelo.Personal"%>
<%@page import="modelo.Usuario"%>
<%@page import="modelo.Torre"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>VILLA FLASH NET</title>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <link href="css/style_1.css" rel="stylesheet" type="text/css" media="all"/>
        <link href='//fonts.googleapis.com/css?family=Electrolize' rel='stylesheet' type='text/css'>
        <script type="text/javascript" src="js/jquery.min.js"></script>
        <script src="https://kit.fontawesome.com/a881412379.js" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <script src="sweetalert2/dist/sweetalert2.all.min.js"></script>
        <link href='images/ordenador-portatil.ico' rel='icon' >
        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.23/css/jquery.dataTables.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    </head>
    <body>
        <% HttpSession s = request.getSession();
            Usuario us = (Usuario) s.getAttribute("us");
            if (us == null) {
                s.invalidate();
                response.sendRedirect("index.jsp");
            } else {
                Personal p = Personaldao.listarPersonalXId(us.getIdPersonal());
                TipoUsuario tp = TipoUsuariodao.listarTiposXId(us.getIdTipoUser());
                Torre torre = (Torre) request.getAttribute("torre");
        %>
        <script type="text/javascript">
            $(document).ready(function () {
                $('#modalRegistro').find(".modal-header").css("background", "linear-gradient(to left, #f5af19,#f5af19)");
                $('#modalRegistro').find(".modal-header").css("color", "white");
            <%if (torre != null) {%>
                $("#modalRegistro").modal('show');
            <%}%>
                function dataTable() {
                    $('#tab').DataTable({
                        responsive: true,
                        autoWidth: true,
                        destroy: true,
                        language: {
                            "decimal": "",
                            "emptyTable": "No hay    información",
                            "info": "Mostrando _START_ a _END_ de _TOTAL_ Entradas",
                            "infoEmpty": "Mostrando 0 to 0 of 0 Entradas",
                            "infoFiltered": "(Filtrado de _MAX_ total entradas)",
                            "infoPostFix": "",
                            "thousands": ",",
                            "lengthMenu": "Mostrar _MENU_ Entradas",
                            "loadingRecords": "Cargando...",
                            "processing": "Procesando...",
                            "search": "Buscar:",
                            "zeroRecords": "Sin resultados encontrados",
                            "paginate": {
                                "first": "Primero",
                                "last": "Ultimo",
                                "next": "Siguiente",
                                "previous": "Anterior"
                            }
                        },
                    });
                }
                function listarTorres() {
                    $.ajax({
                        type: "POST",
                        url: "ListarTorres",
                        success: function (response) {
                            $('#tab').html(response);
                            dataTable();
                        }
                    });
                }
                function registrarTorre() {
                    $('#btnRegistrar').click(function (e) {
                        e.preventDefault();
                        var data = $('#form').serialize();
                        $.ajax({
                            type: "POST",
                            url: "AgregarTorres",
                            data: data,
                            success: function (response) {
                                $("#modalRegistro").modal('hide');
                                if (response == "TRUE") {
                                    Swal.fire({
                                        icon: 'success',
                                        title: 'Buen trabajo',
                                        text: "Torre registrado correctamente",
                                    }).then((result) => {
                                        if (result.isConfirmed) {
                                            listarTorres();
                                        }
                                    })
                                } else {
                                    Swal.fire({
                                        icon: 'error',
                                        title: 'ERROR',
                                        text: "Torre no se puede registrar",
                                    }).then((result) => {
                                        if (result.isConfirmed) {
                                            listarTorres();
                                        }
                                    })
                                }
                            }
                        });
                    });
                }
                function actualizarTorre() {
                    $("#btnActualizar").click(function (e) {
                        e.preventDefault();
                        var data = $('#formUp').serialize();
                        $.ajax({
                            type: "POST",
                            url: "ActualizarTorres",
                            data: data,
                            success: function (response) {
                                $("#modalRegistro").modal('hide');
                                if (response == "TRUE") {
                                    Swal.fire({
                                        icon: 'success',
                                        title: 'Buen trabajo',
                                        text: "Torre modificada correctamente",
                                    }).then((result) => {
                                        if (result.isConfirmed) {
                                            listarTorres();
                                        }
                                    })
                                } else {
                                    Swal.fire({
                                        icon: 'error',
                                        title: 'ERROR',
                                        text: "Torre no se puede modificar",
                                    }).then((result) => {
                                        if (result.isConfirmed) {
                                            listarTorres();
                                        }
                                    })
                                }
                            }
                        });
                    });
                }
                listarTorres();
                registrarTorre();
                actualizarTorre();
            });
        </script>
        <style type="text/css">
            ul.dropdown-menu {
                background-color: gray;
            }
            label {
                display: inline-block;
                margin-bottom: .5rem;
                color:white;
            }
            .dataTables_wrapper .dataTables_filter input {
                border: 1px solid #aaa;
                border-radius: 3px;
                padding: 5px;
                background-color: transparent;
                margin-left: 3px;
                color:white;
            }
            .dataTables_wrapper .dataTables_length select {
                border: 1px solid #aaa;
                border-radius: 3px;
                padding: 5px;
                background-color: white;
                padding: 4px;
            }
            .dataTables_wrapper .dataTables_paginate .paginate_button.disabled, .dataTables_wrapper .dataTables_paginate .paginate_button.disabled:hover, .dataTables_wrapper .dataTables_paginate .paginate_button.disabled:active {
                cursor: default;
                color: white !important;
                border: 1px solid transparent;
                background: transparent;
                box-shadow: none;
            }
            .dataTables_wrapper .dataTables_length, .dataTables_wrapper .dataTables_filter, .dataTables_wrapper .dataTables_info, .dataTables_wrapper .dataTables_processing, .dataTables_wrapper .dataTables_paginate {
                color: white;
            }
            tbody, td {
                margin: 0;
                padding: 0;
                border: 0;
                font-size: 100%;
                font: inherit;
                vertical-align: baseline;
                background-image: url("images/background.png");	
                color: white;

            }
            thead, tr {
                margin: 0;
                padding: 0;
                border: 0;
                font-size: 100%;
                font: inherit;
                vertical-align: baseline;
                background: black;
            }
            .dataTables_wrapper .dataTables_paginate .paginate_button.current, .dataTables_wrapper .dataTables_paginate .paginate_button.current:hover {
                color: #333 !important;
                border: 1px solid #979797;
                background-color: #DFD428;
                background: -webkit-gradient(linear, left top, left bottom, color-stop(0%, white), color-stop(100%, #dcdcdc));
                background: -webkit-linear-gradient(top, white 0%, #dcdcdc 100%);
                background: -moz-linear-gradient(top, white 0%, #dcdcdc 100%);
                background: -ms-linear-gradient(top, white 0%, #dcdcdc 100%);
                background: -o-linear-gradient(top, white 0%, #dcdcdc 100%);
                background: linear-gradient(to bottom, white 0%, #dcdcdc 100%);
            }
            .dataTables_wrapper .dataTables_paginate .paginate_button {
                box-sizing: border-box;
                display: inline-block;
                min-width: 1.5em;
                padding: 0.5em 1em;
                margin-left: 2px;
                text-align: center;
                text-decoration: none !important;
                cursor: pointer;
                *cursor: hand;
                color: white !important;
                border: 1px solid transparent;
                border-radius: 2px;
            }
            input{
                margin: 0;
                font-family: inherit;
                font-size: inherit;
                line-height: inherit;
                color: white;
            }
            .form-dark .font-small {
                font-size: 0.8rem; }

            .form-dark [type="radio"] + label,
            .form-dark [type="checkbox"] + label {
                font-size: 0.8rem; }

            .form-dark [type="checkbox"] + label:before {
                top: 2px;
                width: 15px;
                height: 15px; }

            .form-dark .md-form label {
                color: #fff; }

            .form-dark input[type=email]:focus:not([readonly]) {
                border-bottom: 1px solid #00C851;
                -webkit-box-shadow: 0 1px 0 0 #00C851;
                box-shadow: 0 1px 0 0 #00C851; }

            .form-dark input[type=email]:focus:not([readonly]) + label {
                color: #fff; }

            .form-dark input[type=password]:focus:not([readonly]) {
                border-bottom: 1px solid #00C851;
                -webkit-box-shadow: 0 1px 0 0 #00C851;
                box-shadow: 0 1px 0 0 #00C851; }

            .form-dark input[type=password]:focus:not([readonly]) + label {
                color: #fff; }

            .form-dark input[type="checkbox"] + label:before {
                content: '';
                position: absolute;
                top: 0;
                left: 0;
                width: 17px;
                height: 17px;
                z-index: 0;
                border: 1.5px solid #fff;
                border-radius: 1px;
                margin-top: 2px;
                -webkit-transition: 0.2s;
                transition: 0.2s; }

            .form-dark input[type="checkbox"]:checked + label:before {
                top: -4px;
                left: -3px;
                width: 12px;
                height: 22px;
                border-style: solid;
                border-width: 2px;
                border-color: transparent #00c851 #00c851 transparent;
                -webkit-transform: rotate(40deg);
                -ms-transform: rotate(40deg);
                transform: rotate(40deg);
                -webkit-backface-visibility: hidden;
                -webkit-transform-origin: 100% 100%;
                -ms-transform-origin: 100% 100%;
                transform-origin: 100% 100%; }

            .form-dark .modal-header {
                border-bottom: none;
            }
        </style>
        <div class = "header" >
            <div class="header_top">
                <div class="wrap">		
                    <div class="logo">
                        <a href="principal.jsp"><img src="images/villa flash.png" alt="" /></a>
                    </div>	
                    <div class="menu">
                        <ul>
                            <li><a href="principal.jsp">Inicio</a></li>
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
                                    <a class="dropdown-item active" href="torre.jsp">Torre</a>
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
        </div>
        <div  id="modalRegistro" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <%if (torre == null) {%>
                        <h5 class="modal-title" id="exampleModalLabel">Registro de Personal</h5>
                        <%} else {%>
                        <h5 class="modal-title" id="exampleModalLabel">Edicion de Personal</h5>
                        <%}%>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <%if (torre == null) {%>
                    <form  id="form" action="AgregarAntena" method="POST">
                        <div class="modal-body">
                            <div class="form-group">
                                <label for="nombre" style="color: black">Ingrese su nombre de torre:</label>
                                <input name="nomTorre" style="color: black" type="text" placeholder="Nombre Torre" class="field" required>
                            </div>
                            <div class="form-group">
                                <label for="nombre" style="color: black">Ingrese la direccion de la torre:</label>
                                <input name="dirTorre" style="color: black" type="text" placeholder="Direccion" class="field" required> 
                            </div>
                            <div class="form-group">
                                <label for="nombre" style="color: black">Ingrese nombre del dueño local:</label>
                                <input name="dueLocal" style="color: black" type="text" placeholder="Dueño Local" class="field" required>
                            </div>
                            <div class="form-group">
                                <label for="nombre" style="color: black">Ingrese un telefono de referencia:</label>
                                <input name="telefono" style="color: black" type="text" placeholder="Telefono" class="field" required> 
                            </div>
                        </div>

                        <div class="modal-footer">
                            <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
                            <button type="button" class="btn btn-warning" style="float: right; color: white;" id="btnRegistrar">Registrar</button>
                        </div>
                    </form>
                    <%} else {%>
                    <form  id="formUp" action="ActualizarAntena" method="POST">
                        <div class="modal-body">
                            <div class="form-group">
                                <label for="nombre" style="color: black">Ingrese nombre de la torre:</label>
                                <input name="nomTorre" style="color: black" type="text" placeholder="Nombre Torre" value="<%=torre.getNombreTorre()%>" class="field" required>
                                <input type="hidden" name="idTorre" value="<%=torre.getIdTorre()%>">
                            </div>
                            <div class="form-group">
                                <label for="nombre" style="color: black">Ingrese su direccion:</label>
                                <input name="dirTorre" style="color: black" type="text" placeholder="Direccion" value="<%=torre.getDireccion()%>" class="field" required> 
                            </div>
                            <div class="form-group">
                                <label for="nombre" style="color: black">ingrese nombre del dueño:</label>
                                <input name="dueLocal" style="color: black" type="text" placeholder="Dueño Local" value="<%=torre.getDueñoLocal()%>"class="field" required>
                            </div>
                            <div class="form-group">
                                <label for="nombre" style="color: black">Ingrese telefono de referencia:</label>
                                <input name="telefono" style="color: black" type="text" placeholder="Telefono" value="<%=torre.getTelefono()%>" class="field" required>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
                            <button type="button" class="btn btn-warning" style="float: right; color: white;" id="btnActualizar">Actualizar</button>
                        </div>
                    </form>
                    <%}%>
                </div>
            </div>
        </div>  
        <div class="main">
            <div class="wrap">
                <div class="plans">
                    <h3>Torres Registradas <button type="button" id="btnRegistro" class="btn btn-warning" data-toggle="modal" data-target="#modalRegistro">Registrar Nuevo</button></h3>
                    <div class="plans_table">
                        <table id="tab" width="100%" cellspacing="0" class="compare_plan">
                        </table>
                    </div> 			 
                </div>
            </div>
        </div>
        <div class="copy_right">
            <p> © 2021 VILLA FLASH NET . All rights reserved |  <%=p.getNombre()%> <%=p.getApellidoPaterno()%> <a href="CerrarSesion_srv?btn=true">Salir</a></p>
        </div>
        <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.23/js/jquery.dataTables.js"></script>
        <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.23/js/jquery.dataTables.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
        <%}%>
    </body>
</html>
