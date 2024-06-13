package ChatRoom.Client;
import java.io.*;
import java.net.*;

public class Client {
    Socket clientSocket;

    PrintWriter outputWriter;

    BufferedReader inputReader;

    private final String serverAddress = "localhost";

    private final int networkPort = 8888;




    public Client() {
        try {

            clientSocket = new Socket(serverAddress, networkPort);

            outputWriter = new PrintWriter(clientSocket.getOutputStream(), true);
            inputReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            System.out.println("Connected to server...");

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }




    public void sendMessage(String message) {
        outputWriter.println(message);
    }




    public String receiveMessage() {
        String serverMessage = null;
        try {

            serverMessage = inputReader.readLine();

        } catch (UnknownHostException e) {
            System.err.println("Unknown host: " + serverAddress);
        } catch (IOException e) {
            System.err.println("I/O exception while connecting to the server!");
        }

        return serverMessage;
    }



    public void closeConnection() {
        try {
            inputReader.close();

            outputWriter.close();

            clientSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

