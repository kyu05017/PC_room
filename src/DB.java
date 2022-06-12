import java.io.FileInputStream;
import java.io.FileOutputStream;

public class DB {

    static void memberSave() {

        try {
            FileOutputStream outputStream = new FileOutputStream("D:/pcMemberDB.txt");

            for(Member temp : Control.memberList) {
                String memberSave = temp.getId()+","+temp.getPw()+","+temp.getName()+","
                        +temp.getPhone()+","+temp.getTime()+","+temp.getSeat()+","+temp.isUse()+"\n";
                outputStream.write(memberSave.getBytes());
            }
        }
        catch(Exception e) {

        }
    }


    static void memberLoad() {

        try {
            FileInputStream inputStream = new FileInputStream("D:/pcMemberDB.txt");
            byte[] bytes = new byte[1024];
            inputStream.read(bytes);
            String file = new String(bytes);
            String[]list = file.split("\n");
            int i = 0;
            for(String temp : list) {
                if( i+1 == list.length ) {
                    break;
                }
                String[] filed = temp.split(",");
                Member temp2 = new Member(filed[0], filed[1], filed[2], filed[3],Integer.parseInt(filed[4]), Integer.parseInt(filed[5]), Boolean.parseBoolean(filed[6]));
                Control.memberList.add(temp2);
                i++;
            }

        }
        catch(Exception e) {
        }
    }

    static void itemSave() {
        try {
            FileOutputStream outputStream = new FileOutputStream("D:/itemList.txt");
            for(Product temp : Control.saleItem) {
                String Save = temp.getName()+","+temp.getItem()+","+temp.getMoney()+"\n";
                outputStream.write(Save.getBytes());
            }
        }
        catch(Exception e) {

        }
    }

    static void itemLoad() {
        try {
            FileInputStream inputStream = new FileInputStream("D:/itemList.txt");
            byte[] bytes = new byte[1024];
            inputStream.read(bytes);
            String file = new String(bytes);
            String[]list = file.split("\n");
            int i = 0;
            for(String temp : list) {
                if( i+1 == list.length ) {
                    break;
                }
                String[] filed = temp.split(",");
                Product temp2 = new Product(filed[0],Integer.parseInt(filed[1]),Integer.parseInt(filed[2]));
                Control.saleItem.add(temp2);
                i++;
            }

        }
        catch(Exception e) {
        }
    }

    static void Save() {
        try {
            FileOutputStream outputStream = new FileOutputStream("D:/List.txt");
            for(Customer temp : Control.customerlist) {
                String Save = temp.getId()+","+temp.getC_Name()+","+temp.getC_Item()+","+temp.getC_Pay()+"\n";
                outputStream.write(Save.getBytes());
            }
        }
        catch(Exception e) {

        }
    }

    static void Load() {
        try {
            FileInputStream inputStream = new FileInputStream("D:/List.txt");
            byte[] bytes = new byte[1024];
            inputStream.read(bytes);
            String file = new String(bytes);
            String[]list = file.split("\n");
            int i = 0;
            for(String temp : list) {
                if( i+1 == list.length ) {
                    break;
                }
                String[] filed = temp.split(",");
                Customer temp2 = new Customer(filed[0],filed[1],Integer.parseInt(filed[2]),Integer.parseInt(filed[3]));
                Control.customerlist.add(temp2);
                i++;
            }

        }
        catch(Exception e) {
        }
    }
}