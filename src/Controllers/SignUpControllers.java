package Controllers;

import DAO.DBConnect;
import DAO.LoginDAO;
import EnCode.Encode;
import View.Login;
import View.SignUp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

public class SignUpControllers implements ActionListener {
    public SignUp view;
    public LoginDAO log;
    String addpass,addconf;
    public SignUpControllers(SignUp view, LoginDAO log) {
        this.view=view;
        this.log=log;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.view.bthaveacc) {
            this.view.setVisible(false);
            new Login();
        } else if (e.getSource() == this.view.btcreateacc) {
            if (this.view.tfemail.getText().equals("") || this.view.tfconf.getText().equals("") ||
                    this.view.tffullname.getText().equals("") || this.view.tfusn.getText().equals("") || this.view.tfpass.getText().equals("") || this.view.tfphone.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Must not be blank!!!");
            } else if (this.view.tfpass.getText().equals(this.view.tfconf.getText())) {
                addpass = Encode.toSHA1(this.view.tfpass.getText());
                addconf = Encode.toSHA1(this.view.tfconf.getText());
                Connection conn;
                try {
                    conn = DBConnect.getConnection();

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                if (conn != null) {
                    try {
                        if (log.AddAccount(Integer.parseInt(this.view.tfphone.getText()), this.view.tffullname.getText(), this.view.tfusn.getText(), addpass, addconf, this.view.tfemail.getText())) {
                            JOptionPane.showMessageDialog(null, "Registration successful.");
                        } else {
                            JOptionPane.showMessageDialog(null, "Registration failed.");
                        }
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Password do not match.");
            }
        }
    }
}
