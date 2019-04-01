package cn.im731.servmgr;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JdbcUtils {

    public static void releaseConnection(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static DataSource dataSource = null;

    static {
        dataSource = new ComboPooledDataSource("servmgr");
    }

    public static Connection getConnectoon() throws SQLException {

        return dataSource.getConnection();
    }

}
