/**
 * Scripts de perfil
 */

function instanciaPerfil(calendario,fotoPerfil,inputFotoPerfil,btnSalvaPerfil) {
	formataCalendario(calendario);
	setFotoPerfil(fotoPerfil);
	carregaFotoPerfil(fotoPerfil);
	salvaFotoPerfil(inputFotoPerfil);
	salvaPerfil(btnSalvaPerfil);
	//atualizaFotoPerfil(fotoPerfil);
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

function salvaPerfil(e) {
	$(e).click(function(){
		console.log('Aqui entra o método para salvar os dados de perfil');
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