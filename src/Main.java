
import java.text.DecimalFormat;
import java.util.Hashtable;


public class Main {

    public static void main(String[] args) {
        Control con = new Control();
        Main main = new Main();
        PcUse use = new PcUse();
        DB.memberLoad();
        DB.itemLoad();
        DB.Load();

        for(Member temp : Control.memberList) {
            if(temp.isUse() == true && temp.getTime() != 0) {
                if(temp.getId().equals("admim")) {

                }
                else {
                    Control.PC[temp.getSeat()]="[o]";
                }
            }
        }
        use.start();
        while(true) {

            for(Member temp : Control.memberList) {
                if(temp.isUse() == true && temp.getTime() == 0) {
                    if(temp.getId().equals("admim")) {

                    }
                    else {
                        Control.PC[temp.getSeat()]="[ ]";
                        temp.setUse(false);
                        temp.setSeat(0);
                        Control.PC[0]="[카운터]";
                    }
                }
            }
            System.out.println("-------------------------------------------------");
            System.out.println("현재자리))");

            for(int i = 0; i < Control.PC.length; i++) {
                System.out.print(Control.PC[i]);
                if(i%5==0)System.out.println();
            }
            System.out.println();
            System.out.println("자바 PC방))");
            System.out.println("1)회원가입 2)로그인 3)아이디찾기 4)비밀번호찾기 5)종료");
            System.out.println("-------------------------------------------------");

            String tesk = Control.scanner.next();

            if(tesk.equals("1")) {
                con.signUp();
            }
            else if(tesk.equals("2")) {
                String result = con.logIn();

                if(result.equals("admin")) {
                    System.out.println("메세지)) 관리자 로그인");
                    main.adminMenu();
                }
                else if(result.equals("false")) {
                    System.out.println("메세지)) 로그인에 실패 했습니다.");
                }
                else {
                    System.out.printf("메세지)) %s님 방문해 주셔서 감사힙니다.\n",result);
                    main.memberMenu(result);
                }
            }
            else if(tesk.equals("3")) {
                con.findid();
            }
            else if(tesk.equals("4")) {
                con.findpw();
            }
            else if(tesk.equals("5")) {
                System.out.println("프로그램 종료))");
                System.out.println("관리자 비밀번호 입력 : ");
                String admin_Pw = Control.scanner.next();
                boolean exit = false;
                for(Member temp : Control.memberList) {
                    if(admin_Pw.equals(temp.getPw())) {
                        System.out.println("메세지)) 시스템을 종료 합니다.");
                        exit = true;
                    }
                    else {
                        System.out.println("메세지)) 관리자만 이용가능합니다.");
                        exit = false;
                    }
                }
                if(exit) {
                    break;
                }
            }
            else {
                System.out.println("메세지)) 존재하지 않는 메뉴 입니다.");
            }
        }
    }

