/**
 * Scripts do index
 */

$(document).ready(function() {
	$('.opt-menu').click(function() {
		$('.opt-menu').removeClass('current');
		$(this).addClass('current');
	});
	
	$('#home').click(function() {
		$('#conteudo').hide();
		$.ajax({url:"/namastenso/resources/custom/perfil.xhtml",dataType: "html",success:function(result){
			$('#conteudo').html(result);
			$('#conteudo').fadeIn('slow');
			formataData($('.cal-br'));
		}});
	});
});