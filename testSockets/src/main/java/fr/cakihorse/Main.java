package fr.cakihorse;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    private String message;

    public Main() {
        // Vous n'avez pas besoin d'appeler getMessage() ici.
    }

    public static void main(String[] args) {
        Main mainInstance = new Main(); // Créez une instance de la classe Main
        mainInstance.runServer(); // Exécutez le serveur

        // Attendez ici si nécessaire, puis accédez au message après qu'il a été reçu
        String receivedMessage = mainInstance.getMessage();
        CreateFileAndWriteMessage createFileAndWriteMessage = new CreateFileAndWriteMessage(mainInstance);

        System.out.println("Message reçu : " + receivedMessage);
    }

    public void runServer() {
        try {
            ServerSocket serverSocket = new ServerSocket(12345);
            System.out.println("Serveur socket en attente de connexions...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Connexion reçue depuis : " + clientSocket.getInetAddress());

                // Lire les données envoyées par le client
                InputStream inputStream = clientSocket.getInputStream();
                byte[] buffer = new byte[1024];
                int bytesRead = inputStream.read(buffer);
                if (bytesRead > 0) {
                    message = new String(buffer, 0, bytesRead);
                    System.out.println("Message reçu : " + message);
                    CreateFileAndWriteMessage.createFile();
                }

                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getMessage() {
        return this.message;
    }
}