    public void memberMenu(String id) {

        Control con = new Control();

        while(true) {
            System.out.println("--------------------------------------------------------");
            System.out.println("현재 자리))");
            //저장된 자리배열을 보여줌
            for(int i = 0; i < Control.PC.length; i++) {
                System.out.print(Control.PC[i]);
                if(i%5==0)System.out.println();
                //0을 제외하고 5개마다 줄바꿈
            }
            for(Member temp : Control.memberList) {
                if(id.equals(temp.getId())) {
                    if(temp.isUse() == true) {
                        System.out.println("이용중인 자리 "+temp.getSeat() + "번");
                    }
                    else {
                        System.out.println("메세지)) 자리를 선택해주세요.");
                    }
                    if(temp.getTime() == 0) {
                        System.out.println("메세지)) 시간을 충전해주세요.");
                    }
                    else {
                        System.out.println(id+"님의 잔여시간 " + temp.getTime() + "분");
                    }
                }
            }
            System.out.println("메뉴))");
            System.out.println("0)먹거리");
            System.out.println("1)시간추가 2)자리선택 3)자리이동 4)내정보 5)사용종료 6)메인메뉴(컴퓨터종료x)");
            System.out.println("--------------------------------------------------------");
            String tesk = Control.scanner.next();
            if(tesk.equals("0")) {
                while(true) {
                    System.out.println("먹거리 메뉴))");
                    System.out.println("1)구매 2)구매내역 3)돌아가기");
                    String work = Control.scanner.next();
                    if(work.equals("1")) {
                        if(Control.saleItem.size() == 0) {
                            System.out.println("메세지)) 상품 없음 관리자 문의");
                        }
                        else {
                            int e = 0;
                            System.out.println("| 순번 | 제품 | 가격 |");
                            for(Product temp : Control.saleItem) {
                                System.out.printf("| %d | %s | %s |\n",(e+1),temp.getName(),Control.moneySet.format(temp.getMoney()));
                                e++;
                            }
                            System.out.println("결제)결제 99)뒤로가기");
                            System.out.printf("메뉴)) 품목선택 : (%d : 결제 )\n",Control.saleItem.size()+1);
                            while(true) {
                                try {
                                    int itemNum = Control.scanner.nextInt();
                                    itemNum -= 1;
                                    if(itemNum > Control.saleItem.size()+2) {
                                        System.out.println("메세지)) 존재하지 않는 품목 입니다.");
                                    }
                                    else if(itemNum+1 == Control.saleItem.size()+1) {
                                        try {
                                            Hashtable <String, Integer> map = new Hashtable<>();
                                            DecimalFormat df2 = new DecimalFormat("#,##0원");

                                            for (int i = 0; i < Control.customerlist.size(); i++) {
                                                int ticketfee = 0;
                                                for (int j = 0; j < Control.customerlist.size(); j++) {
                                                    if (Control.customerlist.get(i).getC_Name().equals(Control.customerlist.get(j).getC_Name())) {
                                                        ticketfee += Control.customerlist.get(i).getC_Pay();
                                                    }
                                                }
                                                map.put(Control.customerlist.get(i).getC_Name(), ticketfee);
                                            }

                                            for(String temp : map.keySet()) {
                                                String new_money = df2.format(map.get(temp));
                                                System.out.println("상품 "+temp+ " " + new_money);
                                            }
                                            //총 매출액 표시
                                            int totalsales = 0;
                                            for (int i = 0; i < Control.customerlist.size(); i++) {
                                                totalsales += Control.customerlist.get(i).getC_Pay();
                                            }
                                            String new_money = df2.format(totalsales);
                                            System.out.println("총 결제금액 : " + new_money);
                                            System.out.println("---------------------------------------------");
                                            System.out.println("1) 결제 2) 취소");
                                            int ch = Control.scanner.nextInt();
                                            if(ch == 1) {
                                                System.out.println("메세지)) 지불할 금액을 입력하세요.");
                                                int pay_money = Control.scanner.nextInt();
                                                if(pay_money < totalsales) {
                                                    System.out.println("결제 금액 부족");
                                                    break;
                                                }
                                                else if(pay_money >= totalsales) {
                                                    if(pay_money - totalsales == 0) {
                                                        System.out.println("메세지)) 결제가 완료 되었습니다.");
                                                    }
                                                    else {
                                                        String change = df2.format(pay_money - totalsales);
                                                        System.out.println("메세지)) 결제가 완료되었습니다. 거스름돈은 "+change+ "입니다.");
                                                    }
                                                    Control.customerlist.clear();
                                                    break;
                                                }
                                            }
                                            else if (ch == 2) {
                                                System.out.println("메세지)) 결제를 취소합니다.");
                                                con.buy_cancle();
                                                break;
                                            }
                                        }//try end
                                        catch(Exception a) {

                                        }
                                    }
                                    else {
                                        System.out.println("메세지)) "+Control.saleItem.get(itemNum).getName()+" 삼품을 담았습니다.");
                                        con.buy(id,itemNum);
                                    }

                                }
                                catch(Exception a) {

                                }
                            }
                        }
                    }
                    else if(work.equals("2")) {
                        System.out.println("확인");
                        con.buylist(id);
                    }
                    else if(work.equals("3")) {
                        System.out.println("이전으로 돌아갑니다.");
                        break;
                    }
                    else {
                        System.out.println("존재하지 않는 메뉴입니다.");
                    }
                }
            }
            else if(tesk.equals("1")) {
                System.out.println("시간 추가))");
                System.out.println("1)1시간(60분)  2)2시간(120분) ");
                System.out.println("3)5시간(300분) 4)시간입력(분)  ");

                int insert_Time = 0;
                while(true) {
                    try {
                        insert_Time = Control.scanner.nextInt();
                        break;
                    }
                    catch(Exception e) {
                        System.out.println("메세지)) 숫자만 입력가능 합니다.");
                    }
                }

                if(insert_Time <= 0) {
                    System.out.println("메세지)) 잘못된 입력입니다.");
                }
                else if(insert_Time == 1) {
                    System.out.println("메세지)) 1시간을 추가하였습니다.");
                    con.addTime(id,insert_Time);
                }
                else if(insert_Time == 2) {
                    System.out.println("메세지)) 2시간을 추가하였습니다.");
                    con.addTime(id,insert_Time);
                }
                else if(insert_Time == 3) {
                    System.out.println("메세지)) 3시간을 추가하였습니다.");
                    con.addTime(id,insert_Time);
                }
                else if(insert_Time == 4) {
                    System.out.println("메세지)) 사용할 시간을 입력하세요");
                    System.out.println("입력 : ");
                    int insert_Time2 = 0;
                    while(true) {
                        try {
                            insert_Time2 = Control.scanner.nextInt();
                            if(insert_Time2 < 60) {
                                System.out.println("메세지)) 최소 1시간(60분) 이상 입력가능 합니다.");
                            }
                            else if(insert_Time > 1000) {
                                System.out.println("메세지)) 1000분 이하만 가능합니다.");
                            }
                            else {
                                break;
                            }

                        }
                        catch(Exception e) {
                            System.out.println("메세지)) 숫자만 입력가능 합니다.");
                        }
                    }
                    con.addTime(id,insert_Time2);
                }

            }
            else if(tesk.equals("2")) {
                for(Member temp : Control.memberList) {
                    if(temp.getId().equals(id) && temp.isUse() == true) {
                        System.out.println("메세지)) 이미 PC를 이용중입니다.");
                        break;
                    }
                    else if(temp.getId().equals(id) && temp.getTime() == 0) {
                        System.out.println("메세지)) 이용시간을 먼저 충전해주세요.");
                        break;
                    }
                    else {
                        con.startPC(id);
                        break;
                    }
                }
            }
            else if(tesk.equals("3")) {
                for(Member temp : Control.memberList) {
                    if(temp.getId().equals(id) && temp.isUse() == false) {
                        System.out.println("메세지)) 현재 PC를 이용하고 있지 않습니다.");
                        break;
                    }
                    else {
                        con.ChangePC(id);
                        break;
                    }
                }
            }
            else if(tesk.equals("4")) {
                con.myinfo(id);
            }
            else if(tesk.equals("5")) {
                System.out.println("메세지)) 사용종료 합니다.");
                con.endPC(id);
                break;
            }
            else if(tesk.equals("6")) {
                System.out.println("메세지)) 메인메뉴로 돌아갑니다.");
                break;
            }
            else {
                System.out.println("메세지)) 존재하지 않는 메뉴 입니다.");
            }
        }
    }

