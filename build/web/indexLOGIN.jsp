<%-- 
    Document   :    index.jsp  
    Created on :    JSP
    Description:    Pagina de inicio con Login 
    Tags       :    Login index usuario
    Author     : jose.rodarte

--%>
<jsp:useBean id="AppDB" class="AppUtilidades.AppDB" scope="session" />
<jsp:useBean id="AppUtil" class="AppUtilidades.AppUtil" scope="session" /> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
         <%=AppUtil.insertMETA()%>
<%
    String in = request.getParameter("inicio")!=null?request.getParameter("inicio"):"0";
    String msj = request.getParameter("msj")!=null?request.getParameter("msj"):"0";
    String us="";
    String pa="";
    int inicio=Integer.parseInt(in);
    int correcto=0;
    String mensaje="";
    if(inicio!=0){
        if(inicio==7){
            correcto=3;
        }else{
            //inicio=inicio+1;
               
             us = request.getParameter("usuario")!=null?request.getParameter("usuario"):"0";
             pa = request.getParameter("password")!=null?request.getParameter("password"):"0";
            correcto=AppUtil.validaUsuario(us, pa);
            if(correcto==0){
                response.sendRedirect("Dermolastin.jsp");
            }
        }
        if(correcto==3){
           AppDB.insertaCampo("update tr_usr_usuarios set activo=0 where login='"+request.getParameter("usuario").trim()+"'");
        }
    }
%>
        <script lang="JavaScript">
            function iniciarSession(){
                document.getElementById("cOculto").className='hide';
                document.getElementById("cLoad").className='';
                document.getElementById("inicio").value=<%=inicio+1%>;
                document.indes.action="index.jsp";
                document.indes.submit();
            }
            function validacion(correcto){
                if(correcto=="1"){
                    var toastHTML="<i class=\"material-icons prefix\">warning</i>&nbsp;&nbsp;&nbsp;El usuario no existe.";
                    M.toast({html: toastHTML, classes: 'rounded'});
                    document.getElementById("usuario").value='<%=us%>';
                    document.getElementById("usuario").focus();
                }
                if(correcto=="2"){
                    var toastHTML="<i class=\"material-icons prefix\">warning</i>&nbsp;&nbsp;&nbsp;Los datos introducidos, no son correctos.";
                    M.toast({html: toastHTML, classes: 'rounded'});
                    document.getElementById("usuario").value='<%=us%>';
                    document.getElementById("usuario").focus();
                }
                if(correcto=="3"){
                    var toastHTML="<i class=\"material-icons prefix\">warning</i>&nbsp;&nbsp;&nbsp;Haz exedido el limite de intentos, el usuario estara temporalmente deshabilitado. <br>Comunicate con el Administrador.";
                    M.toast({html: toastHTML});
                }
                
            }
        </script>

       
    </head>
    
    <body class="indexBG" onload="validacion('<%=correcto%>');">
        <nav>
          <div class="nav-wrapper white">
            <a href="#" class="brand-logo center"><img src="Sources/media/images/logo.png" height="55px"  class="responsive-im"></a>
          </div>
        </nav>
        <form name="indes" method="POST" action="" >
              <div class="progress" id="cLoad"><div class="indeterminate"></div></div>
            <br>
            <div id="cOculto" class="hide scale-transition">
            <div class="row">
                <div class="col s12 m6 offset-m3 l4 offset-l4">
                  <div class="card  white darken-1 z-depth-5">
                    <div class="card-content">
                        <span class="card-title"><p style="color: #018CCD">Iniciar sesion</p></span>
                        <div class="input-field col s12 m12 l12  ">
                            <i class="material-icons prefix" style="color:#018CCD;">account_circle</i>
                            <input name="usuario" id='usuario' type="text" class="validate">
                            <label for="usuario" style="color:#018CCD;">Usuario</label>
                        </div>
                        <div class="input-field col s12 m12 l12">
                            <i class="material-icons prefix" style="color: #018CCD;">vpn_key</i>
                          <input name="password" id='password' type="password" class="password">
                          <label for="password" style="color: #018CCD;">Contraseña</label>
                        </div>
                    </div>
                      <br><br><br><br><br><br><br>
                    <div class="card-action ">
                        <input type="hidden" id='inicio' name="inicio" value="1" />
                        <button type="button" class="btn waves-effect right"  style="background-color: #018CCD;" onclick="iniciarSession();">Iniciar</button>
                        <br><br>
                    </div>
                  </div>
                </div>
            </div>
            </div>
        </form>
        <script>
              document.addEventListener('DOMContentLoaded',function(){
                     document.getElementById("cOculto").className='';
                     document.getElementById("cLoad").className='hide';
             });
        </script>
    </body>
</html>
