import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.logging.Filter;


public class mongodb {
    public static void main(String args[]){

        String uri = "mongodb+srv://admin:admin@mongodb-k8sev.mongodb.net/admin";
        MongoClientURI clientURI = new MongoClientURI(uri);
        MongoClient mongoClient = new MongoClient(clientURI);


        MongoDatabase mongoDatabase = mongoClient.getDatabase("MongoDB");
        MongoCollection collection = mongoDatabase.getCollection("test");

        System.out.println("Database connected");

        Block<Document> printBlock= document -> System.out.println(document.toJson());

        collection.aggregate(
                Arrays.asList(
                        Aggregates.match(Filters.eq("Age", 26)),
                        Aggregates.group("$Race",Accumulators.sum("count",1))
                )
        ).forEach(printBlock);

        System.out.println("Collection Aggregated");






    }
}
