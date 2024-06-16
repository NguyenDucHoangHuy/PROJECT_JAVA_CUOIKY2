package DAO;

import Model.KhachHang;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class KhachHangDAO {
    ResultSet rs;
    int check;
    public boolean addInfoCustomer(String tenKhachHang, String ngayDat, String ngayTra, String soDienThoai, String soNguoi, String cccd, String roomName,String Tongtienphong) throws SQLException {
        Connection conn= new DBConnect().getConnection();
        String sql = "INSERT INTO KhachHang (TenKhachHang, NgayDat, NgayTra, Sdt, SoNguoi, Cccd, RoomName, Tongtienphong) VALUES (?, ?, ?, ?, ?, ?, ?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, tenKhachHang);
        ps.setString(2, ngayDat);
        ps.setString(3, ngayTra);
        ps.setString(4, soDienThoai);
        ps.setString(5, soNguoi);
        ps.setString(6, cccd);
        ps.setString(7, roomName);
        ps.setString(8, Tongtienphong);
        check = ps.executeUpdate();
        conn.close();
        return check>0;
    }
    public KhachHang getInfoByRoomName (String roomName) throws SQLException{
        Connection conn = DBConnect.getConnection();
        String sql = "SELECT * FROM KhachHang WHERE RoomName = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, roomName);
        rs = ps.executeQuery();
        if(rs.next()){
            String tenKhachHang = rs.getString("TenKhachHang");
            int sdt = rs.getInt("Sdt");
            float cccd = rs.getFloat("Cccd");
            int songuoi = rs.getInt("SoNguoi");
            String ngaydat = rs.getString("NgayDat");
            String ngaytra = rs.getString("NgayTra");
            String roomNameDb = rs.getString("RoomName");
            float tongtienphong = rs.getFloat("Tongtienphong");

            KhachHang khachHang = new KhachHang(tenKhachHang, sdt, cccd, songuoi, ngaydat, ngaytra, roomNameDb, tongtienphong);
            return khachHang;
        }

        rs.close();
        ps.close();
        conn.close();

        return null;
    }
    public boolean DeleteKhachHang(String roomName) throws  Exception{
        Connection conn=new DBConnect().getConnection();
        String query = "delete from KhachHang where RoomName=?";
        PreparedStatement ps= conn.prepareStatement(query);
        ps.setString(1,roomName);
        check = ps.executeUpdate();
        conn.close();
        return check>0;
    }
}