/**
 * Scripts de configuracoes
 */

function instanciaEmail(btnSalvaEmail) {
	SalvaEmail(btnSalvaEmail);
}

function salvaEmail(btnSalvaEmail) {
	$(btnSalvaEmail).click(function(){
		$('#loader-frame').fadeIn(500);
		nome = $('#nome-da-conta').val();
		host = $('#host').val();
		porta = $('#porta').val();
		usuario = $('usuario').val();
		senha = $('#senha').val();
		$('#loader-frame').fadeOut(500);
	});
}