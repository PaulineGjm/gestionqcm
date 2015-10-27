var searchStagiaire = function() {
	var request = ({
		"message" : 'Hello from browser'
	});
	var jsonobj = JSON.stringify(request);
	$.ajax({
		data : {
			para : jsonobj
		},
		dataType : 'json',
		url : './searchstagiaire',
		type : 'POST',
		success : function(jsonObj) {
			alert(jsonObj);
		},
		error : function(xhr,err) {
			alert('Ajax readyState: ' + xhr.readyState + '\nstatus: '
					+ xhr.status + ' ' + err);
		}
	});
}
