<%-- 
    Document   : servicios
    Created on : 14 jul. 2021, 19:38:21
    Author     : PIERO
--%>

<%@page import="dao.TipoUsuariodao"%>
<%@page import="modelo.TipoUsuario"%>
<%@page import="dao.Personaldao"%>
<%@page import="modelo.Personal"%>
<%@page import="modelo.Usuario"%>
<%@page import="dao.Clientedao"%>
<%@page import="modelo.Cliente"%>
<%@page import="dao.MarcaAntenadao"%>
<%@page import="dao.Antenadao"%>
<%@page import="modelo.MarcaAntena"%>
<%@page import="modelo.Antena"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modelo.Servicio"%>
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
        <%

            HttpSession s = request.getSession();
            Usuario us = (Usuario) s.getAttribute("us");
            if (us == null) {
                s.invalidate();
                response.sendRedirect("index.jsp");
            } else {
                Personal p = Personaldao.listarPersonalXId(us.getIdPersonal());
                TipoUsuario tp = TipoUsuariodao.listarTiposXId(us.getIdTipoUser());
                Servicio servicio = (Servicio) request.getAttribute("servicios");
                String fechaInicio = modelo.Fecha.fecha_actual();
                String fechaFinal = modelo.Fecha.fecha_vencimiento();
                String fechaVencimiento = modelo.Fecha.fecha_corte();
                ArrayList<Antena> lista_antena = Antenadao.listarAntenas();
                ArrayList<MarcaAntena> lista_marcas = MarcaAntenadao.listarMarcaAntena();
                ArrayList<String> lista_bandas = new ArrayList<>();
                lista_bandas.add("512 Kbps");
                lista_bandas.add("1 Mbps");
                lista_bandas.add("2 Mbps");
                ArrayList<String> lista_frecuencia = new ArrayList<>();
                lista_frecuencia.add("2.4 GHz");
                lista_frecuencia.add("5.8 GHz");
                ArrayList<String> lista_mac = new ArrayList<>();
                lista_mac.add("C0:3F:D5:4B:E3:E");
                lista_mac.add("00:23:CD:20:0E:4");
                lista_mac.add("F8:D1:11:7D:A0:C");
                lista_mac.add("64:66:B3:7A:F8:D");
                ArrayList<String> lista_ip = new ArrayList<>();
                for (int i = 10; i <= 30; i++) {
                    lista_ip.add("192.168." + i);
                }
        %>
        <script type="text/javascript">
            $(document).ready(function () {
                $('#modalRegistro').find(".modal-header").css("background", "linear-gradient(to left, #f5af19,#f5af19)");
                $('#modalRegistro').find(".modal-header").css("color", "white");
            <%if (servicio != null) {%>
                $('#modalUpdate').find(".modal-header").css("background", "linear-gradient(to left, #f5af19,#f5af19)");
                $('#modalUpdate').find(".modal-header").css("color", "white");
                $("#modalUpdate").modal('show');
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
                function ListarServicio() {
                    $.ajax({
                        type: "POST",
                        url: "ListarServicios",
                        success: function (response) {
                            $('#tab').html(response);
                            dataTable();
                        }
                    });
                }
                function close() {
                    $("#btnClose1").click(function (e) {
                        e.preventDefault();
                        $("#forMostrar").hide();
                        $("#nomCliente").val("");
                        $("#dni").val("");
                    });
                    $("#btnExit2").click(function (e) {
                        e.preventDefault();
                        $("#forMostrar").hide();
                        $("#nomCliente").val("");
                        $("#dni").val("");
                    });
                }
                function registrarServicio() {
                    $('#btnRegistrar').click(function (e) {
                        e.preventDefault();
                        var data = $('#form').serialize();
                        $.ajax({
                            type: "POST",
                            url: "AgregarServicios",
                            data: data,
                            success: function (response) {
                                $("#modalRegistro").modal('hide');
                                if (response == "TRUE") {
                                    Swal.fire({
                                        icon: 'success',
                                        title: 'Buen trabajo',
                                        text: "Servicio registrado correctamente",
                                    }).then((result) => {
                                        if (result.isConfirmed) {
                                            $("#forMostrar").hide();
                                            $("#nomCliente").val("");
                                            $("#dni").val("");
                                            ListarServicio();
                                        }
                                    })
                                } else {
                                    Swal.fire({
                                        icon: 'error',
                                        title: 'ERROR',
                                        text: "Servicio no se puede registrar",
                                    }).then((result) => {
                                        if (result.isConfirmed) {
                                            $("#modalRegistro").modal('show');
                                            ListarServicio();
                                        }
                                    })
                                }
                            }
                        });
                    });
                }
                function buscarCliente() {
                    $("#btnBuscar").click(function (e) {
                        e.preventDefault();
                        var dni = $("#dni").val();
                        if (dni.length == 8) {
                            $.ajax({
                                type: "POST",
                                url: "BuscarServicios",
                                data: {dni: dni},
                                success: function (response) {
                                    if (response.length == 0) {
                                        Swal.fire('No existe un cliente con ese dni')
                                    } else if (response == "FALSE") {
                                        Swal.fire('Cliente ya cuenta con un servicio')
                                    } else {
                                        $("#nomCliente").val(response);
                                        $("#forMostrar").show();
                                    }
                                }
                            });
                        } else {
                            Swal.fire('Tamaño dni incorrecto');
                        }
                    });
                }
                function actualizarServicio() {
                    $("#btnActualizar").click(function (e) {
                        e.preventDefault();
                        var data = $('#formUp').serialize();
                        $.ajax({
                            type: "POST",
                            url: "ActualizarServicios",
                            data: data,
                            success: function (response) {
                                $("#modalRegistro").modal('hide');
                                if (response == "TRUE") {
                                    Swal.fire({
                                        icon: 'success',
                                        title: 'Buen trabajo',
                                        text: "Servicio modificado correctamente",
                                    }).then((result) => {
                                        if (result.isConfirmed) {
                                            $("#modalUpdate").modal('hide');
                                            ListarServicio();
                                        }
                                    })
                                } else {
                                    Swal.fire({
                                        icon: 'error',
                                        title: 'ERROR',
                                        text: "Servicio no se puede modificar",
                                    }).then((result) => {
                                        if (result.isConfirmed) {
                                            ListarServicio();
                                        }
                                    })
                                }
                            }
                        });
                    });
                }
                close();
                ListarServicio();
                actualizarServicio();
                registrarServicio();
                buscarCliente();
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
                                    <a class="dropdown-item active" href="servicios.jsp">Servicio</a>
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
        </div>
        <div  id="modalRegistro" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Registro de Servicio</h5>
                        <button type="button" class="close" id="btnExit1" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <form  id="form" action="AgregarAntena" method="POST">
                        <div class="modal-body">
                            <div class="d-flex">
                                <label for="nombre" style="color: black">Ingrese su dni:</label>
                                <input id="dni" style="color: black" name="dniSol" style="width:100px;" type="text" placeholder="DNI" class="field" required>
                                <button type="submit" class="btn btn-warning" style="float: right; color: white;" id="btnBuscar">Buscar</button>
                            </div><br>
                            <div class="form-group">
                                <label for="nombre" style="color: black">Cliente:</label>
                                <input id="nomCliente" name="nomCliente" style="color: black" type="text" placeholder="Nombre" disabled required class="field"> 
                            </div>
                            <div id="forMostrar" style="display: none">
                                <div class="form-group">
                                    <label for="nombre" style="color: black">Fecha de inicio:</label>
                                    <input name="fechaInicio" style="color: black" type="date"  value="<%=fechaInicio%>"class="field">
                                </div>
                                <div class="form-group">
                                    <label for="nombre" style="color: black">Fecha de vencimiento:</label>
                                    <input name="fechaFin" type="date"  style="color: black" value="<%=fechaFinal%>" class="field" >
                                </div>
                                <div class="form-group">
                                    <label for="nombre" style="color: black">Fecha de corte:</label>
                                    <input name="fechaCorte" style="color: black" type="date" value="<%=fechaVencimiento%>" class="field" >
                                </div>
                                <div class="form-group">
                                    <label for="nombre" style="color: black">Seleccione IP:</label>
                                    <select id="country" name="cmbo_ip" onchange="change_country(this.value)" class="frm-field required">
                                        <%for (int i = 0; i < lista_ip.size(); i++) {%>
                                        <option value="<%=lista_ip.get(i)%>"><%=lista_ip.get(i)%></option>  
                                        <%}%>
                                    </select> 
                                </div>
                                <div class="form-group">
                                    <label for="nombre" style="color: black">Seleccione MAC:</label>
                                    <select id="country" name="cmbo_mac" onchange="change_country(this.value)" class="frm-field required">
                                        <%for (int i = 0; i < lista_mac.size(); i++) {%>
                                        <option value="<%=lista_mac.get(i)%>"><%=lista_mac.get(i)%></option>  
                                        <%}%>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label for="nombre" style="color: black">Seleccione Frecuencia:</label>
                                    <select id="country" name="cmbo_frecuencia" onchange="change_country(this.value)" class="frm-field required">
                                        <%for (int i = 0; i < lista_frecuencia.size(); i++) {%>
                                        <option value="<%=lista_frecuencia.get(i)%>"><%=lista_frecuencia.get(i)%></option>  
                                        <%}%>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label for="nombre" style="color: black">Seleccione Ancho de Banda:</label>
                                    <select id="country" name="cmbo_anchoBanda" onchange="change_country(this.value)" class="frm-field required">
                                        <%for (int i = 0; i < lista_bandas.size(); i++) {%>
                                        <option value="<%=lista_bandas.get(i)%>"><%=lista_bandas.get(i)%></option>  
                                        <%}%>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label for="nombre" style="color: black">Seleccione Antena:</label>
                                    <select id="country" name="cmbo_antena" onchange="change_country(this.value)" class="frm-field required">
                                        <%for (Antena antena : lista_antena) {%>
                                        <option value="<%=antena.getIdAntena()%>"><%=antena.getNombreAntena()%></option>  
                                        <%}%>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label for="nombre" style="color: black">Seleccione Marca:</label>
                                    <select id="country" name="cmbo_marca" onchange="change_country(this.value)" class="frm-field required">
                                        <%for (MarcaAntena marca : lista_marcas) {%>
                                        <option value="<%=marca.getIdMarca()%>"><%=marca.getMarca()%></option>  
                                        <%}%>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label for="nombre" style="color: black">Seleccione Condicion de la Antena:</label>
                                    <select id="country" name="cmbo_condicion" onchange="change_country(this.value)" class="frm-field required">
                                        <option value="Propia">Propia</option>  
                                        <option value="Alquilada">Alquilada</option>     
                                    </select>  
                                </div>

                                <div class="modal-footer">
                                    <button type="button" class="btn btn-primary" data-dismiss="modal" id="btnClose1">Close</button>
                                    <button type="submit" class="btn btn-warning" style="float: right; color: white;" id="btnRegistrar">Registrar</button>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div> 
        <%if (servicio != null) {%>
        <div  id="modalUpdate" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Edicion de Servicio</h5>
                        <button type="button" class="close" id="btnExit2" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <<form  id="formUp" action="ActualizarAntena" method="POST">
                        <div class="modal-body">
                            <div class="form-group">
                                <label for="nombre" style="color: black">Nombre del cliente:</label>
                                <%  Cliente cliente = Clientedao.listarClienteId(servicio.getIdCliente());%>
                                <input name="nomAntena" style="color: black;" type="text" placeholder="Nombre Antena" class="field" disabled  value="<%=cliente.getNombre()%> <%=cliente.getApellidoPaterno()%>"><br>
                                <input type="hidden" name="idServicio" value="<%=servicio.getIdServicio()%>">
                            </div>
                            <div class="form-group">
                                <label for="nombre" style="color: black">Fecha de Inicio:</label>
                                <input name="fechaInicio" style="color: black" type="date"  value="<%=servicio.getF_inicio()%>"class="field" >
                            </div>
                            <div class="form-group">
                                <label for="nombre" style="color: black">Fecha de Vencimiento:</label>
                                <input name="fechaFin" style="color: black" type="date"  value="<%=servicio.getF_vencimiento()%>"class="field" >
                            </div>
                            <div class="form-group">
                                <label for="nombre" style="color: black">Fecha de Corte:</label>
                                <input name="fechaCorte" style="color: black" type="date"  value="<%=servicio.getF_corte()%>"class="field" >
                            </div>
                            <% String result = null;
                            %>
                            <div class="form-group">
                                <label for="nombre" style="color: black">Seleccion IP:</label>
                                <select id="country" name="cmbo_ip" onchange="change_country(this.value)" class="frm-field required">
                                    <%for (int i = 0; i < lista_ip.size(); i++) {%>
                                    <option 
                                        <%
                                            if (servicio.getIp().equals(lista_ip.get(i))) {
                                                result = "selected";
                                            } else {
                                                result = "";
                                            }

                                        %>
                                        <%=result%>value="<%=lista_ip.get(i)%>"><%=lista_ip.get(i)%></option>  
                                    <%}%>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="nombre" style="color: black">Seleccion MAC:</label>
                                <select id="country" name="cmbo_mac" onchange="change_country(this.value)" class="frm-field required">
                                    <%for (int i = 0; i < lista_mac.size(); i++) {%>
                                    <option 
                                        <%
                                            if (servicio.getMac().equals(lista_mac.get(i))) {
                                                result = "selected";
                                            } else {
                                                result = "";
                                            }

                                        %>
                                        <%=result%> value="<%=lista_mac.get(i)%>"><%=lista_mac.get(i)%></option>  
                                    <%}%>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="nombre" style="color: black">Seleccion Frecuencia:</label>
                                <select id="country" name="cmbo_frecuencia" onchange="change_country(this.value)" class="frm-field required">
                                    <%for (int i = 0; i < lista_frecuencia.size(); i++) {%>
                                    <option 
                                        <%
                                            if (servicio.getFrecuencia().equals(lista_frecuencia.get(i))) {
                                                result = "selected";
                                            } else {
                                                result = "";
                                            }

                                        %>
                                        <%=result%> value="<%=lista_frecuencia.get(i)%>"><%=lista_frecuencia.get(i)%></option>  
                                    <%}%>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="nombre" style="color: black">Seleccione Ancho de Banda:</label>
                                <select id="country" name="cmbo_anchoBanda" onchange="change_country(this.value)" class="frm-field required">
                                    <%for (int i = 0; i < lista_bandas.size(); i++) {%>
                                    <option 
                                        <%
                                            if (servicio.getAnchoBanda().equals(lista_bandas.get(i))) {
                                                result = "selected";
                                            } else {
                                                result = "";
                                            }

                                        %>
                                        <%=result%> value="<%=lista_bandas.get(i)%>"><%=lista_bandas.get(i)%></option>  
                                    <%}%>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="nombre" style="color: black">Seleccione Antena:</label>
                                <select id="country" name="cmbo_antena" onchange="change_country(this.value)" class="frm-field required">
                                    <%for (Antena antena : lista_antena) { %>
                                    <option   <%
                                        if (servicio.getIdAntena() == antena.getIdAntena()) {
                                            result = "selected";
                                        } else {
                                            result = "";
                                        }
                                        %>
                                        <%=result%>
                                        value=<%=antena.getIdAntena()%>><%=antena.getNombreAntena()%></option>
                                    <%}%>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="nombre" style="color: black">Seleccione Marca de Antena:</label>
                                <select id="country" name="cmbo_marca" onchange="change_country(this.value)" class="frm-field required">
                                    <%for (MarcaAntena lista_marca : lista_marcas) {%>
                                    <option 
                                        <%
                                            if (servicio.getIdMarca() == lista_marca.getIdMarca()) {
                                                result = "selected";
                                            } else {
                                                result = "";
                                            }
                                        %>
                                        <%=result%> value=<%=lista_marca.getIdMarca()%>><%=lista_marca.getMarca()%></option>
                                    <%}%>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="nombre" style="color: black">Seleccione Condicion de Antena:</label>
                                <select id="country" name="cmbo_condicion" onchange="change_country(this.value)" class="frm-field required">
                                    <option value="Propia">Propia</option>  
                                    <option value="Alquilada">Alquilada</option>     
                                </select> 
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-primary" data-dismiss="modal" id="btnClose2">Close</button>
                            <button type="button" class="btn btn-warning" style="float: right; color: white;" id="btnActualizar">Actualizar</button>
                        </div>
                    </form>
                </div>
            </div>
        </div> 
        <%}%>
        <div class="main">
            <div class="wrap">
                <div class="plans">
                    <h3>Servicios Registrados <button type="button" id="btnRegistro" class="btn btn-warning" data-toggle="modal" data-target="#modalRegistro">Registrar Nuevo</button></h3>
                    <div class="plans_table">
                        <table id="tab" width="100%" cellspacing="0">
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
