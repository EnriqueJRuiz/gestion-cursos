<%@ page session="false" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<section class="col-xs-8 col-xs-offset-2">
	<div class="row">
		<div class="form-group col-xs-8">
	        <input type="text" id="buscar" class="form-control col-xs-6" placeholder="Buscar">
	        
	    </div>
	    <button type="button" id="search-form" class="btn btn-default">Buscar</button> 
    </div>
     
	<div class="row">
		<table id="tablaPrincipal" class="table table-hover ">
			<thead>
				<tr>
					<th>Codigo Curso</th>
					<th>Nombre Curso</th>
				</tr>
			</thead>
			<tfoot></tfoot>
			<tbody>
				<c:choose>
				 	<c:when test="${not empty listadocursos}">	<!-- cartaController -->
				 		<c:forEach var="curso" items="${listadocursos}">
				 			<tr>
					 			<td>${curso.codCursos}</td>
					 			<td>${curso.nomCursos}</td>
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
</section>

