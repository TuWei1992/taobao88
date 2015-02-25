$(function() {
   $('#basket').text('${basket.size()}');
});

function addToBasket(recId) {
	$.ajax({
		type: "POST",
		url: "${pageContext.request.contextPath}/addToBasket",
		data: "id=" + recId,
		dataType: "json",
		complete: function(jsonData) {
			if (jsonData.responseText == 'already_in_basket') {
				$('#alertModal').modal('show');
			} else {
				$('#basket').text(jsonData.responseText);
			}
		}
	});
}

function removeFromBasket(recId) {
	$.ajax({
		type: "POST",
		url: "${pageContext.request.contextPath}/removeFromBasket",
		data: "id=" + recId,
		dataType: "json",
		complete: function(jsonData) {
			window.location.reload();
		}
	});
}

function translate() {
	var q = $('.input_search').val();
	$.ajax({type: 'POST', 
		dataType: 'json', 
		url: 'https://translate.yandex.net/api/v1.5/tr.json/translate?key=trnsl.1.1.20140821T165843Z.21b5f7cb8dfb120f.12925a65fe70d80c36c509c38ac4282a8fc6805f&text=' + q + '&lang=ru-zh', 
		success: function(jsondata){
					window.open('http://translate.google.ru/translate?hl=ru&sl=zh-CN&tl=ru&u=http://s.taobao.com/search?q=' + jsondata.text, '_blank');
				}
	});
}