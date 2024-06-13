package ChatRoom.Server;
import java.io.*;
import java.net.*;

public class Server {

    private static ServerSocket serverSocket;

    private final int port = 8888;


    public Server() {
        try {

            serverSocket = new ServerSocket(port);

            System.out.println("Server started...");

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }



    public void stopServer() {
        try {
            serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    public static class SocketServer implements Runnable {

        private static Socket clientSocket;
        private static BufferedReader inputReader;
        private static PrintWriter outputWriter;

        @Override
        public void run() {

            try {
                    clientSocket = serverSocket.accept();

                    System.out.println("Client connected!");

                    onClientConnect();

                    receiveMessage();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }



        public static void receiveMessage() {

            String inputLine;

            try {

                inputReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                inputLine = inputReader.readLine();

                System.out.println("Message from client: \"" + inputLine + "\"");

                broadcastMessage(inputLine);

            }catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }


        public static void onClientConnect() {
            try {

                outputWriter = new PrintWriter(clientSocket.getOutputStream(), true);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            outputWriter.println("\"Welcome to the server!\"");
        }


        public static void broadcastMessage(String message) {
            try {

                outputWriter = new PrintWriter(clientSocket.getOutputStream(), true);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            outputWriter.println("Server received: \"" + message + "\"");
        }

        public static void stopClientHandle() {
            try {
                inputReader.close();

                outputWriter.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
