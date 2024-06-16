package Model;

public class DichVuDaDat {
    private String tensanpham;
    private float dongia;
    private String roomname;
    private int soluong;
    private float giatien;

    public DichVuDaDat(String tensanpham, float dongia, String roomname, int soluong, float giatien) {
        this.tensanpham = tensanpham;
        this.dongia = dongia;
        this.roomname = roomname;
        this.soluong =  soluong;
        this.giatien=giatien;

    }
    public String getTensanpham() {
        return tensanpham;
    }
    public void setTensanpham(String tensanpham) {
        this.tensanpham = tensanpham;
    }
    public float getDongia() {
        return dongia;
    }
    public void setDongia(float dongia) {
        this.dongia = dongia;
    }
    public String getRoomname() {
        return roomname;
    }
    public void setRoomname(String roomname) {
        this.roomname = roomname;
    }
    public int getSoluong() {
        return soluong;
    }
    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }
    public float getGiatien() {
        return giatien;
    }
    public void setGiatien(float giatien) {
        this.giatien = giatien;
    }

}







