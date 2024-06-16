package View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Server {
    private ServerSocket serverSocket;
    private static Server serverInstance;
    private boolean running;
    private List<ClientHandler> clients;

    private Server(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        clients = new ArrayList<>();

    }

    public static synchronized Server getServerInstance(int port) throws IOException {
        if (serverInstance == null) {
            serverInstance = new Server(port);
        }
        return serverInstance;
    }

    public void start() {
        if (!running) {
            running = true;
            System.out.println("Server is running...");
            new Thread(() -> {
                while (running) {
                    try {
                        Socket clientSocket = serverSocket.accept();
                        ClientHandler clientHandler = new ClientHandler(clientSocket,this);
                        clients.add(clientHandler);
                        new Thread(clientHandler).start();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    public void stop() throws IOException {
        running = false;
        if (serverSocket != null && !serverSocket.isClosed()) {
            serverSocket.close();
        }
        for (ClientHandler client : clients) {
            client.disconnect();
        }
    }
    public void broadcastMessage(String message) {
        for (ClientHandler client : clients) {
            client.sendMessage(message);
        }
    }

}