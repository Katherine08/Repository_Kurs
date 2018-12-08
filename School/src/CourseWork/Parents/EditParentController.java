package CourseWork.Parents;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class EditParentController {
    public Button btnSaveParent;
    public TextField editParentFIO;
    public Label lblTitle;
    public TextField editParentAdress;
    public TextField editParentPhone;
    int id;

    public TextField getEditParentFIO() {
        return editParentFIO;
    }

    public TextField getEditParentAdress() {
        return editParentAdress;
    }

    public TextField getEditParentPhone() {
        return editParentPhone;
    }

    public int getId() {
        return id;
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
        Stage stage = (Stage) btnSaveParent.getScene().getWindow();
        stage.close();
    }

    private void insert() throws SQLException {
        Parent.insertRecord(editParentFIO.getText(), editParentAdress.getText(), editParentPhone.getText());
    }

    private void update() throws SQLException {
        Parent.updateRecord(id, editParentFIO.getText(), editParentAdress.getText(), editParentPhone.getText());
    }
}
