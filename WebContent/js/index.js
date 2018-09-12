/**
 * Scripts do index
 */

$(document).ready(function() {
	$('.opt-menu').click(function() {
		$('.opt-menu').removeClass('current');
		$(this).addClass('current');
	});
	
	$('#home').click(function() {
		$.ajax({url:"/namastenso/resources/custom/perfil.xhtml",dataType: "html",success:function(result){
			$('#conteudo').html(result);
		}});
	});
});