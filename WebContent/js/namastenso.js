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
				$('#login-form').prepend('<div class="alert alert-danger"><strong>Ops!</strong> Seu nome de usuário ou sua senha estão incorretos.</div>');
				$('#user').val('');
				$('#pass').val('');
			}
		}
	});
	
	$('#create-user-user').focus(function(){
		$('#email-existente').remove();
		$('#email-disponivel').remove();
	});
	
	$('#create-user-user').focusout(function(){
		email = $('#create-user-user').val();
		
		if(email!="") {
			validaEmail([{name:'email',value:email}]);
		}
		window.getValidaEmailCallback = function(xhr, status, args) {
			var retorno = args.retorno;
			if(retorno=='existente') {
				$('#create-user-form').prepend('<div id="email-existente" class="alert alert-danger"><strong>Ops!</strong> Esse e-mail já está cadastrado. Escolha outro e-mail.</div>');
			} else {
				$('#create-user-form').prepend('<div id="email-disponivel" class="alert alert-success"><strong>Oba!</strong> Esse e-mail está disponível para cadastro!</div>');
			}
		}
	});
});