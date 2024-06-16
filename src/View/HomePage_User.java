
package View;

import DAO.RoomDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

public class HomePage_User extends JFrame {
    JButton btExit, btMes;
    JLabel lbExit, lbMes, lbName, lbAllGroup,lbuser;
    JTextField tfuser;
    JPanel pnRoom, pnAllGroup, pnLine;
    static BookingRoom bookingRoom;

        private boolean bookingSuccess = false;
        private String username;

    private JPanel createPanel(String title) {
        JPanel panel = new JPanel();
        JLabel label = new JLabel(title);
        panel.setBackground(new Color(104, 151, 153));
        panel.add(label);
        panel.setLayout(null);
        Font newFont = new Font("Arial", Font.BOLD, 15);
        label.setFont(newFont);
        label.setBounds(20, 6, 90, 20);
        return panel;
    }


    private static JPopupMenu createPopupMenu(JFrame parent, String roomName, float cost) {
        HomePage_User homePage = (HomePage_User) parent;
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem bookRoom = new JMenuItem("Đặt phòng");
        JMenuItem switchRoom = new JMenuItem("Chuyển phòng");

        popupMenu.add(bookRoom);

        // Add action listeners for each menu item
        bookRoom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new BookingRoom(homePage, parent, roomName, cost).setVisible(true);
            }
        });


        return popupMenu;
    }


    private static JPanel createRoom(JFrame parent, String roomName, int x, int y, float cost) {

        JPanel panel = new JPanel();
        JButton button = new JButton();
        JLabel label = new JLabel(roomName);
        ImageIcon imgRoom = new ImageIcon("src/IMG/room.png");
        panel.setLayout(null);
        panel.setBackground(Color.white);
        panel.setBounds(x, y, 64, 90);
        button.setBounds(0, 0, 64, 60);
        button.setBackground(Color.white);
        button.setBorder(null);
        button.setIcon(imgRoom);
        Font newFont = new Font("Arial", Font.BOLD, 15);
        label.setFont(newFont);
        label.setBounds(11, 70, 60, 20);
        panel.add(button);
        panel.add(label);
        panel.setName(roomName);

        JPopupMenu popupMenu = createPopupMenu(parent, roomName,cost);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                popupMenu.show(button, button.getWidth() / 2, button.getHeight() / 2);
            }
        });

        return panel;
    }

    public HomePage_User(String username) {
        this.username = username;

        Font newFont = new Font("Arial", Font.PLAIN, 15);
        Font allGroupsFont = new Font("Arial", Font.BOLD, 15);
        Font nameFont = new Font("Arial", Font.BOLD, 32);
        ImageIcon imgExit = new ImageIcon("src/IMG/EXIT.png");
        ImageIcon imgMes = new ImageIcon("src/IMG/MES.png");

        lbName = new JLabel(" FAIRY HOTEL");
        lbName.setFont(nameFont);
        lbName.setBounds(490, 40, 280, 50);

        btExit = new JButton();
        btExit.setIcon(imgExit);
        btExit.setBounds(30, 30, 48, 48);
        btExit.setBorder(null);
        btExit.setBackground(new Color(238, 238, 238));
        btExit.addActionListener(e ->  {
            int choice = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn thoát chương trình không?", "Xác nhận thoát", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
            }); // Thêm hành động thoát cho nút

        lbExit = new JLabel("Thoát");
        lbExit.setBounds(33, 85, 40, 20);
        lbExit.setFont(newFont);

        btMes = new JButton();
        btMes.setIcon(imgMes);
        btMes.setBounds(100, 30, 48, 48);
        btMes.setBorder(null);
        btMes.setBackground(new Color(238, 238, 238));

        lbMes = new JLabel("Tin nhắn");
        lbMes.setBounds(95, 85, 70, 20);
        lbMes.setFont(newFont);


        lbuser= new JLabel("User");
        lbuser.setFont(newFont);
        lbuser.setBounds(900,110,40,20);
        tfuser = new JTextField();
        tfuser.setFont(newFont);
        tfuser.setBounds(960,110,120,25);
        tfuser.setEditable(false);


        pnRoom = new JPanel();
        pnRoom.setBackground(Color.white);
        pnRoom.setLayout(null);
        pnRoom.setBounds(0, 150, 1200, 650);

        pnLine = new JPanel();
        pnLine.setBounds(0, 149, 1200, 1);
        pnLine.setBackground(Color.black);

        lbAllGroup = new JLabel("     All groups");
        pnAllGroup = new JPanel();
        pnAllGroup.setBounds(0, 0, 1200, 40);
        lbAllGroup.setFont(allGroupsFont);
        lbAllGroup.setBounds(0, 10, 100, 20);
        pnAllGroup.setBackground(new Color(235, 235, 237));
        pnAllGroup.setLayout(null);

        JPanel pnTang1 = createPanel("Tầng 1");
        pnTang1.setBounds(0, 40, 1200, 30);
        JPanel P101 = createRoom(this, "P.101", 30, 80,1500000);
        pnRoom.add(P101);
        JPanel P102 = createRoom(this, "P.102", 158, 80,1500000);
        pnRoom.add(P102);
        JPanel P103 = createRoom(this, "P.103", 286, 80,1500000);
        pnRoom.add(P103);
        JPanel P104 = createRoom(this, "P.104", 414, 80,1500000);
        pnRoom.add(P104);
        JPanel P105 = createRoom(this, "P.105", 542, 80,1500000);
        pnRoom.add(P105);


        JPanel pnTang2 = createPanel("Tầng 2");
        pnTang2.setBounds(0, 180, 1200, 30);
        JPanel P201 = createRoom(this, "P.201", 30, 220,1600000);
        pnRoom.add(P201);
        JPanel P202 = createRoom(this, "P.202", 158, 220,1600000);
        pnRoom.add(P202);
        JPanel P203 = createRoom(this, "P.203", 286, 220,1600000);
        pnRoom.add(P203);
        JPanel P204 = createRoom(this, "P.204", 414, 220,1600000);
        pnRoom.add(P204);
        JPanel P205 = createRoom(this, "P.205", 542, 220,1600000);
        pnRoom.add(P205);

        JPanel pnTang3 = createPanel("Tầng 3");
        pnTang3.setBounds(0, 320, 1200, 30);
        JPanel P301 = createRoom(this, "P.301", 30, 360,1700000);
        pnRoom.add(P301);
        JPanel P302 = createRoom(this, "P.302", 158, 360,1700000);
        pnRoom.add(P302);
        JPanel P303 = createRoom(this, "P.303", 286, 360,1700000);
        pnRoom.add(P303);
        JPanel P304 = createRoom(this, "P.304", 414, 360,1700000);
        pnRoom.add(P304);

        JPanel pnTang4 = createPanel("Tầng 4");
        pnTang4.setBounds(0, 460, 1200, 30);
        JPanel P401 = createRoom(this, "P.401", 30, 500,1800000);
        pnRoom.add(P401);
        JPanel P402 = createRoom(this, "P.402", 158, 500,1800000);
        pnRoom.add(P402);
        JPanel P403 = createRoom(this, "P.403", 286, 500,1800000);
        pnRoom.add(P403);
        this.setTitle("FairyHotel");
        this.setVisible(true);
        this.setSize(1200, 800);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.add(btExit);
        this.add(btMes);
        this.add(lbExit);
        this.add(lbMes);
        this.add(pnRoom);
        this.add(lbName);
        this.add(pnLine);
        this.add(lbuser);
        this.add(tfuser);
        pnRoom.add(pnAllGroup);
        pnRoom.add(pnTang1);
        pnRoom.add(pnTang2);
        pnRoom.add(pnTang3);
        pnRoom.add(pnTang4);
        pnAllGroup.add(lbAllGroup);
        changeRoomIcon();
    }



    public void changeRoomIcon() {
        Component[] components = pnRoom.getComponents();
        RoomDAO roomDAO = new RoomDAO();
        for (Component component : components) {
            if(component instanceof JPanel) {
                JPanel roomPanel = (JPanel)component;
                String roomName = roomPanel.getName();
                try {
                    String status = roomDAO.getRoomStatus(roomName);
                    if ("da dat".equals(status)) {
                        JButton roomButton = (JButton) roomPanel.getComponent(0);
                        ImageIcon imgRoom = new ImageIcon("src/IMG/home.png");
                        roomButton.setIcon(imgRoom);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public void ReturnRoomIcon() {
        Component[] components = pnRoom.getComponents();
        RoomDAO roomDAO = new RoomDAO();
        for (Component component : components) {
            if(component instanceof JPanel) {
                JPanel roomPanel = (JPanel)component;
                String roomName = roomPanel.getName();
                try {
                    String status = roomDAO.getRoomStatus(roomName);
                    if ("trong".equals(status)) {
                        JButton roomButton = (JButton) roomPanel.getComponent(0);
                        ImageIcon imgRoom = new ImageIcon("src/IMG/room.png");
                        roomButton.setIcon(imgRoom);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void setLoggedInUser(String username) {
        tfuser.setText(username);
    }

}

