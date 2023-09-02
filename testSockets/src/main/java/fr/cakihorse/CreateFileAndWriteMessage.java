package fr.cakihorse;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CreateFileAndWriteMessage {

    private static Main mainInstance;

    public CreateFileAndWriteMessage(Main mainInstance) {
        this.mainInstance = mainInstance;
    }

    public void afficherMessage() {
        String message = mainInstance.getMessage();
        System.out.println("Message depuis AutreClasse : " + message);
    }


    public static void createFile() {
        try {
            String message = mainInstance.getMessage();

            // Le nom du fichier à créer
            String fileName = "token.infos";
            // Créez un objet File avec le nom du fichier
            File file = new File(fileName);

            // Créez un FileWriter pour écrire dans le fichier
            FileWriter fileWriter = new FileWriter(file);

            // Créez un BufferedWriter pour améliorer les performances de l'écriture
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            // Écrivez le message dans le fichier
            bufferedWriter.write(message);

            // Assurez-vous de vider et de fermer le BufferedWriter pour que les données soient écrites dans le fichier
            bufferedWriter.flush();
            bufferedWriter.close();

            // Affichez un message pour indiquer que le fichier a été créé avec succès
            System.out.println("Fichier créé avec succès : " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
