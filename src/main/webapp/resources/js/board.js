var isJob;
var $container;
var isLoading = false;
var endOfList = false;
var industry;
var min;
var max;
var advShown = false;

var overhead = function(){
	$(this).css("opacity", "0.7");
}

var resetOpacity = function(){
	$(this).css("opacity", "1.0");
}

var switchToSeekers = function(){
	$('#seekers_form').submit();
}

var switchToJobs = function (){
	$('#jobs_form').submit();
}

$(document).ready(function() {

	$('#home').on('click', function(){
		window.location.href = "/";
	});	
	
	$("#input-address").keyup(function (e) {
	    if (e.keyCode == 13) {
	        $(".search").click();
	    }
	});

	$( ".industry_dropdown" ).change(function() {
		var e = $(this)[0];
		$('#seekers_form_value').val(e.options[e.selectedIndex].value);
		$('#jobs_form_value').val(e.options[e.selectedIndex].value);
	});

	$('#searchSeekers').on('click', function(){
		$('#resubmitSeekers').submit();
	});

	$('#searchJobs').on('click', function(){
		$('#resubmitJobs').submit();
	});

	$(".item").on("mouseover", overhead);
	$(".item").on("mouseout", resetOpacity);

	$('.adv-search').on('click', function(){
		if(advShown){
			$('.dropdown-menu').stop(true, true).slideUp();
		}else{
			$('.dropdown-menu').stop(true, true).slideDown();
		}
		advShown = !advShown;
	});

	$.each($('.interest'), function(){
		var val = $(this).text();
		var idx = val.indexOf("-");
		if(idx === -1){
			$(this).text(interestConversion(parseInt(val)));
		}else{
			$(this).text(interestConversion(parseInt(val.substr(0,idx))) + " " + val.substr(idx));
		}
	});

});