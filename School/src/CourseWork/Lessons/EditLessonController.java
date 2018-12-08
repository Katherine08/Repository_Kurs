package CourseWork.Lessons;

import CourseWork.Childrens.Children;
import CourseWork.DB.DBConnection;
import CourseWork.Employees.Employee;
import CourseWork.Groups.Group;
import CourseWork.Parents.Parent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

public class EditLessonController {
    @FXML
    private ComboBox editLessonGroup;
    @FXML
    private ComboBox editLessonTeacher;
    @FXML
    private ComboBox editLessonDay;
    @FXML
    private TextField editLessonName;
    @FXML
    private Button btnSaveLesson;
    @FXML
    private ComboBox editLessonTime;
    public Label lblTitle;
    private ObservableList<Group> dataGroup;
    private ObservableList<Employee> dataEmployees;
    private ObservableList<String> dataDays;
    private ObservableList<Time> dataTime;
    private int id;

    public ComboBox getEditLessonGroup() {
        return editLessonGroup;
    }

    public ComboBox getEditLessonTeacher() {
        return editLessonTeacher;
    }

    public ComboBox getEditLessonDay() {
        return editLessonDay;
    }

    public TextField getEditLessonName() {
        return editLessonName;
    }

    public ComboBox getEditLessonTime() {
        return editLessonTime;
    }

    public void setGroups() throws SQLException {
        Connection connect = DBConnection.getDBConnection();
        dataGroup = FXCollections.observableArrayList();

        try {
            String SQLGroup = "Select * from \"Groups\"";
            ResultSet rs = connect.createStatement().executeQuery(SQLGroup);
            while (rs.next()) {
                Group group = new Group(rs.getInt("id"), rs.getString("name"));
                dataGroup.add(new Group (group.getId(), group.getName()));
                editLessonGroup.getItems().add(group.getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setEmployees() throws SQLException {
        Connection connect = DBConnection.getDBConnection();
        dataEmployees = FXCollections.observableArrayList();

        try {
            String SQLEmptoyees = "Select * from \"Employees\"";
            ResultSet rs = connect.createStatement().executeQuery(SQLEmptoyees);
            while (rs.next()) {
                Employee employee = new Employee(rs.getInt("id"), rs.getString("fio"));
                dataEmployees.add(new Employee (employee.getId(), employee.getFio()));
                editLessonTeacher.getItems().add(employee.getFio());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setDays() {
        dataDays = FXCollections.observableArrayList(
                "понедельник",
                "вторник",
                "среда",
                "четверг",
                "пятница",
                "суббота",
                "воскресенье"
        );
        editLessonDay.setItems(dataDays);
    }

    public void setTime() {
        dataTime = FXCollections.observableArrayList();
        for (int i = 9; i < 18; i++){
            dataTime.add(new Time(i, 0, 0));
        }
        editLessonTime.setItems(dataTime);
    }

    public void setId(int id) {
        this.id = id;
    }

    public void save() throws IOException, SQLException {
        if (lblTitle.getText().equals("Добавление")){
            insert();
        }else {
            update();
        }
        Stage stage = (Stage) btnSaveLesson.getScene().getWindow();
        stage.close();
    }

    private void insert() throws SQLException {
        Lesson.insertRecord(editLessonName.getText(),
                dataTime.get(getEditLessonTime().getSelectionModel().getSelectedIndex()),
                dataGroup.get(editLessonGroup.getSelectionModel().getSelectedIndex()).getId(),
                dataEmployees.get(editLessonTeacher.getSelectionModel().getSelectedIndex()).getId(),
                dataDays.get(editLessonDay.getSelectionModel().getSelectedIndex()));
    }

    private void update() throws SQLException {
        Lesson.updateRecord(id, editLessonName.getText(),
                dataTime.get(getEditLessonTime().getSelectionModel().getSelectedIndex()),
                dataGroup.get(editLessonGroup.getSelectionModel().getSelectedIndex()).getId(),
                dataEmployees.get(editLessonTeacher.getSelectionModel().getSelectedIndex()).getId(),
                dataDays.get(editLessonDay.getSelectionModel().getSelectedIndex()));
    }

}
