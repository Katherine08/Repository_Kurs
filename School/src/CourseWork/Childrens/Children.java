package CourseWork.Childrens;

import CourseWork.Controller;
import CourseWork.DB.DBConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Children {
    int id;
    String fio;
    String id_parent;
    String id_group;

    public Children(int id, String fio, String id_parent, String id_group) {
        this.id = id;
        this.fio = fio;
        this.id_parent = id_parent;
        this.id_group = id_group;
    }

    public int getId() {
        return id;
    }

    public String getFio() {
        return fio;
    }

    public String getId_parent() {
        return id_parent;
    }

    public String getId_group() {
        return id_group;
    }

    public static void deleteRecord(int id) throws SQLException {
        Connection dbConnection = null;
        Statement statement = null;
        String deleteTableSQL = "DELETE FROM \"Childrens\" WHERE ID = " + id;
        try {
            dbConnection = DBConnection.getDBConnection();
            statement = dbConnection.createStatement();
            statement.executeUpdate(deleteTableSQL);
            System.out.println("Record is deleted from Childrens table!");

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

    public static void insertRecord(String fio, int id_parent, int id_group) throws SQLException {
        Connection dbConnection = null;
        Statement statement = null;
        String insertTableSQL = "INSERT INTO \"Childrens\""
                + "(fio, id_parent, id_group) VALUES\n"
                + "('" + fio + "', " + id_parent + ", " + id_group + ")";

        try {
            dbConnection = DBConnection.getDBConnection();
            statement = dbConnection.createStatement();
            System.out.println(insertTableSQL);
            statement.executeUpdate(insertTableSQL);
            System.out.println("Record is inserted into Childrens table!");
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

    public static void updateRecord(int id, String fio, int id_parent, int id_group) throws SQLException {
        Connection dbConnection = null;
        Statement statement = null;
        String updateTableSQL = "UPDATE \"Childrens\"\n"
                + "SET fio = '" + fio + "', "
                + "id_parent = " + id_parent + ", "
                + "id_group = " + id_group + "\n"
                + "WHERE id = " + id;

        try {
            dbConnection = DBConnection.getDBConnection();
            statement = dbConnection.createStatement();
            System.out.println(updateTableSQL);
            statement.executeUpdate(updateTableSQL);
            System.out.println("Record is updated into Childrens table!");
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
