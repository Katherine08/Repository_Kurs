package CourseWork;

import CourseWork.Childrens.Children;
import CourseWork.Childrens.EditChildrenController;
import CourseWork.DB.DBConnection;
import CourseWork.Employees.EditEmployeesController;
import CourseWork.Employees.Employee;
import CourseWork.Groups.EditGroupController;
import CourseWork.Groups.Group;
import CourseWork.Lessons.EditLessonController;
import CourseWork.Lessons.Lesson;
import CourseWork.Parents.EditParentController;
import CourseWork.Parents.Parent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Controller {

    public TableColumn<Children, Integer> columnChildrenID;
    public TableColumn<Children, String> columnChildrenFIO;
    public TableColumn<Children, String> columnChildrenParent;
    public TableColumn<Children, String> columnChildrenGroup;
    public TableView<Children> tableChildren;
    public TableView<Group> tableGroup;
    public TableColumn<Group, String> columnGroupName;
    public TableColumn<Group, Integer> columnGroupID;
    public TableView<Lesson> tableLessons;
    public TableColumn<Lesson, Integer> columnLessonID;
    public TableColumn<Lesson, String> columnLessonName;
    public TableColumn<Lesson, String> columnLessonTime;
    public TableColumn<Lesson, String> columnLessonGroup;
    public TableColumn<Lesson, String> columnLessonTeacher;
    public TableColumn<Lesson, String> columnLessonDay;
    public TableView<Employee> tableEmployees;
    public TableColumn<Employee, Integer> columnEmployeesID;
    public TableColumn<Employee, String> columnEmployeesFIO;
    public TableColumn<Employee, String> columnEmployeesPositionName;
    public TableColumn<Employee, String> columnEmployeesPhone;
    public TableView<Parent> tableParent;
    public TableColumn<Parent, Integer> columnParentID;
    public TableColumn<Parent, String> columnParentFIO;
    public TableColumn<Parent, String> columnParentAdress;
    public TableColumn<Parent, String> columnParentPhone;
    public Button btnAddChildren;
    public Button btnChangeChildren;
    public Button btnDeleteChildren;
    public Button btnAddParent;
    public Button btnChangeParent;
    public Button btnDeleteParent;
    public Button btnChangeLesson;
    public Button btnDeleteLesson;
    public Button btnAddLesson;
    public Button btnChangeGroup;
    public Button btnDeleteGroup;
    public Button btnAddGroup;
    public Button btnDeleteEmployees;
    public Button btnChangeEmployees;
    public Button btnAddEmployees;
    public Button btnRefresh;
    public TextField searchChildren;
    private ObservableList<Children> dataChildren;
    private ObservableList<Parent> dataParents;
    private ObservableList<Employee> dataEmployees;
    private ObservableList<Lesson> dataLessons;
    private ObservableList<Group> dataGroup;

    Connection connection = DBConnection.getDBConnection();
    Statement statement = DBConnection.getSt();

    public void buildDataChildren(){
        dataChildren = FXCollections.observableArrayList();
        try {
            String SQLChildren = "SELECT C.id, C.fio, C.id_parent, G.name, P.fio as pfio\n" +
                    "FROM \"Childrens\" c\n" +
                    "INNER JOIN \"Groups\" g ON c.id_group = g.id\n" +
                    "INNER JOIN \"Parents\" p ON c.id_parent = p.id\n" +
                    "Order By fio;";
            ResultSet rs = statement.executeQuery(SQLChildren);
            while (rs.next()) {
                dataChildren.add(new Children(rs.getInt("id"), rs.getString("fio"),
                        rs.getString("pfio"), rs.getString("name")));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data of Children");
        }
    }

    public void buildDataGroups(){
        dataGroup = FXCollections.observableArrayList();
        try {
            String SQLGroup = "SELECT * FROM \"Groups\";";
            ResultSet rs = statement.executeQuery(SQLGroup);
            while (rs.next()) {
                dataGroup.add(new Group(rs.getInt("id"), rs.getString("name")));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data of Groups");
        }
    }

    public void buildDataLessons(){
        dataLessons = FXCollections.observableArrayList();
        try {
            String SQLLessons = "SELECT L.id, L.name, L.time, G.name as gname, E.fio as tfio, L.day\n" +
                    "FROM \"Lessons\" L\n" +
                    "INNER JOIN \"Groups\" G ON L.id_group = G.id\n" +
                    "INNER JOIN \"Employees\" E ON L.id_teacher = E.id\n" +
                    "Order By name;";
            ResultSet rs = statement.executeQuery(SQLLessons);
            while (rs.next()) {
                dataLessons.add(new Lesson(rs.getInt("id"), rs.getString("name"),
                        rs.getTime("time"), rs.getString("gname"), rs.getString("tfio"),
                        rs.getString("day")));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data of Lessons");
        }
    }

    public void buildDataEmployees(){
        dataEmployees = FXCollections.observableArrayList();
        try {
            String SQLEmployees = "SELECT * FROM \"Employees\";";
            ResultSet rs = statement.executeQuery(SQLEmployees);
            while (rs.next()) {
                dataEmployees.add(new Employee(rs.getInt("id"), rs.getString("fio"),
                        rs.getString("position_name"), rs.getString("phone")));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data of Employees");
        }
    }

    public void buildDataParent(){
        dataParents = FXCollections.observableArrayList();
        try {
            String SQLParents = "SELECT * FROM \"Parents\";";
            ResultSet rs = statement.executeQuery(SQLParents);
            while (rs.next()) {
                dataParents.add(new Parent(rs.getInt("id"), rs.getString("fio"),
                        rs.getString("adress"), rs.getString("phone")));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data of Parents");
        }
    }

    public void initialize() throws SQLException {
        buildDataChildren();
        buildDataGroups();
        buildDataLessons();
        buildDataEmployees();
        buildDataParent();
        columnChildrenID.setCellValueFactory(new PropertyValueFactory<Children, Integer>("id"));
        columnChildrenFIO.setCellValueFactory(new PropertyValueFactory<Children, String>("fio"));
        columnChildrenParent.setCellValueFactory(new PropertyValueFactory<Children, String>("id_parent"));
        columnChildrenGroup.setCellValueFactory(new PropertyValueFactory<Children, String>("id_group"));
        tableChildren.setItems(dataChildren);

        columnGroupID.setCellValueFactory(new PropertyValueFactory<Group, Integer>("id"));
        columnGroupName.setCellValueFactory(new PropertyValueFactory<Group, String>("name"));
        tableGroup.setItems(dataGroup);

        columnLessonID.setCellValueFactory(new PropertyValueFactory<Lesson, Integer>("id"));
        columnLessonName.setCellValueFactory(new PropertyValueFactory<Lesson, String>("name"));
        columnLessonTime.setCellValueFactory(new PropertyValueFactory<Lesson, String>("time"));
        columnLessonGroup.setCellValueFactory(new PropertyValueFactory<Lesson, String>("id_group"));
        columnLessonTeacher.setCellValueFactory(new PropertyValueFactory<Lesson, String>("id_teacher"));
        columnLessonDay.setCellValueFactory(new PropertyValueFactory<Lesson, String>("day"));
        tableLessons.setItems(dataLessons);

        columnEmployeesID.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("id"));
        columnEmployeesFIO.setCellValueFactory(new PropertyValueFactory<Employee, String>("fio"));
        columnEmployeesPositionName.setCellValueFactory(new PropertyValueFactory<Employee, String>("position_name"));
        columnEmployeesPhone.setCellValueFactory(new PropertyValueFactory<Employee, String>("phone"));
        tableEmployees.setItems(dataEmployees);

        columnParentID.setCellValueFactory(new PropertyValueFactory<Parent, Integer>("id"));
        columnParentFIO.setCellValueFactory(new PropertyValueFactory<Parent, String>("fio"));
        columnParentAdress.setCellValueFactory(new PropertyValueFactory<Parent, String>("adress"));
        columnParentPhone.setCellValueFactory(new PropertyValueFactory<Parent, String>("phone"));
        tableParent.setItems(dataParents);
    }

    public void btnDeleteChildren() throws SQLException {
        if (tableChildren.getSelectionModel().getSelectedItem() != null){
            int id = tableChildren.getSelectionModel().getSelectedItem().getId();
            Children.deleteRecord(id);
            initialize();
        }else {
            new Alert(Alert.AlertType.ERROR, "No rows selected!").showAndWait();
        }
    }

    public void btnDeleteEmployees() throws SQLException {
        if (tableEmployees.getSelectionModel().getSelectedItem() != null){
            int id = tableEmployees.getSelectionModel().getSelectedItem().getId();
            Employee.deleteRecordFromEmployeesTable(id);
            initialize();
        }else {
            new Alert(Alert.AlertType.ERROR, "No rows selected!").showAndWait();
        }
    }

    public void btnDeleteGroup() throws SQLException {
        if (tableGroup.getSelectionModel().getSelectedItem() != null){
            int id = tableGroup.getSelectionModel().getSelectedItem().getId();
            Group.deleteRecordFromGroupTable(id);
            initialize();
        }else {
            new Alert(Alert.AlertType.ERROR, "No rows selected!").showAndWait();
        }
    }

    public void btnDeleteLesson() throws SQLException {
        if (tableLessons.getSelectionModel().getSelectedItem() != null){
            int id = tableLessons.getSelectionModel().getSelectedItem().getId();
            Lesson.deleteRecordFromChildrenTable(id);
            initialize();
        }else {
            new Alert(Alert.AlertType.ERROR, "No rows selected!").showAndWait();
        }
    }

    public void btnDeleteParent() throws SQLException {
        if (tableParent.getSelectionModel().getSelectedItem() != null){
            int id = tableParent.getSelectionModel().getSelectedItem().getId();
            Parent.deleteRecordFromParentTable(id);
            initialize();
        }else {
            new Alert(Alert.AlertType.ERROR, "No rows selected!").showAndWait();
        }
    }

    public void btnEditChildren() throws IOException, SQLException {
        if (tableChildren.getSelectionModel().getSelectedItem() != null) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Childrens/EditChildren.fxml"));
            openStage(fxmlLoader,"Редактирование");
            EditChildrenController editChildrenController = fxmlLoader.getController();
            editChildrenController.lblTitle.setText("Редактирование");
            editChildrenController.setParents();
            editChildrenController.setGroups();
            editChildrenController.getEditChildrenFIO().setText(tableChildren.getSelectionModel().getSelectedItem().getFio());
            editChildrenController.getEditChildrenGroup().getSelectionModel().select(tableChildren.getSelectionModel().getSelectedItem().getId_group());
            editChildrenController.getEditChildrenParent().getSelectionModel().select(tableChildren.getSelectionModel().getSelectedItem().getId_parent());
            editChildrenController.setId(tableChildren.getSelectionModel().getSelectedItem().getId());
        }else {
            new Alert(Alert.AlertType.WARNING, "No row selected!").showAndWait();
        }
    }

    public void btnAddChildren() throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Childrens/EditChildren.fxml"));
        openStage(fxmlLoader,"Добавление");
        EditChildrenController editChildrenController = fxmlLoader.getController();
        editChildrenController.lblTitle.setText("Добавление");
        editChildrenController.setParents();
        editChildrenController.setGroups();
    }

    public void btnEditEmployees() throws IOException, SQLException {
        if (tableEmployees.getSelectionModel().getSelectedItem() != null) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Employees/EditEmployees.fxml"));
            openStage(fxmlLoader,"Редактирование");
            EditEmployeesController editEmployeesController = fxmlLoader.getController();
            editEmployeesController.lblTitle.setText("Редактирование");
            editEmployeesController.getEditEmployeesFIO().setText(tableEmployees.getSelectionModel().getSelectedItem().getFio());
            editEmployeesController.getEditEmployeesPosition().setText(tableEmployees.getSelectionModel().getSelectedItem().getPosition_name());
            editEmployeesController.getEditEmployeesPhone().setText(tableEmployees.getSelectionModel().getSelectedItem().getPhone());
            editEmployeesController.setId(tableEmployees.getSelectionModel().getSelectedItem().getId());
        }else {
            new Alert(Alert.AlertType.WARNING, "No row selected!").showAndWait();
        }
    }

    public void btnAddEmployees() throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Employees/EditEmployees.fxml"));
        openStage(fxmlLoader,"Добавление");
        EditEmployeesController editEmployeesController = fxmlLoader.getController();
        editEmployeesController.lblTitle.setText("Добавление");
    }

    public void btnEditGroup() throws IOException, SQLException {
        if (tableGroup.getSelectionModel().getSelectedItem() != null) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Groups/EditGroup.fxml"));
            openStage(fxmlLoader,"Редактирование");
            EditGroupController editGroupController = fxmlLoader.getController();
            editGroupController.lblTitle.setText("Редактирование");
            editGroupController.getEditNameGroup().setText(tableGroup.getSelectionModel().getSelectedItem().getName());
            editGroupController.setId(tableGroup.getSelectionModel().getSelectedItem().getId());
        }else {
            new Alert(Alert.AlertType.WARNING, "No row selected!").showAndWait();
        }
    }

    public void btnAddGroup() throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Groups/EditGroup.fxml"));
        openStage(fxmlLoader,"Добавление");
        EditGroupController editGroupController = fxmlLoader.getController();
        editGroupController.lblTitle.setText("Добавление");
    }

    public void btnEditParent() throws IOException, SQLException {
        if (tableParent.getSelectionModel().getSelectedItem() != null) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Parents/EditParent.fxml"));
            openStage(fxmlLoader,"Редактирование");
            EditParentController editParentController = fxmlLoader.getController();
            editParentController.lblTitle.setText("Редактирование");
            editParentController.getEditParentFIO().setText(tableParent.getSelectionModel().getSelectedItem().getFio());
            editParentController.getEditParentAdress().setText(tableParent.getSelectionModel().getSelectedItem().getAdress());
            editParentController.getEditParentPhone().setText(tableParent.getSelectionModel().getSelectedItem().getPhone());
            editParentController.setId(tableParent.getSelectionModel().getSelectedItem().getId());
        }else {
            new Alert(Alert.AlertType.WARNING, "No row selected!").showAndWait();
        }
    }

    public void btnAddParent() throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Parents/EditParent.fxml"));
        openStage(fxmlLoader,"Добавление");
        EditParentController editParentController = fxmlLoader.getController();
        editParentController.lblTitle.setText("Добавление");
    }

    public void btnEditLesson() throws IOException, SQLException {
        if (tableLessons.getSelectionModel().getSelectedItem() != null) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Lessons/EditLesson.fxml"));
            openStage(fxmlLoader,"Редактирование");
            EditLessonController editLessonController = fxmlLoader.getController();
            editLessonController.lblTitle.setText("Редактирование");
            editLessonController.setGroups();
            editLessonController.setTime();
            editLessonController.setDays();
            editLessonController.setEmployees();
            editLessonController.getEditLessonName().setText(tableLessons.getSelectionModel().getSelectedItem().getName());
            editLessonController.getEditLessonTime().getSelectionModel().select(tableLessons.getSelectionModel().getSelectedItem().getTime());
            editLessonController.getEditLessonGroup().getSelectionModel().select(tableLessons.getSelectionModel().getSelectedItem().getId_group());
            editLessonController.getEditLessonTeacher().getSelectionModel().select(tableLessons.getSelectionModel().getSelectedItem().getId_teacher());
            editLessonController.getEditLessonDay().getSelectionModel().select(tableLessons.getSelectionModel().getSelectedItem().getDay());
            editLessonController.setId(tableLessons.getSelectionModel().getSelectedItem().getId());
        }else {
            new Alert(Alert.AlertType.WARNING, "No row selected!").showAndWait();
        }
    }

    public void btnAddLesson() throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Lessons/EditLesson.fxml"));
        openStage(fxmlLoader,"Добавление");
        EditLessonController editLessonController = fxmlLoader.getController();
        editLessonController.setGroups();
        editLessonController.setTime();
        editLessonController.setDays();
        editLessonController.setEmployees();
        editLessonController.lblTitle.setText("Добавление");
    }

    public void openStage(FXMLLoader fxmlLoader, String title) throws IOException {
        javafx.scene.Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(title);
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void searchChildren(ActionEvent event) {
        String updateTableSQL = "select from get_children('"+ searchChildren.getText() +"')";

        try {
            statement.executeUpdate(updateTableSQL);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
