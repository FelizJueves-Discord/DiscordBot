import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ReceivedMessage extends ListenerAdapter {
    private int count = 0;

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        final String prefix = "!";
        final Message msg = event.getMessage();
        ArrayList<String> HelpList = FillHelpList();

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
            case prefix + "help":
                String liste = "```";
                for (String chose : HelpList) {
                    liste += chose + "\n\n";
                }
                liste+="```";
                channel.sendMessage(liste).queue();
                break;
        }
    }

    public ArrayList<String> FillHelpList(){
        ArrayList<String> stringList = new ArrayList<>();
        stringList.add("!ping : sends back pong! and the time needed to respond");
        stringList.add("allo : sends back 'bye'");
        stringList.add("https://dbx10.thigh-high.club/npni9jxzk2407yywbuwi.mp4 : sends your count");
        return stringList;
    }
}