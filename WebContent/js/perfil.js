/**
 * Scripts de perfil
 */

function instanciaPerfil(calendario,fotoPerfil,inputFotoPerfil,btnSalvaPerfil) {
	formataCalendario(calendario);
	setFotoPerfil(fotoPerfil);
	carregaFotoPerfil(fotoPerfil);
	salvaFotoPerfil(inputFotoPerfil);
	salvaPerfil(btnSalvaPerfil);
}

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
		$('.executa-upload').click();
	});
}

function salvaPerfil(btnSalvaPerfil) {
	$(btnSalvaPerfil).click(function(){
		$('#loader-frame').fadeIn(500);
		var primeiroNome = $('#primeiro-nome').val();
		var apelido = $('#apelido').val();
		var sobrenome = $('#sobrenome').val();
		var dataNascimento = $('.box-calendario > input').val();
		var biografia = $('#biografia').val();
		atualizaPerfil([{name: 'primeiroNome', value: primeiroNome},{name: 'apelido', value: apelido},{name: 'sobrenome', value: sobrenome},{name: 'dataNascimento', value: dataNascimento},{name: 'biografia', value: biografia}]);
		$('#loader-frame').fadeOut(500);
	});
}

function getAtualizaFotoCallback(xhr, status, args){
	$('.foto-perfil').attr('img-src',args.fotoPerfil);
	setFotoPerfil($('.foto-perfil'));
}

function getAtualizaPerfilCallback(xhr, status, args){
	$('#form-perfil').prepend('<div id="email-salvo" class="alert alert-success">O seu perfil foi atualizado.</div>');
}