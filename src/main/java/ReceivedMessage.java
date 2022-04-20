import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

public class ReceivedMessage extends ListenerAdapter {
    private int count = 0;

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        final String prefix = "!";
        final Message msg = event.getMessage();

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
                    channel.sendMessage("Es oficialmente jueves. " + author.getAsMention() + " nos desea un feliz jueves \uD83E\uDD73"
                            + "\n\nIt's officially Thursday. " + author.getName() + " wishes us merry Thursday \uD83E\uDD73"
                            + "\n```Feliz jueves sent by " + author.getName() + ", " + count + "```").queue();
                }
                break;
        }
    }
}