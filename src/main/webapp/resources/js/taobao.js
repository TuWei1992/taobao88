/**
 * 
 */
function translate() {
			var googleWindow = window.open('','_blank');
        	var q = $('.input_search').val();
        	var googleHref = 'http://translate.google.ru/translate?hl=ru&sl=zh-CN&tl=ru&u=';
        	var taobaoSearch = 'http://s.taobao.com/search?';
        	var kingHref = 'tab=coefp&bcoffset=1&sort=credit-desc&';
        	var google = false;
        	var king = false;
        	var inputs = $('input[type=checkbox]:checked');
        	$(inputs).each(function(i, item) {
        		if ($(item).val() == 'google') {
        			google = true;
        		} else if ($(item).val() == 'king') {
        			king = true;
        		}
        	});
        	$.ajax({type: 'POST', 
    			dataType: 'json', 
    			url: 'https://translate.yandex.net/api/v1.5/tr.json/translate?key=trnsl.1.1.20140821T165843Z.21b5f7cb8dfb120f.12925a65fe70d80c36c509c38ac4282a8fc6805f&text=' + q + '&lang=ru-zh', 
    			success: function(jsondata){
    						if (google) {
    							if (king) {
    								googleWindow.location.replace(googleHref + taobaoSearch + kingHref + 'q=' + jsondata.text);
    							} else {
    								googleWindow.location.replace(googleHref + taobaoSearch + 'q=' + jsondata.text);
    							}
    						} else {
    							if (king) {
    								googleWindow.location.replace(taobaoSearch + kingHref + 'q=' + jsondata.text);
    							} else {
    								googleWindow.location.replace(taobaoSearch + 'q=' + jsondata.text);
    							}
    						}
						}
    		});
        }

function addToBasket(currUrl, recId) {
	$.ajax({
		type: 'POST',
		url: currUrl + '/addToBasket',
		data: 'id=' + recId,
		dataType: 'json',
		complete: function(jsonData) {
			if (jsonData.responseText == 'already_in_basket') {
				$('#alertModal').modal('show');
			} else {
				$('#basket').text(jsonData.responseText);
				$('#addedToBasketModal').modal('show');
			}
		}
	});
}

function removeFromBasket(currUrl, recId) {
	$.ajax({
		type: 'POST',
		url: currUrl + '/removeFromBasket',
		data: 'id=' + recId,
		dataType: 'json',
		complete: function(jsonData) {
			window.location.reload();
		}
	});
}