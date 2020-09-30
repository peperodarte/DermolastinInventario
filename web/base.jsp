<%-- 
    Document   : nombre JSP
    Created on : 20/09/2020, 09:25:41 PM
    Author     : JOSE.RODARTE
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="AppDB" class="AppUtilidades.AppDB" scope="session" />
<jsp:useBean id="AppUtil" class="AppUtilidades.AppUtil" scope="session" /> 
<!DOCTYPE html>
<html>
    <head>
     <%=AppUtil.insertMETA()%>
    </head>
    <body>
        <div class="progress" id="cLoad"><div class="indeterminate"></div></div>
        <div id="cOculto" class="hide scale-transition">
            <!--Aqui va el contenido de la pagina-->
        </div>
         <script>
              document.addEventListener('DOMContentLoaded',function(){
                document.getElementById("cOculto").className='';
                document.getElementById("cLoad").className='hide';
              });
         </script>
    </body>
</html>