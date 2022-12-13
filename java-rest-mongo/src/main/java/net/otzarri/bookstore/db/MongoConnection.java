package net.otzarri.bookstore.db;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Properties;

public class MongoConnection {
    private static final Logger logger = Logger.getLogger(MongoConnection.class);
    private static String database;
    private static String uri;
    private static MongoConnection instance = new MongoConnection();
    private static MongoClient mongoClient = null;
    private static MongoDatabase mongoDatabase = null;

    public static MongoConnection getInstance() {
        return instance;
    }

    public MongoClient getMongoClient() throws RuntimeException {
        if (mongoClient == null) {
            try {
                MongoClientOptions.Builder options = MongoClientOptions.builder()
                        .connectionsPerHost(4)
                        .maxConnectionIdleTime((60 * 1_000))
                        .maxConnectionLifeTime((120 * 1_000));

                logger.info("Connecting to MongoDB");
                mongoClient = new MongoClient(new MongoClientURI(uri, options));
            } catch (Exception e) {
                logger.error("Error connecting to MongoDB", e);
            }
        }
        return mongoClient;
    }

    public MongoDatabase getMongoDatabase() {
        if (mongoDatabase == null) {
            try {
                mongoDatabase = mongoClient.getDatabase(database);
            } catch (Exception e) {
                logger.error("Error retrieving database: " + database, e);
            }
        }
        logger.debug("Retrieving database: " + database);
        return mongoDatabase;
    }

    public MongoCollection getMongoCollection(String collectionName) {
        MongoCollection collection = null;

        if (mongoDatabase == null) {
            getMongoDatabase();
        }

        try {
            collection = mongoDatabase.getCollection(collectionName);
        } catch (Exception e) {
            logger.error("Error retrieving collection: " + collectionName, e);
        }

        logger.debug("Retrieving collection: " + collectionName);
        return collection;
    }

    public void init() {
        logger.debug("Starting connection process with MongoDB");
        loadProps();
        getMongoClient();
    }

    public void close() {
        logger.info("Closing connection to MongoDB");
        if (mongoClient != null) {
            try {
                mongoClient.close();
                logger.debug("Setting client and database objects as null");
                mongoClient = null;
                mongoDatabase = null;
            } catch (Exception e) {
                logger.error("Error closing connection to MongoDB", e);
            }
        } else {
            logger.warn("Error closing connection to MongoDB because mongo object was null");
        }
    }

    private void loadProps() {
        try {
            Properties props = new Properties();
            props.load(MongoConnection.class.getClassLoader().getResourceAsStream("/application.properties"));

            database = props.getProperty("database");
            uri = String.format("%s://%s:%s@%s/%s",
                    props.getProperty("protocol"),
                    props.getProperty("username"),
                    props.getProperty("password"),
                    props.getProperty("hostname"),
                    props.getProperty("database"));
        } catch (IOException e) {
            logger.error("Error loading properties file");
        }
    }
}