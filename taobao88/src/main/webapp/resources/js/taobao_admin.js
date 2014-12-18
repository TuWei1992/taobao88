$(function() {
	
	$('.delete_image').click(function() {
			var delete_btn = $(this);
			var imageId = $(delete_btn).attr('for');
			imageId = imageId.replace('img_', '');
			$.ajax({
				type: 'POST',
				url: document.URL + '/deleteImage',
				data: 'imageId=' + imageId,
				complete: function(jsonData) {
					var response = JSON.parse(jsonData.responseText);
					if (response.success) {
						$(delete_btn).remove();
						$('#img_' + imageId).remove();
						var a = $('.main_image');
						$(a).each(function(i, item) {
							var forImg = $(item).attr('for');
							if (forImg != null) {
								if (forImg.startsWith('img_' + imageId)) {
									$(item).remove();
								}
							}
						});
					}
				}
			});
		});
		
		$('.main_image').click(function() {
			var main_btn = $(this);
			var imgRec = $(main_btn).attr('for').split('&');
			var imageId = imgRec[0].replace('img_', '');
			var recId = imgRec[1].replace('rec_', '');
			$.ajax({
				type: 'POST',
				url: document.URL + '/makeImageAsMain',
				data: 'imageId=' + imageId + '&recId=' + recId,
				complete: function(jsonData) {
					var response = JSON.parse(jsonData.responseText);
					if (response.success) {
						$('.image_changed').show();
					}
				}
			});
		});
	});