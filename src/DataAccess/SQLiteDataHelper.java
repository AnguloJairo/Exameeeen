package DataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class SQLiteDataHelper {

    private static final String DBPathConnection = "jdbc:sqlite:DataBase//Datos.sqlite";

    // Abre una nueva conexión a la base de datos
    protected Connection openConnection() throws SQLException {
        return DriverManager.getConnection(DBPathConnection);
    }

    // Cierra una conexión a la base de datos
    protected void closeConnection(Connection conn) throws SQLException {
        if (conn != null && !conn.isClosed()) {
            conn.close();
        }
    }
}
