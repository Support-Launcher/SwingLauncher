package fr.cakihorse.swinglauncher.utils;

import java.util.UUID;

public class Random {
    public Random() {
    }

    public static String generateRandomString(int length) {
        String characters = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder randomString = new StringBuilder();
        java.util.Random random = new java.util.Random();

        for(int i = 0; i < length; ++i) {
            int index = random.nextInt(characters.length());
            randomString.append(characters.charAt(index));
        }

        return randomString.toString();
    }

    public static String generateRandomAccesToken(int length) {
        String characters = "0123456789";
        StringBuilder randomString = new StringBuilder();
        java.util.Random random = new java.util.Random();

        for(int i = 0; i < length; ++i) {
            int index = random.nextInt(characters.length());
            randomString.append(characters.charAt(index));
        }

        return randomString.toString();
    }

    public static String generateRandomUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
