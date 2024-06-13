package ChatRoom.Server;

public class Program {

    public static void main(String[] args) {
        Server socketServer = new Server();

        while (true) {
            Server.SocketServer clientHandler = new Server.SocketServer();

            Thread clientHandlerThread = new Thread(clientHandler);

            clientHandlerThread.start();
        }
    }
}
