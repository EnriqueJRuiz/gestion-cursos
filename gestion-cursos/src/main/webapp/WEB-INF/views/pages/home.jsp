<%@ page session="false" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<section class="col-xs-8 col-xs-offset-2">
	<div class="row">
		<div class="form-group col-xs-8 col-xs-offset-2">
			<div class="icon-addon addon-lg">
	        	<input type="text" id="buscar" class="form-control col-xs-6" placeholder="Buscar">
	        	<label for="buscar" class="glyphicon glyphicon-search" rel="tooltip" title="buscar"></label>
	        </div>
	    </div>
	</div>
     
	<div class="row">
		<table id="tablaPrincipal" class="table table-hover ">
			<thead>
				<tr>
					<th class="col-xs-2">Codigo Curso</th>
					<th class="col-xs-8">Nombre Curso</th>
				</tr>
			</thead>
			<tfoot></tfoot>
			<tbody id="tbody">
				<c:choose>
				 	<c:when test="${not empty listadocursos}">	<!-- cartaController -->
				 		<c:forEach var="curso" items="${listadocursos}">
				 			<tr>
					 			<td class="col-xs-2">${curso.codCursos}</td>
					 			<td class="col-xs-8">${curso.nomCursos}</td>
					 		</tr>	
				 		</c:forEach>	
				 	</c:when>
				 	<c:otherwise>
				 		<tr><td colspan="2">No se han encontrado Cursos en la Base de Datos</td></tr>
				 	</c:otherwise>
				</c:choose>
			</tbody>
		</table>
	</div>
</section>

