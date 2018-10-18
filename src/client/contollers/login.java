package client.contollers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import client.Main;

import java.net.URL;
import java.util.ResourceBundle;

public class login implements Initializable {

    @FXML
    public Button enterBtn;

    @FXML
    public TextField usernameField;
    private String LOGIN;


    @FXML
    public PasswordField passwordField;
    private String PASSWORD;

    @FXML
    public Label alertLbl;


    @FXML
    public void Enter() throws Exception{
        if(passwordField.getText().equals(PASSWORD) && usernameField.getText().equals(LOGIN)) {
            Stage stage = Main.getStage();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../fxml/main.fxml"))));
        }else{
            alertLbl.setVisible(true);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        alertLbl.setVisible(false);

        LOGIN = Main.getLog();
        PASSWORD = Main.getPass();
    }
}
