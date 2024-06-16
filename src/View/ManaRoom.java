package View;

import Controllers.DataSP_DVControllers;
import Controllers.RoleControllers;
import DAO.LoginDAO;
import DAO.RoomDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class ManaRoom extends JFrame  {
    JLabel lbbgr, lbproductmana ,lbexit;
    JPanel pnproduct;
    public JTable table;
    JScrollPane scrollPane;
    private Server server;



    private JLabel createLabel(String a, int n) {
        JLabel label = new JLabel(a);
        Font newFont = new Font("Arial", Font.BOLD, n);
        label.setFont(newFont);
        return label;
    }

    private JTextField createText() {
        JTextField textField = new JTextField();
        Font newFont = new Font("Arial", Font.BOLD, 15);
        textField.setFont(newFont);
        return textField;
    }

    public ManaRoom(Server server) {
        this.server = server;
        ////--------------------bg----------------//
        ImageIcon bg = new ImageIcon("src/IMG/pp.png");
        lbbgr = new JLabel(bg);
        lbbgr.setSize(1000, 600);
        lbbgr.setPreferredSize(new Dimension(bg.getIconWidth(), bg.getIconHeight()));
        //----------------------------------------//
        pnproduct = new JPanel();
        pnproduct.setBounds(150, 80, 700, 400);
        pnproduct.setBackground(new Color(0, 0, 0, 250));
        pnproduct.setLayout(null);
        lbproductmana = createLabel("ROOM MANAGEMENT", 21);
        lbproductmana.setForeground(Color.white);
        lbproductmana.setBounds(210, 50, 300, 50);

        String[] columnNames = {"RoomName", "RoomStatus"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames);


        table = new JTable(model);
        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 120, 600, 180);
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    String selectedRoomName = (String) table.getValueAt(selectedRow, 0);
                    new Bill_request(selectedRoomName,ManaRoom.this,server).displayInfo(selectedRoomName); // Hiển thị thông tin bill_request cho phòng được chọn
                }
            }
        });

        try {
            RoomDAO DAO = new RoomDAO();
            DAO.showTable(model);
        } catch (Exception ex) {
            ex.printStackTrace();
        }



        ImageIcon exitIcon = new ImageIcon("src/IMG/left-arrow.png");
        lbexit = new JLabel(exitIcon);
        lbexit.setBounds(0, 370, 40, 30);

        // Adding a click listener to lbexit
        lbexit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new HomePage_Admin(server); // Exit the application on click
            }
        });

        ///---------GUI------------//
        this.setVisible(true);
        this.setSize(1000, 600);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setResizable(false);

        //---------------------------//

        ////-------------add-----------//

        pnproduct.add(lbexit);
        pnproduct.add(lbproductmana);
        pnproduct.add(scrollPane);
        lbbgr.add(pnproduct);
        this.add(lbbgr);
    }
    public void refreshTableData() {
        try {
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            RoomDAO DAO = new RoomDAO();
            model.setRowCount(0); // Clear existing rows
            DAO.showTable(model);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }





}

