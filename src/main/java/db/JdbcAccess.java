package db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcAccess {
    private String url;
    private String database;

    public JdbcAccess(String database) {
        this.database = database;
    }

    public void execute(String sql) throws SQLException {
        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } finally {
            close(connection);
        }
    }

    private void close(Connection connection) throws SQLException {
        if (connection != null)
            connection.close();
    }

    Connection getConnection() throws SQLException {
        if (url == null) {
            loadDriver();
            url = "jdbc:mysql://localhost/" + database;
        }
        return DriverManager.getConnection(url);
    }

    private void loadDriver() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception cause) {
            throw new SQLException(cause.getMessage());
        }
    }

    public String getFirstRowFirstColumn(String query) throws SQLException {
        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery(query);
            results.next();
            return results.getString(1);
        } finally {
            close(connection);
        }
    }

    public PreparedStatement prepare(String sql) throws SQLException {
        Connection connection = getConnection();
        return connection.prepareStatement(sql);
    }

    public List<String> getUnique(PreparedStatement statement, String... values) throws SQLException {
        int i = 1;
        for (String value : values)
            statement.setString(i++, value);
        ResultSet results = statement.executeQuery();
        results.next();

        List<String> row = new ArrayList<>();
        ResultSetMetaData metadata = results.getMetaData();
        for (int column = 1; column <= metadata.getColumnCount(); column++)
            row.add(results.getString(column));
        return row;
    }
}
