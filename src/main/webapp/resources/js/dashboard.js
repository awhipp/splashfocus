var down = true;

$(document).ready(function() {
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
