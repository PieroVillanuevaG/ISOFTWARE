<%-- 
    Document   : personal
    Created on : 12 jul. 2021, 19:36:24
    Author     : PIERO
--%>

<%@page import="dao.TipoUsuariodao"%>
<%@page import="modelo.TipoUsuario"%>
<%@page import="dao.Personaldao"%>
<%@page import="modelo.Usuario"%>
<%@page import="modelo.Personal"%>
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
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <script src="https://kit.fontawesome.com/a881412379.js" crossorigin="anonymous"></script>
        <script src="sweetalert2/dist/sweetalert2.all.min.js"></script>
        <link href='images/ordenador-portatil.ico' rel='icon' >
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.23/css/jquery.dataTables.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    </head>
    <body>
        <%
            HttpSession s = request.getSession();
            Usuario us = (Usuario) s.getAttribute("us");
            if (us == null) {
                s.invalidate();
                response.sendRedirect("index.jsp");
            } else {
                Personal p = (Personal) request.getAttribute("personal");
                String msg = (String) request.getAttribute("msg");
                Personal pe = Personaldao.listarPersonalXId(us.getIdPersonal());
                TipoUsuario tp = TipoUsuariodao.listarTiposXId(us.getIdTipoUser());
        %>
        <script type="text/javascript">
            $(document).ready(function () {
            <%if (us.getIdTipoUser() == 1) {%>
                $("#usuario").show();
                $("#principal").show();
                $("#personal").show();
                $("#mantenimiento").show();
                $("#reporte").show();
                $("#registro").show();
            <%}%>
            <%if (us.getIdTipoUser() == 2) {%>
                $("#principal").show();
                $("#registro").show();
            <%}%>
            <%if (us.getIdTipoUser() == 3) {%>
                $("#principal").show();
                $("#reporte").show();
            <%}%>
                $('#modalRegistro').find(".modal-header").css("background", "linear-gradient(to left, #f5af19,#f5af19)");
                $('#modalRegistro').find(".modal-header").css("color", "white");
            
                function tabDatatable() {
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
                function validarFormularioRegistro(nombre, apeP, apeM, dni, correo, archivos) {
                    if (nombre.length != 0) {
                        if (apeP.length != 0) {
                            if (apeM.length != 0) {
                                if ($.isNumeric(dni)) {
                                    if (dni.length > 0 && dni.length < 9) {
                                        if (!(correo.indexOf('@', 0) == -1 || correo.indexOf('.', 0) == -1)) {
                                            if (archivos.length != 0) {
                                                return true;
                                            } else {
                                                Swal.fire('Seleccione una imagen');
                                                return false;
                                            }
                                            ;
                                        } else {
                                            Swal.fire('Formato del correo incorrecto');
                                            return false;
                                        }
                                    } else {
                                        Swal.fire('Tamaño de Dni incorrecto');
                                        return false;
                                    }
                                } else {
                                    Swal.fire('Solo se aceptan numeros en el campo dni');
                                    return false;
                                }
                            } else {
                                Swal.fire('Escriba un apellido correcto');
                                return false;
                            }
                        } else {
                            Swal.fire('Escriba un apellido correcto');
                            return false;
                        }
                    } else {
                        Swal.fire('Escriba un nombre correcto');
                        return false;
                    }
                }
                function vaciar() {
                    $("#nombre").val("");
                    $("#apeP").val("");
                    $("#apeM").val("");
                    $("#dni").val("");
                    $("#correo").val("");
                    $("#archivos").val("");
                }
                function exit() {
                    $("#btnClose1").click(function (e) {
                        e.preventDefault();
                        vaciar();
                    });
                    $("#btnClose2").click(function (e) {
                        e.preventDefault();
                        vaciar();
                    });
                    $("#btnExit1").click(function (e) {
                        e.preventDefault();
                        vaciar();
                    });
                    $("#btnExit2").click(function (e) {
                        e.preventDefault();
                        vaciar();
                    });
                }
                function ListarPersonal() {
                    $.ajax({
                        type: "POST",
                        url: "ListarPersonal",
                        success: function (response) {
                            $("#tab").html(response);
                            $("tr #btnEditar").click(function (e) {
                                e.preventDefault();
                                var id = $(this).attr("href");
                                $.ajax({
                                    type: "POST",
                                    url: "EditarPersonal",
                                    data: {id: id},
                                    success: function (response) {
                                        $("#modalUpdate").html(response);
                                        $('#modalUpdate').find(".modal-header").css("background", "linear-gradient(to left, #f5af19,#f5af19)");
                                        $('#modalUpdate').find(".modal-header").css("color", "white");
                                        $("#modalUpdate").modal("show");
                                        actualizarPersonal();
                                    }
                                });
                            });
                            $("tr #btnEliminar").click(function (e) {
                                e.preventDefault();
                                var id = $(this).attr("href");
                                $.ajax({
                                    type: "POST",
                                    url: "EliminarPersonal",
                                    data: {id: id},
                                    success: function (response) {
                                        Swal.fire(response);
                                        ListarPersonal();
                                    }
                                });
                            });
                            tabDatatable();
                        }
                    });
                }
                function registrarPersonal() {
                    $('#btnRegistrar').click(function (e) {
                        e.preventDefault();
                        var nombre = $("#nombre").val();
                        var apeP = $("#apeP").val();
                        var apeM = $("#apeM").val();
                        var dni = $("#dni").val();
                        var correo = $("#correo").val();
                        var archivos = document.getElementById('archivos').files;
                        if (validarFormularioRegistro(nombre, apeP, apeM, dni, correo, archivos)) {
                            var data = new FormData($('#form')[0]);
                            $.ajax({
                                type: "POST",
                                url: "AgregarPersonal",
                                data: data,
                                contentType: false,
                                processData: false,
                                success: function (response) {
                                    $("#modalRegistro").modal('hide');
                                    if (response == "TRUE") {
                                        Swal.fire({
                                            icon: 'success',
                                            title: 'Buen trabajo',
                                            text: "Personal registrado correctamente",
                                        }).then((result) => {
                                            if (result.isConfirmed) {
                                                ListarPersonal();
                                                vaciar();
                                            }
                                        })
                                    } else {
                                        Swal.fire({
                                            icon: 'error',
                                            title: 'ERROR',
                                            text: "Personal con ese dni ya existe",
                                        }).then((result) => {
                                            if (result.isConfirmed) {
                                                ListarPersonal();
                                                $("#modalRegistro").modal('show');
                                            }
                                        })
                                    }
                                }
                            });
                        }
                    });
                }
                function actualizarPersonal() {
                    $('#btnActualizar').click(function (e) {
                        e.preventDefault();
                        var data = $("#formUp").serialize();
                        $.ajax({
                            type: "POST",
                            url: "ActualizarPersonal",
                            data: data,
                            success: function (response) {
                                $("#modalUpdate").modal('hide');
                                if (response == "TRUE") {
                                    Swal.fire({
                                        icon: 'success',
                                        title: 'Buen trabajo',
                                        text: "Personal modificado correctamente",
                                    }).then((result) => {
                                        if (result.isConfirmed) {
                                            ListarPersonal();
                                        }
                                    })
                                } else {
                                    Swal.fire({
                                        icon: 'error',
                                        title: 'ERROR',
                                        text: "Personal no se puede modificar",
                                    }).then((result) => {
                                        if (result.isConfirmed) {
                                            ListarPersonal();
                                            $("#modalUpdate").modal('show');
                                        }
                                    })
                                }
                            }
                        });
                    });
                }
                exit();
                ListarPersonal();
                registrarPersonal();

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
        <div class="header">
            <div class="header_top">
                <div class="wrap">		
                    <div class="logo">
                        <a href="principal.jsp"><img src="images/villa flash.png" alt="" /></a>
                    </div>	
                    <div class="menu">
                        <ul>
                            <li id="principal" style="display: none"><a href="principal.jsp">Inicio</a></li>
                            <li id="personal" class="active" style="display: none"><a href="personal.jsp">Personal</a></li>
                            <li id="usuario" style="display: none"><a href="usuarios.jsp">Usuarios</a></li>
                            <li id="registro" style="display: none" class="dropdown">
                                <a class="dropdown-toggle" href="#" role="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    Registro
                                </a>
                                <ul class="dropdown-menu">
                                    <a class="dropdown-item" href="cliente.jsp">Cliente</a>
                                    <a class="dropdown-item" href="servicios.jsp">Servicio</a>
                                    <a class="dropdown-item" href="pagos.jsp">Pagos</a>
                                </ul>
                            </li>
                            <li id="mantenimiento" style="display: none" class="dropdown">
                                <a class="dropdown-toggle" href="#" role="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    Mantenimiento
                                </a>
                                <ul class="dropdown-menu">
                                    <a class="dropdown-item" href="torre.jsp">Torre</a>
                                    <a class="dropdown-item" href="servidor.jsp">Servidor</a>
                                    <a class="dropdown-item" href="antena.jsp">Antena</a>
                                </ul>
                            </li>
                            <li id="reporte" style="display: none" class="dropdown">
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

                        <h5 class="modal-title" id="exampleModalLabel">Registro de Personal</h5>
                        <button type="button" class="close" id="btnExit1" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <form  id="form" action="AgregarPersonal" method="POST" enctype="multipart/form-data">
                        <div class="modal-body">

                            <div class="form-group">
                                <label for="nombre" style="color: black">Ingrese su nombre:</label>
                                <input type="text" style="color: black;" name="nombre" id="nombre" placeholder="Nombre" required><br>
                            </div>
                            <div class="form-group">
                                <label for="nombre" style="color: black">Ingrese su Apellido Paterno:</label>
                                <input type="text" style="color: black;" name="apeP" id="apeP" placeholder="Apellido Paterno" required><br>
                            </div>
                            <div class="form-group">
                                <label for="nombre" style="color: black">Ingrese su Apellido Materno:</label>
                                <input type="text" style="color: black;" name="apeM" id="apeM" placeholder="Apellido Materno" required><br>
                            </div>
                            <div class="form-group">
                                <label for="nombre" style="color: black">Ingrese su DNI:</label>
                                <input type="text" style="color: black;" name="dni" id="dni" placeholder="DNI"  pattern="[0-9]{8}" title="Debe poner 8 números" required><br>
                            </div>
                            <div class="form-group">
                                <label for="nombre" style="color: black">Ingrese su correo:</label>
                                <input type="email" style="color: black;" name="correo" id="correo" placeholder="Correo" required><br>
                            </div>
                            <div class="form-group">
                                <input type="file" style="color: black;" name="files[]" id="archivos" accept=".jpg,.jpeg,.png" required><br>
                            </div>

                        </div>

                        <div class="modal-footer">
                            <button type="button" class="btn btn-primary" data-dismiss="modal" id="btnClose1">Close</button>
                            <button type="submit" class="btn btn-warning" style="float: right; color: white;" id="btnRegistrar">Registrar</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>  
        <div  id="modalUpdate" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        </div> 
        <div class="main">
            <div class="wrap">
                <div class="plans">
                    <h3>Empleados Registrados <button type="button" id="btnRegistro" class="btn btn-warning" data-toggle="modal" data-target="#modalRegistro">Registrar Nuevo</button></h3>
                    <div class="plans_table">
                        <table id="tab" width="100%" cellspacing="0"></table>
                    </div>
                </div>
            </div>
        </div>
        <div class="copy_right">
            <p> © 2021 VILLA FLASH NET . All rights reserved |  <%=pe.getNombre()%> <%=pe.getApellidoPaterno()%> <a href="CerrarSesion_srv?btn=true">Salir</a></p>
        </div>
        <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.23/js/jquery.dataTables.js"></script>
        <script type="text/javascript" charset="utf8" src="https://code.jquery.com/jquery-3.5.1.js"></script>
        <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.23/js/jquery.dataTables.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
        <%}%>
    </body>
</html>
