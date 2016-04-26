
//dom ready handler
jQuery(function ($) {
   textAreaAdjust($('#description').get()[0]);
   //form submit handler
   $('#form').on('submit', function (e) {
	  $(".error").hide();
      var company = $('#company').val() && $('#company').val().length > 2;
      var position = $('#position').val() && $('#position').val().length > 2;
      var address = $('#address').val() && $('#address').val().length > 0;
      var description = $('#description').val() && $('#description').val().length > 0;

      if(!company && !position && !address && !description){
         $('#warning').css('color', 'red');
         if(!company){
        	 $("#company-error").show();
         }
         if(!position){
        	 $("#position-error").show();        	 
         }
         if(!address){
        	 $("#address-error").show();        	 
         }
         if(!description){
        	 $("#description-error").show();
         }
         //prevent the default form submit if it is not checked
         e.preventDefault();
      }
   });
   $(window).resize(function(){
      textAreaAdjust($('#description').get()[0]);
   });
});

function textAreaAdjust(o) {
   if(o !== undefined){
      o.style.height = "1px";
      o.style.height = (5+o.scrollHeight)+"px";
   }
}
