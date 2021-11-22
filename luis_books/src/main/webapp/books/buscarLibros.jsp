<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="p" tagdir="/WEB-INF/tags"%>
<p:layout ventanaTitulo="Buscar" moduloTitulo="Busqueda">
	<jsp:attribute name="moduloContenido">				
	<section class="content" style="margin-left: 0px">
	 <div class="content">  
		<div class="row ">

	        
<!-- 	        Comienza Filtros -->
	       <div class="col-md-12">  
	          <div class="box box-primary ">
	           	<div class="box-body">
                	<div class="col-md-4">
	                	<div class="form-group">
	            			<label for="busqueda">Busque por Google Book ID, Autor, Titulo o Editorial</label>
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
	         <table id="libros"	class="cedula table table-bordered table-hover">
                  <thead>
                     <tr class="bg-primary">
                         <th bgcolor="#00A65A">Google Book ID</th>
                         <th bgcolor="#00A65A">Autor</th>
                         <th bgcolor="#00A65A">Titulo</th>
                         <th bgcolor="#00A65A">Editorial</th>
                         <th bgcolor="#00A65A">Listas</th>
                         <th bgcolor="#00A65A">Agregar</th>
                     </tr>
                  </thead>
             		<tbody>
                    </tbody>
            </table>	
	       </div>
	        
	        	
	</div>

		</div> 		<!-- 	Fin row filtros -->
		</section>	
</jsp:attribute>
	<jsp:attribute name="bodyScriptArea">
<script>
	$(document).ready(function() {
		
	    var urlListas = "${pageContext.request.contextPath}/libros/obtenerListas";
	    var listas = {};
	      $.ajax({
	          type : 'GET',
	          url : urlListas,
	          contentType : 'application/json',
	          success : function(data, textStatus, jQxhr) {
	        		listas = data.entity;
	          }
	      });
		
			$("#btn-buscar").click(
					function(event) {
					// stop submit the form, we will post it manually.
					event.preventDefault();

					var busqueda = $('#busqueda').val();
					// If you want to add an extra field for the FormData					
					// disabled the submit button
					$.ajax({
						type : "GET",
						url : "https://www.googleapis.com/books/v1/volumes?q="+ busqueda,
						timeout : 600000,
						success : function(data) {
						$('#libros > tbody').find('td').remove();
						if(data){
							for (var i = 0; i < data.items.length; i++) {
							     tr = $('<tr/>');
							    $(tr).append("<td class=" + "googleid" + " >" + data.items[i].id + "</td>");
							    $(tr).append("<td class=" + "autor" + " >" + data.items[i].volumeInfo.authors + "</td>");
							    $(tr).append("<td class=" + "titulo" + " >" + data.items[i].volumeInfo.title + "</td>");
							    $(tr).append("<td class=" + "editora" + " >" + data.items[i].volumeInfo.publisher + "</td>");
							    $(tr).append("<td class=" + "listas>"+ armarListas() +"</td>");
							    $(tr).append("<td class=btn-agregar-book> <button type=" +"submit "+ " class="+ "btn btn-primary btn-block margin-bottom padding " + "id=btn-agregar-book "+ ">Agregar</button> </td>");
							    $('#libros > tbody').append(tr);
							 }
					}															
			},error : function(data,textStatus, xhr) {
						console.log(data);
			}
		});
	  });
			
	 
	function armarListas(){
		var select = "<select id="+"selectListas" +">";
		if(listas){			
			for (var i = 0; i < listas.length; i++) {
			    select += "<option value="+listas[i].idLista+">"+listas[i].nombreLista+"</option>";										    	
			 }
		    select +="</select>";										    	
		}
		return select;
	}
			
			
		
	$(document).on("click","#libros tbody tr td button.btn", function(event) {
		var contador = 0;
		var book = {};
		var idLista = 0;
		$(this).closest('tr').find('td').each(function() {
			var textval = $(this).text(); 
			switch (contador) {
			case 1:
				book.googleBookId = textval;
				break;
			case 2:
				book.autor = textval;
				break;
			case 3:
				book.titulo = textval;
				break;
			case 4:
				book.editorial = textval;		        
				break;
			case 5:
				$.each($(".listas option:selected"), function(){            
					if(idLista == 0){
						idLista =  $(this).val();
					}
		        });
				break;
			}
			contador +=1;
	   	});

		$.ajax({
			type : "POST",
			url : "${pageContext.request.contextPath}/libros/crearLibro?idLista="+ idLista,
			data : JSON.stringify(book),
            contentType : 'application/json',
			timeout : 600000,
			success : function(data) {
				swal({
                    title : "Libro agregado correctamente.",
                    type : "success",
                    closeOnCancel : false
                });		
				idLista = 0;
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
</jsp:attribute>
</p:layout>