    public void adminMenu() {
        Control con = new Control();

        while(true) {
            System.out.println("-------------------------------------------------");
            System.out.println("현재 자리))");
            //저장된 자리배열을 보여줌
            for(int i = 0; i < Control.PC.length; i++) {
                System.out.print(Control.PC[i]);
                if(i%5==0)System.out.println();
                //0을 제외하고 5개마다 줄바꿈
            }
            System.out.println("관리자 메뉴))");
            System.out.println("0)식품추가 6)재고확인 7)품목삭제");
            System.out.println("1)강제로그아웃 2)회원목록 3)시간변경 4)매출확인 5)로그아웃");

            System.out.println("-------------------------------------------------");
            String tesk = Control.scanner.next();
            if(tesk.equals("0")) {
                con.addItem();
            }
            else if(tesk.equals("1")) {
                con.members();
                System.out.println("로그아웃할 회원 번호 입력 : ");
                con.changeTime();
            }
            else if(tesk.equals("2")) {
                con.members();
            }
            else if(tesk.equals("3")) {
                con.members();
                System.out.println("수정할 회원 번호 입력 : ");
                con.changeTime();
            }
            else if(tesk.equals("4")) {
                System.out.println("회원별 매출현황");
                con.sale();
            }
            else if(tesk.equals("5")) {
                System.out.println("메세지)) 로그아웃 합니다.");
                break;
            }
            else if(tesk.equals("6")) {
                con.itemCheckList();
            }
            else if(tesk.equals("7")) {
                con.removeItem();
            }
            else {
                System.out.println("메세지)) 존재하지 않는 메뉴 입니다.");
            }
        }
    }
}