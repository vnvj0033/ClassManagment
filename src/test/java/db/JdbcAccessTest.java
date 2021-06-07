package db;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class JdbcAccessTest {
    JdbcAccess access;

    @BeforeEach
    public void setUp() {
        access = new JdbcAccess("test");
    }

    @Test
    public void testConnection() throws SQLException {
        Connection connection = null;
        try {
            connection = access.getConnection();
            assertFalse(connection.isClosed());
        } finally {
            connection.close();
        }
    }

    @Test
    public void testExecute() throws SQLException {
        access.execute("create table testExecute (fieldA char)");
        try {
            assertEquals("fieldA", access.getFirstRowFirstColumn("desc testExecute"));
        } finally {
            access.execute("drop table testExecute");
        }
    }

    @Test
    public void testQueryBy() throws SQLException {
        drop("testQueryBy");
        access.execute("create table testQueryBy (id varchar(10), name varchar(30))");
        PreparedStatement statement = null;

        try {
            access.execute("insert into testQueryBy values('123', 'schmoe')");
            access.execute("insert into testQueryBy values('234', 'patella')");

            statement = access.prepare("select id, name from testQueryBy where id = ?");

            List<String> row = access.getUnique(statement, "234");
            assertEquals("234", row.get(0));
            assertEquals("patella", row.get(1));

            row = access.getUnique(statement, "123");
            assertEquals("123", row.get(0));
            assertEquals("schmoe", row.get(1));
        } finally {
            statement.close();
            drop("testQueryBy");
        }
    }

    private void drop(String tableName) {
        try {
            access.execute("drop table " + tableName);
        } catch (SQLException ignore) { }
    }
}
