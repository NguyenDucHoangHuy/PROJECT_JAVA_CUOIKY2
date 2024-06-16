package Controllers;

import DAO.LoginDAO;
import EnCode.Encode;
import View.Login;
import View.ResetPass;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResetPassControllers implements ActionListener {
 public ResetPass view;
 public LoginDAO log;
  public ResetPassControllers(ResetPass view, LoginDAO log) {
      this.view=view;
      this.log=log;
  }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.view.btback) {
            this.view.setVisible(false);
            new Login();
        } else if (this.view.tfusn.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Username must not be blank");
        } else if (this.view.tfnewpass.getText().equals("") || this.view.tfconfpass.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Password must not be blank");
        } else if(this.view.tfnewpass.getText().equals(this.view.tfconfpass.getText())){
            try{
                String pass= Encode.toSHA1(this.view.tfnewpass.getText());
                String cofpass =Encode.toSHA1(this.view.tfconfpass.getText());
                log.ResetPass(pass,cofpass);
                JOptionPane.showMessageDialog(null, "Password has been successfully reset");
                this.view.setVisible(false);
                new Login();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "An error occurred while resetting the password");
                ex.printStackTrace();
            }
        }
    }
}
