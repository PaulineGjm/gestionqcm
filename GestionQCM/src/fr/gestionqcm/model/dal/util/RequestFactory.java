package fr.gestionqcm.model.dal.util;

public class RequestFactory {

	private String tableName;

	private static final String RQ_SELECT_ALL = "SELECT * FROM %s;";
	private static final String RQ_SELECT_ONE = "SELECT * FROM %s WHERE %s = ?;";
	private static final String RQ_INSERT = "INSERT INTO %s (%s) VALUES(?, ?, ?, ?);";
	private static final String RQ_UPDATE = "UPDATE %s SET %s WHERE %s = ?;";
	private static final String RQ_DELETE = "DELETE FROM %s WHERE %s = ?;";

	public RequestFactory(String tableName) {
		this.tableName = tableName;
	}

	public String getSelectAll() {
		return String.format(RQ_SELECT_ALL, tableName);
	}

	public String getSelectOne(String idColumName) {
		return String.format(RQ_SELECT_ONE, tableName, idColumName);
	}

	public String getInsert(String... columnsName) {
		StringBuilder request = new StringBuilder();

		for (int i = 0; i < columnsName.length; i++) {
			String column = columnsName[i];
			request.append(column);
			if (i < (columnsName.length - 1)) {
				request.append(",");
			}
		}

		return String.format(RQ_INSERT, tableName, request.toString());
	}

	public String getUpdate(String idColumName, String... columnsName) {
		StringBuilder request = new StringBuilder();

		for (int i = 0; i < columnsName.length; i++) {
			String column = columnsName[i];
			request.append(column + " = ?");
			if (i < (columnsName.length - 1)) {
				request.append(",");
			}
		}

		return String.format(RQ_UPDATE, tableName, request.toString(),
				idColumName);
	}

	public String getDelete(String idColumName) {
		return String.format(RQ_DELETE, tableName, idColumName);
	}
}
