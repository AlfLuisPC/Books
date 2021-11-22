<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Log in</title>
        <!-- Tell the browser to be responsive to screen width -->
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <link href="resources/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <!-- Font Awesome -->
        <link href="resources/font-awesome/css/font-awesome.css" rel="stylesheet" type="text/css"/>
        <!-- Theme style -->
        <link href="resources/admin-lte/css/AdminLTE.css" rel="stylesheet" type="text/css"/>
        <!-- iCheck -->
        <link href="resources/iCheck/square/green.css" rel="stylesheet" type="text/css"/>

        <!-- Google Font -->
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
        
        <!-- Sweetalert -->
    	<link href="${pageContext.request.contextPath}/resources/sweetalert2/css/sweetalert2.css" rel="stylesheet" type="text/css" />
    	<script src="${pageContext.request.contextPath}/resources/sweetalert2/js/sweetalert2.min.js"></script>
        
    </head>
    <body class="hold-transition login-page">
        <div class="login-box">
            <div class="login-logo">
                <a href="../../index2.html"><b>Books</b></a>
            </div>
            <!-- /.login-logo -->
            <div class="login-box-body">
                <p class="login-box-msg">Identifiquese para iniciar su sesión</p>

                <form id="login-form" method="post">
                    <div class="form-group has-feedback">
                        <input id="usuario" type="text" class="form-control" placeholder="Nombre de usuario">
                        <span class="glyphicon glyphicon-user form-control-feedback"></span>
                    </div>
                    <div class="form-group has-feedback">
                        <input id="password" type="password" class="form-control" placeholder="Contraseña">
                        <span class="glyphicon glyphicon-lock form-control-feedback"></span>
                    </div>
                    <div class="row">
                        <div class="col-xs-12">
                            <button id="login-btn" type="submit" class="btn btn-primary btn-block btn-flat">Iniciar sesión</button>
                        </div>
                        <!-- /.col -->
                    </div>
                </form>
				<br/>
                <a href="${pageContext.request.contextPath}/signup.jsp">Registrarse</a><br>
            </div>
        </div>

        <script src="resources/jquery/js/jquery.min.js" type="text/javascript"></script>
        <script src="resources/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
        <script src="resources/iCheck/icheck.min.js" type="text/javascript"></script>
        <script>
        
        $(document).ready(function(){
        	
        	$("#login-btn").click(
				function(event) {.
					event.preventDefault();
								
					var credenciales = {};
					credenciales.nombreUsuario = $('#usuario').val();
					credenciales.claveUsuario = $('#password').val();
						$("#login-btn").prop("disabled", true);

						$.ajax({
							type : "POST",
							url : "${pageContext.request.contextPath}/usuario/login",
							data : JSON.stringify(credenciales),
	                        contentType : 'application/json',
							timeout : 600000,
							success : function(data) {
								var token = data.token;
							    window.location = '${pageContext.request.contextPath}/index.jsp';			
							},
				            error: function (data, textStatus, xhr) {
								$("#login-btn").prop("disabled",	false);
								var msgerror = data.responseText;
								swal({
									title:"Error " + data.statusCode,
									text:msgerror,
									type:"error"
								});								
							}
						});
					});
			});
        </script>
    </body>
</html>
