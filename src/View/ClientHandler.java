package View;

import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private Socket clientSocket;
    private ObjectOutputStream outputStream;
    private BufferedReader reader;
    private String username;
    private Server server;
    public ClientHandler(Socket socket, Server server) {
        this.clientSocket = socket;
        this.server = server;
        try {
            outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
            reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            String message;
            while ((message = reader.readLine()) != null) {

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void disconnect() throws IOException {
        if (clientSocket != null && !clientSocket.isClosed()) {
            clientSocket.close();
        }
    }
    public void sendMessage(String message) {
        try {
            // Gửi thông điệp đến client
            outputStream.writeObject(message);
            outputStream.flush();
            System.out.println("Sent message to client: " + message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String getUsername() {
        return username;
    }
}
