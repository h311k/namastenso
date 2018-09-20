/**
 * Scripts do index
 */

$(document).ready(function() {
	
	$('.opt-menu').click(function() {
		$(this).toggleClass('current');
	});
	
	$('#home').click(function() {
		$('#conteudo').hide();
		$.ajax({url:"/namastenso/resources/custom/perfil.xhtml",dataType: "html",success:function(result){
			$('#conteudo').html(result);
			$('#conteudo').fadeIn('slow');
			formataData($('.cal-br'));
			$('.foto-perfil').css('background', 'url("'+$('.foto-perfil').attr('img-src')+'")');
			$('.foto-perfil').css('background-size', 'cover');
		}});
	});
});