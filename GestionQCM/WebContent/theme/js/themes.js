$(document).ready(function(){
	$.fn.editable.defaults.mode = 'inline';
	
	$('#addtheme').slideUp();
	
	$('.edit_theme').editable();
	
	$('#display_addtheme').click(function(){
		$('#addtheme').slideToggle();
	});
})