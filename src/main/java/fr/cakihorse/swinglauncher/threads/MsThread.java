package fr.cakihorse.swinglauncher.threads;

import fr.cakihorse.swinglauncher.utils.Auth;
import fr.litarvan.openauth.microsoft.MicrosoftAuthenticationException;

import java.net.MalformedURLException;

public class MsThread implements Runnable{
    @Override
    public void run() {
        try {

            Auth.launch();
        } catch (MicrosoftAuthenticationException e) {
            throw new RuntimeException(e);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

    }
}