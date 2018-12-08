package CourseWork.Groups;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class EditGroupController {
    public Button btnSaveGroup;
    public TextField editNameGroup;
    public Label lblTitle;
    int id;

    public TextField getEditNameGroup() {
        return editNameGroup;
    }
    
    public int getId() {
        return id;
    }

    public void save() throws IOException, SQLException {
        if (lblTitle.getText().equals("Добавление")){
            insert();
        }else {
            update();
        }
        Stage stage = (Stage) btnSaveGroup.getScene().getWindow();
        stage.close();
    }

    private void insert() throws SQLException {
        Group.insertRecord(editNameGroup.getText());
    }

    private void update() throws SQLException {
        Group.updateRecord(id, editNameGroup.getText());
    }

    public void setId(int id) {
        this.id = id;
    }
}
