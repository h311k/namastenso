/**
 * Scripts do index
 */

$(document).ready(function() {
	
	$('.opt-menu').click(function() {
		$('.opt-menu').removeClass('current');
		$(this).addClass('current');
	});
	
	//Meu Perfil
	$('#home').click(function() {
		$.ajax({url:"/namastenso/resources/custom/perfil.xhtml",dataType: "html",success:function(result){
			$('#conteudo').html(result);
			instanciaPerfil($('.box-calendario input'),$('.foto-perfil'),$('.input-foto-perfil'),$('#btn-salvar-perfil'),$('#primeiro-nome'));
			$('#conteudo').fadeIn('slow');
		}});
	});
	
	//Configurações
	$('#configuracoes').click(function(){
		$.ajax({url:"/namastenso/resources/custom/email.xhtml",dataType: "html",success:function(result){
			$('#conteudo').html(result);
			$('#conteudo').fadeIn('slow');
		}});
	});
});