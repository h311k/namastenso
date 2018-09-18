$(document).ready(function(){


  $(".submenu > a").click(function(e) {
    e.preventDefault();
    var $li = $(this).parent("li");
    var $ul = $(this).next("ul");

    if($li.hasClass("open")) {
      $ul.slideUp(350);
      $li.removeClass("open");
    } else {
      $(".nav > li > ul").slideUp(350);
      $(".nav > li").removeClass("open");
      $ul.slideDown(350);
      $li.addClass("open");
    }
  });
  
});

/**
 * Configuracoes globais de calendario
 */

function formataData(e){
	$(e).datepicker({
    	format: 'dd/mm/yyyy',
    	language: 'pt-BR',
    	todayHighlight: true
    });
}