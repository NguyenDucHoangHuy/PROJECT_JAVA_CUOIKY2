package View;

import javax.swing.*;
import java.awt.*;

public class ChangeRoom extends JDialog{
    private JPanel pnmain,pntitle;
    private JLabel lbtitle,lbpht,lbpcd;
    private JButton btchange;
    String[] listRoom = {"P.101", "P.103", "P.104", "P.105","P.201", "P.201", "P.203", "P.204", "P.205","P.301", "P.301", "P.303", "P.304", "P.401","P.402", "P.403"};
    public  ChangeRoom(JFrame parent, String roomName,float cost){
        super(parent, "Đặt phòng - " + roomName, true);
        Font newFont = new Font("Arial", Font.PLAIN, 15);
        ImageIcon imgChange = new ImageIcon("src/IMG/Change.png");
        pnmain = new JPanel();
        pnmain.setSize(500,270);
        pnmain.setLayout(null);
        pntitle = new JPanel();
        pntitle.setBounds(0,0,600,40);
        pntitle.setLayout(null);
        pntitle.setBackground(new Color(0x989898));
        lbtitle = new JLabel("Chọn phòng chuyển đến");
        lbtitle.setBounds(20,0,600,40);
        lbtitle.setFont(newFont);
        pntitle.add(lbtitle);
        pnmain.add(pntitle);
        lbpht = new JLabel("Phòng hiện tại :");
        lbpht.setBounds(60,55,90,20);
        JLabel label = new JLabel("Phòng " + roomName + " - Giá : " + cost);
        label.setFont(newFont);
        label.setBounds(180, 50, 250, 30);
        lbpcd = new JLabel("Phòng chuyển đến");
        lbpcd.setBounds(40,95,110,20);
        JComboBox cb = new JComboBox(listRoom);
        cb.setBounds(180, 95, 250, 20);

        btchange = new JButton("Đổi phòng");
        btchange.setIcon(imgChange);
        btchange.setBounds(180,140,120,50);
        pnmain.add(lbpht);
        pnmain.add(lbpcd);
        pnmain.add(label);
        pnmain.add(cb);
        pnmain.add(btchange);
        this.setSize(500,280);
        this.setTitle("Đổi phòng");
        this.setLocationRelativeTo(null);
        this.add(pnmain);


    }
}

