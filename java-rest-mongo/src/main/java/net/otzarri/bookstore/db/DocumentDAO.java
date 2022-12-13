package net.otzarri.bookstore.db;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.IndexOptions;
import com.mongodb.client.model.Indexes;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import net.otzarri.bookstore.model.DocumentList;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;

public class DocumentDAO {
    private static MongoCollection<Document> collection;

    public DocumentDAO() {
        collection = MongoConnection.getInstance().getMongoCollection("books");

        IndexOptions indexOptions = new IndexOptions().unique(true);
        collection.createIndex(Indexes.ascending("isbn"), indexOptions);
    }

    public DocumentList find(Document query) {
        FindIterable<Document> cursor = collection.find(query);
        DocumentList documentList = new DocumentList((ArrayList<Document>) collection.find(query).into(new ArrayList<Document>()), "books");
        if (documentList.size() == 0) { throw new NullPointerException("Find Books: Not found"); }

        return documentList;
    }

    public void insert(Document document) {
        collection.insertOne(document);
    }

    public void insert(ArrayList<Document> documentList) {
        collection.insertMany(documentList);
    }

    public void update(Document query, Document document){
        UpdateResult result = collection.replaceOne(query, document);
        if (result.getMatchedCount() == 0) { throw new NullPointerException("Update Book: Not found"); }
    }

    public void delete(Document query) {
        DeleteResult result = collection.deleteOne(query);
        if (result.getDeletedCount() == 0) { throw new NullPointerException("Delete Book: Not found"); }
    }

    public Document buildQuery(String id, String isbn) {
        Document query = new Document();
        if (id != null && !ObjectId.isValid(id)) { query.append("_id", new ObjectId()); }
        if (id != null && ObjectId.isValid(id)) { query.append("_id", new ObjectId(id)); }
        if (isbn != null) { query.append("isbn", isbn); }
        return query;
    }
}