/**
 * Scripts de perfil
 */

function formataCalendario(e) {
	$(e).addClass('form-control');
}

function setFotoPerfil(e) {
	$(e).css('background', 'url("'+$('.foto-perfil').attr('img-src')+'")');
	$(e).css('background-size', 'cover');
}

function carregaFotoPerfil(e) {
	$(e).click(function() {
		$('.input-foto-perfil').click();
	});
}

function salvaFotoPerfil(e) {
	$(e).change(function(){
		uploadArquivo();
	});
}


function salvaPerfil(e) {
	$(e).click(function(){
		console.log('Aqui entra o método para salvar os dados de perfil');
	});
}