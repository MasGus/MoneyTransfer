package com.revolut.money.transfer;

import com.revolut.money.transfer.controller.AccountController;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import org.apache.log4j.Logger;


public class App {
    private static final Logger logger = Logger.getLogger(App.class);
    public static void main(String[] args) {
        initServer();
    }

    private static void initServer() {
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");

        Server jettyServer = new Server(8080);
        jettyServer.setHandler(context);

        ServletHolder jerseyServlet = context.addServlet(
                org.glassfish.jersey.servlet.ServletContainer.class, "/*");
        jerseyServlet.setInitOrder(0);

        jerseyServlet.setInitParameter(
                "jersey.config.server.provider.classnames",
                AccountController.class.getCanonicalName());

        try {
            jettyServer.start();
            jettyServer.join();
        } catch (Exception e){
            logger.error("Init Server error", e);
        } finally {
            jettyServer.destroy();
        }
    }

}