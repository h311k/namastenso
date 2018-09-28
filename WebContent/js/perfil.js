/**
 * Scripts de perfil
 */

function instanciaPerfil(calendario,fotoPerfil,inputFotoPerfil,btnSalvaPerfil,primeiroNome,apelido,sobrenome,dataNascimento,biografia) {
	formataCalendario(calendario);
	setFotoPerfil(fotoPerfil);
	carregaFotoPerfil(fotoPerfil);
	salvaFotoPerfil(inputFotoPerfil);
	salvaPerfil(btnSalvaPerfil, primeiroNome, apelido, sobrenome, dataNascimento, biografia);
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

function salvaPerfil(btnSalvaPerfil, primeiroNome, apelido, sobrenome, dataNascimento, biografia) {
	$(btnSalvaPerfil).click(function(){
		console.log('Aqui entra o método para salvar os dados de perfil');
		$('#loader-frame').fadeIn(500);
		var primeiroNome = $(primeiroNome).val();
		var apelido = $(apelido).val();
		var sobrenome = $(sobrenome).val();
		var dataNascimento = $(dataNascimento).val();
		var biografia = $(biografia).val();
		atualizaPerfil([{name: 'primeiroNome', value: primeiroNome},{name: 'apelido', value: apelido},{name: 'sobrenome', value: sobrenome},{name: 'dataNascimento', value: dataNascimento},{name: 'biografia', value: biografia}]);
		$('#loader-frame').fadeOut(500);
	});
}

function atualizaFoto(data) {
	if(data.status=='success'){
		if(data.responseText.indexOf('<error>') >=0){
			//Tratamento de erro no upload
			console.log('erro na importação');
			console.log(data);
		} else {
			console.log('sucesso');
			console.log(data);
			atualizaFoto();
		}
	} 
}
function getAtualizaFotoCallback(xhr, status, args){
	$('.foto-perfil').attr('img-src',args.fotoPerfil);
	setFotoPerfil($('.foto-perfil'));
}