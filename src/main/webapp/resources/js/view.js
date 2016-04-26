var down = true;

$(document).ready(function() {

	$.each($('.experience-time'), function(){
		var range = $(this).text();
		range = range.split("-to-");
		var start = convertDate(range[0]);
		var end = convertDate(range[1]);
		$(this).text(start + " - " + end);
	});

	var val = $("#industry").text();
	$("#industry").text(interestConversion(parseInt(val)));

	$(".object").on("mouseover", function () {
		$(this).css("opacity", "0.7");
	});

	$(".object").on("mouseout", function () {
		$(this).css("opacity", "1.0");
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

var convertDate = function(date){
	if(date === undefined || date.length === 0)
		return "Present";
	date = date.split("-");
	reformat = "";

	switch(date[1]) {
	case "01":
		reformat  += "Jan ";
		break;
	case "02":
		reformat  += "Feb ";
		break;
	case "03":
		reformat  += "Mar ";
		break;
	case "04":
		reformat  += "Apr ";
		break;
	case "05":
		reformat  += "May ";
		break;
	case "06":
		reformat  += "Jun ";
		break;
	case "07":
		reformat  += "Jul ";
		break;
	case "08":
		reformat  += "Aug ";
		break;
	case "09":
		reformat  += "Sept ";
		break;
	case "10":
		reformat  += "Oct ";
		break;
	case "11":
		reformat  += "Nov ";
		break;
	case "12":
		reformat  += "Dec ";
		break;
	}

	reformat += date[2] + ", ";
	reformat += date[0];

	return reformat;
}
