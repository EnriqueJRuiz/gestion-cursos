<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%><!--  -->
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%><!--  -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 

			<section class="row">
				<div class="col-xs-12  col-md-10 col-md-offset-1 col-lg-6 col-lg-offset-3 jumbotron">
					<div class="row">
						<header class="col-xs-12  col-md-10 col-md-offset-1  col-lg-6 col-lg-offset-3 text-center page-header"><h3>Cursos form</h3></header>
					</div>
					<form:form action="save" nethod="post" commandName="curso" cssClass="form-horizontal">
						<c:if test="${not empty curso}">
							<form:hidden path="idProxCurso"/>
						</c:if>
					
						<div class="form-group">
							<form:label path="nomCursos" for="nomCursos" cssClass="col-xs-2 form-label">Nombre:</form:label>
							<div class=" col-xs-8">
								<form:input path="nomCursos" cssErrorClass="form-control" cssClass="form-control"  required="required"/>
							</div>
						</div>
						
						<div class="form-group">
							<form:label path="codCursos" for="codCursos" cssClass="col-xs-2 form-label">Codigo:</form:label>
							<div class=" col-xs-8">
								<form:input path="codCursos" cssErrorClass="form-control" cssClass="form-control"  required="required"/>
							</div>
						</div>

						
						<c:set  var="menssg" value="Crear"/>
						<c:if test="${curso.idProxCurso > 0}">
							<c:set var="menssg"  value="Editar" />
						</c:if>
						<a href="<c:url value='/cursos'/>" class="btn btn-danger" ><span class="glyphicon glyphicon-arrow-left"></span> Volver</a>
						<button type="submit"  class="btn btn-success" ><span class="glyphicon glyphicon-ok"></span> ${menssg}</button>
					</form:form>
				</div>
			</section>
		