var invalid=[];

var testZip = function(zip){
	return (/(^\d{5}$)/.test(zip));
}

var testString = function(string, amount){
	var matches = string.match(/\d+/g);
	if (matches != null) {
		return false;
	}
	return string.replace(/ /g,'').length >= 2 && string.replace(/ /g,'').length <= amount;
}

var testPassword = function(p1, p2){
	if(p1 !== undefined && p2 !== undefined){
		if(!(p1.length >= 7 && p1.length <= 32)){
			return 1;
		}

		if(p1 !== p2){
			return 2;
		}

		return 0;
	}
	return 1;
}

var testInterest = function(interest){
	return (parseInt(interest) >= 0 && parseInt(interest) <= 16);
}

function emailExists(email) {
	var result="";
	$.ajax({
		url : '/emailExists/'+email,
		type : "GET",
		async: false,
		success : function(data) {
			var taken = data.split(",")[0];
			var valid = data.split(",")[1];
			if(taken == "false"){
				$("#email").addClass("invalid"); 
				$("#email-taken-error").show();
			}
			if(valid == "false"){
				$("#email").addClass("invalid"); 
				$("#email-error").show();
			}
		}
	});
}

$(document).ready(function () {

	$("#firstname").on("blur", function(){
		$("#server-error").hide();
		if( !testString($(this).val(), 30)){ 
			$(this).addClass("invalid"); 
			$("#"+$(this).attr('id')+"-error").show();
		}else{
			$(this).removeClass("invalid"); 
			$("#"+$(this).attr('id')+"-error").hide();
		} 
	});

	$("#lastname").on("blur", function(){
		$("#server-error").hide();
		if( !testString($(this).val(), 30)){ 
			$(this).addClass("invalid"); 
			$("#"+$(this).attr('id')+"-error").show();
		}else{
			$(this).removeClass("invalid"); 
			$("#"+$(this).attr('id')+"-error").hide();
		} 
	});

	$(".password").on("blur", function(){
		$("#server-error").hide();
		var result = testPassword($("#password").val(), $("#password_confirmation").val());
		if(  result == 1 ){ 
			$("#password").addClass("invalid");
			$("#password-error").show();
			$("#password_confirmation-error").hide();
		}else if (result == 2){
			$("#password_confirmation").addClass("invalid"); 
			$("#password_confirmation-error").show();
			$("#password-error").hide();
		}else {
			$("#password_confirmation").removeClass("invalid");
			$("#password").removeClass("invalid"); 
			$("#password-error").hide();
			$("#password_confirmation-error").hide();
		}
	});

	$("#email").on("blur", function(){
		$("#server-error").hide();
		$(this).removeClass("invalid"); 
		$("#"+$(this).attr('id')+"-error").hide();
		$("#email-taken-error").hide();
		
		emailExists($(this).val());
	});

	$("#interest").on("blur", function(){
		$("#server-error").hide();
		if( !testInterest($(this).val()) ){ 
			$(this).addClass("invalid"); 
			$("#"+$(this).attr('id')+"-error").show();
		}else{
			$(this).removeClass("invalid"); 
			$("#"+$(this).attr('id')+"-error").hide();
		} 
	});

	$("#zipcode").on("blur", function(){
		$("#server-error").hide();
		if( !testZip($(this).val()) ){ 
			$(this).addClass("invalid"); 
			$("#"+$(this).attr('id')+"-error").show();
		}else{
			$(this).removeClass("invalid"); 
			$("#"+$(this).attr('id')+"-error").hide();
		} 
	});

	$("#city").on("blur", function(){
		$("#server-error").hide();
		if( !testString($(this).val(), 90)){ 
			$(this).addClass("invalid"); 
			$("#"+$(this).attr('id')+"-error").show();
		}else{
			$(this).removeClass("invalid"); 
			$("#"+$(this).attr('id')+"-error").hide();
		} 
	});

	$("#state").on("blur", function(){
		$("#server-error").hide();
		if( !testString($(this).val(), 20)){ 
			$(this).addClass("invalid"); 
			$("#"+$(this).attr('id')+"-error").show();
		}else{
			$(this).removeClass("invalid"); 
			$("#"+$(this).attr('id')+"-error").hide();
		} 
	});


	$('#form').on('submit',function(e){
		invalid = [];
		$(".invalid").removeClass("invalid");
		$(".error").hide();

		if( !testString($("#firstname").val(), 30)){ invalid.push("firstname"); } 
		if( !testString($("#lastname").val(), 30)){ invalid.push("lastname"); }

		var result = 0;
		if( (result = testPassword($("#password").val(), $("#password_confirmation").val())) !== 0 ){
			invalid.push("password");
			if(result === 2){
				invalid.push("password_confirmation");
			}
		}

		emailExists($("#email").val());
		if( !testInterest($("#interest").val()) ){ invalid.push("interest"); }
		if( !testString($("#city").val(), 90)){ invalid.push("city"); }
		if( !testString($("#state").val(), 20)){ invalid.push("state"); }
		if( !testZip($("#zipcode").val()) ){ invalid.push("zipcode"); }

		if(invalid.length > 0){
			$.each(invalid, function(){
				$('#'+this).addClass("invalid");
				$("#notice").addClass("invalid");
				$("#"+this+"-error").css("display","block");
			});
			if(e.preventDefault){ 
				e.preventDefault(); 
			}else{
				e.returnValue = false; 
			}
		}

	});
});