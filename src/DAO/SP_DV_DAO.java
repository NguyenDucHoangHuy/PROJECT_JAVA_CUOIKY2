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

public class SP_DV_DAO {
    ResultSet result;
    int check;

    public void showDataInTable(DefaultTableModel model) {
        try {
            Connection conn = new DBConnect().getConnection();
            String query = "SELECT TenDichVu, DonGia FROM SanPhamDichVu";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            // Xóa dữ liệu cũ trong bảng

            model.setRowCount(0);

            // Thêm dữ liệu từ ResultSet vào model của bảng
            while (rs.next()) {
                String tensanpham = rs.getString("TenDichVu");
                String dongia = rs.getString("DonGia");
                model.addRow(new Object[]{tensanpham, dongia});
            }

            // Đóng kết nối và các tài nguyên liên quan
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public boolean AddProduct(String ten, float gia) throws Exception {
        Connection conn = new DBConnect().getConnection();
        String query = "insert into SanPhamDichVu (TenDichVu,DonGia) values(?,?)";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, ten);
        ps.setFloat(2, gia);
        check = ps.executeUpdate();
        conn.close();
        return check > 0;
    }

    public boolean DeleteProduct(String ten) throws SQLException {
        Connection conn = new DBConnect().getConnection();
        String query = "delete from SanPhamDichVu where TenDichVu=? ";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, ten);
        check = ps.executeUpdate();
        conn.close();
        return check > 0;
    }

    public boolean updateProduct(float gia, String ten) throws SQLException {
        Connection conn = new DBConnect().getConnection();
        String query = "UPDATE SanPhamDichVu SET DonGia=? WHERE TenDichVu=? ";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setFloat(1, gia);
        ps.setString(2, ten);
        int check = ps.executeUpdate();
        conn.close();
        return check > 0;
    }

    public boolean saveProductUsed(List<Map<String, Object>> productsAndServices) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        try {

            conn = new DBConnect().getConnection();
            String sql = "INSERT INTO DichVuDaDat (RoomName,tensanpham,soluong,dongia,giatien) VALUES (?, ?, ?, ?, ?)";
            ps = conn.prepareStatement(sql);
            for (Map<String, Object> item : productsAndServices) {
                ps.setString(1, (String) item.get("RoomName"));
                ps.setString(2, (String) item.get("tensanpham"));
                ps.setInt(3, (int) item.get("soluong"));
                ps.setFloat(4, (float) item.get("dongia"));
                ps.setFloat(5, (float) item.get("giatien"));
                ps.addBatch();
            }
            int[] result = ps.executeBatch();
            boolean allInserted = true;
            for (int r : result) {
                if (r == PreparedStatement.EXECUTE_FAILED) {
                    allInserted = false;
                    break;
                }
            }
            return allInserted;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        }
    }
    public List<DichVuDaDat> getServicesByRoomName(String roomName) throws SQLException {
        Connection conn = new DBConnect().getConnection();
        String sql = "SELECT * FROM DichVuDaDat WHERE RoomName = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, roomName);
        ResultSet rs = ps.executeQuery();

        List<DichVuDaDat> dichVuList = new ArrayList<>();
        while (rs.next()) {
            String tenSanPham = rs.getString("tensanpham");
            float donGia = rs.getFloat("dongia");
            int soLuong = rs.getInt("soluong");
            float giaTien = rs.getFloat("giatien");
            DichVuDaDat dichVu = new DichVuDaDat(tenSanPham, donGia, roomName, soLuong, giaTien);
            dichVuList.add(dichVu);
        }

        rs.close();
        ps.close();
        conn.close();

        return dichVuList;
    }
    public boolean DeleteDVDD(String roomName) throws  Exception{
        Connection conn=new DBConnect().getConnection();
        String query = "delete from DichVuDaDat where RoomName=?";
        PreparedStatement ps= conn.prepareStatement(query);
        ps.setString(1,roomName);
        check = ps.executeUpdate();
        conn.close();
        return check>0;
    }
}

