package Model;

public class Account {
    private String fullname;
    private String username;
    private String email;
    private String password;
    public Account(){
        super();
    }
    public Account(String fullname, String username, String email, String password){
        super();
        this.fullname = fullname;
        this.username = username;
        this.email = email;
        this.password = password;
    }
    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email= email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
