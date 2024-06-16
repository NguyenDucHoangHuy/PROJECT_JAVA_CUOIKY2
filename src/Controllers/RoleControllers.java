package Controllers;

import DAO.LoginDAO;
import View.Role;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RoleControllers implements ActionListener {
    public Role view;
    public LoginDAO log;
    public RoleControllers(Role view, LoginDAO log) {
        this.view = view;
        this.log = log;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()== this.view.btdelete){
            String username= this.view.tfusn.getText();
            String email= this.view.tfemail.getText();
            if(!username.equals("") && !email.equals("")){
                int response= JOptionPane.showConfirmDialog(null,"Are you sure you want to delete this account?","Confirm",JOptionPane.YES_NO_OPTION);
                if(response==JOptionPane.YES_OPTION){
                    try{
                        if(log.DeleteAccount(username,email)){
                            JOptionPane.showMessageDialog(null,"Account deleted successfully");
                            DefaultTableModel model= (DefaultTableModel) this.view.table.getModel();
                            log.showDataInTable(model);
                            this.view.tfusn.setText("");
                            this.view.tfemail.setText("");
                        }
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to delete account.");
                }
            } else{
                JOptionPane.showMessageDialog(null, "Please enter a valid username or email");
            }
        }
        else  if(e.getSource()== this.view.btupdate){
                String username= this.view.tfusn.getText();
                String email= this.view.tfemail.getText();
                String newRole= this.view.roleComboBox.getSelectedItem().toString();
            if (!username.isEmpty() && !email.isEmpty()) {
                try {
                    if (log.updateRole(username, email, newRole)) {
                        JOptionPane.showMessageDialog(null, "Role updated successfully");
                        DefaultTableModel model = (DefaultTableModel) this.view.table.getModel();
                        log.showDataInTable(model);
                        this.view.tfusn.setText("");
                        this.view.tfemail.setText("");
                    } else {
                        JOptionPane.showMessageDialog(null, "Failed to update role.");
                    }
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Please enter a valid username and email");
            }
        }
    }
}
