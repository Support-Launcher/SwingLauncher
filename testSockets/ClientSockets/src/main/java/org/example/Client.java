package org.example;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.UUID;

public class Client {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 12345); // Remplacez "localhost" par l'adresse du serveur
            System.out.println("Connecté au serveur.");

            OutputStream outputStream = socket.getOutputStream();

            // Envoyez des données au serveur
            String message = "a4329efe-4ea7-41ef-9c44-91396f7f0779";
            byte[] data = message.getBytes();
            outputStream.write(data);
            outputStream.flush();

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
