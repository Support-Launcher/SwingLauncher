package fr.cakihorse.swinglauncher.threads;

import fr.cakihorse.swinglauncher.Launcher;
import fr.litarvan.openauth.microsoft.MicrosoftAuthenticationException;

public class MsThread  implements Runnable{
    @Override
    public void run() {
        try {
            Launcher.authMs();
        } catch (MicrosoftAuthenticationException e) {
            throw new RuntimeException(e);
        }
    }
}
