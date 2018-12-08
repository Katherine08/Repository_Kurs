package CourseWork.Parents;

import CourseWork.DB.DBConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Parent {
    int id;
    String fio;
    String adress;
    String phone;

    public Parent(int id, String fio, String adress, String phone) {
        this.id = id;
        this.fio = fio;
        this.adress = adress;
        this.phone = phone;
    }

    public Parent(int id, String fio) {
        this.id = id;
        this.fio = fio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public String getAdress() {
        return adress;
    }

    public String getPhone() {
        return phone;
    }

    public static void deleteRecordFromParentTable(int id) throws SQLException {
        Connection dbConnection = null;
        Statement statement = null;
        String deleteTableSQL = "DELETE FROM \"Parents\" WHERE ID = " + id;
        try {
            dbConnection = DBConnection.getDBConnection();
            statement = dbConnection.createStatement();
            statement.executeUpdate(deleteTableSQL);
            System.out.println("Record is deleted from Parents table!");

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

    public static void insertRecord(String fio, String adress, String phone) throws SQLException {
        Connection dbConnection = null;
        Statement statement = null;
        String insertTableSQL = "INSERT INTO \"Parents\""
                + "(fio, adress, phone) VALUES\n"
                + "('" + fio + "', '" + adress + "', '" + phone + "')";

        try {
            dbConnection = DBConnection.getDBConnection();
            statement = dbConnection.createStatement();
            System.out.println(insertTableSQL);
            statement.executeUpdate(insertTableSQL);
            System.out.println("Record is inserted into Parents table!");
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

    public static void updateRecord(int id, String fio, String adress, String phone) throws SQLException {
        Connection dbConnection = null;
        Statement statement = null;
        String updateTableSQL = "UPDATE \"Parents\"\n"
                + "SET fio = '" + fio + "', "
                + "adress = '" + adress + "', "
                + "phone = '" + phone + "'\n"
                + "WHERE id = " + id;

        try {
            dbConnection = DBConnection.getDBConnection();
            statement = dbConnection.createStatement();
            System.out.println(updateTableSQL);
            statement.executeUpdate(updateTableSQL);
            System.out.println("Record is updated into Parents table!");
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
