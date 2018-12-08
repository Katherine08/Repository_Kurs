package CourseWork.Childrens;

import CourseWork.DB.DBConnection;
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

public class EditChildrenController {

    @FXML
    public Label lblTitle;
    @FXML
    public ComboBox editChildrenParent;
    @FXML
    public ComboBox editChildrenGroup;
    @FXML
    public TextField editChildrenFIO;
    public Button btnSaveChildren;
    private ObservableList<Group> dataGroup;
    private ObservableList<Parent> dataParent;
    private int id;

    public void setId(int id) {
        this.id = id;
    }

    public void save() throws IOException, SQLException {
        if (lblTitle.getText().equals("Добавление")){
            insert();
        }else {
            update();
        }
        Stage stage = (Stage) btnSaveChildren.getScene().getWindow();
        stage.close();
    }

    private void insert() throws SQLException {
        Children.insertRecord(editChildrenFIO.getText(),
                dataParent.get(editChildrenParent.getSelectionModel().getSelectedIndex()).getId(),
                dataGroup.get(editChildrenGroup.getSelectionModel().getSelectedIndex()).getId());
    }

    private void update() throws SQLException {
        Children.updateRecord(id, editChildrenFIO.getText(),
                dataParent.get(editChildrenParent.getSelectionModel().getSelectedIndex()).getId(),
                dataGroup.get(editChildrenGroup.getSelectionModel().getSelectedIndex()).getId());
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
                editChildrenGroup.getItems().add(group.getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setParents() throws SQLException {
        Connection connect = DBConnection.getDBConnection();
        dataParent = FXCollections.observableArrayList();

        try {
            String SQLParent = "Select * from \"Parents\"";
            ResultSet rs = connect.createStatement().executeQuery(SQLParent);
            while (rs.next()) {
                Parent parent = new Parent(rs.getInt("id"), rs.getString("fio"));
                dataParent.add(new Parent (parent.getId(), parent.getFio()));
                editChildrenParent.getItems().add(parent.getFio());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ComboBox getEditChildrenParent() {
        return editChildrenParent;
    }

    public ComboBox getEditChildrenGroup() {
        return editChildrenGroup;
    }

    public TextField getEditChildrenFIO() {
        return editChildrenFIO;
    }
}
