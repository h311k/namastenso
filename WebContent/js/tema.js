/**
 * Controla as funcionalidades do tema do dashboard
 */
$(document).ready(function(){
	var cidade;
	var estado;
	var idLocal;
	
	$.ajax('http://ip-api.com/json')
	  .then(
	      function success(response) {
	          cidade = response.city;
	          estado = response.region;
	          getClimaTempoCidadeId(cidade, estado);
	      },

	      function fail(data, status) {
	          console.log('Request failed.  Returned status of',
	                      status);
	      }
	  );
});

function getClimaTempoCidadeId(cidade, estado) {
	$.getJSON('https://cors.io/?http://apiadvisor.climatempo.com.br/api/v1/locale/city?name='+cidade+'&state='+estado+'&token=207dd08101b70c9a6a72b196757c9d98', function(data){
		idLocal = data[0].id;
	    getClimaTempoDados(idLocal);
	});
}

function getClimaTempoDados(idLocal) {
	$.getJSON('https://cors.io/?http://apiadvisor.climatempo.com.br/api/v1/forecast/locale/'+idLocal+'/hours/72?token=207dd08101b70c9a6a72b196757c9d98', function(data){
		console.log(data);
	});
}
