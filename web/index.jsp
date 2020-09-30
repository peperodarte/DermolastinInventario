<%-- 
    Document   : Dermolastin
    Created on : 20/09/2020, 10:32:11 PM
    Author     : JOSE.RODARTE
--%>
<jsp:useBean id="AppDB" class="AppUtilidades.AppDB" scope="session" />
<jsp:useBean id="AppUtil" class="AppUtilidades.AppUtil" scope="session" /> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%=AppUtil.insertMETA()%>
    </head>
    <body class="indexBG">
        <div class="progress" id="cLoad"><div class="indeterminate"></div></div>
        <div id="cOculto" class="hide scale-transition">

            <div class="navbar-fixed">
                <nav class="navBreadcrubs">
                    <div class="nav-wrapper">
                        <a href="#" data-target="menu" class="show-on-large sidenav-trigger left"><i class="material-icons">menu</i></a>
                        <a href="#" class="brand-logo hide-on-small center"><img src="Sources/media/images/logoBlanco.png" height="56px"   class="responsiv"></a> 
                        <a href="#" class=" hide-on-small right">
                            <div class="chip">
                                <img src="Sources/media/images/user.png" alt="Usuario">
                                Usuario
                            </div>
                        </a> 
                    </div>
                </nav>
            </div>
            <ul id="menu" class="sidenav" style="width: auto;">
                <li>&nbsp;</li>
                <li style="text-align: center;"><img src="Sources/media/images/logo.png"  class="responsive-image"></li>
                <li>&nbsp;</li>
                <li><a hidden="#" class="sidenav-close" onclick="abrePrincipal('Clientes/Clientes.jsp')"><i class="material-icons prefix">supervisor_account</i> Clientes</a></li>
                <li><a hidden="#" class="sidenav-close" onclick="abrePrincipal('puntoVenta.jsp')"><i class="material-icons prefix">point_of_sale</i> Ventas</a></li>
                <li><a hidden="#" class="sidenav-close" onclick="abrePrincipal('inventario.jsp')"><i class="material-icons prefix">save_alt</i> Inventario</a></li>
                <li><a hidden="#" class="sidenav-close" onclick="abrePrincipal('reportes.jsp')"><i class="material-icons prefix">insert_chart_outlined</i>Reportes</a></li>
                <li><a hidden="#" class="sidenav-close" onclick="abrePrincipal('adminPanel.jsp')"><i class="material-icons prefix">admin_panel_settings</i>Administracion</a></li>
                <li class="divider"></li>
                <li><a hidden="#" class="sidenav-close" onclick=""><i class="material-icons prefix">login</i>Cerrar sesion</a></li>
            </ul>
        </div>
        
                <div class="responsiveContent">
                    <iframe id="principal" class="responsiveContent" src=""  allowfullscreen="" frameborder="0" scrolling="yes" noresize></iframe>
                </div>
          
        <script>
            document.addEventListener('DOMContentLoaded',function(){
                document.getElementById("cOculto").className='';
                document.getElementById("cLoad").className='hide';
                
                var elemsCollapsibl = document.querySelectorAll('.collapsible');
                var instancesCollapsibl = M.Collapsible.init(elemsCollapsibl);
                     
                var elemsMenuMovil = document.querySelectorAll('.sidenav');
                var instancesMenuMovil = M.Sidenav.init(elemsMenuMovil);
                var elemsToolTips = document.querySelectorAll('.tooltipped');
                var instancesToolTips = M.Tooltip.init(elemsToolTips,{margin: 0,transitionMovement:1});
                     
            });
             
            function abrePrincipal(pagina){
                var iframe=document.getElementById("principal");
                iframe.setAttribute("src",'Modules/'+pagina);          
            }
        </script>
    </body>
</html>
