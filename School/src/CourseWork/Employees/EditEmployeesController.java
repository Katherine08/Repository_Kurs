package CourseWork.Employees;

import CourseWork.Childrens.Children;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class EditEmployeesController {
    @FXML
    public Label lblTitle;
    public Button btnSaveEmployees;
    public TextField editEmployeesFIO;
    public TextField editEmployeesPosition;
    public TextField editEmployeesPhone;
    int id;

    public TextField getEditEmployeesFIO() {
        return editEmployeesFIO;
    }

    public TextField getEditEmployeesPosition() {
        return editEmployeesPosition;
    }

    public TextField getEditEmployeesPhone() {
        return editEmployeesPhone;
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
        Stage stage = (Stage) btnSaveEmployees.getScene().getWindow();
        stage.close();
    }

    private void insert() throws SQLException {
        Employee.insertRecord(editEmployeesFIO.getText(), editEmployeesPosition.getText(), editEmployeesPhone.getText());
    }

    private void update() throws SQLException {
        Employee.updateRecord(id, editEmployeesFIO.getText(), editEmployeesPosition.getText(), editEmployeesPhone.getText());
    }

}
