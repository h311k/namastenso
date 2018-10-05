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
	    	  console.log(response);
	          cidade = response.city;
	          estado = response.region;
	          getClimaTempo(cidade, estado);
	      },

	      function fail(data, status) {
	          console.log('Request failed.  Returned status of',
	                      status);
	      }
	  );
});

function getClimaTempo(cidade, estado) {
	$.ajax('https://cors.io/?http://apiadvisor.climatempo.com.br/api/v1/locale/city?name='+cidade+'&state='+estado+'&token=207dd08101b70c9a6a72b196757c9d98')
	  .then(
	      function success(response) {
	          console.log(response);
	      },

	      function fail(data, status) {
	          console.log('Request failed.  Returned status of',
	                      status);
	      }
	  );
}