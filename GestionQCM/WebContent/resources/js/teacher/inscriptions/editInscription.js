function deleteStagiaires() {
	var subscribedInscriptionsTestTable = $("#subscribedInscriptionsTestTable").DataTable();
	
	$("#editInscriptionForm input[name='usersSelected']").each(function(index, field) {
		if(field.checked) {
			subscribedInscriptionsTestTable.fnDeleteRow($(field).closest("tr")[0]);
		}
	});
}