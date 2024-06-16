package Model;

public class KhachHang {
    private String TenKhachHang;
    private int sdt;
    private float cccd;
    private int songuoi;
    private String ngaydat;
    private String ngaytra;
    private String roomName;
    private float tongtienphong;


    public KhachHang(String tenKhachHang, int sdt, float cccd, int songuoi, String ngaydat, String ngaytra, String roomName, float tongtienphong) {
       this.TenKhachHang = tenKhachHang;
       this.sdt = sdt;
       this.cccd = cccd;
       this.songuoi = songuoi;
       this.ngaydat = ngaydat;
       this.ngaytra = ngaytra;
       this.roomName = roomName;
       this.tongtienphong = tongtienphong;
    }

    public KhachHang() {

    }

    public String getTenKhachHang() {
        return TenKhachHang;
    }
    public void setTenKhachHang(String tenKhachHang) {
        this.TenKhachHang = tenKhachHang;
    }
    public int getSdt() {
        return sdt;
    }
    public void setSdt(int sdt) {
        this.sdt = sdt;
    }
    public float getCccd() {
        return cccd;
    }
    public void setCccd(float cccd) {
        this.cccd = cccd;
    }
    public int getSonguoi() {
        return songuoi;
    }
    public void setSonguoi(int songuoi) {
        this.songuoi = songuoi;
    }
    public String getNgaydat() {
        return ngaydat;
    }
    public void setNgaydat(String ngaydat) {
        this.ngaydat = ngaydat;
    }
    public String getNgaytra() {
        return ngaytra;
    }
    public void setNgaytra(String ngaytra) {
        this.ngaytra = ngaytra;
    }
    public String getRoomName() {
        return roomName;
    }
    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }
    public float getTongtienphong() {
        return tongtienphong;
    }
    public void setTongtienphong(float tongtienphong) {
        this.tongtienphong = tongtienphong;
    }

}
