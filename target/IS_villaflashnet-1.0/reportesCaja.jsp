<%-- 
    Document   : reportesCaja
    Created on : 15 jul. 2021, 21:05:32
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
        <link href="css/style_1.css" rel="stylesheet" type="text/css" media="all"/>
        <link href='//fonts.googleapis.com/css?family=Electrolize' rel='stylesheet' type='text/css'>
        <script type="text/javascript" src="js/jquery.min.js"></script>
        <link href='images/ordenador-portatil.ico' rel='icon' >
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://kit.fontawesome.com/a881412379.js" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <script src="sweetalert2/dist/sweetalert2.all.min.js"></script>
        <link rel="stylesheet" href="datables/css/buttons.dataTables.min.css">
        <link rel="stylesheet" href="datables/css/jquery.dataTables.min.css">
        <script type="text/javascript" src="datables/js/jquery-3.5.1.js"></script>
        <script type="text/javascript" src="datables/js/jquery.dataTables.min.js"></script>
        <script type="text/javascript" src="datables/js/dataTables.buttons.min.js"></script>
        <script type="text/javascript" src="datables/js/jszip.min.js"></script>
        <script type="text/javascript" src="datables/js/pdfmake.min.js"></script>
        <script type="text/javascript" src="datables/js/vfs_fonts.js"></script>
        <script type="text/javascript" src="datables/js/buttons.html5.min.js"></script>
        <script type="text/javascript" src="datables/js/buttons.print.min.js"></script>
    </head>
    <body>
        <%  HttpSession s = request.getSession();
            Usuario us = (Usuario) s.getAttribute("us");
            if (us == null) {
                s.invalidate();
                response.sendRedirect("index.jsp");
            } else {
                Personal p = Personaldao.listarPersonalXId(us.getIdPersonal());
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
                function dataTable() {
                    $('#tab').DataTable({
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
                        dom: 'Bfrtip',
                        responsive: 'true',
                        destroy: 'true',
                        buttons: [
                            {
                                extend: 'excelHtml5',
                                text: '<i class="fas fa-file-excel"></i>',
                                titleAttr: 'Exportal a excel',
                                className: 'btn btn-success',
                            },
                            {
                                extend: 'pdfHtml5',
                                text: '<i class="fas fa-file-pdf"></i>',
                                titleAttr: 'Exportal a pdf',
                                className: 'btn btn-warning',
                            },
                            {
                                extend: 'print',
                                text: '<i class="fa fa-print"></i>',
                                titleAttr: 'Imprimir',
                                className: 'btn btn-info'
                            },
                        ]
                    });
                }
                function ListarReporte() {
                    $("#btnReporte").click(function (e) {
                        e.preventDefault();
                        var fecha = $("#date").val();
                        if (fecha.length != 0) {
                            $.ajax({
                                type: "POST",
                                url: "ListarReportesCaja",
                                data: {fecha: fecha},
                                success: function (response) {
                                    $("#main").show();
                                    $("#divTable").html(response);
                                    dataTable();
                                    $("#btnConfirmar").click(function (e) {
                                        e.preventDefault();
                                        var monto = $("#monto").val();
                                        $.ajax({
                                            type: "POST",
                                            url: "AgregarMonto",
                                            data: {monto: monto},
                                            success: function (response) {
                                                alert(response);
                                            }
                                        });
                                    });
                                }
                            });
                        }else{
                            Swal.fire("Escoge una fecha");
                        }
                    });
                }
                ListarReporte();
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

            .btn-group, .btn-group-vertical{
                position: absolute !important;
            }
            button.dt-button, div.dt-button, a.dt-button, input.dt-button {
                position: relative;
                display: inline-block;
                box-sizing: border-box;
                margin-right: .333em;
                margin-bottom: .333em;
                padding: .5em 1em;
                border: 1px solid rgba(0, 0, 0, 0.3);
                border-radius: 2px;
                cursor: pointer;
                font-size: .88em;
                line-height: 1.6em;
                color: black;
                white-space: nowrap;
                overflow: hidden;
                background-color: #DFD428;
                background: -moz-linear-gradient(top, rgba(230, 230, 230, 0.1) 0%, rgba(0, 0, 0, 0.1) 100%);
                background: -ms-linear-gradient(top, rgba(230, 230, 230, 0.1) 0%, rgba(0, 0, 0, 0.1) 100%);
                background: -o-linear-gradient(top, rgba(230, 230, 230, 0.1) 0%, rgba(0, 0, 0, 0.1) 100%);

                filter: progid:DXImageTransform.Microsoft.gradient(GradientType=0,StartColorStr="rgba(230, 230, 230, 0.1)", EndColorStr="rgba(0, 0, 0, 0.1)");
                -webkit-user-select: none;
                -moz-user-select: none;
                -ms-user-select: none;
                user-select: none;
                text-decoration: none;
                outline: none;
                text-overflow: ellipsis;
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
                            <li id="principal" style="display: none"><a href="principal.jsp">Inicio</a></li>
                            <li id="personal" style="display: none"><a href="personal.jsp">Personal</a></li>
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
        <div class = "content_bottom" id = "account">
            <div class="wrap">
                <div class="domain-search">
                    <div class="domain-desc">
                        <h3>REPORTE DE CIERRE DE CAJA</h3>
                    </div>
                    <form id="form" method="POST">
                        <input style="color: black;width:400px;height: 50px" type="date" id="date"><label></label>
                        <div class="login"><input style="height: 50px"type="submit" name="accion"  value="Buscar" id="btnReporte"></div>

                    </form>   
                </div>
            </div>             
            <div class="clear"></div>
        </div>
        <div class="main" id="main" style="display: none">
            <div class="wrap">
                <div class="plans">
                    <h3>Reporte de Cierre caja </h3>
                    <div class="plans_table" id="divTable">

                    </div> 			 
                </div>
            </div>
            <div class="copy_right">
                <p> © 2021 VILLA FLASH NET . All rights reserved |  <%=p.getNombre()%> <%=p.getApellidoPaterno()%> <a href="CerrarSesion_srv?btn=true">Salir</a></p>
            </div>
        </div> 

        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
        <%}%>
    </body>
</html>
