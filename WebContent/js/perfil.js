/**
 * Scripts de perfil
 */

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
		console.log('Chama o método para salvar a foto');
	});
}


function salvaPerfil(e) {
	$(e).click(function(){
		console.log('Aqui entra o método para salvar os dados de perfil');
	});
}