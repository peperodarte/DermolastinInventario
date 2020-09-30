<%-- 
    Document   : inventario
    Created on : 21/09/2020, 02:41:47 PM
    Author     : JOSE.RODARTE
--%>

<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="AppDB" class="AppUtilidades.AppDB" scope="session" />
<jsp:useBean id="AppUtil" class="AppUtilidades.AppUtil" scope="session" /> 

<%
    int a = Integer.parseInt(request.getParameter("a") != null ? request.getParameter("a") : "0");
    int idC = Integer.parseInt(request.getParameter("idC") != null ? request.getParameter("idC") : "0");
    String search = request.getParameter("search") != null ? request.getParameter("search") : "";
    String qry = "SELECT id, nombre, apellidos, email, telefono, celular FROM tclientes";
    if (search.length() != 0) {
        qry += " where UPPER(nombre) like '%" + search.toUpperCase() + "%' or UPPER(apellidos)like '%" + search.toUpperCase() + "%'";
    }
    qry += " Order by apellidos ";
    String fechaAc = AppUtil.fechaActual();
%>

<html>
    <head>
        <%=AppUtil.insertMETA()%>
    </head>
    <body>
        <div class="progress" id="cLoad"><div class="indeterminate"></div></div>
        <div id="cOculto" class="hide scale-transition">
            <div class="fixed-navbar s12 m12 l12 xl12">
                <nav>
                    <form name="clientes" method="POST" action="Clientes.jsp">
                        <div class="nav-wrapper navBreadcrubs row">
                            
                            <div class="input-field col l2">
                                <input id="search" name="search" type="search" value="<%=search%>" size="10"  required>
                                <label class="label-icon" for="search"><i class="material-icons">search</i></label>
                                <i class="material-icons" onclick="cancelarBusqueda()">close</i>
                            </div>
                        <ul>
                                <li class="divider"></li><li class="active">
                                <a  href="/Modules/Clientes/Clientes.jsp">Clientes</a></li></ul>
                            <!--<a class="waves-effect  btn-flat white" href="agregarProductos.jsp">Pagos </a>-->
                        </div>
                    </form>
                </nav>
            </div>
                                <br>
            <div>
                <form  name="controlClientes" action="/controlCliente" method="POST">
                    <input type="hidden" name="ac" id="acc" value=""/>
                    <input type="hidden" name="id" id="idd" value="<%=idC%>"/>
                    <input type="hidden" name="credito"  value="0"/>
                    <input type="hidden" name="fecha"  value="<%=fechaAc%>"/>
                    <div class="" id="tablaClientesDiv">
                        <div class="container row s12 m12 l10 offset-l1 white z-depth-4">
                            <%=AppDB.imprimeTablaReporte(qry, "editarCliente", "id")%>                    
                        </div>
                        <div class="fixed-action-btn">
                            <%if (idC != 0) {%>
                                <a class="btn-floating btn-large waves-effect waves-light blue"  onclick="nuevoClienteRefresh();"><i class="material-icons">add</i></a>
                            <%} else {%>
                                <a class="btn-floating btn-large waves-effect waves-light blue"  onclick="nuevoClienteVer();"><i class="material-icons">add</i></a>
                            <%}%>
                        </div>
                            
                            
                            
                    </div>
                    <div class="hide" id="clienteNuevoDiv">
                        <div class="container row s12 m12 l12 xl12 white z-depth-4">
                            <div class="row center">
                                <%if (idC != 0) {%>
                                <h5>Modificar cliente</h5>
                                <%} else {%>
                                <h5>Agregar cliente</h5>
                                <%}%>
                                <div class="input-field col s6 m6">
                                    <i class="material-icons prefix">account_circle</i>
                                    <input id="nombreC" name="nombreC" type="text" class="validate" value="${cliente.getNombre()}">
                                    <label for="nombreC">Nombre</label>
                                </div>
                                <div class="input-field col s6 m6">
                                    <input id="apellidosC" name="apellidosC" type="text" class="validate" value="${cliente.getApellidos()}">
                                    <label for="apellidosC">Apellidos</label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="input-field col s12 m6">
                                    <i class="material-icons prefix">smartphone</i>
                                    <input id="celC" name="celC"  type="tel" min="1000000000" max="9999999999" maxlength="10" class="validate" value="${cliente.getCelular()}">
                                    <label for="celC">Celular</label>
                                </div>
                                <div class="input-field col s12 m6">
                                    <i class="material-icons prefix">phone</i>
                                    <input id="telC" name="telC" type="number" min="1000000000" max="9999999999" maxlength="10" class="" value="${cliente.getTelefono()}">
                                    <label for="telC">Telefono</label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="input-field col s12 m6">
                                    <i class="material-icons prefix">email</i>
                                    <input id="emailC" name="emailC" type="email" class=""  value="${cliente.getEmail()}">
                                    <label for="emailC">Email</label>
                                </div>
                                <!--
                                <div class="file-field input-field col s12 m6">
                                    <span></span>
                                    <input type="file" id="fileC" name="fileC" accept="image/png, image/jpeg">
                                    <div class="file-path-wrapper">
                                        <i class="material-icons prefix">add_a_photo</i>
                                        <input class="file-path disabled" id="foto" type="text">
                                    </div>
                                </div>
                                -->
                            </div>
                            <div class="row">
                                <div class="input-field col s12 m12">
                                    <i class="material-icons prefix">place</i>
                                    <input id="direccionC" name="direccionC" type="text" class="validate"  value="${cliente.getDireccion()}">
                                    <label for="direccionC">Direccion</label>
                                </div>
                            </div>
                            <div class="card-action row center">
                                <%if (idC != 0) {%>
                                <button class="btn waves-effect" type="button" onclick="modificarCliente();">Modificiar</button>
                                <%} else {%>
                                <button class="btn waves-effect" type="button" onclick="validarCliente();">Aceptar</button>
                                <%}%>
                                <button class="btn waves-effect" type="button" onclick="cancelarNuevoClienteVer()">Cancelar</button>
                            </div>

                        </div>
                    </div>
                </form>

            </div>
        </div>
        <script>
            document.addEventListener('DOMContentLoaded',function(){
                document.getElementById("cOculto").className='';
                document.getElementById("cLoad").className='hide';
                
            });
            function validarCliente(){
                var mensaje="";
                var ban=false;
                if(document.getElementById("nombreC").value.length==0){
                    mensaje="Falta agregar un nombre.";
                    ban=true;
                }
                if(document.getElementById("celC").value.length==0 && ban==false){
                    mensaje="Falta agregar el teléfono celular.";
                    ban=true;
                }
                if(document.getElementById("direccionC").value.length==0 && ban==false){
                    mensaje="Falta agregar la dirección.";
                    ban=true;
                }
                if(ban){
                    M.toast({html: '<i class=\"material-icons prefix\">warning</i>&nbsp;&nbsp;&nbsp;'+mensaje});
                }else{
                    document.getElementById("cOculto").className='hide';
                    document.getElementById("cLoad").className='';
                    document.getElementById("acc").value="1";
                    document.controlClientes.submit();
                }
            }
            function cancelar(){
                document.clientes.action="Clientes.jsp"
                document.clientes.submit();
            }
            function cancelarBusqueda(){
                document.clientes.search.value="";
                document.clientes.action="Clientes.jsp"
                document.clientes.submit();
            }
            function editarCliente(id){
                document.getElementById("acc").value="2";
                document.getElementById("idd").value=id;
                document.controlClientes.submit();
            }
            function nuevoClienteRefresh(){
                document.clientes.action="Modules/Clientes/Clientes.jsp?a=1"
                document.clientes.submit();
            }
            function nuevoClienteVer(){
                document.getElementById("tablaClientesDiv").className='hide';
                document.getElementById("clienteNuevoDiv").className='';
            }
            function cancelarNuevoClienteVer(){
                document.getElementById("tablaClientesDiv").className='';
                document.getElementById("clienteNuevoDiv").className='hide';
            }
            function modificarCliente(id){
                document.getElementById("acc").value="3";
                document.controlClientes.submit();
            }
        </script>
        <%
            String cC = request.getParameter("cC") != null ? request.getParameter("cC") : "";
            String mC = request.getParameter("cM") != null ? request.getParameter("cM") : "";
            if (cC.equalsIgnoreCase("true")) {
                out.println("<script>M.toast({html: '<i class=\"material-icons prefix\">done</i>&nbsp;&nbsp;&nbsp;Cliente creado correctamente.', classes: 'rounded'});</script>");
            }
            if (cC.equalsIgnoreCase("false")) {
                out.println("<script>M.toast({html: '<i class=\"material-icons prefix\">warning</i>&nbsp;&nbsp;&nbsp;No se ha podido crear el cliente.', classes: 'rounded'});</script>");
            }
            if (mC.equalsIgnoreCase("true")) {
                out.println("<script>M.toast({html: '<i class=\"material-icons prefix\">done</i>&nbsp;&nbsp;&nbsp;Cliente modificado correctamente.', classes: 'rounded'});</script>");
            }
            if (mC.equalsIgnoreCase("false")) {
                out.println("<script>M.toast({html: '<i class=\"material-icons prefix\">warning</i>&nbsp;&nbsp;&nbsp;No se ha podido modificar el cliente.', classes: 'rounded'});</script>");
            }
            if (idC != 0) {
                out.println("<script>nuevoClienteVer();</script>");
            }
            if (a == 1) {
                out.println("<script>nuevoClienteVer();</script>");
            }
        %>

    </body>
</html>
