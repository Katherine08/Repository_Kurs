package CourseWork.Groups;

import CourseWork.DB.DBConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Group {
    int id;
    String name;

    public Group(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public static void deleteRecordFromGroupTable(int id) throws SQLException {
        Connection dbConnection = null;
        Statement statement = null;
        String deleteTableSQL = "DELETE FROM \"Groups\" WHERE ID = " + id;
        try {
            dbConnection = DBConnection.getDBConnection();
            statement = dbConnection.createStatement();
            statement.executeUpdate(deleteTableSQL);
            System.out.println("Record is deleted from Groups table!");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
    }

    public static void insertRecord(String name) throws SQLException {
        Connection dbConnection = null;
        Statement statement = null;
        String insertTableSQL = "INSERT INTO \"Groups\""
                + "(name) VALUES\n"
                + "('"+ name +"')";

        try {
            dbConnection = DBConnection.getDBConnection();
            statement = dbConnection.createStatement();
            System.out.println(insertTableSQL);
            statement.executeUpdate(insertTableSQL);
            System.out.println("Record is inserted into Groups table!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
    }

    public static void updateRecord(int id, String name) throws SQLException {
        Connection dbConnection = null;
        Statement statement = null;
        String updateTableSQL = "UPDATE \"Groups\"\n"
                + "SET name = '" + name + "'"
                + "WHERE id = " + id;

        try {
            dbConnection = DBConnection.getDBConnection();
            statement = dbConnection.createStatement();
            System.out.println(updateTableSQL);
            statement.executeUpdate(updateTableSQL);
            System.out.println("Record is updated into Groups table!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
    }
}
