package CourseWork.Lessons;

import CourseWork.DB.DBConnection;

import java.sql.*;

public class Lesson {
    int id;
    String name;
    Time time;
    String id_group;
    String id_teacher;
    String day;

    public Lesson(int id, String name, Time time, String id_group, String id_teacher, String day) {
        this.id = id;
        this.name = name;
        this.time = time;
        this.id_group = id_group;
        this.id_teacher = id_teacher;
        this.day = day;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Time getTime() {
        return time;
    }

    public String getId_group() {
        return id_group;
    }

    public String getId_teacher() {
        return id_teacher;
    }

    public String getDay() {
        return day;
    }

    public static void deleteRecordFromChildrenTable(int id) throws SQLException {
        Connection dbConnection = null;
        Statement statement = null;
        String deleteTableSQL = "DELETE FROM \"Lessons\" WHERE ID = " + id;
        try {
            dbConnection = DBConnection.getDBConnection();
            statement = dbConnection.createStatement();
            statement.executeUpdate(deleteTableSQL);
            System.out.println("Record is deleted from Lessons table!");

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

    public static void insertRecord(String name, Time time, int id_group, int id_teacher, String day) throws SQLException {
        Connection dbConnection = null;
        Statement statement = null;
        String insertTableSQL = "INSERT INTO \"Lessons\""
                + "(name, time, id_group, id_teacher, day) VALUES\n"
                + "('" + name + "', "
                + "'" +time + "', "
                + id_group + ", "
                + id_teacher + ", '"
                + day + "')";

        try {
            dbConnection = DBConnection.getDBConnection();
            statement = dbConnection.createStatement();
            System.out.println(insertTableSQL);
            statement.executeUpdate(insertTableSQL);
            System.out.println("Record is inserted into Lessons table!");
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

    public static void updateRecord(int id, String name, Time time, int id_group, int id_teacher, String day) throws SQLException {
        Connection dbConnection = null;
        Statement statement = null;
        String updateTableSQL = "UPDATE \"Lessons\"\n"
                + "SET name = '" + name + "', "
                + "time = '" + time + "', "
                + "id_group =" +  id_group + ", "
                + "id_teacher =" +  id_teacher + ", "
                + "day = '" +  day + "'\n"
                + "WHERE id = " + id;

        try {
            dbConnection = DBConnection.getDBConnection();
            statement = dbConnection.createStatement();
            System.out.println(updateTableSQL);
            statement.executeUpdate(updateTableSQL);
            System.out.println("Record is updated into Lessons table!");
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
