package Services;
import java.sql.*;
import java.sql.Date;
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
        try (CallableStatement callableStatement = connection.prepareCall(query)) {
            Enumeration<String> keys = parameters.keys();
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
                            row.AddField(columnName,columnValue);
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

    private static <T> T getField(Map<String, Object> row, String columnName, Class<T> type) {
        Object value = row.get(columnName);
        if (value != null && type.isInstance(value)) {
            return type.cast(value);
        }
        return null;
    }

}
