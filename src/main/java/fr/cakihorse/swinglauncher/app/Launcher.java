package fr.cakihorse.swinglauncher.app;

import fr.cakihorse.swinglauncher.utils.Random;
import fr.flowarg.flowlogger.ILogger;
import fr.flowarg.flowlogger.Logger;
import fr.flowarg.flowupdater.FlowUpdater;
import fr.flowarg.flowupdater.versions.VanillaVersion;
import fr.flowarg.openlauncherlib.NoFramework;
import fr.theshark34.openlauncherlib.minecraft.*;
import fr.theshark34.openlauncherlib.minecraft.util.GameDirGenerator;

import java.awt.*;
import java.nio.file.Path;


import static fr.cakihorse.swinglauncher.app.Main.getSaver;


public class Launcher extends Component {
    //private static GameInfos gameInfos = new GameInfos("launcherswing", new GameVersion("1.8.8", GameType.V1_8_HIGHER), new GameTweak[]{});
    private static final Path gameDir = GameDirGenerator.createGameDir("swinglauncher", false);
    //private static Path gameDir = gameInfos.getGameDir();
    static final ILogger logger = new Logger("[LAUNCHER]", null);
    //private static CrashReporter cReporter = new CrashReporter(String.valueOf(crashFile), gameDir);
    public static AuthInfos authInfos;



    public static void update() throws Exception {

        final VanillaVersion vanillaVersion = new VanillaVersion.VanillaVersionBuilder()
                //keep this line if you have mcp !
                .withName("1.8.8")
                //with mcp :
                //.withMCP(new MCP("YOUR_URL", Random.generateRandomString(10), 354254))
                //you can do a Random.generateRandomString for the sha1 but the client will be downloaded each restart.
                .build();


        //for more information about the update, join this discord : https://discord.gg/CS5NxapkDU
        final FlowUpdater updater = new FlowUpdater.FlowUpdaterBuilder()
                .withVanillaVersion(vanillaVersion)
                .withLogger(logger)
                .build();
        updater.update(gameDir);
    }

    public static void launch() {
        try {
            NoFramework noFramework = new NoFramework(
                    gameDir,
                    authInfos,
                    GameFolder.FLOW_UPDATER
            );
            noFramework.getAdditionalVmArgs().add("-Xms1G");
            noFramework.getAdditionalVmArgs().add("-Xmx" + getSaver().get("ram") + "G");

            Process p = noFramework.launch("1.8.8", "",NoFramework.ModLoader.VANILLA);
        } catch (Exception e) {
            logger.printStackTrace(e);
        }
    }


    public static void authCrack() {
        /*
         * WARNING: If you want the users to use their own username (entered in a text field, for example),
         * you will need to handle it yourself. In this context, a new username is regenerated every time
         * the app is restarted.
         */
        authInfos = new AuthInfos(Random.generateRandomString(10), Random.generateRandomAccesToken(10),Random.generateRandomUUID());
    }

    public static Path getGameDir() {
        return gameDir;
    }
}
