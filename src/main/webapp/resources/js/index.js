var selected = 0; // 0 is jobs, 1 is people

var execute = function(e){
	$(this).removeClass("invalid"); 
	if(e.keyCode === 13 || e.type === "click"){
		var input = $('#input-address').val();
		if(input.replace(/ /g,"").length == 0){
			$('#input-address').addClass("invalid");
			$('#input-address').attr("placeholder", "Location required");
		}else{
			if(selected == 0){
				$('#jobs-address').val(input);
				$('#jobs-form').submit();
			}else{
				$('#people-address').val(input);
				$('#people-form').submit();
			}
		}
	}	
}

var swap = function(){
	if(selected === 0){
		selected = 1;
		$("#subheading").fadeOut(function() {
			$(this).html("Find <font color='white'>work</font> instead").fadeIn();
		});
		$("#tagline").fadeOut(function() {
			$(this).html("Find <font color='white'>PEOPLE</font> near you").fadeIn();
		});
	}else {
		selected = 0;
		$("#subheading").fadeOut(function() {
			$(this).html("Find <font color='white'>people</font> instead").fadeIn();
		});
		$("#tagline").fadeOut(function() {
			$(this).html("Find <font color='white'>WORK</font> near you").fadeIn();
		});
	}
}

$(document).ready(function () {

	if($("#inner-message").text() === "Location you entered provided no user listings. Try again."){

		selected = 1;
		$("#subheading").html("Find <font color='white'>work</font> instead");
		$("#tagline").html("Find <font color='white'>PEOPLE</font> near you");
	}


	window.setTimeout(function(){
		$("#message").hide();
	}, 5000);

	var isShown = false;
	var isHighlighted = false;
	$('#input-address').on('keydown', execute);

	$(".search").on('click', execute);
});
