package DAO;

import Model.DichVuDaDat;

import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RoomDAO {
    ResultSet result;
    int check;

    public boolean AddRoom(String RoomName) throws SQLException {

        Connection conn = new DBConnect().getConnection();
        String query = "INSERT INTO Phong (RoomName, TrangThai) VALUES (?, 'trong')";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, RoomName);
        check = ps.executeUpdate();
        conn.close();
        return check > 0;

    }
    public void showTable(DefaultTableModel model) {
        try {
            Connection conn=new DBConnect().getConnection();
            String query = "SELECT RoomName,TrangThai FROM Phong";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            // Xóa dữ liệu cũ trong bảng

            model.setRowCount(0);

            // Thêm dữ liệu từ ResultSet vào model của bảng
            while (rs.next()) {
                String roomName = rs.getString("RoomName");
                String status = rs.getString("TrangThai");
                model.addRow(new Object[]{roomName, status});
            }

            // Đóng kết nối và các tài nguyên liên quan
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public void updateStatusWaiting(String RoomName) throws SQLException {
        Connection conn = new DBConnect().getConnection();
        String query = "UPDATE Phong SET TrangThai = 'doi duyet' WHERE RoomName = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, RoomName);
        check = ps.executeUpdate();
        conn.close();
    }
    public void updateStatusDone(String RoomName) throws SQLException {
        Connection conn = new DBConnect().getConnection();
        String query = "UPDATE Phong SET TrangThai = 'da dat' WHERE RoomName = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, RoomName);
        check = ps.executeUpdate();
        conn.close();
    }
    public void updateStatusNo(String RoomName) throws SQLException {
        Connection conn = new DBConnect().getConnection();
        String query = "UPDATE Phong SET TrangThai = 'trong' WHERE RoomName = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, RoomName);
        check = ps.executeUpdate();
        conn.close();
    }
    public String getRoomStatus(String roomName) throws SQLException {
        String status = "";
        Connection conn = new DBConnect().getConnection();
        String query = "SELECT TrangThai FROM Phong WHERE RoomName = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, roomName);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            status = rs.getString("TrangThai");
        }
        conn.close();
        return status;
    }
}

