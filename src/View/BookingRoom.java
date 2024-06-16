package View;

import DAO.KhachHangDAO;
import DAO.RoomDAO;
import DAO.SP_DV_DAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import static View.HomePage_User.bookingRoom;

public class BookingRoom extends JDialog {
    public JLabel lbKh, lbDay, lbSdt, lbCccd, lbNb, lbExit, lbnt, lbthanhtoan;
    public JTextField tfKh, tfSdt, tfCccd, tfNb, tfTotalAmount;
    public JButton btExit, btComplete ;
    public SpinnerDateModel tfDay, tfnt;
    public JSpinner spDay, spnt;
    public JPanel PNMAIN, pnInfor, pnthanhtoan, pnsanpham, pntable1, pntable2, pntitle1, pntitle2;
    public JTable table1, table2;
    JScrollPane scrollPane1, scrollPane2;
    private HomePage_User homePage;
    private Bill_request billRequest;

    private KhachHangDAO khachHangDAO;

    float roomCost;
    String roomName;


    public BookingRoom(HomePage_User homePage,JFrame parent, String roomName, float roomCost) {
        super(parent, "Đặt phòng - " + roomName, true);
        this.roomName=roomName;
        this.roomCost=roomCost;
        this.homePage = homePage;
        khachHangDAO= new KhachHangDAO();


        Font newFont = new Font("Arial", Font.PLAIN, 15);
        ImageIcon imgExit = new ImageIcon("src/IMG/EXIT.png");
        PNMAIN = new JPanel();
        PNMAIN.setLayout(null);
        PNMAIN.setBackground(new Color(250, 250, 250));
        PNMAIN.setBounds(0, 0, 900, 700);

        btExit = new JButton();
        btExit.setIcon(imgExit);
        btExit.setBounds(30, 10, 48, 48);
        btExit.setBorder(null);
        btExit.setBackground(new Color(250, 250, 250));
        btExit.addActionListener(e-> this.dispose());

        lbExit = new JLabel("Thoát");
        lbExit.setBounds(33, 55, 40, 20);
        lbExit.setFont(newFont);


        ////////////////////////////////information///////////////////////////////
        pnInfor = new JPanel();
        pnInfor.setBounds(0, 100, 600, 230);
        pnInfor.setBackground(new Color(237, 238, 240));
        pnInfor.setLayout(null);
        JPanel pnTitle = new JPanel();
        pnTitle.setBounds(0, 0, 600, 30);
        pnTitle.setLayout(null);
        pnTitle.setBackground(new Color(228, 228, 236));
        JLabel lbTitle = new JLabel("    Thông tin khách hàng");
        lbTitle.setFont(newFont);
        lbTitle.setBounds(0, 8, 200, 20);
        pnTitle.add(lbTitle);


        JLabel label = new JLabel("Phòng " + roomName + " - Giá : " + roomCost + "VND");
        label.setFont(newFont);
        label.setBounds(180, 40, 250, 20);
        pnInfor.add(label);
        pnInfor.add(pnTitle);
        lbKh = new JLabel("  Khách hàng");
        lbKh.setBounds(70, 70, 80, 20);
        tfKh = new JTextField();
        tfKh.setBounds(160, 69, 335, 25);
        //
        lbDay = new JLabel("       Ngày đặt");
        lbDay.setBounds(60, 100, 80, 20);
        lbnt = new JLabel("Ngày trả");
        lbnt.setBounds(300, 100, 80, 20);
        tfDay = new SpinnerDateModel();
        tfDay.setValue(new Date());
        spDay = new JSpinner(tfDay);
        JSpinner.DateEditor editor = new JSpinner.DateEditor(spDay, "dd/MM/yyyy");
        spDay.setEditor(editor);
        spDay.setBounds(160, 99, 130, 25);
        tfnt = new SpinnerDateModel();
        tfnt.setValue(new Date());
        spnt = new JSpinner(tfnt);
        JSpinner.DateEditor editor2 = new JSpinner.DateEditor(spnt, "dd/MM/yyyy");
        spnt.setEditor(editor2);
        spnt.setBounds(370, 99, 130, 25);

        lbNb = new JLabel("      Số người");
        lbNb.setBounds(60, 130, 80, 20);
        tfNb = new JTextField();
        tfNb.setBounds(160, 129, 120, 25);
        lbSdt = new JLabel("Số điện thoại");
        lbSdt.setBounds(60, 160, 120, 20);
        tfSdt = new JTextField();
        tfSdt.setBounds(160, 159, 120, 25);
        lbCccd = new JLabel("            CCCD");
        lbCccd.setBounds(60, 190, 80, 20);
        tfCccd = new JTextField();
        tfCccd.setBounds(160, 189, 300, 25);

        pntitle1 = new JPanel();
        pntitle1.setBounds(0, 330, 600, 30);
        pntitle1.setLayout(null);

        JLabel lbdanhsach = new JLabel("Danh sách Sản phẩm - Dịch vụ");
        lbdanhsach.setFont(newFont);
        pntitle1.setBackground(new Color(228, 228, 236));
        lbdanhsach.setBounds(25, 0, 300, 30);
        pntitle1.add(lbdanhsach);


        String[] columnNames1 = {"PHÒNG", "SP-DV", "SL", "ĐƠN GIÁ", "THÀNH TIỀN"};
        DefaultTableModel model1 = new DefaultTableModel();
        model1.setColumnIdentifiers(columnNames1);

        pntable1 = new JPanel(new BorderLayout());
        pntable1.setBounds(0, 360, 600, 160);
        table1 = new JTable(model1);
        scrollPane1 = new JScrollPane(table1);
        pntable1.add(scrollPane1, BorderLayout.CENTER);

        pnthanhtoan = new JPanel();
        pnthanhtoan.setBackground(new Color(228, 228, 236));
        pnthanhtoan.setBounds(0, 520, 600, 70);
        pntitle2 = new JPanel();
        pntitle2.setBounds(0, 0, 600, 30);
        lbthanhtoan = new JLabel("TỔNG TIỀN THANH TOÁN");
        lbthanhtoan.setForeground(new Color(250, 0, 0));
        lbthanhtoan.setFont(newFont);
        lbthanhtoan.setBounds(25, 0, 300, 30);
        pntitle2.add(lbthanhtoan);
        pntitle2.setBackground(new Color(228, 228, 236));

        // Adding total amount text field
        tfTotalAmount = new JTextField();
        tfTotalAmount.setBounds(25, 30, 200, 25);
        tfTotalAmount.setEditable(false); // Make it non-editable by the user
        JLabel lbdonvi = new JLabel("VND");
        lbdonvi.setFont(newFont);
        lbdonvi.setForeground(new Color(250, 0, 0));
        lbdonvi.setBounds(240, 30, 50, 25);


        pnthanhtoan.setLayout(null);
        pnthanhtoan.add(pntitle2);
        pnthanhtoan.add(tfTotalAmount);
        pnthanhtoan.add(lbdonvi);

        pnInfor.add(lbKh);
        pnInfor.add(lbDay);
        pnInfor.add(lbNb);
        pnInfor.add(lbSdt);
        pnInfor.add(lbCccd);
        pnInfor.add(tfKh);
        pnInfor.add(spDay);
        pnInfor.add(tfSdt);
        pnInfor.add(tfCccd);
        pnInfor.add(tfNb);
        pnInfor.add(spnt);
        pnInfor.add(lbnt);

        //////////////////////////////////////////////////////////END INFOR////////////////////DV//////////////////////
        pnsanpham = new JPanel();
        pnsanpham.setLayout(null);
        pnsanpham.setBounds(605, 100, 295, 600);
        pnsanpham.setBackground(Color.WHITE);
        JPanel pntitelsp = new JPanel();
        pntitelsp.setLayout(null);
        pntitelsp.setBounds(0, 0, 295, 30);
        pntitelsp.setBackground(new Color(228, 228, 236));
        JLabel lbtitlesp = new JLabel("Sản phẩm - Dịch vụ");
        lbtitlesp.setFont(newFont);
        lbtitlesp.setBounds(0, 0, 200, 20);
        pntitelsp.add(lbtitlesp);
        pnsanpham.add(pntitelsp);

        String[] columnNames2 = {"SP-DV", "ĐƠN GIÁ"};
        DefaultTableModel model2 = new DefaultTableModel();
        model2.setColumnIdentifiers(columnNames2);

        pntable2 = new JPanel(new BorderLayout());
        pntable2.setBounds(605, 130, 275, 510);
        table2 = new JTable(model2);
        scrollPane2 = new JScrollPane(table2);
        pntable2.add(scrollPane2, BorderLayout.CENTER);

        // Adding the "Complete" button
        btComplete = new JButton("Hoàn tất");
        btComplete.setBounds(250, 600, 100, 30);
        btComplete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(!tfCccd.getText().equals("") || !tfNb.getText().equals("")|| !tfSdt.getText().equals("")|| !tfKh.getText().equals("")) {
                    int choice = JOptionPane.showConfirmDialog(null, "Xác nhận đặt phòng","Confirm", JOptionPane.YES_NO_OPTION);
                    if (choice == JOptionPane.YES_OPTION) {
                        JOptionPane.showMessageDialog(null,"Vui lòng đợi duyệt từ admin");
                        saveData();
                        updateRoomStatus(roomName);



                        dispose();
                    }
                } else {
                    JOptionPane.showMessageDialog(null,"Must not be blank");
                }
            }
        });

        ///////////////////////////////////////////////////////END DV/////////////////////////////////////////////////
        this.setSize(900, 700);
        this.setLayout(null);
        this.add(PNMAIN);
        this.setLocationRelativeTo(null);
        PNMAIN.add(pnthanhtoan);
        PNMAIN.add(pntable1);
        PNMAIN.add(pntable2);
        PNMAIN.add(pntitle1);
        PNMAIN.add(pnInfor);
        PNMAIN.add(btExit);
        PNMAIN.add(lbExit);
        PNMAIN.add(pnsanpham);
        PNMAIN.add(btComplete);
        try {
            SP_DV_DAO dao = new SP_DV_DAO();
            dao.showDataInTable(model2);
        } catch (Exception e) {
            e.printStackTrace();
        }
       setClickTransfer();

        TableColumn quantityColumn = table1.getColumnModel().getColumn(2);
        JTextField textField = new JTextField();
        quantityColumn.setCellEditor(new DefaultCellEditor(textField) {
            @Override
            public boolean stopCellEditing() {
                try {
                    int value = Integer.parseInt(textField.getText());
                    if (value < 0) throw new NumberFormatException();
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập một số nguyên dương!", "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
                return super.stopCellEditing();
            }
        });
        // Add a listener to update the total amount when editing stops
        textField.addActionListener(e -> updateTotalAmount());

        table1.getModel().addTableModelListener(e -> {
            if (e.getType() == javax.swing.event.TableModelEvent.UPDATE && e.getColumn() == 2) {

                updateTotalAmount();
            }
        });
    }
    public void setClickTransfer() {
        table2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = table2.getSelectedRow();
                if (selectedRow != -1) {
                    DefaultTableModel model2 = (DefaultTableModel) table2.getModel();
                    Vector rowData = (Vector) model2.getDataVector().elementAt(selectedRow);

                    DefaultTableModel model1 = (DefaultTableModel) table1.getModel();
                    Vector<Object> newRow = new Vector<>();
                    newRow.add(roomName);  // Add room name
                    newRow.add(rowData.get(0));  // Add SP-DV
                    newRow.add(1);  // Initial quantity as 1
                    // Convert the unit price to a float
                    float unitPrice = Float.parseFloat(rowData.get(1).toString());
                    newRow.add(unitPrice);  // Add ĐƠN GIÁ
                    newRow.add(unitPrice);  // THÀNH TIỀN = ĐƠN GIÁ * 1

                    model1.addRow(newRow);

                    // Update the total amount
                    updateTotalAmount();

                    // Update the table1 display
                    model1.fireTableDataChanged();
                }
            }
        });
    }
    private void updateTotalAmount() {
        DefaultTableModel model1 = (DefaultTableModel) table1.getModel();
        float totalAmount = 0;
        float costproduct=0;
        for (int i = 0; i < model1.getRowCount(); i++) {
            int quantity = Integer.parseInt(model1.getValueAt(i, 2).toString());
            float unitPrice = (float) model1.getValueAt(i, 3);
            float totalPrice = unitPrice * quantity;
            model1.setValueAt(totalPrice, i, 4); // Update THÀNH TIỀN column
            totalAmount += totalPrice;
        }
        totalAmount += roomCost;
        tfTotalAmount.setText(String.valueOf(totalAmount));
    }

    public List<Map<String, Object>> getProductsAndServices() {
        List<Map<String, Object>> productsAndServices = new ArrayList<>();
        DefaultTableModel model = (DefaultTableModel) table1.getModel();

        for (int i = 0; i < model.getRowCount(); i++) {
            Map<String, Object> item = new HashMap<>();
            item.put("RoomName", model.getValueAt(i, 0));
            item.put("tensanpham", model.getValueAt(i, 1));
            item.put("soluong", model.getValueAt(i, 2));
            item.put("dongia", model.getValueAt(i, 3));
            item.put("giatien", model.getValueAt(i, 4));
            productsAndServices.add(item);
        }

        return productsAndServices;
    }
    public void saveData(){
        try{
            String tenKhachHang = tfKh.getText();
            String ngayDat = new SimpleDateFormat("dd/MM/yyyy").format((Date) spDay.getValue());
            String ngayTra = new SimpleDateFormat("dd/MM/yyyy").format((Date) spnt.getValue());
            String soDienThoai = tfSdt.getText();
            String soNguoi = tfNb.getText();
            String cccd = tfCccd.getText();
            String tongtienphong = tfTotalAmount.getText();
            khachHangDAO.addInfoCustomer(tenKhachHang,ngayDat,ngayTra,soDienThoai,soNguoi,cccd,roomName,tongtienphong);

            List<Map<String, Object>> productsAndServices = getProductsAndServices();
            SP_DV_DAO dao = new SP_DV_DAO();
            dao.saveProductUsed(productsAndServices);

        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public void updateRoomStatus(String roomName){
        try {
            // Gọi phương thức từ DAO để cập nhật trạng thái phòng
            RoomDAO roomDAO = new RoomDAO();
            roomDAO.updateStatusWaiting(roomName);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }



}


