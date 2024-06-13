package ChatRoom.Client;

import ChatRoom.Server.Server;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {

    private Server server;
    private Client client;

    @BeforeEach
    void setUp() {
        server = new Server();

        Server.SocketServer clientHandler = new Server.SocketServer();

        Thread clientHandlerThread = new Thread(clientHandler);

        clientHandlerThread.start();

        client = new Client();
    }

    @AfterEach
    void tearDown() {
        client.closeConnection();
        server.stopServer();
    }

    // TODO: Change the greeting message from the server on established connection
    // Change the greeting string of the server and/or the unit test to a personalized greeting.
    @Test
    public void testGreetingMessage() throws IOException {
        // Arrange
        String expectedGreeting = "Welcome to our chat room, valued guest!";

        // Act
        client.sendMessage("Hello");
        String receivedMessage = client.receiveMessage();

        client.closeConnection();
        server.stopServer();

        // Assert
        assertEquals(expectedGreeting, receivedMessage);
    }

    // TODO: Implement a server reply to the client
    // Make the server reply to a message sent from the client. Change the message to reflect a different reply.
    @Test
    public void testReplyFromServer() throws IOException {
        // Arrange
        String clientMessage = "How are you?";
        String expectedResponse = "I am just a server, but I'm here to assist you!";

        // Act
        client.sendMessage(clientMessage);
        String receivedMessage = client.receiveMessage();

        client.closeConnection();
        server.stopServer();

        // Assert
        assertEquals(expectedResponse, receivedMessage);
    }

    // TODO: Implement a method in the server that checks if an incoming message is a number.
    // If the message has numbers ONLY, the server should reply to the client with the square of the number in the message.
    @Test
    public void testSquareNumber() throws IOException {
        // Arrange
        String numberString = "9";
        String expectedResponse = "The square of 9 is 81.";

        // Act
        client.sendMessage(numberString);
        String receivedMessage = client.receiveMessage();

        client.closeConnection();
        server.stopServer();

        // Assert
        assertEquals(expectedResponse, receivedMessage);
    }

    // TODO: Implement a method in the server that checks an incoming message from a client is a lowercase string.
    // If the message has lowercase letters only, the server should reverse the message and broadcast it to the client.
    @Test
    public void testStringReversal() throws IOException {
        // Arrange
        String message = "coding is fun";
        String expectedResponse = "nuf si gnidoc";

        // Act
        client.sendMessage(message);
        String receivedMessage = client.receiveMessage();

        client.closeConnection();
        server.stopServer();

        // Assert
        assertEquals(expectedResponse, receivedMessage);
    }

    // TODO: Implement a method in the server that handles uppercase strings
    // If the message is in uppercase, the server should convert it to lowercase and send it back.
    @Test
    public void testUppercaseToLowercase() throws IOException {
        // Arrange
        String message = "HELLO WORLD";
        String expectedResponse = "hello world";

        // Act
        client.sendMessage(message);
        String receivedMessage = client.receiveMessage();

        client.closeConnection();
        server.stopServer();

        // Assert
        assertEquals(expectedResponse, receivedMessage);
    }

    // TODO: Implement a method in the server to broadcast a message to all connected clients
    // Test that a message sent from one client is received by another connected client.
    @Test
    public void testBroadcastMessage() throws IOException, InterruptedException {
        // Arrange
        Client client2 = new Client();
        String message = "Hello, everyone!";
        String expectedResponse = "Hello, everyone!";

        // Act
        client.sendMessage(message);
        String receivedMessage = client2.receiveMessage();

        client.closeConnection();
        client2.closeConnection();
        server.stopServer();

        // Assert
        assertEquals(expectedResponse, receivedMessage);
    }

    // TODO: Implement a method in the server to handle private messages between clients
    // Test that a private message sent from one client is received only by the intended recipient.
    @Test
    public void testPrivateMessage() throws IOException {
        // Arrange
        Client client2 = new Client();
        String privateMessage = "@client2: Hi, just for you!";
        String expectedResponse = "Hi, just for you!";

        // Act
        client.sendMessage(privateMessage);
        String receivedMessage = client2.receiveMessage();

        client.closeConnection();
        client2.closeConnection();
        server.stopServer();

        // Assert
        assertEquals(expectedResponse, receivedMessage);
    }

    // TODO: Implement a method in the server to handle commands
    // Test that a command sent from a client is executed correctly by the server.
    @Test
    public void testServerCommand() throws IOException {
        // Arrange
        String command = "/uptime";
        String expectedResponse = "Server uptime: 12345 seconds";

        // Act
        client.sendMessage(command);
        String receivedMessage = client.receiveMessage();

        client.closeConnection();
        server.stopServer();

        // Assert
        assertEquals(expectedResponse, receivedMessage);
    }
}
