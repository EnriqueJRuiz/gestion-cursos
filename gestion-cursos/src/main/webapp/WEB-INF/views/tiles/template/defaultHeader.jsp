<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

	<nav class="navbar navbar-custom" role="navigation" >
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
				<span class="sr-only">Desplegar navegación</span>
			    <span class="icon-bar"></span>
			    <span class="icon-bar"></span>
			    <span class="icon-bar"></span>
			</button>
		    <a class="navbar-brand" href="/gestioncursos">Gestion Cursos</a>
		</div>
		    <div class="collapse navbar-collapse navbar-ex1-collapse">
				<ul class="nav navbar-nav">
				    <li><a href="<c:url value='/cursos'/>">Cursos</a></li>
			    </ul>
			    <ul class="nav navbar-nav navbar-right  btn-group">
			    	<li>
				 		<sec:authorize access="isAnonymous()">
			                <form method="POST" action="<c:url value='/login'/>" role="form"  class="navbar-form navbar-right">
			                    <div class="input-group">
									<input name="userId" id="userId" type="text" placeholder="Username" value="${SPRING_SECURITY_LAST_USERNAME}"/> 
			                   </div>
			                    <div class="input-group">
									<input name="password" id="password" placeholder="Password" type="password"/>
			                   </div>					                    
			                    <input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
			              		<div class="form-group ">
			                    	<input type="submit" value="Login" class="btn btn-info"/>
			                    </div>
			                </form>
			            </sec:authorize>
			            <sec:authorize access="isAuthenticated()">
			            	<div class="form-group navbar-form navbar-right">
			                	<a href="<c:url value="/logout" />" class="btn btn-info" type="button" >Logout</a>
			            	</div>
			            </sec:authorize>
				 	</li>
 				</ul>
		    </div>
		</nav>

	<h1 class="text-center">CURSOS</h1>

		