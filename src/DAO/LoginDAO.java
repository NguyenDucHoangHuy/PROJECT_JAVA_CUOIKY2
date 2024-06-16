package DAO;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {
    ResultSet result;
    int check;
    public boolean AddAccount (int phone,String fullname,String usr,String pass,String conf,String email) throws Exception{
        Connection conn=new DBConnect().getConnection();
        String query="insert into Account (phone,fullname,usrname,password,confirmpass,email,role) values(?,?,?,?,?,?,?)";
        PreparedStatement ps= conn.prepareStatement(query);
            ps.setInt(1,phone);
            ps.setString(2,fullname);
            ps.setString(3,usr);
            ps.setString(4,pass);
            ps.setString(5,conf);
            ps.setString(6,email);
            ps.setString(7,"User");
            check = ps.executeUpdate();
            conn.close();
            return check>0;
    }
    public boolean DeleteAccount(String usn, String email) throws  Exception{
        Connection conn=new DBConnect().getConnection();
        String query = "delete from Account where usrname=? and email=?";
        PreparedStatement ps= conn.prepareStatement(query);
        ps.setString(1,usn);
        ps.setString(2,email);
        check = ps.executeUpdate();
        conn.close();
        return check>0;
    }
    public boolean LoginCheck(String urs, String pass) throws Exception{
        Connection conn=new DBConnect().getConnection();
        String query= "select * from Account where usrname=? and password=?";
        PreparedStatement ps =conn.prepareStatement(query);
        ps.setString(1,urs);
        ps.setString(2,pass);
        result = ps.executeQuery();
        while(result.next()){
            if(result.getString("usrname").equals(urs) && result.getString("password").equals(pass)){
                    return true;
            }
        }
        result.close();
        conn.close();
        return false;

    }
    public void ResetPass(String newpass, String urs) throws Exception{
        Connection conn=new DBConnect().getConnection();
        String query= "update Account set password=? , confirmpass=? where usrname=?";
        PreparedStatement ps= conn.prepareStatement(query);
        ps.setString(1,newpass);
        ps.setString(2,newpass);
        ps.setString(3,urs);
        check = ps.executeUpdate();
        conn.close();
    }
    public String getRole(String usn) throws Exception{
        Connection conn=new DBConnect().getConnection();
        String query="select role from Account where usrname=?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, usn);
        result = ps.executeQuery();
        if (result.next()) {
            String role = result.getString("role");
            conn.close();
            return role;
        } else {
            conn.close();
            return null; // Trả về null nếu không tìm thấy vai trò cho người dùng
        }
    }
    public void showDataInTable(DefaultTableModel model) {
        try {
            Connection conn=new DBConnect().getConnection();
            String query = "SELECT usrname, email, role FROM Account";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            // Xóa dữ liệu cũ trong bảng

            model.setRowCount(0);

            // Thêm dữ liệu từ ResultSet vào model của bảng
            while (rs.next()) {
                String username = rs.getString("usrname");
                String email = rs.getString("email");
                String role = rs.getString("role");
                model.addRow(new Object[]{username, email, role});
            }

            // Đóng kết nối và các tài nguyên liên quan
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public boolean updateRole(String username, String email, String newRole) throws Exception {
        Connection conn = new DBConnect().getConnection();
        String query = "UPDATE Account SET role=? WHERE usrname=? AND email=?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, newRole);
        ps.setString(2, username);
        ps.setString(3, email);
        int check = ps.executeUpdate();
        conn.close();
        return check > 0;
    }
}
