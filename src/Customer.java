
public class Customer {

    private String id;
    private String c_Name;
    private int c_Item;
    private int c_Pay;


    public Customer() {

    }
    public Customer(String id, String c_Name, int c_Item, int c_Pay) {
        super();
        this.id = id;
        this.c_Name = c_Name;
        this.c_Item = c_Item;
        this.c_Pay = c_Pay;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getC_Name() {
        return c_Name;
    }


    public void setC_Name(String c_Name) {
        this.c_Name = c_Name;
    }


    public int getC_Item() {
        return c_Item;
    }

    public void setC_Item(int c_Item) {
        this.c_Item = c_Item;
    }

    public int getC_Pay() {
        return c_Pay;
    }

    public void setC_Pay(int c_Pay) {
        this.c_Pay = c_Pay;
    }



}