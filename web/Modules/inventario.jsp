<%-- 
    Document   : inventario
    Created on : 21/09/2020, 02:41:47 PM
    Author     : JOSE.RODARTE
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="AppDB" class="AppUtilidades.AppDB" scope="session" />
<jsp:useBean id="AppUtil" class="AppUtilidades.AppUtil" scope="session" /> 
<!DOCTYPE html>
<%
    String search=request.getParameter("search")!=null?request.getParameter("search"):"";
    String qry="";
    qry="SELECT "
            + " prod.descripcion  Producto "
            + ", prov.nombre  Proveedor "
            + ", alm.descripcion  Almacen "
            + ", ad.cantidad Cantidad "
            + " FROM "
            + " tr_almacen_detalle as ad "
            + ", tr_proveedores as prov "
            + ", tc_almacen as alm "
            + ", tr_productos as prod "
            + " WHERE "
            + " ad.id_producto=prod.id_producto "
            + " and ad.id_almacen=alm.id_almacen "
            + " and ad.id_proveedor=prov.id_proveedor ";
            if(search.length()!=0){
                qry+=" AND UPPER(prod.descripcion) like '%"+search.toUpperCase()+"%' ";
            }
            qry+= " ORDER BY "
            + " prod.descripcion ";
%>

<html>
    <head>
        <%=AppUtil.insertMETA()%>
    </head>
    <body>
        <div class="progress" id="cLoad"><div class="indeterminate"></div></div>
        <div id="cOculto" class="hide scale-transition">
            <div class="row s12 m12 l12 xl12">
                <nav class="fixed-navbar">
                    <div class="nav-wrapper navBreadcrubs">
                        <ul id="nav-mobile" class="right">
                            <li><a href="agregarProductos.jsp">Agregar</a></li>
                            <li>
                                <nav class="fixed-navbar">
                                    <div class="nav-wrapper navBreadcrubs">
                                        <form name="inventario" method="post" action="inventario.jsp">
                                            <div class="input-field">
                                                <input id="search" name="search" type="search" value="<%=search%>"  required>
                                                <label class="label-icon" for="search"><i class="material-icons">search</i></label>
                                                <i class="material-icons" onclick="cancelarBusqueda()">close</i>
                                            </div>
                                        </form>
                                    </div>
                                     
                                </nav>
                            </li>
                        </ul>
                    </div>
                </nav>
            </div>
            <div class="container row s12 m12 l12 xl12 white shadow">
                <%=AppDB.imprimeTablaReporte(qry,"","")%>
            </div>
        </div>
        <script>
            document.addEventListener('DOMContentLoaded',function(){
                document.getElementById("cOculto").className='';
                document.getElementById("cLoad").className='hide';
                
            });
            function cancelarBusqueda(){
                document.inventario.action="inventario.jsp?search=";
                document.inventario.submit();
            }
        </script>
    </body>
</html>
