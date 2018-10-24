/**
 * Scripts de configuracoes
 */

function instanciaEmail(btnSalvaEmail) {
	salvaEmail(btnSalvaEmail);
}

function salvaEmail(e) {
	$(e).click(function(){
		$('#loader-frame').fadeIn(500);
		nome = $('#nome-da-conta').val();
		host = $('#host').val();
		porta = $('#porta').val();
		usuario = $('#usuario').val();
		senha = $('#senha').val();
		atualizaEmail([{name: 'nome' , value: nome},{name: 'host' , value: host},{name: 'porta' , value: porta},{name: 'usuario' , value: usuario},{name: 'senha' , value: senha}]);
		$('#loader-frame').fadeOut(500);
	});
}

function getAtualizaServicoEmailCallback(xhr, status, args) {
	$('#form-email').prepend('<div id="email-salvo" class="alert alert-success">O serviço de e-mail foi atualizado.</div>');
}