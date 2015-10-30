function fnGetSelected( oTableLocal ) {
    return oTableLocal.$('tr.row_selected');
}

var searchStagiaire = function() {
	reinitSelectAllButton();
	$.ajax({
		data : {
			"lastName" : $("#lastName").val(),
			"firstName" : $("#firstName").val(),
			"idPromo" : $("#idPromo").val()
		},
		dataType : 'json',
		url : './searchstagiaire',
		type : 'POST',
		success : function(jsonObj) {
			if (jsonObj.stagiaires) {
				if($.fn.DataTable.fnIsDataTable( $('#stagiaireDataTable')[0])) {
					var stagiaireDataTable = $('#stagiaireDataTable').DataTable();
					stagiaireDataTable.fnClearTable();
					stagiaireDataTable.fnAddData(jsonObj.stagiaires);
				} else {
					var stagiaireDataTable = $('#stagiaireDataTable').DataTable({
						"aaData" : jsonObj.stagiaires,
						"aoColumns": [
				             { "sTitle": "Nom", "mDataProp":"lastName" },
				             { "sTitle": "Prénom", "mDataProp":"firstName" }
				         ],
				         "sDom" : 'lrtip<"clear">',
				         "oLanguage": {
				             "sLengthMenu": "Afficher _MENU_ lignes par page",
				             "sZeroRecords": "Aucune données",
				             "sInfo": "Affichage _START_ a _END_ de _TOTAL_ lignes",
				             "sInfoEmpty": "Affichage 0 a 0 de 0 lignes",
				             "sInfoFiltered": "(filtré depuis _MAX_ total d'lignes)"
				         }
					});
				}
				$('#stagiaireDataTable tr').click( function() {
					reinitSelectAllButton();
					$(this).toggleClass('row_selected');
				} );
			}
		},
		error : function(xhr, err) {
			console.log('Ajax readyState: ' + xhr.readyState
					+ '\nstatus: ' + xhr.status + ' ' + err);
		}
	});
};

function selectAll(element) {
	var jQueryElement = $(element);
	if(jQueryElement.hasClass("glyphicon-unchecked")) {
		jQueryElement.removeClass("glyphicon-unchecked");
		jQueryElement.addClass("glyphicon-check");
		$('#stagiaireDataTable tr').addClass('row_selected');
	} else {
		jQueryElement.removeClass("glyphicon-check");
		jQueryElement.addClass("glyphicon-unchecked");
		$('#stagiaireDataTable tr').removeClass('row_selected');
	}
}

function sendStagiaire() {
	var stagiaireDataTable = $('#stagiaireDataTable').DataTable();
	var subscribedInscriptionsTestTable = $("#subscribedInscriptionsTestTable").DataTable();
	var selectedStagiaire = fnGetSelected($('#stagiaireDataTable').DataTable());
	
	for (var i = 0; i < selectedStagiaire.length; i++) {
		stagiaire = stagiaireDataTable.fnGetData(selectedStagiaire[i]);
		
		var stagiaireNotPresent = true;
		var subscribedInscriptionsTestData = subscribedInscriptionsTestTable.fnGetData();
		for (var j = 0; j < subscribedInscriptionsTestData.length; j++) {
			var inputWithId = subscribedInscriptionsTestData[j][0];
			if(stagiaire.id == $(inputWithId).closest("input[name='usersSelected']").val()) {
				stagiaireNotPresent = false;
				break;
			}
		}
		
		if(stagiaireNotPresent) {
			subscribedInscriptionsTestTable.fnAddData([
			    '<input type="hidden" name="users" value="' + stagiaire.id + '">' +
				'<input type="checkbox" value="' + stagiaire.id + '" name="usersSelected"/>',
	             stagiaire.lastName,
	             stagiaire.firstName
             ]);
		}
	}
	reinitSelectAllButton();
};

function reinitSelectAllButton() {
	var selectAllButton = $("#selectAllButton");
	if(selectAllButton.hasClass("glyphicon-check")) {
		selectAllButton.removeClass("glyphicon-check");
		selectAllButton.addClass("glyphicon-unchecked");
	} 
}