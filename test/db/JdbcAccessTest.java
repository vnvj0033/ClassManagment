package db;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import search.SearchTest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
        }finally {
            connection.close();
        }
    }

    @Test
    public void testExecute() throws SQLException {
        access.execute("create table testExecute (fieldA char)");
        try{
            assertEquals("fieldA", access.getFirstRowFirstColumn("desc testExecute"));
        }finally {
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

            List<S>
        }
    }
}
