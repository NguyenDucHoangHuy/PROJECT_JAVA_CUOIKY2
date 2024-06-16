package View;

import Controllers.LoginControllers;
import Controllers.ResetPassControllers;
import DAO.LoginDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResetPass extends JFrame  {
    JLabel lbbgr, lbresetpass, lbusn, lbnewpass, lbconfpass;
    public JTextField tfusn;
    public JPasswordField tfnewpass;
    public JPasswordField tfconfpass;
    JPanel pnresetpass;
    public JButton btback;
    JButton btreset;
    ResetPassControllers controller;
    LoginDAO loginDAO=new LoginDAO();
    ActionListener action= new ResetPassControllers(this,loginDAO);
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

    public ResetPass() {
        ////--------------------bg----------------//
        ImageIcon bg = new ImageIcon("src/IMG/pp.png");
        lbbgr = new JLabel(bg);
        lbbgr.setSize(1000, 700);
        lbbgr.setPreferredSize(new Dimension(bg.getIconWidth(), bg.getIconHeight()));
        //----------------------------------------//
        pnresetpass = new JPanel();
        pnresetpass.setBounds(300, 80, 400, 500);
        pnresetpass.setBackground(new Color(0, 0, 0));
        pnresetpass.setLayout(null);
        lbresetpass = createLabel("RESET PASSWORD", 26);
        lbresetpass.setForeground(Color.white);
        lbresetpass.setBounds(82, 50, 260, 50);
        lbusn = createLabel("Username :", 17);
        lbusn.setForeground(Color.white);
        lbusn.setBounds(20, 130, 98, 30);
        tfusn = createText();
        tfusn.setBounds(20, 160, 360, 30);
        lbnewpass = createLabel("New password :", 17);
        lbnewpass.setForeground(Color.white);
        lbnewpass.setBounds(20, 200, 128, 30);
        tfnewpass = createPass();
        tfnewpass.setBounds(20, 230, 360, 30);
        lbconfpass = createLabel("Confirm password :", 17);
        lbconfpass.setForeground(Color.white);
        lbconfpass.setBounds(20, 270, 160, 30);
        tfconfpass = createPass();
        tfconfpass.setBounds(20, 300, 360, 30);
        btback = new JButton("Back to Login");
        btback.setBorder(null);
        btback.setBackground(Color.black);
        btback.setForeground(Color.white);
        btback.setBounds(140, 410, 120, 17);
        btback.addActionListener(action);
//        btsignup = new JButton("Sign up");
//        btsignup.setBackground(Color.green);
//        btsignup.setBounds(240,320,100,35);
        btreset = new JButton("Reset");
        btreset.setFont(new Font("Arial", Font.BOLD, 17));
        btreset.setForeground(Color.black);
        btreset.setBackground(Color.cyan);
        btreset.setBounds(140, 370, 120, 30);
        btreset.addActionListener(action);
        ///---------GUI------------//
        this.setVisible(true);
        this.setSize(1000, 700);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        //---------------------------//

        ////-------------add-----------//
        pnresetpass.add(lbresetpass);
        pnresetpass.add(lbusn);
        pnresetpass.add(tfusn);
        pnresetpass.add(lbnewpass);
        pnresetpass.add(tfnewpass);
        pnresetpass.add(lbconfpass);
        pnresetpass.add(tfconfpass);
        pnresetpass.add(btback);
        pnresetpass.add(btreset);
        lbbgr.add(pnresetpass);
        this.add(lbbgr);
    }

    public static void main(String[] args) {
        new ResetPass();
    }
}