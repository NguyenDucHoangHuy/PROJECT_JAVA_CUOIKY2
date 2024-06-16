package View;

import Controllers.LoginControllers;
import DAO.LoginDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame  {
    JLabel lbbgr, lblogin, lbusn, lbpass;
    public JTextField tfusn;
    public JPasswordField tfpass;
    JPanel pnlogin;
    public JButton btlogin;
    public JButton btsignup;
    public JButton btresetpass;
    LoginControllers controller;
    LoginDAO loginDAO=new LoginDAO();
    ActionListener action= new LoginControllers(this,loginDAO);
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

    private JPasswordField createPass() {
        JPasswordField passwordField = new JPasswordField();
        Font newFont = new Font("Arial", Font.BOLD, 26);
        passwordField.setFont(newFont);
        return passwordField;
    }

    public Login() {
        ////--------------------bg----------------//
        ImageIcon bg = new ImageIcon("src/IMG/pp.png");
        lbbgr = new JLabel(bg);
        lbbgr.setSize(1000, 700);
        lbbgr.setPreferredSize(new Dimension(bg.getIconWidth(), bg.getIconHeight()));
        //----------------------------------------//
        pnlogin = new JPanel();
        pnlogin.setBounds(300, 80, 400, 500);
        pnlogin.setBackground(new Color(0, 0, 0));
        pnlogin.setLayout(null);
        lblogin = createLabel("LOGIN", 26);
        lblogin.setForeground(Color.white);
        lblogin.setBounds(160, 50, 90, 50);
        lbusn = createLabel("Username :", 17);
        lbusn.setForeground(Color.white);
        lbusn.setBounds(20, 130, 98, 30);
        tfusn = createText();
        tfusn.setBounds(20, 160, 360, 30);
        lbpass = createLabel("Password :", 17);
        lbpass.setForeground(Color.white);
        lbpass.setBounds(20, 200, 98, 30);
        tfpass = createPass();
        tfpass.setBounds(20, 230, 360, 30);
        btlogin = new JButton("Login");
        btlogin.setBackground(Color.lightGray);
        btlogin.setBounds(60, 310, 100, 35);
        btlogin.addActionListener(action);
        btsignup = new JButton("Sign up");
        btsignup.setBackground(Color.green);
        btsignup.setBounds(240, 310, 100, 35);
        btsignup.addActionListener(action);
        btresetpass = new JButton("Forget password ?");
        btresetpass.setBorder(null);
        btresetpass.setFont(new Font("Arial", Font.BOLD, 13));
        btresetpass.setBackground(Color.black);
        btresetpass.setForeground(Color.WHITE);
        btresetpass.setBounds(135, 400, 140, 27);
        btresetpass.addActionListener(action);
        ///---------GUI------------//
        this.setVisible(true);
        this.setSize(1000, 700);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);

        //---------------------------//

        ////-------------add-----------//
        pnlogin.add(lblogin);
        pnlogin.add(lbusn);
        pnlogin.add(tfusn);
        pnlogin.add(lbpass);
        pnlogin.add(tfpass);

        pnlogin.add(btlogin);
        pnlogin.add(btsignup);
        pnlogin.add(btresetpass);
        lbbgr.add(pnlogin);
        this.add(lbbgr);
    }

    public static void main(String[] args) {
        new Thread(() -> createAndShowLogin()).start();
    }

    private static void createAndShowLogin() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                Login login = new Login();
                login.setVisible(true);
            }
        });
    }
}

