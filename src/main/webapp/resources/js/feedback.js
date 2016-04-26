
//dom ready handler
jQuery(function ($) {
	textAreaAdjust($('#feedback').get()[0]);
	//form submit handler
	$('#warning').hide();
	$('#form').submit(function (e) {
		//check atleat 1 checkbox is checked
		if (!$('#name').val().length > 0 || !$('#email').val().length > 0) {
			$('#warning').show();
			//prevent the default form submit if it is not checked
			e.preventDefault();
		}
	})
	$(window).resize(function(){
		textAreaAdjust($('#feedback').get()[0]);
	});
});

function textAreaAdjust(o) {
	if(o !== undefined){
		o.style.height = "1px";
		o.style.height = (5+o.scrollHeight)+"px";
	}
}
