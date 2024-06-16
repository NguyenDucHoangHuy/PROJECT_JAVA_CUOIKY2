package View;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

public class ClientManagement {
    private static ClientManagement instance;
    private final List<Socket> connections = new ArrayList<>();

    public ClientManagement() {}

    public static synchronized ClientManagement getInstance() {
        if (instance == null) {
            instance = new ClientManagement();
        }
        return instance;
    }

    public synchronized Socket connect(String host, int port) throws IOException {
        Socket socket = new Socket(host, port);
        connections.add(socket);
        return socket;
    }

    public synchronized void disconnect(Socket socket) throws IOException {
        if (socket != null && !socket.isClosed()) {
            socket.close();
            connections.remove(socket);
        }
    }

    public synchronized void disconnectAll() throws IOException {
        for (Socket socket : connections) {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        }
        connections.clear();
    }
}