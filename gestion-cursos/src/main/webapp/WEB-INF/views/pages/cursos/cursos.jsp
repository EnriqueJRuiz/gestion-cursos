<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
			<section class="row">
				<div class="col-xs-12  col-md-10 col-md-offset-1 col-lg-6 col-lg-offset-3 jumbotron">					
					<div class="row">
						<header class="col-xs-12  col-md-10 col-md-offset-1  col-lg-6 col-lg-offset-3 page-header text-center"><h3>Cursos</h3></header>
					</div>
					<div class="panel-body">
						<a href="<c:url value='cursos/addCurso'/>" class="btn btn-success "><span class="glyphicon glyphicon-plus-sign"></span> Crear</a>
					</div>
					<table>
						<thead>
							<tr>
								<th>Codigo Curso</th>
								<th>Nombre Curso</th>
								<th></th>
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
								 			<td>
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
			</section>	    