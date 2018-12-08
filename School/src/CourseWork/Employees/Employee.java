package CourseWork.Employees;

import CourseWork.DB.DBConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Employee {
    int id;
    String fio;
    String position_name;
    String phone;

    public Employee(int id, String fio, String position_name, String phone) {
        this.id = id;
        this.fio = fio;
        this.position_name = position_name;
        this.phone = phone;
    }

    public Employee(int id, String fio) {
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

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getPosition_name() {
        return position_name;
    }

    public void setPosition_name(String position_name) {
        this.position_name = position_name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public static void deleteRecordFromEmployeesTable(int id) throws SQLException {
        Connection dbConnection = null;
        Statement statement = null;
        String deleteTableSQL = "DELETE FROM \"Employees\" WHERE ID = " + id;
        try {
            dbConnection = DBConnection.getDBConnection();
            statement = dbConnection.createStatement();
            statement.executeUpdate(deleteTableSQL);
            System.out.println("Record is deleted from Employees table!");

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

    public static void insertRecord(String fio, String position_name, String phone) throws SQLException {
        Connection dbConnection = null;
        Statement statement = null;
        String insertTableSQL = "INSERT INTO \"Employees\""
                + "(fio, position_name, phone) VALUES\n"
                + "('" + fio + "', '"
                + position_name + "', '"
                + phone + "')";

        try {
            dbConnection = DBConnection.getDBConnection();
            statement = dbConnection.createStatement();
            System.out.println(insertTableSQL);
            statement.executeUpdate(insertTableSQL);
            System.out.println("Record is inserted into Employees table!");
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

    public static void updateRecord(int id, String fio, String position_name, String phone) throws SQLException {
        Connection dbConnection = null;
        Statement statement = null;
        String updateTableSQL = "UPDATE \"Employees\"\n"
                + "SET fio = '" + fio + "', "
                + "position_name = '" + position_name + "', "
                + "phone = '" + phone + "'\n"
                + "WHERE id = " + id;

        try {
            dbConnection = DBConnection.getDBConnection();
            statement = dbConnection.createStatement();
            System.out.println(updateTableSQL);
            statement.executeUpdate(updateTableSQL);
            System.out.println("Record is updated into Employees table!");
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
