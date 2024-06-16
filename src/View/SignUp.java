package View;


import Controllers.LoginControllers;
import Controllers.SignUpControllers;
import DAO.LoginDAO;
import Model.Account;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SignUp  extends JFrame {
    public javax.swing.JOptionPane JOptionPane;
    JLabel lbbgr,lbsignup,lbusn,lbpass,lbconf,lbfullname,lbphone,lbemail;
    public JTextField tfusn;
    public JTextField tffullname;
    public JTextField tfemail;
    public JTextField tfphone;
    JPanel pnsignup;
    public JPasswordField tfpass;
    public JPasswordField tfconf;
    public JButton btcreateacc;
    public JButton bthaveacc;
    String addpass,addconf;
    int addphone;
    SignUpControllers controllers;
    LoginDAO loginDAO=new LoginDAO();
    ActionListener action= new SignUpControllers(this,loginDAO);
    private JLabel createLabel(String a,int n)
    {
        JLabel label = new JLabel(a);
        Font newFont = new Font("Arial", Font.BOLD, n);
        label.setFont(newFont);
        return label;
    }
    private JTextField createText()
    {
        JTextField textField = new JTextField();
        Font newFont = new Font("Arial", Font.BOLD, 15);
        textField.setFont(newFont);
        return textField;
    }
    private JPasswordField createPass()
    {
        JPasswordField passwordField = new JPasswordField();
        Font newFont = new Font("Arial", Font.BOLD, 26);
        passwordField.setFont(newFont);
        return passwordField;
    }
    public SignUp() {
        ////--------------------bg----------------//
        ImageIcon bg=new ImageIcon("src/IMG/pp.png");
        lbbgr = new JLabel(bg);
        lbbgr.setSize(1000,700);
        lbbgr.setPreferredSize(new Dimension(bg.getIconWidth(), bg.getIconHeight()));
        //----------------------------------------//
        pnsignup =new JPanel();
        pnsignup.setBounds(150,90,700,500);
        pnsignup.setBackground(new Color(0, 0, 0));
        pnsignup.setLayout(null);
        lbsignup=createLabel("SIGN UP",26);
        lbsignup.setForeground(Color.white);
        lbsignup.setBounds(290,50,120,50);
        lbfullname = createLabel("Full name :",17);
        lbfullname.setForeground(Color.white);
        lbfullname.setBounds(60,130,100,30);
        tffullname = createText();
        tffullname.setBounds(60,170,260,30);
        lbusn =createLabel("Username :",17);
        lbusn.setForeground(Color.white);
        lbusn.setBounds(380,130,98,30);
        tfusn = createText();
        tfusn.setBounds(380,170,260,30);
        lbpass=createLabel("Password :",17);
        lbpass.setForeground(Color.white);
        lbpass.setBounds(380,210,98,30);
        tfpass = createPass();
        tfpass.setBounds(380,240,260,30);
        lbconf = createLabel("Confirm password :",17);
        lbconf.setForeground(Color.white);
        lbconf.setBounds(380,280,160,30);
        tfconf = createPass();
        tfconf.setBounds(380,310,260,30);
        lbphone = createLabel("Phone :",17);
        lbphone.setForeground(Color.white);
        lbphone.setBounds(60,210,98,30);
        tfphone = createText();
        tfphone.setBounds(60,240,260,30);
        tfphone.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke) {
                char ch = ke.getKeyChar();
                if (!(Character.isDigit(ch)|| ch == KeyEvent.VK_BACK_SPACE || ch == KeyEvent.VK_DELETE || ch == KeyEvent.VK_LEFT || ch == KeyEvent.VK_RIGHT)) {
                    JOptionPane.showMessageDialog(null, "asgjahfkjashfahsfjkaskhf");
                }
            }
        });
        lbemail = createLabel("Email :",17);
        lbemail.setForeground(Color.white);
        lbemail.setBounds(60,280,98,30);
        tfemail = createText();
        tfemail.setBounds(60,310,260,30);
        btcreateacc = new JButton("Create Account");
        btcreateacc.setBackground(Color.lightGray);
        btcreateacc.setBounds(200,380,300,35);
        btcreateacc.addActionListener(action);
        bthaveacc = new JButton("I already have an account !!!");
        bthaveacc.setForeground(Color.white);
        bthaveacc.setBackground(Color.black);
        bthaveacc.setBorder(null);
        bthaveacc.setBounds(200,430,300,20);
        bthaveacc.addActionListener(action);
        ///---------GUI------------//
        this.setVisible(true);
        this.setSize(1000,700);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        //---------------------------//

        ////-------------add-----------//
        pnsignup.add(lbsignup);
        pnsignup.add(lbfullname);
        pnsignup.add(tffullname);
        pnsignup.add(lbusn);
        pnsignup.add(tfusn);
        pnsignup.add(lbpass);
        pnsignup.add(tfpass);
        pnsignup.add(lbconf);
        pnsignup.add(tfconf);
        pnsignup.add(lbphone);
        pnsignup.add(tfphone);
        pnsignup.add(lbemail);
        pnsignup.add(tfemail);
        pnsignup.add(btcreateacc);
        pnsignup.add(bthaveacc);
//        pnlogin.add(btlogin);
//        pnlogin.add(btsignup);
//        pnlogin.add(btresetpass);
        lbbgr.add(pnsignup);
        this.add(lbbgr);
    }

    public static void main(String[] args) {
        new SignUp();
    }
}