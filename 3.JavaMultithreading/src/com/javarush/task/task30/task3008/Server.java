package com.javarush.task.task30.task3008;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by sikonder on 12.03.17.
 */
public class Server {

    private static Map<String, Connection> connectionMap = new ConcurrentHashMap<>();

    public static void main(String[] args) throws IOException {

        ConsoleHelper.writeMessage("Введите порт сервера: ");
        int serverPort = ConsoleHelper.readInt();

        try (ServerSocket serverSocket = new ServerSocket(serverPort)) {

            ConsoleHelper.writeMessage("Сервер запущен");

            while (true) {

                Socket socket = serverSocket.accept();
                Handler handler = new Handler(socket);
                handler.start();
            }
        }catch (IOException e){
            ConsoleHelper.writeMessage(e.getMessage());
        }

    }

    public static void sendBroadcastMessage(Message message){
        try {
            for(Connection connection : connectionMap.values()){
                connection.send(message);
            }
        }catch (IOException e){
            e.printStackTrace();
            ConsoleHelper.writeMessage("FAIL");
        }


    }

    private static class Handler extends Thread{
        private Socket socket;

        public Handler(Socket socket) {
            this.socket = socket;
        }
        private String serverHandshake(Connection connection) throws IOException, ClassNotFoundException {
            while (true) {
                connection.send(new Message(MessageType.NAME_REQUEST));
                Message message = connection.receive();
                if (message.getType() == MessageType.USER_NAME) {
                    if (!message.getData().isEmpty()) {
                        if (connectionMap.get(message.getData()) == null) {
                            connectionMap.put(message.getData(), connection);
                            connection.send(new Message(MessageType.NAME_ACCEPTED));
                            return message.getData();
                        }
                    }
                }
            }
        }

        private void sendListOfUsers(Connection connection, String userName) throws IOException{
            for(String x : connectionMap.keySet()){
                Message message = new Message(MessageType.USER_ADDED,x);

                if (!x.equals(userName)) {
                    connection.send(message);
                }
            }
        }

        private void serverMainLoop(Connection connection, String userName) throws IOException, ClassNotFoundException{
            while (true){
                Message message = connection.receive();
                if(message.getType()==MessageType.TEXT){
                    String s = userName+": "+message.getData();
                    Message newMessage = new Message(MessageType.TEXT,s);
                    sendBroadcastMessage(message);
                }else {
                    ConsoleHelper.writeMessage("FAIL");
                }
            }
        }

        @Override
        public void run() {

            ConsoleHelper.writeMessage("Установлено соединение с удаленным клиентом с адресом: " +
                    socket.getRemoteSocketAddress());



            Connection connection = null;
            String clientName = null;
            try {
                connection = new Connection(socket);


                clientName = serverHandshake(connection);

                sendBroadcastMessage(new Message(MessageType.USER_ADDED, clientName));


                sendListOfUsers(connection, clientName);

                serverMainLoop(connection, clientName);

            } catch (IOException e) {
                ConsoleHelper.writeMessage("FAIL");
            } catch (ClassNotFoundException e) {
                ConsoleHelper.writeMessage("FAIL");
            }

            if (clientName != null) {
                connectionMap.remove(clientName);
                sendBroadcastMessage(new Message(MessageType.USER_REMOVED, clientName));
            }

            ConsoleHelper.writeMessage(String.format("Соединение с удаленным адресом (%s) закрыто.", socket.getRemoteSocketAddress()));
        }


    }
}
