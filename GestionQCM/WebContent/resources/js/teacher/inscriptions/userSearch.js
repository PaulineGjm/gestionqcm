function fnGetSelected( oTableLocal ) {
    return oTableLocal.$('tr.row_selected');
}

var searchStagiaire = function() {
	var request = ({
		"lastName" : 'testLastName',
		"firstName" : 'testFirstName',
		"idPromo" : 10
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
			if (jsonObj.stagiaires) {
				var stagiaireDataTable = $('#stagiaireDataTable').DataTable({
					"aaData" : jsonObj.stagiaires,
					"aoColumns": [
			             { "sTitle": "Nom", "mDataProp":"lastName" },
			             { "sTitle": "Prénom", "mDataProp":"firstName" }
			         ],
			         "sDom" : 'rt<"clear">',
			         "oLanguage": {
			             "sLengthMenu": "Afficher _MENU_ lignes par page",
			             "sZeroRecords": "Aucune données",
			             "sInfo": "Affichage _START_ a _END_ de _TOTAL_ lignes",
			             "sInfoEmpty": "Affichage 0 a 0 de 0 lignes",
			             "sInfoFiltered": "(filtré depuis _MAX_ total d'lignes)"
			         }
				});
				
				$('#stagiaireDataTable tr').click( function() {
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

var sendStagiaire = function() {
	var stagiaireDataTable = $('#stagiaireDataTable').DataTable();
	var subscribedInscriptionsTestTable = $("#subscribedInscriptionsTestTable").DataTable()
	var selectedStagiaire = fnGetSelected($('#stagiaireDataTable').DataTable());
	
	for (var i = 0; i < selectedStagiaire.length; i++) {
		stagiaire = stagiaireDataTable.fnGetData(selectedStagiaire[i]);
		
		var stagiaireNotPresent = true;
		var subscribedInscriptionsTestData = subscribedInscriptionsTestTable.fnGetData();
		for (var j = 0; j < subscribedInscriptionsTestData.length; j++) {
			var inputWithId = subscribedInscriptionsTestData[j][0];
			if(stagiaire.id == $(inputWithId).val()) {
				stagiaireNotPresent = false;
				break;
			}
		}
		
		if(stagiaireNotPresent) {
			subscribedInscriptionsTestTable.fnAddData([
	             '<input type="checkbox" name="usersSelected" value="' + stagiaire.id + '">',
	             stagiaire.lastName,
	             stagiaire.firstName
             ]);
		}
	}
};