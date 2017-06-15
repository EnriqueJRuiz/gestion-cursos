jQuery(document).ready(function($) {
	$("#buscar").keyup(function(event) {
		searchAjax();
	});
});




function searchAjax() {
	var txt = "";
	var data = $("#buscar").val();
		$.ajax({
			url : "http://localhost:8080/gestioncursos/search",
			type : "POST",
			contentType : "application/json",
			data : data,
			dataType : 'json'
		}).done(function(data){
			$('tbody tr').remove();
			if (data.length > 0) {
		
				for (var i = 0; i < data.length; i++) {
	                let curso = data[i];
	                
	                txt += parseCurso($.parseJSON(JSON.stringify(curso)));
	                
	            }
			}else{
                txt ="<tr><td colspan='2'>No se han encontrado Cursos en la Base de Datos</td></tr>";
            }
			
        }).fail(function() {//error
            txt ="error en la carga de cursos";
        })
        .always(function() {
        	pintar(txt);
        });
		
		
	
}
function pintar(txt){
	console.log(txt);
	$('tbody').html(txt);
}

function parseCurso(curso){
    let texto = "<tr>" +
        "<td>" +
        curso.codCursos +
        "</td>" +
        "<td>" +
        curso.nomCursos +
        "</td>" +
        "</tr>";
    return texto;
}



