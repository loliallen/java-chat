package server.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;


public class controller implements Initializable{

    private static LinkedList<ClientThread> serverList = new LinkedList<>();
    private static final int PORT = 8080;

    @FXML
    public static ListView<String> messageBox;


    private ServerSocket server;
    private Socket client;


    @Override
    public void initialize(URL location, ResourceBundle resources)   {
        System.out.print("hi");
           try {
               server = new ServerSocket(PORT);

               while (true) {
                   client = server.accept();

               }
           } catch (IOException e) {
               e.printStackTrace();
           } finally {
               try {
                   client.close();
                   server.close();
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }
    }
    public static class ClientThread extends Thread{
        private Socket socket;

        private BufferedWriter out;
        private BufferedReader in;

        public ClientThread(Socket client){
            socket = client;
            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            }catch (IOException e){
                e.printStackTrace();
            }
        }

        @Override
        public void run(){
            String word;

            try {
                while (true) {
                    word = in.readLine();
                    messageBox.getItems().add(word);
                    if(word.equals("stop")){ break; }
                    for(ClientThread ct: serverList){
                        ct.send(word);
                    }
                }
            }catch (IOException e){

            }
        }
        private void send(String msg){
            try {
                out.write(msg+ "\n");
                out.flush();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
