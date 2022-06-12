public class PcUse extends Thread{

    static boolean stop = true;

    public PcUse() {}



    @Override
    public void run() {

        while(true) {
            try {
                for(Member temp : Control.memberList) {
                    if(temp.isUse() == true) {
                        temp.setTime(temp.getTime() -1);
                        DB.memberSave();
                    }
                }
                Thread.sleep(60000);
            }
            catch (InterruptedException e) {

            }
        }
    }
}