<%@tag description="Plantilla del SIT" pageEncoding="UTF-8" %>
<%@attribute name="ventanaTitulo" %>
<%@attribute name="moduloTitulo" %>
<%@attribute name="moduloSubtitulo" required="false" %>
<%@attribute name="moduloContenido" fragment="true" required="true" %>
<%@attribute name="headScriptArea" fragment="true" required="false" %>
<%@attribute name="bodyScriptArea" fragment="true" required="false" %>

<!DOCTYPE html>
<html lang="es-MX" xmlns="http://www.w3.org/1999/xhtml"
      xmlns:ui="http://xmlns.jcp.org/jsf/facelets"
      xmlns:h="http://xmlns.jcp.org/jsf/html"
      xmlns:f="http://xmlns.jcp.org/jsf/core">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport" />
        <title>${ventanaTitulo} | Books </title>

		<!-- jQuery -->
		<script src="${pageContext.request.contextPath}/resources/jquery/js/jquery.min.js"></script>
		<!-- jQuery UI -->
        <script src="${pageContext.request.contextPath}/resources/jquery-ui/jquery-ui.min.js"></script>
		<link href="${pageContext.request.contextPath}/resources/jquery-ui/jquery-ui.css" rel="stylesheet" type="text/css"/>
		<!-- jQuery Form -->
        <script src="${pageContext.request.contextPath}/resources/jquery-form/jquery.form.min.js" /></script>
        <%-- Bootstrap --%>
        <link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css" />
        <script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.min.js"></script>
        <%-- SlimScroll --%>
        <script src="${pageContext.request.contextPath}/resources/slimscroll/js/jquery.slimscroll.min.js"></script>
        <%-- Font Awesome --%>
        <link href="${pageContext.request.contextPath}/resources/font-awesome/css/font-awesome.css" rel="stylesheet" type="text/css" />
        <%-- AdminLTE App --%>
        <link href="${pageContext.request.contextPath}/resources/admin-lte/css/AdminLTE.css" rel="stylesheet" type="text/css" />
        <link href="${pageContext.request.contextPath}/resources/admin-lte/css/skin-green-light.css" rel="stylesheet" type="text/css" />
        <link href="${pageContext.request.contextPath}/resources/admin-lte/css/skin-blue-light.css" rel="stylesheet" type="text/css" />
        <link href="${pageContext.request.contextPath}/resources/admin-lte/css/skin-red-light.css" rel="stylesheet" type="text/css" />
        <script src="${pageContext.request.contextPath}/resources/admin-lte/js/app.min.js"></script>
        <%-- Sweetalert --%>
         <link href="${pageContext.request.contextPath}/resources/sweetalert2/css/sweetalert2.css" rel="stylesheet" type="text/css" />
        <script src="${pageContext.request.contextPath}/resources/sweetalert2/js/sweetalert2.min.js"></script>
        <%-- FormValidation --%>
        <link href="${pageContext.request.contextPath}/resources/formvalidation/css/formValidation.min.css" rel="stylesheet" type="text/css" />
        <script src="${pageContext.request.contextPath}/resources/formvalidation/js/formValidation.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/formvalidation/js/framework/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/formvalidation/js/language/es_ES.min.js"></script>        
      
        <%-- Ionicons --%>
        <link href="${pageContext.request.contextPath}/resources/ionicons/css/ionicons.css" rel="stylesheet" type="text/css" />
                
        <!-- bootstrap datepicker -->
	    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/datepicker/datepicker3.css">	
	    <!-- Bootstrap Color Picker -->
	    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/colorpicker/bootstrap-colorpicker.min.css">
	    <!-- bootstrap datepicker -->
	    <script src="${pageContext.request.contextPath}/resources/datepicker/bootstrap-datepicker.js"></script>
	    <!-- bootstrap color picker -->
	    <script src="${pageContext.request.contextPath}/resources/colorpicker/bootstrap-colorpicker.min.js"></script>
	
	    <script src="${pageContext.request.contextPath}/resources/datepicker/locales/bootstrap-datepicker.es.js"></script>
	    
	     <%-- SIT App --%>
        <script src="${pageContext.request.contextPath}/resources/sit/js/util.js"></script>
            <!-- DataTables -->
    	<script src="${pageContext.request.contextPath}/resources/datatables/js/jquery.dataTables.js"></script>
    	<script src="${pageContext.request.contextPath}/resources/jquerytable2json/jquery.tabletojson.js"></script>
        
        <jsp:invoke fragment="headScriptArea" />
    </head>
    <body class="hold-transition  sidebar-mini fixed" id="bodyPrincipal">
        <div class="wrapper">

            <%-- Agrega el header --%>
            <%@include file="/WEB-INF/jspf/header.jspf"%>

            <%-- Agrega el menu --%>
            <%@include file="/WEB-INF/jspf/left-sidebar.jspf"%>

            <!-- Content Wrapper. Contains page content -->
            <div class="content-wrapper">
                <!-- Content Header (Page header) -->
                <section class="content-header">
                    <h1>
                        ${moduloTitulo}
                        <small>${moduloSubtitulo}</small>
                    </h1>
                </section>

                <!-- Main content -->
                <section class="content">
                 
                    <jsp:invoke fragment="moduloContenido" />
                </section>
                <!-- /.content -->
            </div>
            <!-- /.content-wrapper -->

            <%@include file="/WEB-INF/jspf/footer.jspf"%>
        </div>
        <!-- ./wrapper -->

       
        <script>
$(document).ready(function () {	       
       
     $('#cerrarSesion').click(
    		 
             function () {
            	 console.log('entra a cerrar sesion');
                 $.ajax({
                     type: "DELETE",
                     url: "${pageContext.request.contextPath}/api/sesiones",
                     success: function (data) {
                       window.location = "${pageContext.request.contextPath}/";
                     },
                     error: function (jqXHR, textStatus, errorThrown) {
                         console.log(textStatus + " " + errorThrown);
                     }
                 });
             }
         );   
     
     $(".navbar .applications-menu .menu").slimscroll({
         height: "400px",
         alwaysVisible: false,
         size: "3px"
     }).css("width", "100%"); 

    
});

var entorno ="${ambiente.entorno}";
if( entorno =="DEV"){
	$('#bodyPrincipal').addClass("skin-blue-light")
		
	}else if( entorno =="PROD"){
		$('#bodyPrincipal').addClass("skin-green-light")
	
}else{
	$('#bodyPrincipal').addClass("skin-red-light")
	
	
}

        </script>
        <jsp:invoke fragment="bodyScriptArea" />
    </body>
</html>