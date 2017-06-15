<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<section class="col-xs-8 col-xs-offset-2">
	<div class="row">
	<a href="<c:url value='cursos/addCurso'/>" class="btn btn-primary "><span class="glyphicon glyphicon-plus-sign"></span> Crear</a>
	<button type="button" class="btn btn-warning" data-toggle="modal" data-target="#cargaModal">Cargar cursos.csv</button>
		<table id="tablaCursos" class="table table-hover ">
			<thead>
				<tr>
					<th class="col-xs-2">Codigo Curso</th>
					<th class="col-xs-8">Nombre Curso</th>
					<th class="col-xs-2"></th>
				</tr>
			</thead>
			<tfoot></tfoot>
			<tbody>
				<c:choose>
				 	<c:when test="${not empty listadocursos}">	<!-- cartaController -->
				 		<c:forEach var="curso" items="${listadocursos}">
				 			<tr>
					 			<td class="col-xs-2">${curso.codCursos}</td>
					 			<td class="col-xs-8">${curso.nomCursos}</td>
					 			<td class="col-xs-2">
					 				<a href="<c:url value='cursos/${curso.idProxCurso}'/>"  class="btn btn-warning " role="button"><span class="glyphicon glyphicon-pencil"></span></a>
					 				<a href="<c:url value='cursos/deleteCurso/${curso.idProxCurso}'/>"id="${curso.idProxCurso}" class="btn btn-danger  borrarColor" role="button"><span class="glyphicon glyphicon-trash"></span></a>
					 		
					 			</td>
					 		</tr>	
				 		</c:forEach>	
				 	</c:when>
				 	<c:otherwise>
				 		<tr><td colspan="3">No se han encontrado Cursos en la Base de Datos</td></tr>
				 	</c:otherwise>
				</c:choose>
			</tbody>
		</table>
	</div>
	
	<!-- Modal -->
	<div class="modal fade" id="cargaModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalLabel">Cargar CSV</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
	       <p>Se reiniciara la BBDD con los datos del CSV 
	       	¿Desea reiniciar la BBDD?
	       	Tardará un momento. tenga paciencia.
	       </p>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
	        <a href="<c:url value='cursos/cargarCSV'/>" class="btn btn-success" >Cargar</a>
	      </div>
	    </div>
	  </div>
	</div>
</section>	    