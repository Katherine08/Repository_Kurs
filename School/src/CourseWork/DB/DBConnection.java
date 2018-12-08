package CourseWork.DB;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class DBConnection {

    private static Connection connection = null;
    private static final String DB_DRIVER = "org.postgresql.Driver";
    private static final String DB_CONNECTION = "jdbc:postgresql://localhost:5432/school";
    private static String DB_USER;
    private static String DB_PASSWORD;
    private static Statement st;
    public static boolean resultCon;

    public static Statement getSt(){
        return st;
    }

    public static Connection getDBConnection() {
        Connection dbConnection = null;
        try {
            resultCon = true;
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            resultCon = false;
            System.out.println("Where is your PostgreSQL JDBC Driver?");
            System.out.println(e.getMessage());
        }
        System.out.println("PostgreSQL JDBC Driver Registered!");
        try {
            resultCon = true;
            dbConnection = DriverManager.getConnection(
                    DB_CONNECTION, DB_USER,DB_PASSWORD);
            st = dbConnection.createStatement();
            System.out.println("You made it, take control your database now!");
            return dbConnection;

        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            resultCon = false;
            System.out.println(e.getMessage());
        }
        return dbConnection;
    }

    public static String getDbUser() {
        return DB_USER;
    }

    public static void setDbUser(String dbUser) {
        DB_USER = dbUser;
    }

    public static String getDbPassword() {
        return DB_PASSWORD;
    }

    public static void setDbPassword(String dbPassword) {
        DB_PASSWORD = dbPassword;
    }
}
