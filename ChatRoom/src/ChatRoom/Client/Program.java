package ChatRoom.Client;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);

        Client client = new Client();

        System.out.println(client.receiveMessage());

        while (true) {
            System.out.print("Enter message to send: ");

            client.sendMessage(userInput.nextLine());

            System.out.println(client.receiveMessage());
        }
    }
}
