<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="p" tagdir="/WEB-INF/tags"%>
<p:layout ventanaTitulo="Buscar" moduloTitulo="Busqueda">
	<jsp:attribute name="moduloContenido">				
	<section class="content" style="margin-left: 0px">
	 <div class="content">  
		<div class="row ">
        <div class="box">
            <div class="box-body">
                    <form class="form-horizontal" role="form" id="frm-data-lista" method="post">                  
                <section>
                   
                    	<div class="form-group">
                            <label for="numero-complementaria" id="label-complementaria" class="col-sm-2 control-label">Nombre</label>
                            <div class="col-sm-4">
                                <input id="nombreLista" type="text" name="nombreLista" class="form-control ">
                            </div>
                        </div>
                        <div class="box-footer">
                            <div class="col-sm-4">
                                <button type="button" id="btn-crear"
                                        class="btn btn-primary pull-right"> <i class="fa fa-book"
                                        aria-hidden="true"></i> Crear lista
                                </button>
                            </div>
                        </div>
                </section>
                
                <section>
               		<div class="row ">	        
	       <div class="col-md-12">  
	          <div class="box box-primary ">
	           	<div class="box-body">
                	<div class="col-md-4">
	                	<div class="form-group">
	            			<label for="busqueda">Busque por nombre de lista</label>
			                <input id="busqueda" name="busqueda">
			            </div>
	                	
		                	<button type="submit"
										class="btn btn-primary btn-block margin-bottom padding"
										id="btn-buscar">Buscar</button>
		                </div>
		                </div>
	          </div>	          
	        </div>	
	      <div class="col-md-12">  
	         <table id="listas"	class="cedula table table-bordered table-hover">
                  <thead>
                     <tr class="bg-primary">
                         <th bgcolor="#00A65A">ID</th>
                         <th bgcolor="#00A65A">Nombre</th>
                         <th bgcolor="#00A65A">Fecha creación</th>
                         <th bgcolor="#00A65A">
	                         <ul id ="listaLibros">
							</ul>
                         </th>
                       <th bgcolor="#00A65A"></th>
                     </tr>
                  </thead>
             		<tbody>
                    </tbody>
            </table>	
	       </div>
	        
	        	
	</div>

                </section>
                    </form>                
                
                
                
                
                
            </div>
        </div>
        </div>
        </div>
        </section>
        
            </jsp:attribute>
        

	<jsp:attribute name="bodyScriptArea">
<script>

$(document).ready(function() {

    var urlListas = "${pageContext.request.contextPath}/libros/obtenerListas";
      $.ajax({
          type : 'GET',
          url : urlListas,
          contentType : 'application/json',
          success : function(data, textStatus, jQxhr) {
        		$('#listas > tbody').find('td').remove();
				if(data){
					for (var i = 0; i < data.entity.length; i++) {
					     tr = $('<tr/>');
					    $(tr).append("<td class=" + "idLista" + " >" + data.entity[i].idLista + "</td>");
					    $(tr).append("<td class=" + "nombreLista" + " >" + data.entity[i].nombreLista + "</td>");
					    $(tr).append("<td class=" + "fechaCreacion" + " >" + data.entity[i].fechaCreacion + "</td>");	
					    $(tr).append("<td class=" + "books>"+ armarLibros(data.entity[i].libros) +"</td>");		
					    $(tr).append("<td class=btn-eliminar-book> <button type=" +"submit "+ " class="+ "btn btn-primary btn-block margin-bottom padding " + "id=btn-eliminar-book "+ ">Eliminar</button> </td>");
					    $('#listas > tbody').append(tr);
					 }
			}	
          }
      });
      
      
    function armarLibros(libros){
  		var lista = "<ul id="+"libros" +">";
  		if(libros){			
  			for (var i = 0; i < libros.length; i++) {
  			    lista += "<li>"+libros[i].titulo+"</li>";										    	
  			 }
  			lista +="</ul>";										    	
  		}
  		return lista;
  	}
      
	
		$("#btn-buscar").click(
				function(event) {
				// stop submit the form, we will post it manually.
				event.preventDefault();

				var busqueda = $('#busqueda').val();
				// If you want to add an extra field for the FormData					
				// disabled the submit button
				$.ajax({
					type : "GET",
					url : "${pageContext.request.contextPath}/libros/obtenerLista?nombre="+ busqueda,
			        contentType : 'application/json',
					timeout : 600000,
			          success : function(data, textStatus, jQxhr) {
						$('#listas > tbody').find('td').remove();
						for (var i = 0; i < data.entity.length; i++) {
						    tr = $('<tr/>');
						    $(tr).append("<td class=" + "idLista" + " >" + data.entity[i].idLista + "</td>");
						    $(tr).append("<td class=" + "nombreLista" + " >" + data.entity[i].nombreLista + "</td>");
						    $(tr).append("<td class=" + "fechaCreacion" + " >" + data.entity[i].fechaCreacion + "</td>");
						    $(tr).append("<td class=" + "books>"+ armarLibros(data.entity[i].libros) +"</td>");		
						    $(tr).append("<td class=btn-eliminar-book> <button type=" +"submit "+ " class="+ "btn btn-primary btn-block margin-bottom padding " + "id=btn-eliminar-book "+ ">Eliminar</button> </td>");
						    $('#listas > tbody').append(tr);
						}
																			
		},error : function(data,textStatus, xhr) {
					console.log(data);
		}
	});
  });
      
    $("#btn-crear").click(function(event) {

			  var nombreLista = $('#nombreLista').val();
			  console.log(nombreLista);
              var urlCrearLista = "${pageContext.request.contextPath}/libros/crearLista?nombreLista="+nombreLista;
                $.ajax({
                    type : 'POST',
                    url : urlCrearLista,
                    contentType : 'application/json',
                    success : function(data, textStatus, jQxhr) {     
                        swal({
                            title : "Lista creada correctamente.",
                            type : "success",
                            closeOnCancel : false
                        });
                    }
                });
            
        
    });
    
});
 </script>
    </jsp:attribute>
</p:layout>