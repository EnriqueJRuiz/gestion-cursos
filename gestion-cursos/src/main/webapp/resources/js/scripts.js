jQuery(document).ready(function($) {
	$("#search-form").click(function(event) {
		event.preventDefault();
		
		console.log("primer paso");
		
		var text = searchAjax();
		
		console.log("text");
		
		
	});
});

function searchAjax() {
	console.log("segundo paso");
	var txt = "";
	var data = $("#buscar").val();
	return new Promise(function(resolve, reject){
		$.ajax({
			url : "http://localhost:8080/gestioncursos/search",
			type : "POST",
			contentType : "application/json",
			data : data,
			dataType : 'json'
		}).done(function(data){
			$('tbody').remove();
			if (data.length > 0) {
			console.log(data);
				for (var i = 0; i < data.length; i++) {
	                let curso = data[i];
	                console.log(curso);
	                txt += parseCurso(curso);
	            }
			}else{
                txt ="no se encuentran ampliaciones en la BBDD";
            }
            resolve(txt)
        }, function(error) {//error
            console.log(error);
            txt ="error en la carga de ampliaciones";
            reject(txt);
        });
		
	});
	
}
function parseCurso(curso){
    let htmlEdit = "<button  type='button' class='btn btn-primary' name='editar' data-whatever='Editar'>Editar</button>";
    let htmlDelete = "<button >Borrar</button>";
    let texto = "<tr>" +
        "<td>" +
        curso.NomCurso +
        "</td>" +
        "<td>" +
        curso.CodCurso +
        "</td>" +
        "<td>" +
        htmlEdit +
        htmlDelete +
        "</td>" +
        "</tr>";
    console.log(texto);
    return texto;
}



