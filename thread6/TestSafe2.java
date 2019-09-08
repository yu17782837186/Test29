package cn.com.state;

public class TestSafe2 {
    public static void main(String[] args) {
        Account2 account2 = new Account2(1000,"结婚的彩礼");
        Drawing2 you = new Drawing2(account2,50,"悲伤的你");
        Drawing2 wife = new Drawing2(account2,60,"快乐的她");
        you.start();
        wife.start();
    }
}
class Account2 {
     int Money;
     String name;

    public Account2(int money, String name) {
        Money = money;
        this.name = name;
    }
}
class Drawing2 extends Thread {
    Account2 account;
    int drawingMoney;
    int packetTotal;
    public Drawing2(Account2 account,int drawingMoney,String name) {
        super(name);
        this.account = account;
        this.drawingMoney = drawingMoney;
    }
    @Override
    public void run() {
        test();
    }
    public  void test() {
        //提高性能
        if (account.Money <= 0) {
            return;
        }
        //同步块 锁定account
        synchronized (account) {
            if (account.Money - drawingMoney < 0) {
                return;
            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            account.Money = account.Money - drawingMoney;
            packetTotal = packetTotal + drawingMoney;
            System.out.println(account.Money + "-->账户的余额为：" + account.Money);
            System.out.println(packetTotal + "-->口袋里的钱为：" + packetTotal);
        }
    }
}