package Controllers;

import DAO.LoginDAO;
import EnCode.Encode;
import View.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;

public class LoginControllers implements ActionListener {
    private Login view;
    private LoginDAO log;

    public LoginControllers(Login view, LoginDAO log) {
        this.view = view;
        this.log = log;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.view.btsignup) {
            new SignUp().setVisible(true);
        } else if (e.getSource() == this.view.btresetpass) {
            new ResetPass().setVisible(true);
        } else if (e.getSource() == this.view.btlogin) {
            if (this.view.tfusn.getText().equals("") || this.view.tfpass.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Must not be blank.");
            } else {
                String namecheck = this.view.tfusn.getText();
                String passcheck = Encode.toSHA1(this.view.tfpass.getText());
                try {
                    String role = log.getRole(namecheck);
                    if (log.LoginCheck(namecheck, passcheck)) {
                        if (role.equals("Admin")) {
                            this.view.dispose(); // Close the login window for Admin
                                try {
                                    Server server = Server.getServerInstance(4000);
                                    server.start();
                                        new HomePage_Admin(server).setVisible(true);
                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                }

                        } else if (role.equals("User")) {
                            this.view.dispose(); // Close the login window for User
                            new Thread(() -> {
                                try {
                                    Socket socket = ClientManagement.getInstance().connect("localhost", 4000);
                                    HomePage_User homepage = new HomePage_User(namecheck);
                                    Client client = new Client(socket,homepage);
                                    client.start(namecheck);
                                    homepage.setLoggedInUser(namecheck);
                                    homepage.setVisible(true);
                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                }
                            }).start();
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Login failed.");
                    }
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }
}