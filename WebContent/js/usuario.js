/**
 * Scripts customizados do projeto
 */

$(document).ready(function() {
	
	$('#btn-login').click(function() {
		$('#erro-login').remove();
		$('#desativado-login').remove();
		
		user = $('#user').val();
		pass = $('#pass').val();
		
		if(user=="" ) {
			alert("O campo de usuário está em branco");
		} else {
			$('#loader-frame').fadeIn(500);
			efetuaLogin([{name:'user', value:user},{name:'pass', value:pass}]);
		}
		window.getAutenticaUsuarioCallback = function(xhr, status, args) {
			$('#loader-frame').fadeOut(500);
			var retorno = args.retorno;
			if(retorno!="ok"){
				if(retorno=="falha"){
					$('#login-form').prepend('<div id="erro-login" class="alert alert-danger"><strong>Ops!</strong> Seu nome de usuário ou sua senha estão incorretos.</div>');
				} else {
					$('#login-form').prepend('<div id="desativado-login" class="alert alert-warning"><strong>Ops!</strong> Sua conta ainda está inativa. Aguarde o e-mail de ativação.</div>');
				}
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
			$('#loader-frame').show();
			validaEmail([{name:'email',value:email}]);
		}
		window.getValidaEmailCallback = function(xhr, status, args) {
			$('#loader-frame').fadeOut(500);
			var retorno = args.retorno;
			if(retorno=='existente') {
				$('#create-user-form').prepend('<div id="email-existente" class="alert alert-danger"><strong>Ops!</strong> Esse e-mail já está cadastrado. Escolha outro e-mail.</div>');
			} else {
				$('#create-user-form').prepend('<div id="email-disponivel" class="alert alert-success"><strong>Oba!</strong> Esse e-mail está disponível para cadastro!</div>');
			}
		}
	});
	
	$('#btn-criar-conta').click(function(){
		email = $('#create-user-user').val();
		senha = $('#create-user-pass').val();
		
		if(senha=="") {
			alert("O campo de senha não pode ficar em branco");
			$('#create-user-pass').focus();
		} else {
			$('#loader-frame').fadeIn(500);
			criaUsuario([{name:'email', value:email},{name:'senha', value:senha}]);
			window.getCriaUsuarioCallback = function(xhr, status, args) {
				$('#loader-frame').fadeOut(500);
				var retorno = args.retorno;
				if(retorno=="ok"){
					alert("Sua conta foi cadastrada.\nEnviaremos um e-mail com instruções para você para ativar seu acesso, ok?");
				} else {
					alert("Algo deu errado no cadastro, mas a culpa foi nossa.\nPode tentar novamente mais tarde, por favor?");
				}
			}
		}
	});
	
	if($('body').hasClass('ativa-conta')){
		ativarUsuario();
	}
	function ativarUsuario(){
		console.log("fui carregado");
		$('#loader-frame').fadeIn(500);
		ativaUsuario();
		window.getAtivaUsuarioCallback = function(xhr, status, args) {
			$('#loader-frame').fadeOut(500);
			var retorno = args.retorno;
			if(retorno=="existente"){
				alert("Sua conta foi ativada.\nVocê já pode realizar o login em nossa página.");
			} else {
				alert("Não encontramos sua conta.\nVocê pode criar uma agora mesmo.");
			}
		};
	};
});