<%@ page session="false" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

	<section class="col-xs-12">
		<div class="col-xs-12  col-md-10 col-md-offset-1 col-lg-6 col-lg-offset-3 jumbotron">
			<div class="row">
				<header class="col-xs-12  col-md-10 col-md-offset-1  col-lg-8 col-lg-offset-2 text-center page-header titulo"><h1></h1></header>
			</div> 
			<div class="row">
				<table>
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
		</div>
	</section>

