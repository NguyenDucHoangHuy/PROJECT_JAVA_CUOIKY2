package View;

import DAO.RoomDAO;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

public class HomePage_Admin extends JFrame {
    JButton btExit, btgraph, btkh, btqlp, btspdv;
    JLabel lbExit,lbname, lballgroup, lbgraph;
    JPanel pnroom, pnallgroup, pnline, pnline2, pnmenu, pndanhmuc;


    private static HomePage_Admin instance;
    private Server server;

    float roomCost;
    String roomName;

    private JPanel createPanel(String a) {
        JPanel panel = new JPanel();
        JLabel label = new JLabel(a);
        panel.setBackground(new Color(104, 151, 153));
        panel.add(label);
        panel.setLayout(null);
        Font newFont = new Font("Arial", Font.BOLD, 15);
        label.setFont(newFont);
        label.setBounds(20, 6, 90, 20);
        return panel;
    }

    private JPanel createRoom(String roomName, int x, int y) {
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
        return panel;
    }

    private JButton createMenu(String a, int x, int y) {
        JButton button = new JButton(a);
        button.setHorizontalTextPosition(SwingConstants.LEFT);
        button.setBorder(null);
        button.setBackground(new Color(201, 201, 198));
        button.setForeground(Color.BLACK);
        button.setFont(new Font("Arial", Font.TRUETYPE_FONT, 14));
        button.setBounds(x, y, 268, 30);
        return button;
    }

