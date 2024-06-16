package View;

import Controllers.DataSP_DVControllers;
import Controllers.RoleControllers;
import DAO.LoginDAO;
import DAO.SP_DV_DAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class DataSP_DV extends JFrame  {
    JLabel lbbgr, lbproductmana, lbten, lbgia,lbexit;
    JPanel pnproduct;
    public JTable table;
    public JTextField tften;
    public JTextField tfgia;
    JScrollPane scrollPane;
    public JButton btupdate,btadd;
    public JButton btdelete;
    private Server server;

    SP_DV_DAO dao= new SP_DV_DAO();
    DataSP_DVControllers controllers;
    ActionListener action= new DataSP_DVControllers(this, dao);

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

    public DataSP_DV(Server server) {
        this.server = server;
        ////--------------------bg----------------//
        ImageIcon bg = new ImageIcon("src/IMG/pp.png");
        lbbgr = new JLabel(bg);
        lbbgr.setSize(1000, 700);
        lbbgr.setPreferredSize(new Dimension(bg.getIconWidth(), bg.getIconHeight()));
        //----------------------------------------//
        pnproduct = new JPanel();
        pnproduct.setBounds(150, 80, 700, 500);
        pnproduct.setBackground(new Color(0, 0, 0, 250));
        pnproduct.setLayout(null);
        lbproductmana = createLabel("PRODUCT MANAGEMENT", 21);
        lbproductmana.setForeground(Color.white);
        lbproductmana.setBounds(210, 50, 300, 50);

        String[] columnNames = {"SP-DV", "ĐƠN GIÁ"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames);


        table = new JTable(model);
        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 120, 600, 180);

        lbten= createLabel("Tên SP-DV ", 15);
        lbten.setForeground(Color.white);
        lbten.setBounds(70,335,120,35);

        tften= createText();
        tften.setForeground(Color.black);
        tften.setBounds(160,340,170,28);

        lbgia = createLabel("Đơn Giá", 15);
        lbgia.setForeground(Color.white);
        lbgia.setBounds(370,335,120,35);

        tfgia= createText();
        tfgia.setForeground(Color.black);
        tfgia.setBounds(450,340,170,28);


        btadd = new JButton("Add");
        btadd.setBackground(Color.lightGray);
        btadd.setBounds(130, 400, 100, 35);
        btadd.addActionListener(action);

        btupdate = new JButton("Update");
        btupdate.setBackground(Color.lightGray);
        btupdate.setBounds(300, 400, 100, 35);
        btupdate.addActionListener(action);

        btdelete = new JButton("Delete");
        btdelete.setBackground(Color.lightGray);
        btdelete.setBounds(470, 400, 100, 35);
        btdelete.addActionListener(action);

        ImageIcon exitIcon = new ImageIcon("src/IMG/left-arrow.png");
        lbexit = new JLabel(exitIcon);
        lbexit.setBounds(0, 470, 40, 30);

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
        this.setSize(1000, 700);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        //---------------------------//

        ////-------------add-----------//
        pnproduct.add(lbten);
        pnproduct.add(tften);
        pnproduct.add(tfgia);
        pnproduct.add(lbexit);
        pnproduct.add(lbgia);
        pnproduct.add(lbproductmana);
        pnproduct.add(btupdate);
        pnproduct.add(btdelete);
        pnproduct.add(btadd);
        pnproduct.add(scrollPane);
        lbbgr.add(pnproduct);
        this.add(lbbgr);
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = table.getSelectedRow();
                if(selectedRow >=0) {
                    tften.setText(model.getValueAt(selectedRow,0).toString());
                    tfgia.setText(model.getValueAt(selectedRow,1).toString());
                }
            }
        });
        try{
            SP_DV_DAO dao= new SP_DV_DAO();
            dao.showDataInTable(model);
        } catch (Exception e){
            e.printStackTrace();
        }

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = table.getSelectedRow();
                if(selectedRow >=0) {
                    tften.setText(model.getValueAt(selectedRow,0).toString());
                    tfgia.setText(model.getValueAt(selectedRow,1).toString());
                }
            }
        });

    }



}

