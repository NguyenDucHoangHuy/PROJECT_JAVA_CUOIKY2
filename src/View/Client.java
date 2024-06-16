package View;

import javax.swing.*;
import java.io.*;
import java.net.Socket;

public class Client {
    private Socket socket;
    private boolean connected;
    private String roomName;
    HomePage_User view;
    private ObjectOutputStream outputStream;
    private BufferedReader reader;
    private ObjectInputStream inputStream;
    private HomePage_User homePageUser;
    public Client(Socket socket,HomePage_User homePageUser) {
        this.socket = socket;
        this.connected = false;
        this.homePageUser = homePageUser;

        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            inputStream = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void connect(String user) {
        try {
            // Mở luồng vào ra cho việc giao tiếp với máy chủ
            connected = true;
            System.out.println(user + " Connected to server");
            outputStream.writeObject(user); // Gửi username lên server khi kết nối
            outputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void disconnect() {
        try {
            // Đóng kết nối với máy chủ
            if (socket != null) {
                socket.close();
            }
            connected = false;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String receiveMessage() {
        try {
            return (String) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean isConnected() {
        return connected;
    }

    public void sendMessage(String message) throws IOException {
        outputStream.writeObject(message);
        outputStream.flush();
    }

    // Phương thức này sẽ được gọi khi start một Thread mới
    public void start(String user) {
        new Thread(() -> {
            connect(user);
            while (connected) {
                try {
                    System.out.println(user + " vẫn đang kết nối với server");
                    // Đọc tin nhắn từ server
                   String message =" Received message from server: " + receiveMessage();
                    homePageUser.btMes.addActionListener(e -> {
                        JOptionPane.showMessageDialog(null,message);
                    });


                    homePageUser.changeRoomIcon();
                    homePageUser.ReturnRoomIcon();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}