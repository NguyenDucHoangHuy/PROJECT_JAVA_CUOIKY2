package Controllers;

import DAO.DBConnect;
import DAO.LoginDAO;
import DAO.SP_DV_DAO;
import View.DataSP_DV;
import View.Role;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

public class DataSP_DVControllers implements ActionListener {
    public DataSP_DV view;
    public SP_DV_DAO dao;
    public DataSP_DVControllers(DataSP_DV view , SP_DV_DAO dao) {
        this.view = view;
        this.dao = dao;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()== this.view.btadd){
            if (this.view.tften.getText().equals("") || this.view.tfgia.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Must not be blank!!!");
            } else {
                Connection conn;
                try {
                    conn = DBConnect.getConnection();

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                if (conn != null) {
                    try {
                        float gia = Float.parseFloat(this.view.tfgia.getText());
                        boolean result = dao.AddProduct(this.view.tften.getText(), gia);
                        if (result) {
                            JOptionPane.showMessageDialog(null, "Add product successful.");
                            DefaultTableModel model = (DefaultTableModel) this.view.table.getModel();
                            dao.showDataInTable(model);
                        } else {
                            JOptionPane.showMessageDialog(null, "Add product failed.");
                        }
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        } else

        if(e.getSource()== this.view.btdelete){
                String ten= this.view.tften.getText();
                if(!ten.equals("")){
                    int response= JOptionPane.showConfirmDialog(null,"Are you sure you want to delete this product?","Confirm",JOptionPane.YES_NO_OPTION);
                    if(response==JOptionPane.YES_OPTION){
                        try{
                            if(dao.DeleteProduct(ten)){
                                JOptionPane.showMessageDialog(null,"Product deleted successfully");
                                DefaultTableModel model= (DefaultTableModel) this.view.table.getModel();
                                dao.showDataInTable(model);
                                this.view.tften.setText("");
                                this.view.tfgia.setText("");
                            }
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Failed to delete product.");
                    }
                } else{
                    JOptionPane.showMessageDialog(null, "Please enter a valid name product");
                }

        } else
        if (e.getSource()== this.view.btupdate){
            String ten= this.view.tften.getText();
            float gia= Float.parseFloat(this.view.tfgia.getText());
            if (!ten.isEmpty()) {
                try {
                    if (dao.updateProduct(gia,ten)) {
                        JOptionPane.showMessageDialog(null, "Product updated successfully");
                        DefaultTableModel model = (DefaultTableModel) this.view.table.getModel();
                        dao.showDataInTable(model);
                        this.view.tften.setText("");
                        this.view.tfgia.setText("");
                    } else {
                        JOptionPane.showMessageDialog(null, "Failed to update product.");
                    }
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Please enter a valid name product");
            }
        }
    }
}
