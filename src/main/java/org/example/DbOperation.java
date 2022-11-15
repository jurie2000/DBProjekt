package org.example;

import java.sql.*;
import java.util.*;

public class DbOperation {
    DbConnection dbConnection;

    DbOperation(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
        dbConnection.connectToDb();
    }

    /**
     * @param table - name of the table
     * @return a list off all tuples as Map - Key = ColumnName, Value = content
     * @throws SQLException
     */
    public List<Map<String, Object>> getTuple(String table) throws SQLException {
        List<Map<String, Object>> list = new LinkedList<>();
        PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement("SELECT * FROM " + table);
        try (ResultSet set = preparedStatement.executeQuery()) {
            while (set.next()) {
                Map<String, Object> currentElementList = new HashMap<>();
                for (int i = 0; i < set.getMetaData().getColumnCount(); i++) {
                    currentElementList.put(set.getMetaData().getColumnName(i + 1), set.getObject(i + 1));
                }
                list.add(currentElementList);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<String> getAllAvailableColumns(String table) throws SQLException {
        List<String> list = new LinkedList<>();
        PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement("SELECT * FROM " + table);
        try (ResultSet set = preparedStatement.executeQuery()) {
            for (int i = 0; i < set.getMetaData().getColumnCount(); i++) {
                list.add(set.getMetaData().getColumnName(i + 1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * @return a list of all tables by the user
     */

    public List<String> getAllAvailableTables() {
        List<String> list = new LinkedList<>();
        try {
            ResultSet set = dbConnection.getConnection().prepareStatement("SELECT table_name FROM user_tables").executeQuery();
            while (set.next()) {
                list.add(set.getString(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public List<String> getAllAvailableDataTypes(String table) throws SQLException {
        List<String> list = new LinkedList<>();
        PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement("SELECT * FROM " + table);
        try (ResultSet set = preparedStatement.executeQuery()) {
            for (int i = 0; i < set.getMetaData().getColumnCount(); i++) {
                list.add(String.valueOf(set.getMetaData().getColumnTypeName(i + 1)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }


}
