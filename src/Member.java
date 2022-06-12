
public class Member {

    private String id;
    private String pw;
    private String name;
    private String phone;
    private int time;
    private int seat;
    private boolean use;

    public Member() {}

    public Member(String id, String pw, String name, String phone, int time, int seat, boolean use) {
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.phone = phone;
        this.time = time;
        this.seat = seat;
        this.use = use;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public boolean isUse() {
        return use;
    }

    public void setUse(boolean use) {
        this.use = use;
    }


}