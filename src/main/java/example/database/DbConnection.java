package example.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

    private final String url, password, userid;

    private Connection connection;

    public DbConnection(String url, String userid, String password) {
        this.url = url;
        this.userid = userid;
        this.password = password;
    }

    /**
     * Author: Justin Riedel
     * stellt eine Verbindung zur Datenbank her
     */
    public void connectToDb() {
        try {
            connection = DriverManager.getConnection(url, userid, password);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Author: Justin Riedel
     * Schließt die Verbindung zur Datenbank
     */

    public void disconnectFromDb() {
        try {
            if (connection.isClosed()) {
                return;
            }
            connection.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
