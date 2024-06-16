    package View;

    import DAO.KhachHangDAO;
    import DAO.RoomDAO;
    import DAO.SP_DV_DAO;
    import Model.DichVuDaDat;
    import Model.KhachHang;

    import javax.swing.*;
    import javax.swing.table.DefaultTableModel;
    import java.awt.*;
    import java.net.Socket;
    import java.sql.SQLException;
    import java.text.SimpleDateFormat;
    import java.util.Date;
    import java.util.Vector;
    import java.util.List;

    public class Bill_request extends JFrame {
        public JLabel lbkh, lbsn,lbnd,lbnt,lbsdt,lbcccd,lbtitle,lbtc,lbvnd;
        public JTextField tfkh,tfsn,tfnd,tfnt,tfsdt,tfcccd,tftc;
        public JTable table;
        public JPanel panel;
        JScrollPane scrollpane;
        public JButton btok,bthuy;
        Server server;
        private String roomName;
        ManaRoom manaRoom;
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
        public Bill_request(String roomName,ManaRoom manaRoom,Server server) {
            this.manaRoom = manaRoom;
            this.roomName = roomName;
            this.server = server;
            this.setVisible(true);
            this.setSize(850, 820);
            this.setLocationRelativeTo(null);
            this.setLayout(null);
            this.setResizable(false);

            panel = new JPanel();
            panel.setBounds(0, 0, 850, 800);
            panel.setBackground(new Color(255, 253, 253, 250));
            panel.setLayout(null);

            lbtitle = createLabel("PHIẾU ĐẶT PHÒNG CHI TIẾT", 21);
            lbtitle.setForeground(Color.black);
            lbtitle.setBounds(270, 20, 360, 50);

            lbkh = createLabel("Tên khách hàng: ", 15);
            lbkh.setForeground(Color.black);
            lbkh.setBounds(20, 100, 170, 35);

            tfkh = createText();
            tfkh.setForeground(Color.black);
            tfkh.setBounds(160, 100, 240, 28);

            lbnd = createLabel("Ngày đặt: ", 15);
            lbnd.setForeground(Color.black);
            lbnd.setBounds(20, 170, 170, 35);

            tfnd = createText();
            tfnd.setForeground(Color.black);
            tfnd.setBounds(160, 170, 240, 28);

            lbnt = createLabel("Ngày trả: ", 15);
            lbnt.setForeground(Color.black);
            lbnt.setBounds(445, 170, 170, 35);

            tfnt = createText();
            tfnt.setForeground(Color.black);
            tfnt.setBounds(560, 170, 240, 28);

            lbsdt = createLabel("Số điện thoại: ", 15);
            lbsdt.setForeground(Color.black);
            lbsdt.setBounds(20, 240, 170, 35);

            tfsdt = createText();
            tfsdt.setForeground(Color.black);
            tfsdt.setBounds(160, 240, 240, 28);

            lbsn = createLabel("Số người: ", 15);
            lbsn.setForeground(Color.black);
            lbsn.setBounds(445, 240, 170, 35);

            tfsn = createText();
            tfsn.setForeground(Color.black);
            tfsn.setBounds(560, 240, 240, 28);

            lbcccd = createLabel("CCCD: ", 15);
            lbcccd.setForeground(Color.black);
            lbcccd.setBounds(20, 310, 170, 35);

            tfcccd = createText();
            tfcccd.setForeground(Color.black);
            tfcccd.setBounds(160, 310, 240, 28);

            String[] columnNames = {"PHÒNG", "SP-DV", "SL", "ĐƠN GIÁ", "THÀNH TIỀN"};
            DefaultTableModel model = new DefaultTableModel();
            model.setColumnIdentifiers(columnNames);

            table = new JTable(model);
            scrollpane = new JScrollPane(table);
            scrollpane.setBounds(20, 380, 800, 240);
            panel.add(scrollpane, BorderLayout.CENTER);

            lbtc = createLabel("Tổng hóa đơn: ", 15);
            lbtc.setForeground(Color.black);
            lbtc.setBounds(445, 650, 170, 35);

            tftc = createText();
            tftc.setForeground(Color.black);
            tftc.setBounds(590, 650, 170, 28);

            lbvnd = createLabel("VND", 15);
            lbvnd.setForeground(Color.black);
            lbvnd.setBounds(770, 650, 170, 35);

            btok = new JButton("Xác nhận");
            btok.setBackground(Color.lightGray);
            btok.setBounds(250, 720, 100, 35);

            btok.addActionListener(e->{
                RoomDAO dao = new RoomDAO();
                JOptionPane.showMessageDialog(null,"Chấp nhận đặt phòng!");
                try {
                    dao.updateStatusDone(roomName);
                    manaRoom.refreshTableData();
                    dispose();
                    server.broadcastMessage(roomName+" Đặt phòng thành công!");

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            });

            bthuy = new JButton("Hủy");
            bthuy.setBackground(Color.lightGray);
            bthuy.setBounds(500, 720, 100, 35);
            bthuy.addActionListener(e->{
                RoomDAO dao = new RoomDAO();
                KhachHangDAO khdao = new KhachHangDAO();
                SP_DV_DAO spdao = new SP_DV_DAO();
                JOptionPane.showMessageDialog(null,"Hủy đơn thành công!");
                try {
                    dao.updateStatusNo(roomName);
                    khdao.DeleteKhachHang(roomName);
                    spdao.DeleteDVDD(roomName);
                    manaRoom.refreshTableData();
                    dispose();
                    server.broadcastMessage(roomName+" Đã bị hủy phòng!");

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });

            tfkh.setEditable(false);
            tfsn.setEditable(false);
            tfnd.setEditable(false);
            tfnt.setEditable(false);
            tfsdt.setEditable(false);
            tfcccd.setEditable(false);


            panel.add(lbtitle);
            panel.add(lbkh);
            panel.add(tfkh);
            panel.add(tfnd);
            panel.add(lbnd);
            panel.add(tfnt);
            panel.add(lbnt);
            panel.add(tfsdt);
            panel.add(lbsdt);
            panel.add(lbsn);
            panel.add(tfsn);
            panel.add(lbcccd);
            panel.add(tfcccd);
            panel.add(lbtc);
            panel.add(tftc);
            panel.add(lbvnd);
            panel.add(btok);
            panel.add(bthuy);
            add(panel);
        }
        public void displayInfo(String roomName){
            KhachHangDAO khdao= new KhachHangDAO();
            SP_DV_DAO spdao= new SP_DV_DAO();
            try{
                KhachHang kh = khdao.getInfoByRoomName(roomName);
                if(kh!=null){
                    tfkh.setText(kh.getTenKhachHang());
                    tfcccd.setText(String.valueOf(kh.getCccd()));
                    tfsdt.setText(String.valueOf(kh.getSdt()));
                    tfsn.setText(String.valueOf(kh.getSonguoi()));
                    tfnd.setText(kh.getNgaydat());
                    tfnt.setText(kh.getNgaytra());
                    List<DichVuDaDat> dichVuList = spdao.getServicesByRoomName(roomName);
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    model.setRowCount(0); // Clear existing rows
                    for (DichVuDaDat dichVu : dichVuList) {
                        model.addRow(new Object[]{
                                dichVu.getRoomname(),
                                dichVu.getTensanpham(),
                                dichVu.getSoluong(),
                                dichVu.getDongia(),
                                dichVu.getGiatien()
                        });
                    }

                    tftc.setText(String.valueOf(kh.getTongtienphong()));
                } else {
                    JOptionPane.showMessageDialog(this, "Không tìm thấy khách hàng cho phòng: " + roomName);
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }

    }
