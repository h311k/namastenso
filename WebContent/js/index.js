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
			instanciaPerfil($('.box-calendario input'),$('.foto-perfil'),$('.input-foto-perfil'),$('#btn-salvar-perfil'),$('#primeiro-nome'));
			$('#conteudo').fadeIn('slow');
		}});
	});
});