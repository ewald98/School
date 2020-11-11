package ro.vvs.upt;

import ro.vvs.upt.config.Configuration;
import ro.vvs.upt.config.ConfigurationManager;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

public class WebServer {

    public static void main(String[] args) {

        ConfigurationManager.getInstance().loadConfigurationFile("src/main/resources/config.txt");
        Configuration config = ConfigurationManager.getInstance().getCurrentConfig();

        ServerSocket serverSocket = null;

//        System.out.println(System.getProperty("user.dir"));

        try {
            serverSocket = new ServerSocket(config.getPort());
            System.out.println("Connection Socket Created");
            try {
                while (true) {
                    System.out.println("Waiting for Connection");
                    new WebServerThread(serverSocket.accept());
                }
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }
        } catch (IOException e) {
            System.err.println("Could not listen on port: " + config.getPort());
            System.exit(1);
        } finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                System.err.println("Could not close port: " + config.getPort());
                System.exit(1);
            }
        }

        System.out.println("");

    }

}
