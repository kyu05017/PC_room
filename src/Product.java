
public class Product {

    private String name;
    private int item;
    private int money;

    public Product() {
    }

    public Product(String name, int item, int money) {
        this.name = name;
        this.item = item;
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getItem() {
        return item;
    }

    public void setItem(int item) {
        this.item = item;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }


}