    public HomePage_Admin(Server server) {
        this.server = server;

        Font newFont = new Font("Arial", Font.TRUETYPE_FONT, 15);
        Font allgroupsFont = new Font("Arial", Font.BOLD, 15);
        Font nameFont = new Font("Arial", Font.BOLD, 32);
        ImageIcon imgExit = new ImageIcon("src/IMG/EXIT.png");
        ImageIcon imgMes = new ImageIcon("src/IMG/MES.png");
        ImageIcon imggraph = new ImageIcon("src/IMG/graph.png");
        ImageIcon imgmenu = new ImageIcon("src/IMG/menu.png");
        ImageIcon imgdot = new ImageIcon("src/IMG/Dot.png");
        lbname = new JLabel(" FAIRY HOTEL");
        lbname.setFont(nameFont);
        lbname.setBounds(490, 40, 280, 50);

        btExit = new JButton();
        btExit.setIcon(imgExit);
        btExit.setBounds(30, 30, 48, 48);
        btExit.setBorder(null);
        btExit.setBackground(new Color(238, 238, 238));
        btExit.addActionListener(e -> {
            int choice = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn thoát chương trình không?", "Xác nhận thoát", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });
        lbExit = new JLabel("Thoát");
        lbExit.setBounds(33, 85, 40, 20);
        lbExit.setFont(newFont);

        btgraph = new JButton();
        btgraph.setIcon(imggraph);
        btgraph.setBounds(100, 30, 48, 48);
        btgraph.setBorder(null);
        btgraph.setBackground(new Color(238, 238, 238));
        lbgraph = new JLabel("Báo cáo");
        lbgraph.setBounds(95, 85, 70, 20);
        lbgraph.setFont(newFont);



        pnmenu = new JPanel();
        pnmenu.setBackground(new Color(214, 214, 214));
        pnmenu.setLayout(null);
        pnmenu.setBounds(902, 149, 282, 651);
        pndanhmuc = new JPanel();
//        pndanhmuc.setLayout(null);
        JLabel lbdanhmuc = new JLabel("Danh mục");
        lbdanhmuc.setIcon(imgmenu);
        lbdanhmuc.setFont(newFont);
        pndanhmuc.setBackground(new Color(235, 235, 237));
        pndanhmuc.setBounds(8, 20, 268, 30);
        pndanhmuc.setFont(newFont);
        pndanhmuc.add(lbdanhmuc);

        JLabel lbQuanLyRole = new JLabel("Quản lý role");
        JLabel lbQuanLySanPhamDichVu = new JLabel("Quản lý sản phẩm - dịch vụ");
        JLabel lbQuanLyKhach = new JLabel("Quản lý Phòng");


        lbQuanLyRole.setBounds(8, 80, 268, 30);
        lbQuanLySanPhamDichVu.setBounds(8, 150, 268, 30);
        lbQuanLyKhach.setBounds(8, 220, 268, 30);


        lbQuanLyRole.setFont(newFont);
        lbQuanLySanPhamDichVu.setFont(newFont);
        lbQuanLyKhach.setFont(newFont);


        lbQuanLyRole.setForeground(new Color(250, 0, 0));
        lbQuanLySanPhamDichVu.setForeground(new Color(250, 0, 0));
        lbQuanLyKhach.setForeground(new Color(250, 0, 0));


        lbQuanLyRole.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new Role(server);
            }
        });
        lbQuanLySanPhamDichVu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new DataSP_DV(server); // Exit the application on click
            }
        });
        lbQuanLyKhach.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new ManaRoom(server); // Exit the application on click
            }
        });


        pnroom = new JPanel();
        pnroom.setBackground(Color.white);
        pnroom.setLayout(null);
        pnroom.setBounds(0, 150, 900, 650);

        pnline = new JPanel();
        pnline.setBounds(0, 149, 900, 1);
        pnline.setBackground(Color.black);

        lballgroup = new JLabel("     All groups");
        pnallgroup = new JPanel();
        pnallgroup.setBounds(0, 0, 900, 40);
        lballgroup.setFont(allgroupsFont);
        lballgroup.setBounds(0, 10, 100, 20);
        pnallgroup.setBackground(new Color(235, 235, 237));
        pnallgroup.setLayout(null);

        JPanel pntang1 = createPanel("Tầng 1");
        pntang1.setBounds(0, 40, 900, 30);
        JPanel P101 = createRoom("P.101", 30, 80);
        pnroom.add(P101);
        JPanel P102 = createRoom("P.102", 158, 80);
        pnroom.add(P102);
        JPanel P103 = createRoom("P.103", 286, 80);
        pnroom.add(P103);
        JPanel P104 = createRoom("P.104", 414, 80);
        pnroom.add(P104);
        JPanel P105 = createRoom("P.105", 542, 80);
        pnroom.add(P105);


        JPanel pntang2 = createPanel("Tầng 2");
        pntang2.setBounds(0, 180, 900, 30);
        JPanel P201 = createRoom("P.201", 30, 220);
        pnroom.add(P201);
        JPanel P202 = createRoom("P.202", 158, 220);
        pnroom.add(P202);
        JPanel P203 = createRoom("P.203", 286, 220);
        pnroom.add(P203);
        JPanel P204 = createRoom("P.204", 414, 220);
        pnroom.add(P204);
        JPanel P205 = createRoom("P.205", 542, 220);
        pnroom.add(P205);

        JPanel pntang3 = createPanel("Tầng 3");
        pntang3.setBounds(0, 320, 900, 30);
        JPanel P301 = createRoom("P.301", 30, 360);
        pnroom.add(P301);
        JPanel P302 = createRoom("P.302", 158, 360);
        pnroom.add(P302);
        JPanel P303 = createRoom("P.303", 286, 360);
        pnroom.add(P303);
        JPanel P304 = createRoom("P.304", 414, 360);
        pnroom.add(P304);


        JPanel pntang4 = createPanel("Tầng 4");
        pntang4.setBounds(0, 460, 900, 30);
        JPanel P401 = createRoom("P.401", 30, 500);
        pnroom.add(P401);
        JPanel P402 = createRoom("P.402", 158, 500);
        pnroom.add(P402);
        JPanel P403 = createRoom("P.403", 286, 500);
        pnroom.add(P403);

        pnline2 = new JPanel();
        pnline2.setBackground(Color.black);
        pnline2.setBounds(901, 149, 1, 800);

        this.setTitle("FairyHotel");
        this.setVisible(true);
        this.setSize(1200, 800);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.add(btExit);

        this.add(btgraph);
        this.add(lbExit);

        this.add(lbgraph);
        this.add(pnroom);
        this.add(pnmenu);
        this.add(lbname);
        pnmenu.add(pndanhmuc);
        pnmenu.add(lbQuanLyRole);
        pnmenu.add(lbQuanLySanPhamDichVu);
        pnmenu.add(lbQuanLyKhach);
        this.add(pnline);
        this.add(pnline2);
        pnroom.add(pnallgroup);
        pnroom.add(pntang1);
        pnroom.add(pntang2);
        pnroom.add(pntang3);
        pnroom.add(pntang4);
        pnallgroup.add(lballgroup);
        changeRoomIcon();
    }
    public void changeRoomIcon() {
        Component[] components = pnroom.getComponents();
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


}


