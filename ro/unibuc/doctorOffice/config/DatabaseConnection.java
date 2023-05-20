package ro.unibuc.doctorOffice.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// "final" because we don't want to inherit from it.
public final class DatabaseConnection {

    private static Connection dbConn;
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/doctoroffice";
    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "hesoyam";

    // util class, no instance
    private DatabaseConnection() {}

    public static Connection getConnection() throws SQLException
    {
        if(dbConn == null)
            dbConn = DriverManager.getConnection(DatabaseConnection.DB_URL,DatabaseConnection.DB_USERNAME,DatabaseConnection.DB_PASSWORD);

        return dbConn;
    }

    public static void closeConnection() throws SQLException
    {
        if(dbConn != null || !dbConn.isClosed())
            dbConn.close();
    }
}
