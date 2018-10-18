package client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    private static Stage stage;
    public static Stage getStage(){
        return stage;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("fxml/login.fxml"));
        primaryStage.setTitle("Chat 1-00");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    // затычки на пароль и юзернейм
    private static String pass;
    private static String log;

    public static String getPass(){
        pass = "admin";
        return pass;
    }
    public static String getLog(){
        log = "admin";
        return log;
    }

}
