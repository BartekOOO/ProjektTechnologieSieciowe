package Services.SQLService;
import java.sql.*;
import java.util.*;

import Interfaces.IConfig;

public class SQLService {
    private Connection connection = null;

    public SQLService(IConfig config){
        try {
            connection = DriverManager.getConnection(config.GetUrl(), config.GetUser(), config.GetPassword());
        }
        catch (SQLException ex){
            System.out.println(ex.getErrorCode());
        }
    }

    public DataTable ExecuteStoredProcedureWithResult(String query, Dictionary<String, Object> parameters) {
        DataTable resultTable = new DataTable();

        StringBuilder queryWithPlaceholders = new StringBuilder("{CALL " + query + "(");
        for (int i = 0; i < parameters.size(); i++) {
            if (i > 0) queryWithPlaceholders.append(", ");
            queryWithPlaceholders.append("?");
        }
        queryWithPlaceholders.append(")}");

        try (CallableStatement callableStatement = connection.prepareCall(queryWithPlaceholders.toString())) {
            Enumeration<String> keys = parameters.keys();
            while (keys.hasMoreElements()) {
                String key = keys.nextElement();
                Object value = parameters.get(key);


                if (value instanceof String) {
                    callableStatement.setString(key, (String) value);
                } else if (value instanceof Integer) {
                    callableStatement.setInt(key, (Integer) value);
                } else if (value instanceof Boolean) {
                    callableStatement.setBoolean(key, (Boolean) value);
                } else {
                    callableStatement.setObject(key, value);
                }
            }

            boolean hasResultSet = callableStatement.execute();
            if (hasResultSet) {
                try (ResultSet resultSet = callableStatement.getResultSet()) {
                    ResultSetMetaData metaData = resultSet.getMetaData();
                    int columnCount = metaData.getColumnCount();

                    while (resultSet.next()) {
                        DataRow row = new DataRow();
                        for (int i = 1; i <= columnCount; i++) {
                            String columnName = metaData.getColumnName(i);
                            Object columnValue = resultSet.getObject(i);
                            row.AddField(columnName, columnValue);
                        }
                        resultTable.AddDataRow(row);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultTable;
    }


}
