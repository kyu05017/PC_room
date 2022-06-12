
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Control {

    static ArrayList<Member> memberList = new ArrayList<>();
    static ArrayList<Product> saleItem = new ArrayList<>();
    static ArrayList<Customer> customerlist = new ArrayList<>();
    static String[] PC = {"[카운터]","[ ]","[ ]","[ ]","[ ]","[ ]","[ ]","[ ]","[ ]","[ ]","[ ]","[ ]","[ ]","[ ]","[ ]","[ ]"};
    static Scanner scanner = new Scanner(System.in);
    DecimalFormat timeSet = new DecimalFormat("00시간");
    DecimalFormat timeSet3 = new DecimalFormat("00분");
    static DecimalFormat moneySet = new DecimalFormat("#,##0원");
    DecimalFormat df2 = new DecimalFormat("####개");

    public void signUp() {

        while(true) {

            String id = null;
            System.out.println("회원가입))");
            while(true) {
                boolean pass1 = true;
                System.out.print("ID : \n");
                id = scanner.next();

                for(int i = 0; i < memberList.size(); i++) {
                    if(id.equals(memberList.get(i).getId())) {
                        System.out.println("동일한 아이디가 존재합니다.");
                        pass1 = false;
                        break;
                    }
                    else {
                        System.out.println("아이디확인))");
                        System.out.println(id+" 아이디르 사용합니다.");
                        System.out.println("1)네 2)아니요");
                        String work = scanner.next();

                        if(work.equals("1")) {
                            pass1 = true;
                            break;
                        }
                        else if(work.equals("2")) {
                            System.out.println("메세지)) 아이디를 입력해주세요");
                            pass1 = false;
                            break;
                        }
                    }
                }
                if(pass1) {break;}
            }

            String pw = null;
            while(true) {
                boolean pass2 = true;
                System.out.print("PW : \n");
                pw = scanner.next();

                Pattern passPattern1 = Pattern.compile("^(?=.*[a-zA-Z])(?=.*\\d)(?=.*\\W).{8,20}$");
                Matcher passMatcher1 = passPattern1.matcher(pw);

                if(!passMatcher1.find()){
                    System.out.println("메세지)) 비밀번호는 영문과 특수문자 숫자를 포함하며 8자 이상이어야 합니다.");
                    pass2 = false;
                }
                Pattern passPattern2 = Pattern.compile("(\\w)\\1\\1\\1");
                Matcher passMatcher2 = passPattern2.matcher(pw);

                if(passMatcher2.find()){
                    System.out.println("메세지)) 비밀번호에 동일한 문자를 과도하게 연속해서 사용할 수 없습니다.");
                    pass2 = false;
                }

                if(pw.contains(id)){
                    System.out.println("메세지)) 아이디에 비밀번호가 포함되어있습니다.");
                    pass2 = false;
                }

                if(pw.contains(" ")){
                    System.out.println("메세지)) 비밀번호에 공란이 포함되어있습니다.");
                    pass2 = false;
                }

                if(pass2) {
                    break;
                }

            }
            if(id.equals("admin")) {
                Member admin = new Member(id, pw, null, null, 0, 0, true);
                System.out.println("메세지) 관리자 계정생성 완료");
                memberList.add(admin);
                DB.memberSave();
                break;
            }
            else {

                String name = null;
                while(true) {
                    boolean pass3 = true;
                    System.out.print("Name : \n");
                    name = scanner.next();

                    String regExp_symbol = "(.*[!,@,#,^,&,*,(,)])|([!,@,#,^,&,*,(,)].*)";
                    Pattern pattern_symbol = Pattern.compile(regExp_symbol);
                    Matcher matcher_symbol = pattern_symbol.matcher(name);

                    if(matcher_symbol.find()){
                        System.out.println("메세지)) 이름에 특수문자를 포함할수 없습니다.");
                        pass3 = false;
                    }

                    if(name.contains(" ")){
                        System.out.println("메세지)) 이름에 공란이 포함되어있습니다.");
                        pass3 = false;
                    }

                    if(pass3) {
                        break;
                    }
                }

                String phone = null;
                while(true) {
                    boolean pass4 = true;
                    System.out.print("Phone : \n");
                    phone = scanner.next();

                    Pattern pattern = Pattern.compile("\\d{3}-\\d{4}-\\d{4}");
                    Matcher matcher = pattern.matcher(phone);
                    if(phone.contains("-")) {
                        if(phone.length() == 13) {}
                        else {
                            System.out.println("메세지)) 올바르지 않는 번호입니다.1");
                            pass4 = false;
                        }
                    }
                    else {
                        if(phone.length() == 11) {}
                        else {
                            System.out.println("메세지)) 올바르지 않는 번호입니다.2");
                            pass4 = false;
                        }
                    }
                    if (matcher.matches()) {
                        pass4 = true;
                    }
                    else if(!matcher.matches() &&  phone.length() == 11){
                        String str1 = phone.substring(0, 3);
                        String str2 = phone.substring(3, 7);
                        String str3 = phone.substring(7, 11);
                        phone = str1+"-"+str2+"-"+str3;
                        pass4 = true;
                    }

                    if(pass4){
                        break;
                    }
                }
                System.out.println("메세지)) 회원가입이 완료 되었습니다.");
                System.out.println("회원정보))");
                System.out.println("ID : " + id);
                System.out.println("PW : " + pw);
                System.out.println("Name : " + name);
                System.out.println("Phone : "+ phone);
                Member member = new Member(id, pw, name, phone, 0, 0, false);
                memberList.add(member);
                DB.memberSave();
                break;
            }

        }
    }

    public String logIn() {

        System.out.println("로그인))");
        System.out.println("ID : ");
        String id = scanner.next();
        System.out.println("PW : ");
        String pw = scanner.next();

        for(Member temp : memberList) {
            if(id.equals("admin") && pw.equals(temp.getPw())) {
                return "admin";
            }
            else if(id.equals(temp.getId()) && pw.equals(temp.getPw())) {
                return temp.getId();
            }
        }
        return "false";
    }

    public void addTime(String id, int time) {

        int change = 0;
        for(Member temp : memberList) {
            if(id.equals(temp.getId())) {
                if(time == 1) {
                    System.out.println("결제)) 1시간 1,000원");
                    System.out.println("메세지)) 지불할 금액을 입력하세요");
                    System.out.println("입력 : ");
                    int money = scanner.nextInt();

                    if(money == 1000) {
                        System.out.println("메세지)) 1시간 추가되었습니다.");
                        temp.setTime(temp.getTime() + 60);
                        System.out.println(id+"님의 잔여시간 " + temp.getTime() + "분");
                    }
                    else if(money >= 1000) {
                        change = money - 1000;
                        String new_money = moneySet.format(change);
                        System.out.println("메세지)) 1시간 추가되었습니다. 거스름돈은 " + new_money + "입니다.");
                        temp.setTime(temp.getTime() + 60);
                        System.out.println(id+"님의 잔여시간 " + temp.getTime() + "분");
                    }
                    else {
                        System.out.println("메세지)) 결제금액이 부족합니다.");
                        return;
                    }
                }
                else if(time == 2) {
                    System.out.println("결제)) 2시간 2,000원");
                    System.out.println("메세지)) 지불할 금액을 입력하세요");
                    System.out.println("입력 : ");
                    int money = scanner.nextInt();

                    if(money == 2000) {
                        System.out.println("메세지)) 2시간 추가되었습니다.");
                        temp.setTime(temp.getTime() + 120);
                        System.out.println(id+"님의 잔여시간 " + temp.getTime() + "분");
                    }
                    else if(money >= 2000) {
                        change = money - 2000;
                        String new_money = moneySet.format(change);
                        System.out.println("메세지)) 2시간 추가되었습니다. 거스름돈은 " + new_money + "입니다.");
                        temp.setTime(temp.getTime() + 120);
                        System.out.println(id+"님의 잔여시간 " + temp.getTime() + "분");
                    }
                    else {
                        System.out.println("메세지)) 결제금액이 부족합니다.");
                        return;
                    }
                }
                else if(time == 3) {
                    System.out.println("결제)) 5시간 5,000원");
                    System.out.println("메세지)) 지불할 금액을 입력하세요");
                    System.out.println("입력 : ");
                    int money = scanner.nextInt();

                    if(money == 5000) {
                        System.out.println("메세지)) 5시간 추가되었습니다.");
                        temp.setTime(temp.getTime() + 300);
                        System.out.println(id+"님의 잔여시간 " + temp.getTime() + "분");
                    }
                    else if(money >= 5000) {
                        change = money - 5000;
                        String new_money = moneySet.format(change);
                        System.out.println("메세지)) 5시간 추가되었습니다. 거스름돈은 " + new_money + "입니다.");
                        temp.setTime(temp.getTime() + 300);
                        System.out.println(id+"님의 잔여시간 " + temp.getTime() + "분");
                    }
                    else {
                        System.out.println("메세지)) 결제금액이 부족합니다.");
                        return;
                    }
                }
                else {
                    int a = time/60;
                    int b = time%60;
                    if(b >= 60){
                        a += 1;
                        b = 0;
                    }

                    if(b == 0) {
                        int pay = a*1000;
                        String new_money = moneySet.format(pay);
                        System.out.printf("결제)) %d시간 %s\n",a,new_money);
                        System.out.println("메세지)) 지불할 금액을 입력하세요");
                        System.out.println("입력 : ");
                        int money = scanner.nextInt();

                        if(money == (pay)) {
                            System.out.println("메세지)) "+a+"시간 추가되었습니다.");
                            temp.setTime(temp.getTime() + time);
                            System.out.println(id+"님의 잔여시간 " + temp.getTime() + "분");
                        }
                        else if(money >= (pay)) {
                            change = money - pay;
                            String new_money2 = moneySet.format(change);
                            System.out.println("메세지)) "+a+"시간 추가되었습니다. 거스름돈은 " + new_money2 + "입니다.");
                            temp.setTime(temp.getTime() + time);
                            System.out.println(id+"님의 잔여시간 " + temp.getTime() + "분");
                        }
                        else {
                            System.out.println("메세지)) 결제금액이 부족합니다.");
                            return;
                        }
                    }
                    else {
                        int pay = 0;
                        int nb = 0;
                        if(b < 10) {
                            nb  = 0;
                        }
                        else {
                            nb = b / 10;

                        }
                        pay = (a*1000)+(nb*100);
                        String new_money = moneySet.format(pay);
                        System.out.printf("결제)) %d시간 %d분 %s원\n",a,b,new_money);
                        System.out.println("메세지)) 지불할 금액을 입력하세요");
                        System.out.println("입력 : ");
                        int money = scanner.nextInt();

                        if(money == pay) {
                            System.out.println("메세지)) "+a+"시간 "+b+"분 추가되었습니다.");
                            temp.setTime(temp.getTime() + time);
                            System.out.println(id+"님의 잔여시간 " + temp.getTime() + "분");
                        }
                        else if(money >= pay) {
                            change = money - pay;
                            String new_money2 = moneySet.format(change);
                            System.out.println("메세지)) "+a+"시간 "+b+"분 추가되었습니다. 거스름돈은 " + new_money2 + "입니다.");
                            temp.setTime(temp.getTime() + time);
                            System.out.println(id+"님의 잔여시간 " + temp.getTime() + "분");
                        }
                        else {
                            System.out.println("메세지)) 결제금액이 부족합니다.");
                            return;
                        }
                    }
                }
            }
        }
        DB.memberSave();
    }

    public void startPC(String id) {
        System.out.println("자리선택))");
        for(int i = 0; i < PC.length; i++) {
            System.out.print(PC[i]);
            if(i%5==0)System.out.println();
        }
        System.out.println("입력 : ( 1 ~ 15 )");
        while(true) {
            try {
                int seat = scanner.nextInt();

                if(seat == 0) {
                    System.out.println("메세지)) 존재하지 않는 자리 입니다.");
                }
                else if(seat >= 16) {
                    System.out.println("메세지)) 존재하지 않는 자리 입니다.");
                }
                else if(PC[seat].equals("[ ]")) {
                    PC[seat]="[o]";
                }
                else {
                    System.out.println("메세지)) 사용중인 자리 입니다.");
                }

                for(Member temp : memberList) {
                    if(temp.getId().equals(id)) {
                        temp.setSeat(seat);
                        temp.setUse(true);
                        System.out.println("메세지)) "+seat+"번 자리 사용이 시작되었습니다.");
                        DB.memberSave();
                        return;
                    }
                }

            }
            catch (Exception e) {
                System.out.println("메세지)) 입력오류");
            }
        }
    }

    public void ChangePC(String id) {
        System.out.println("자리선택))");
        int my_seat = 0;
        for(Member temp : memberList) {
            if(temp.getId().equals(id)){
                my_seat = temp.getSeat();
            }
        }
        for(int i = 0; i < PC.length; i++) {
            System.out.print(PC[i]);
            if(i%5==0)System.out.println();
        }
        System.out.println("입력 : ( 1 ~ 15 )");
        while(true) {
            try {
                int seat = scanner.nextInt();

                if(seat == 0) {
                    System.out.println("메세지)) 존재하지 않는 자리 입니다.");
                }
                else if(seat >= 16) {
                    System.out.println("메세지)) 존재하지 않는 자리 입니다.");
                }
                else if(seat == my_seat) {
                    System.out.println("메세지)) 현재 이용중인 자리 입니다.");
                }
                else if(PC[seat].equals("[ ]")) {
                    PC[my_seat]="[ ]";
                    PC[seat]="[o]";
                }
                else {
                    System.out.println("메세지)) 사용중인 자리 입니다.");
                }

                for(Member temp : memberList) {
                    if(temp.getId().equals(id)) {
                        temp.setSeat(seat);
                        temp.setUse(true);
                        System.out.println("메세지)) "+seat+"번 자리로 변경되었습니다.");
                        DB.memberSave();
                        return;
                    }
                }

            }
            catch (Exception e) {
                System.out.println("메세지)) 입력오류");
            }
        }
    }

    public void endPC(String id) {
        int my_seat = 0;
        for(Member temp : memberList) {
            if(temp.getId().equals(id)) {
                my_seat = temp.getSeat();
                PC[my_seat]="[ ]";
                temp.setSeat(0);
                temp.setUse(false);
                System.out.println("메세지)) PC사용을 종료합니다. 이용해주셔서 감사합니다.");
                DB.memberSave();
                return;
            }
        }
    }

    public void members() {
        System.out.println("회원목록))");
        System.out.println("번호 아이디\t\t이용자리\t남은시간\t이용금액");
        int i = 0;
        for(Member temp : Control.memberList) {
            if(temp.getId().equals("admin")) {

            }
            else {
                int a = temp.getTime()/60;
                int b = temp.getTime()%60;
                String aa = null;
                String bb = null;
                String cc = null;
                int nb = b;
                if(b >= 60){
                    a += 1;
                    nb = 0;
                }
                else {
                    nb = b / 10;
                }
                if(temp.getSeat() == 0) {
                    if(a == 0 && b == 0) {
                        String money = moneySet.format(a*1000);
                        System.out.printf("%d,%s\t%s\t%s\t%s\n",i,temp.getId(),"오프라인","시간없음",money);
                    }
                    else if(b == 0){
                        aa = timeSet.format(a);
                        String money = moneySet.format(a*1000);
                        System.out.printf("%d,%s\t%s\t%s\t%s\n",i,temp.getId(),"오프라인",aa,money);
                    }
                    else {
                        bb = timeSet.format(a);
                        cc = timeSet3.format(b);
                        String money = moneySet.format(a*1000+nb*100);
                        System.out.printf("%d,%s\t%s\t%s%s\t%s\n",i,temp.getId(),"오프라인",bb,cc,money);
                    }
                }
                else {
                    if(a == 0 && b == 0) {
                        String money = moneySet.format(a*1000);
                        System.out.printf("%d,%s\t%s\t%s\t%s\n",i,temp.getId(),"오프라인","시간없음",money);
                    }
                    else if(b == 0){
                        aa = timeSet.format(a);
                        String money = moneySet.format(a*1000);
                        System.out.printf("%d,%s\t%d\t%s\t%s\n",i,temp.getId(),temp.getSeat(),aa,money);
                    }
                    else {
                        bb = timeSet.format(a);
                        cc = timeSet3.format(b);
                        String money = moneySet.format(a*1000+nb*100);
                        System.out.printf("%d,%s\t%d\t%s%s\t%s\n",i,temp.getId(),temp.getSeat(),bb,cc,money);
                    }
                }
            }
            i++;
        }
    }

    public void changeTime () {

        int insert = 0;
        while(true) {
            try {
                if(insert > Control.memberList.size()) {
                    System.out.println("메세지)) 존재하지 않는 회원입니다.");
                }
                else {
                    insert = Control.scanner.nextInt();
                }
                break;
            }
            catch(Exception e) {
                System.out.println("메세지)) 숫자만 입력가능 합니다.");
            }
        }
        System.out.println("수정할 시간 입력 : ");
        int time = 0;
        while(true) {
            try {
                time = Control.scanner.nextInt();
                break;
            }
            catch(Exception e) {
                System.out.println("메세지)) 숫자만 입력가능 합니다.");
            }
        }
        memberList.get(insert).setTime(time);
        DB.memberSave();
    }

    public void forcedLogout () {
        int insert = 0;
        while(true) {
            try {
                if(insert > Control.memberList.size()) {
                    System.out.println("메세지)) 존재하지 않는 회원입니다.");
                }
                else {
                    insert = Control.scanner.nextInt();
                }
                break;
            }
            catch(Exception e) {
                System.out.println("메세지)) 숫자만 입력가능 합니다.");
            }
        }
        memberList.get(insert).setSeat(0);
        memberList.get(insert).setUse(false);
        DB.memberSave();
    }

    public void sale() {

        Hashtable <String, Integer> map = new Hashtable<>();
        for (Member temp1 : memberList) {
            int ticketfee = 0;
            for (Member temp2 : memberList) {
                if (temp1.getId().equals(temp2.getId())) {
                    ticketfee += temp1.getTime();
                }
            }
            map.put(temp1.getId(), ticketfee);
        }
        for(String temp : map.keySet()) {
            if(!temp.equals("admin")) {
                int a = map.get(temp)/60;
                int b = map.get(temp)%60;
                String new_money = null;
                if(b == 0) {
                    new_money = moneySet.format(a*1000);
                }
                if(b >= 60) {
                    b = 0;
                    a += 1;
                }
                else {
                    a = a*1000;
                    b = b*100;
                    new_money = moneySet.format(a+b);
                }
                System.out.println(temp+ " " + new_money);
            }
        }
        int totalsales = 0;
        for (Member temp1 : memberList) {
            totalsales += temp1.getTime();
        }
        int a = totalsales/60;
        int b = totalsales%60;
        String new_money = null;
        if(b == 0) {
            new_money = moneySet.format(a*1000);
        }
        if(b >= 60) {
            b = 0;
            a += 1;
        }
        else {
            a = a*1000;
            b = b*100;
            new_money = moneySet.format(a+b);
        }
        System.out.println("총 매출액 : " + new_money);
        System.out.println("---------------------------------------------");
    }

    public void findid() {
        boolean pass = true;
        String name = null;
        String phone = null;
        System.out.println("아이디 찾기))");
        System.out.println("Name : ");
        name = scanner.next();

        for(Member temp : memberList) {
            if(temp.getName().equals(name)) {
                pass = true;
                break;
            }
            else {
                System.out.println("메세지)) 존재하지 않는 이름입니다.");
                pass = false;
                break;
            }
        }
        if(pass) {
            System.out.println("Phone : ");
            phone = scanner.next();

            Pattern pattern = Pattern.compile("\\d{3}-\\d{4}-\\d{4}");
            Matcher matcher = pattern.matcher(phone);

            if(phone.contains("-")) {
                if(phone.length() == 13) {}
                else {
                    System.out.println("메세지)) 올바르지 않는 번호입니다.1");
                    pass = false;
                }
            }
            else {
                if(phone.length() == 11) {}
                else {
                    System.out.println("메세지)) 올바르지 않는 번호입니다.2");
                    pass = false;
                }
            }
            if (matcher.matches()) {
                pass = true;
            }
            else if(!matcher.matches() &&  phone.length() == 11){
                String str1 = phone.substring(0, 3);
                String str2 = phone.substring(3, 7);
                String str3 = phone.substring(7, 11);
                phone = str1+"-"+str2+"-"+str3;
                pass = true;
            }

            for(Member temp : memberList) {
                if(phone.equals(temp.getPhone())) {
                    pass = true;
                    break;
                }
                else {
                    System.out.println("메세지)) 존재하지 않는 번호입니다.");
                    pass = false;
                    break;
                }
            }
        }
        if(pass) {
            for(Member temp : memberList) {
                if(phone.equals(temp.getPhone()) && name.equals(temp.getName())) {
                    System.out.println("메세지)) 회원님의 아이디는 " + temp.getId() + "입니다.");
                }
            }
        }
    }

    public void findpw() {
        boolean pass = true;
        String id = null;
        String phone = null;
        System.out.println("비밀번호 찾기))");
        System.out.println("ID : ");
        id = scanner.next();

        for(Member temp : memberList) {
            if(temp.getId().equals(id)) {
                pass = true;
                break;
            }
            else {
                System.out.println("메세지)) 존재하지 않는 이름입니다.");
                pass = false;
                break;
            }
        }
        if(pass) {
            System.out.println("Phone : ");
            phone = scanner.next();

            Pattern pattern = Pattern.compile("\\d{3}-\\d{4}-\\d{4}");
            Matcher matcher = pattern.matcher(phone);

            if(phone.contains("-")) {
                if(phone.length() == 13) {}
                else {
                    System.out.println("메세지)) 올바르지 않는 번호입니다.1");
                    pass = false;
                }
            }
            else {
                if(phone.length() == 11) {}
                else {
                    System.out.println("메세지)) 올바르지 않는 번호입니다.2");
                    pass = false;
                }
            }
            if (matcher.matches()) {
                pass = true;
            }
            else if(!matcher.matches() &&  phone.length() == 11){
                String str1 = phone.substring(0, 3);
                String str2 = phone.substring(3, 7);
                String str3 = phone.substring(7, 11);
                phone = str1+"-"+str2+"-"+str3;
                pass = true;
            }

            for(Member temp : memberList) {
                if(phone.equals(temp.getPhone())) {
                    pass = true;
                    break;
                }
                else {
                    System.out.println("메세지)) 존재하지 않는 번호입니다.");
                    pass = false;
                    break;
                }
            }
        }
        if(pass) {
            for(Member temp : memberList) {
                if(phone.equals(temp.getPhone()) && id.equals(temp.getId())) {
                    System.out.println("새로 사용할 비밀번호를 입력하세요.");
                    String pw = null;
                    while(true) {
                        boolean pass2 = true;
                        System.out.print("PW : \n");
                        pw = scanner.next();

                        Pattern passPattern1 = Pattern.compile("^(?=.*[a-zA-Z])(?=.*\\d)(?=.*\\W).{8,20}$");
                        Matcher passMatcher1 = passPattern1.matcher(pw);

                        if(!passMatcher1.find()){
                            System.out.println("메세지)) 비밀번호는 영문과 특수문자 숫자를 포함하며 8자 이상이어야 합니다.");
                            pass2 = false;
                        }
                        Pattern passPattern2 = Pattern.compile("(\\w)\\1\\1\\1");
                        Matcher passMatcher2 = passPattern2.matcher(pw);

                        if(passMatcher2.find()){
                            System.out.println("메세지)) 비밀번호에 동일한 문자를 과도하게 연속해서 사용할 수 없습니다.");
                            pass2 = false;
                        }

                        if(pw.contains(id)){
                            System.out.println("메세지)) 아이디에 비밀번호가 포함되어있습니다.");
                            pass2 = false;
                        }

                        if(pw.contains(" ")){
                            System.out.println("메세지)) 비밀번호에 공란이 포함되어있습니다.");
                            pass2 = false;
                        }

                        if(pass2) {
                            break;
                        }
                    }
                    temp.setPw(pw);
                    DB.memberSave();
                    return;
                }
            }
        }
    }

    public void myinfo(String id) {
        System.out.println(id + "님의 정보");
        for(Member temp : memberList) {
            if(temp.getId().equals(id)) {
                System.out.println("이름 : "+temp.getName());
                System.out.println("전화번호 : "+temp.getPhone());
            }
        }
        System.out.println("1)비밀번호 변경 2)회원탈퇴 3)돌아가기");
        String work = scanner.next();
        if(work.equals("1")) {
            ChangePw(id);
        }
        else if(work.equals("2")) {
            signout(id);
        }
        else if(work.equals("3")) {
            return;
        }
    }

    public void ChangePw(String id) {
        boolean pass = true;
        String Pw = null;
        System.out.println("비밀번호 변경))");
        System.out.println("PW : ");
        Pw = scanner.next();

        for(Member temp : memberList) {
            if(temp.getPw().equals(Pw)) {
                pass = true;
                break;
            }
            else {
                System.out.println("메세지)) 비밀번호가 틀렸습니다.");
                pass = false;
                break;
            }
        }
        if(pass) {
            for(Member temp : memberList) {
                if(Pw.equals(temp.getPw()) && id.equals(temp.getId())) {
                    System.out.println("새로 사용할 비밀번호를 입력하세요.");
                    String pw = null;
                    while(true) {
                        boolean pass2 = true;
                        System.out.print("PW : \n");
                        pw = scanner.next();

                        Pattern passPattern1 = Pattern.compile("^(?=.*[a-zA-Z])(?=.*\\d)(?=.*\\W).{8,20}$");
                        Matcher passMatcher1 = passPattern1.matcher(pw);

                        if(!passMatcher1.find()){
                            System.out.println("메세지)) 비밀번호는 영문과 특수문자 숫자를 포함하며 8자 이상이어야 합니다.");
                            pass2 = false;
                        }
                        Pattern passPattern2 = Pattern.compile("(\\w)\\1\\1\\1");
                        Matcher passMatcher2 = passPattern2.matcher(pw);

                        if(passMatcher2.find()){
                            System.out.println("메세지)) 비밀번호에 동일한 문자를 과도하게 연속해서 사용할 수 없습니다.");
                            pass2 = false;
                        }

                        if(pw.contains(id)){
                            System.out.println("메세지)) 아이디에 비밀번호가 포함되어있습니다.");
                            pass2 = false;
                        }

                        if(pw.contains(" ")){
                            System.out.println("메세지)) 비밀번호에 공란이 포함되어있습니다.");
                            pass2 = false;
                        }

                        if(pass2) {
                            break;
                        }
                    }
                    temp.setPw(pw);
                    DB.memberSave();
                    return;
                }
            }
        }
    }

    public void signout(String id) {
        boolean pass = true;
        String Pw = null;
        System.out.println("비밀번호 변경))");
        System.out.println("PW : ");
        Pw = scanner.next();

        for(Member temp : memberList) {
            if(temp.getPw().equals(Pw)&& id.equals(temp.getId())) {
                pass = true;
                break;
            }
            else {
                System.out.println("메세지)) 비밀번호가 틀렸습니다.");
                pass = false;
                break;
            }
        }
        int index = 0;
        int i = 0;
        for(Member temp : memberList) {
            if(Pw.equals(temp.getPw()) && id.equals(temp.getId())) {
                index = i;
                break;
            }
            i++;
        }
        memberList.remove(index);
        DB.memberSave();
        return;
    }

    public void addItem() {

        System.out.println("재고 및 품목 추가))");
        System.out.println("1) 품목 추가 2) 재고 추가");

        String work3 = scanner.next();
        while(true) {

            if(work3.equals("1")) {
                System.out.println("품목추가))");
                System.out.println();
                System.out.print("상품이름 : \n");
                String name = scanner.next();

                int price = 0;
                int item = 0;
                boolean pass1 = true;
                while(pass1) {
                    try {
                        System.out.print("상품가격 : \n");
                        price = scanner.nextInt();
                        if(price <= 0) {
                            System.out.println("메세지) 가격 오류 입니다.");
                            pass1 = true;
                        }
                        else {
                            pass1 = false;
                        }
                    }
                    catch(Exception e) {
                        System.out.println("메세지)) 잘못된 입력입니다.");
                        pass1 = true;
                    }
                }
                boolean pass2 = true;
                while(pass2) {
                    try {
                        System.out.print("발주할 물량 : \n");
                        item = scanner.nextInt();
                        if(item <= 0) {
                            System.out.println("메세지) 최소 1개부터 발주 가능합니다.");
                            pass2 = true;
                        }
                        else {
                            pass2 = false;
                        }
                    }
                    catch(Exception e) {
                        System.out.println("메세지)) 잘못된 입력입니다.");
                        pass2 = true;
                    }
                }
                System.out.println("-------------------");
                System.out.println("상품등록 및 발주))");
                System.out.println("상품명	: " + name);
                System.out.println("가격	: " + moneySet.format(price));
                System.out.println("발주물량	: " + df2.format(item));
                System.out.println("-------------------");
                System.out.println("1)확인 2)취소");
                String work4 = scanner.next();

                if(work4.equals("1")) {
                    System.out.println("메세지)) 발주 및 등록 완료");
                    Product product = new Product(name, item, price);
                    saleItem.add(product);
                    DB.itemSave();
                    break;
                }
                else if(work4.equals("2")) {
                    System.out.println("메세지)) 이전 메뉴로 돌아갑니다.");
                    break;
                }
            }
            else if(work3.equals("2")) {
                System.out.println("재고 추가))");
                if(saleItem.size() == 0) {
                    System.out.println("메세지))추가 발주할 상품이 없습니다.");
                }
                else {
                    int i = 0;
                    System.out.println("| 순번 | 제품 | 가격 | 재고 |");
                    for(Product temp : saleItem) {
                        System.out.printf("| %d | %s | %s | %s |\n",(i+1),temp.getName(),moneySet.format(temp.getMoney()),df2.format(temp.getItem()));
                        i++;
                    }
                    System.out.print("번호입력 : \n");
                    try {
                        boolean pass = true;
                        int add_Item = 0;
                        int num = 0;
                        while(pass) {
                            num = scanner.nextInt();
                            if(num-1 > saleItem.size()) {
                                System.out.println("메세지)) 존재하지 않는 상품입니다.");
                                pass = false;
                            }
                        }
                        boolean pass2 = true;
                        while(pass2) {
                            add_Item = scanner.nextInt();
                            if(add_Item <= 0) {
                                System.out.println("메세지)) 최소 1개 이상 발주 가능합니다.");
                            }
                        }
                        num -= 1;
                        System.out.println("-------------------");
                        System.out.println("상품등록 및 발주))");
                        System.out.println("상품명	: " + saleItem.get(num).getName());
                        System.out.println("가격	: " + moneySet.format(saleItem.get(num).getMoney()));
                        System.out.println("발주물량	: " + df2.format(saleItem.get(num).getItem()));
                        System.out.println("-------------------");
                        System.out.println("1)확인 2)취소");
                        String work4 = scanner.next();

                        if(work4.equals("1")) {
                            System.out.println("메세지)) 발주 완료");
                            saleItem.get(num).setItem(add_Item);
                            DB.itemSave();
                            break;
                        }
                        else if(work4.equals("2")) {
                            System.out.println("메세지)) 이전 메뉴로 돌아갑니다.");
                            break;
                        }
                    }

                    catch (Exception e) {
                        System.out.println("잘못된 입력입니다.");
                    }
                }
            }
        }
    }

    public void removeItem() {
        System.out.println("상품삭제))");
        if(saleItem.size() == 0) {
            System.out.println("메세지))삭제할 상품이 없습니다.");
        }
        else {
            int i = 0;
            System.out.println("| 순번 | 제품 | 가격 | 재고 |");
            for(Product temp : saleItem) {
                System.out.printf("| %d | %s | %s | %s |\n",(i+1),temp.getName(),moneySet.format(temp.getMoney()),df2.format(temp.getItem()));
                i++;
            }
            System.out.print("번호입력 : \n");
            try {
                int num = scanner.nextInt();
                if(num > saleItem.size()) {
                    System.out.println("메세지)) 존재하지 않는 상품입니다.");
                }
                else {
                    saleItem.remove(num-1);
                    DB.itemSave();
                }
            }
            catch (Exception e) {
                System.out.println("잘못된 입력입니다.");
            }
        }
    }

    public void itemCheckList() {
        System.out.println("상품 목록))");
        int i = 0;
        System.out.println("| 순번 | 제품 | 가격 | 재고 |");
        for(Product temp : saleItem) {
            System.out.printf("| %d | %s | %s | %s |\n",(i+1),temp.getName(),moneySet.format(temp.getMoney()),df2.format(temp.getItem()));
            i++;
        }
    }

    public void buy(String id,int num) {
        Customer customer = new Customer(id,saleItem.get(num).getName(), 1, saleItem.get(num).getMoney());
        customerlist.add(customer);
        saleItem.get(num).setItem((saleItem.get(num).getItem()-1));
        DB.itemSave();
        DB.Save();
    }

    public void buylist(String id) {
        Hashtable <String, Integer> map = new Hashtable<>();
        DecimalFormat df2 = new DecimalFormat("#,##0원");

        for (Customer temp : customerlist) {
            int ticketfee = 0;
            for (Customer temp2 : customerlist) {
                if(id.equals(temp.getId())) {
                    if (temp.getC_Name().equals(temp2.getC_Name())) {
                        ticketfee += temp.getC_Pay();
                    }
                }
            }
            map.put(temp.getC_Name(), ticketfee);
        }

        for(String temp : map.keySet()) {
            String new_money = df2.format(map.get(temp));
            System.out.println("상품 "+temp+ " " + new_money);
        }
    }
    public void buy_cancle() {
        Hashtable <String, Integer> map = new Hashtable<>();
        for(Customer temp : customerlist) {
            System.out.println(temp.getC_Name() + " " + temp.getC_Item());
        }
        for (Customer temp : customerlist) {
            int ticketfee = 0;
            for (Customer temp2 : customerlist) {
                if (temp.getC_Name().equals(temp2.getC_Name())) {
                    ticketfee += temp.getC_Item();
                }
            }
            map.put(temp.getC_Name(), ticketfee);
        }
        for(Product temp : saleItem) {
            for(String temp2 : map.keySet()) {
                if(temp.getName().equals(temp2)) {
                    System.out.println(map.get(temp2));
                    System.out.println(temp.getItem());
                    temp.setItem(temp.getItem()+map.get(temp2));
                    DB.itemSave();
                    break;
                }
            }
        }
    }

}