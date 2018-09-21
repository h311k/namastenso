/**
 * Scripts do index
 */

$(document).ready(function() {
	
	$('.opt-menu').click(function() {
		$(this).toggleClass('current');
	});
	
	//Meu Perfil
	$('#home').click(function() {
		$('#conteudo').hide();
		$.ajax({url:"/namastenso/resources/custom/perfil.xhtml",dataType: "html",success:function(result){
			$('#conteudo').html(result);
			$('#conteudo').fadeIn('slow');
			formataCalendario($('.box-calendario input'));
			setFotoPerfil($('.foto-perfil'));
			carregaFotoPerfil($('.foto-perfil'));
			salvaFotoPerfil($('.input-foto-perfil'));
			salvaPerfil($('#btn-salvar-perfil'));
		}});
	});
});