jQuery(document).ready(function($) {
	$("#search-form").click(function(event) {
		event.preventDefault();
		
		//console.log("primer paso");
		
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
                txt ="no se encuentran cursos con esa busqueda";
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



