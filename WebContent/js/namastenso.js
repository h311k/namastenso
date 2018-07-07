/**
 * Scripts customizados do projeto
 */

$(document).ready(function() {
	$('#btn-login').click(function() {
		user = $('#user').val();
		pass = $('#pass').val();
		
		if(user=="" ) {
			alert("O campo de usuário está em branco");
		} else {
			efetuaLogin([{name:'user', value:user},{name:'pass', value:pass}]);
		}
		window.getAutenticaUsuarioCallback = function(xhr, status, args) {
			var retorno = args.retorno;
			if(retorno!="ok"){
				alert(retorno);
			} else {
				//Abrir a pagina inicial
				window.open("http://localhost:8080/namastenso/index.xhtml", '_self');
			}
		}
	});
});