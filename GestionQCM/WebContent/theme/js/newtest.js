var nbSection = 1;

$(document).ready(function(){
	
	$("#newsection").click(displayNewSection);
	
	$(".deletesection").click(function(){
		deleteSection($(this));
	});
})

function displayNewSection()
{
	nbSection = $('.section').size();
	var div = $(".section").first().clone();
	div.find("select").attr("name", "theme_" + nbSection);
	div.find("input.nbquestion").attr("name", "nbquestion_" + nbSection);
	div.show();
	$("#sections").append(div);
	
	$(".deletesection").click(function(){
		deleteSection($(this));
	});
}

function deleteSection(button)
{
	console.log(button);
	button.parent().remove();
}