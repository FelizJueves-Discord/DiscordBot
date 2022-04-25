import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Main extends ReceivedMessage {
    private static final String filename = "./BOT_TOKEN.txt";

    public static void main(String[] args) throws LoginException {
        //read token in "BOT_TOKEN" text file
        final String token;
        final File myObj = new File(filename);

        try (Scanner myReader = new Scanner(myObj)) {
            token = myReader.nextLine();
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Something went wrong loading token");
        }

        // We only need 2 intents in this bot. We only respond to messages in guilds and private channels.
        // All other events will be disabled.
        final JDABuilder jdaBuilder = JDABuilder.createLight(token, GatewayIntent.GUILD_MESSAGES, GatewayIntent.DIRECT_MESSAGES)
                .addEventListeners(new Main());
        if (LocalDateTime.now().getDayOfWeek().equals(DayOfWeek.THURSDAY)) {
            jdaBuilder.setActivity(Activity.playing("Feliz jueves \uD83E\uDD73"))
                    .build();
        } else {
            jdaBuilder.setActivity(Activity.playing("No es jueves ☹️"))
                    .build();
        }
    }
}
