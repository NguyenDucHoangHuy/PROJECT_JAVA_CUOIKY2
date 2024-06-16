package View;

import Controllers.RoleControllers;
import DAO.LoginDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class Role extends JFrame  {
    JLabel lbbgr, lbrolemana,lbrole,lbhp, lbusn, lbemail;
    JPanel pnrole;
    public JTable table;
    public JTextField tfusn;
    public JTextField tfemail;
    JScrollPane scrollPane;
    public JComboBox<String> roleComboBox;
    LoginDAO loginDAO= new LoginDAO();
    RoleControllers controllers;
    ActionListener action= new RoleControllers(this,loginDAO);

    public JButton btupdate;
    public JButton btdelete;

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

    public Role(Server server) {
        this.server = server;

        ////--------------------bg----------------//
        ImageIcon bg = new ImageIcon("src/IMG/pp.png");
        lbbgr = new JLabel(bg);
        lbbgr.setSize(1000, 700);
        lbbgr.setPreferredSize(new Dimension(bg.getIconWidth(), bg.getIconHeight()));
        //----------------------------------------//
        pnrole = new JPanel();
        pnrole.setBounds(150, 80, 700, 500);
        pnrole.setBackground(new Color(0, 0, 0, 250));
        pnrole.setLayout(null);
        lbrolemana = createLabel("ROLE MANAGEMENT", 21);
        lbrolemana.setForeground(Color.white);
        lbrolemana.setBounds(240, 50, 250, 50);

        String[] columnNames = {"Username", "Email", "Role"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames);


          table = new JTable(model);
          scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 120, 600, 180);

        lbusn= createLabel("Username", 15);
        lbusn.setForeground(Color.white);
        lbusn.setBounds(20,335,120,35);

        tfusn= createText();
        tfusn.setForeground(Color.black);
        tfusn.setBounds(100,340,150,28);

        lbemail = createLabel("Email", 15);
        lbemail.setForeground(Color.white);
        lbemail.setBounds(270,335,120,35);

        tfemail= createText();
        tfemail.setForeground(Color.black);
        tfemail.setBounds(320,340,180,28);

        lbrole = createLabel("Role", 15);
        lbrole.setForeground(Color.white);
        lbrole.setBounds(520,335,120,35);

        String[] roles = {"Admin", "User"};
        roleComboBox = new JComboBox<>(roles);
        roleComboBox.setBounds(570, 340, 120, 28);

        btupdate = new JButton("Update");
        btupdate.setBackground(Color.lightGray);
        btupdate.setBounds(180, 400, 100, 35);
        btupdate.addActionListener(action);

        btdelete = new JButton("Delete");
        btdelete.setBackground(Color.lightGray);
        btdelete.setBounds(390, 400, 100, 35);
        btdelete.addActionListener(action);


        lbhp = createLabel("HomePage ->", 15);
        lbhp.setForeground(Color.green);
        lbhp.setBounds(590,463,120,35);
        lbhp.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();  // Đóng cửa sổ hiện tại
                new HomePage_Admin(server);
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
        pnrole.add(lbrole);
        pnrole.add(lbusn);
        pnrole.add(tfusn);
        pnrole.add(tfemail);
        pnrole.add(lbemail);
        pnrole.add(lbrolemana);
        pnrole.add(btupdate);
        pnrole.add(btdelete);
        pnrole.add(scrollPane);
        pnrole.add(roleComboBox);
        pnrole.add(lbhp);
        lbbgr.add(pnrole);
        this.add(lbbgr);

        try {
            LoginDAO loginDAO = new LoginDAO();
            loginDAO.showDataInTable(model);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = table.getSelectedRow();
                if(selectedRow >=0) {
                    tfusn.setText(model.getValueAt(selectedRow,0).toString());
                    tfemail.setText(model.getValueAt(selectedRow,1).toString());
                    roleComboBox.setSelectedItem(model.getValueAt(selectedRow,2).toString());
                }
            }
        });
    }


}

