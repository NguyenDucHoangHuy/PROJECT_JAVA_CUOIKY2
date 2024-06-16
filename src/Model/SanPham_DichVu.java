package Model;

public class SanPham_DichVu {
    private String TenDichVu;
    private float DonGia;
    public SanPham_DichVu() {

    }
    public SanPham_DichVu(String TenDichVu, float DonGia){
            this.TenDichVu = TenDichVu;
            this.DonGia = DonGia;
    }
    public String getTenDichVu() {
        return TenDichVu;
    }
    public void setTenDichVu(String TenDichVu) {
        this.TenDichVu = TenDichVu;
    }
    public float getDonGia() {
        return DonGia;
    }
    public void setDonGia(float donGia) {
        this.DonGia = donGia;
    }
}

