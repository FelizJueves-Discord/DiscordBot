import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class DB {
    public static void main(String[] args) {
        MongoClient client = MongoClients.create("mongodb+srv://domidom77:GOoLicc0LOOkk3AG@cluster0.ytiyb.mongodb.net/DiscordBot?retryWrites=true&w=majority");

        MongoDatabase db = client.getDatabase("DiscordBot");

        MongoCollection<Document> collection = db.getCollection("Discord");

        Document doc = new Document("_id", "1").append("name", "allo");

        collection.insertOne(doc);
    }
}

