package client.contollers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class main implements Initializable {

    @FXML
    public TextField msgIn;
    private static String message;

    @FXML
    public static ListView msgBox;

    @FXML
    public Button sendBtn;


    private static Socket socket;

    private static BufferedReader userMsg; // прием сообщений из приложения от пользователя
    private static BufferedReader in; // прием сообщений от сервера
    private static BufferedWriter out; // отправка сообщений на сервер


    public void PrintMsg(){
        System.out.println(msgIn.getText());
        msgIn.clear();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try{
            socket = new Socket("localhost" , 8080);

            userMsg = new BufferedReader(new InputStreamReader(System.in));
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            // Зпустить поток для параллельного считывания сообщений с сервера

            while (message.equals("stop")){
                message = userMsg.readLine();
                out.write(" smb > " + message + "\n");
                out.flush();
            }

        }catch (IOException e){
            e.printStackTrace();
        }finally {
            close();
        }
    }

    private static class ListenServer extends  Thread{ // класс для прослушивания сервера
        boolean stop = false;

        @Override
        public void run() {
            String str = ""; // сообщение с сервера
            try {


                while (!stop) {
                    str = in.readLine();
                    msgBox.getItems().add(str);
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    private void close(){
        try {
            socket.close();
            userMsg.close();
            in.close();
            out.close();

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
