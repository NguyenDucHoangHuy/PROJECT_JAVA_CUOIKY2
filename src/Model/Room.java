package Model;

public class Room {
    private String RoomName;
    private String trangthai;
    public Room(String roomName, String trangthai) {
        this.RoomName = roomName;
        this.trangthai = trangthai;

    }
    public String getRoomName() {
        return RoomName;

    }
    public void setRoomName(String roomName) {
        RoomName = roomName;
    }
    public String getTrangthai() {
        return trangthai;

    }
    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }
}

