package net.otzarri.bookstore.listener;

import net.otzarri.bookstore.db.MongoConnection;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextListener implements ServletContextListener {
    public void contextInitialized(ServletContextEvent sce) {
        MongoConnection conn = MongoConnection.getInstance();
        conn.init();
    }

    public void contextDestroyed(ServletContextEvent sce) {
        MongoConnection conn = MongoConnection.getInstance();
        conn.close();
    }
}