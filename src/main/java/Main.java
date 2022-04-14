import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.exceptions.RateLimitedException;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.requests.RestAction;
import net.dv8tion.jda.api.requests.restaction.MessageAction;
import net.dv8tion.jda.api.utils.AttachmentOption;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Time;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;

public class Main extends ListenerAdapter {
    int count = 0;

    public static void main(String[] args) throws LoginException {
        if (args.length < 1) {
            System.out.println("You have to provide a token as first argument!");
            System.exit(1);
        }
        // args[0] should be the token
        // We only need 2 intents in this bot. We only respond to messages in guilds and private channels.
        // All other events will be disabled.
        JDABuilder.createLight(args[0], GatewayIntent.GUILD_MESSAGES, GatewayIntent.DIRECT_MESSAGES)
                .addEventListeners(new Main())
                .setActivity(Activity.playing("Type !ping"))
                .build();
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        final String prefix = "!";
        Message msg = event.getMessage();

        String raw = msg.getContentRaw();
        MessageChannel channel = event.getChannel();
        switch (raw) {
            case prefix + "ping":
                long time = System.currentTimeMillis();
//                RestAction.getDefaultTimeout();
                channel.sendMessageFormat("Pong! `%d ms`", System.currentTimeMillis() - time).queue();
                break;
            case "allo":
                channel.sendMessage("bye").queue();
                break;
            case "https://dbx10.thigh-high.club/npni9jxzk2407yywbuwi.mp4":
                if (LocalDateTime.now().getDayOfWeek().equals(DayOfWeek.THURSDAY)) {
                    count++;
                    User author = event.getAuthor();
                    channel.sendMessage("Es oficialmente Jueves. " + author.getAsMention() + " nos desea un feliz jueves \uD83E\uDD73 \n\n"
                            + "It's officially Thursday. " + author.getName() + " wishes us merry thursday \uD83E\uDD73 \n" +
                            "```Feliz Jueves sent by " + author.getName() + ", " + count + "```").queue();
                }
                break;
        }
    }
}
