(function( $ ){

	$(function() {

		$('.form-horizontal').each(function(){
			var form = $(this), divGroupForm = form.find('.form-group'),
				btn = form.find('.btn');
			
			form.find('.form-control').addClass('empty_field');
			divGroupForm.addClass('empty_in_field');
			
			// Р¤СѓРЅРєС†РёСЏ РїСЂРѕРІРµСЂРєРё РїРѕР»РµР№ С„РѕСЂРјС‹
			function checkInput(){
				form.find('.form-control').each(function(){
					if($(this).val() != ''){
						$(this).removeClass('empty_field');
						divGroupForm.removeClass('empty_in_field');
						//divGroupForm.removeClass('has-error');
						//divGroupForm.addClass('has-success');
					} else {
						$(this).addClass('empty_field');
						divGroupForm.addClass('empty_in_field');
						//divGroupForm.addClass('has-error');
						//divGroupForm.removeClass('has-success');
					}
				});
			}
			
			// Функция подсветки незаполненных полей
			function lightEmpty(){
				if(divGroupForm.hasClass('empty_in_field')){
					divGroupForm.addClass('has-error');
					setTimeout(function(){
						divGroupForm.removeClass('has-error');
					},500);
				}				
			}
			
			setInterval(function(){
				checkInput();
				var sizeEmpty = form.find('.empty_field').size();
				if(sizeEmpty > 0){
					if(btn.hasClass('disabled')){
						return false
					} else {
						btn.addClass('disabled');
					}
					divGroupForm.addClass('has-error');
					divGroupForm.removeClass('has-success');
					btn.removeClass('btn-success');	
					
				} else {
					divGroupForm.removeClass('has-error');
					divGroupForm.addClass('has-success');
					btn.removeClass('disabled');
					btn.addClass('btn-success');
				}
			},500);

		});
		
	});

})( jQuery );