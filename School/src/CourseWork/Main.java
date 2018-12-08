package CourseWork;

import CourseWork.DB.DBConnection;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;

public class Main extends Application {

    @FXML
    private TextField editLogin;
    @FXML
    private TextField editPass;
    @FXML
    private Button btnAuth;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Auth/Auth.fxml"));
        primaryStage.setTitle("Вход");
        primaryStage.setScene(new Scene(root, 315, 240));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void auth(ActionEvent event) throws IOException {
        DBConnection database = new DBConnection();
        String login, password;
        login = editLogin.getText();
        password = editPass.getText();
        database.setDbUser(login);
        database.setDbPassword(password);
        DBConnection.getDBConnection();

        if (!database.resultCon) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Ошибка");
            alert.setContentText("Проверьте логин или пароль!");
            alert.show();
        } else {

            Stage stage = (Stage) btnAuth.getScene().getWindow();
            stage.close();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Main.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Детский сад");
            stage.setScene(new Scene(root1));
            stage.show();
        }
    }
}
