package cn.com.state;

import java.util.ArrayList;
import java.util.List;

public class TestHappyCinema {
    public static void main(String[] args) {
//        Cinema cinema = new Cinema(2,"太平洋影院");
//        new Thread(new Customer(cinema,2),"小红").start();
//        new Thread(new Customer(cinema,3),"小明").start();
//        List<Integer> available = new ArrayList<>();
//        available.add(1);
//        available.add(2);
//        available.add(3);
//        available.add(4);
//        available.add(5);
//        available.add(6);
//        //顾客需要的位置
//        List<Integer> seats1 = new ArrayList<>();
//        seats1.add(1);
//        seats1.add(2);
//        List<Integer> seats2 = new ArrayList<>();
//        seats2.add(3);
//        seats2.add(4);
//        seats2.add(8);
//        Cinema cinema = new Cinema(available,"太平洋影院");
//        new Thread(new Customer(cinema,seats1),"小红").start();
//        new Thread(new Customer(cinema,seats2),"小军").start();
        List<Integer> available = new ArrayList<>();
        available.add(1);
        available.add(2);
        available.add(3);
        available.add(4);
        available.add(5);
        List<Integer> seats1 = new ArrayList<>();
        seats1.add(1);
        seats1.add(2);
        List<Integer> seats2 = new ArrayList<>();
        seats2.add(3);
        seats2.add(4);
        Cinema cinema = new Cinema(available,"太平洋影院");
        new Thread(new Customer(cinema,seats1),"小红").start();
        new Thread(new Customer(cinema,seats2),"小明").start();
    }
}
class Customer implements Runnable {
    Cinema cinema;
    List<Integer> seats;

    public Customer(Cinema cinema, List<Integer> seats) {
        this.cinema = cinema;
        this.seats = seats;
    }

    @Override
    public void run() {
        synchronized(cinema) {
            boolean flag = cinema.bookTicket(seats);
            if (flag) {
                System.out.println("出票成功" + Thread.currentThread().getName() + "当前位置为：" + seats);
            } else {
                System.out.println("出票失败" + Thread.currentThread().getName() + "当前位置不够");
            }
        }
    }
}
class Cinema {
    List<Integer> available;
    String name;

    public Cinema(List<Integer> available, String name) {
        this.available = available;
        this.name = name;
    }
    public boolean bookTicket(List<Integer> seats) {
        System.out.println("欢迎光临"+this.name+"，当前可用位置为："+available);
        List<Integer> tmp = new ArrayList<>();
        tmp.addAll(available);
        tmp.removeAll(seats);
        if (available.size() - tmp.size() != seats.size()) {
            return false;
        }
        available = tmp;
        return true;
    }
}
//class Customer implements Runnable {
//    Cinema cinema;
//    List<Integer> seats;
//
//    public Customer(Cinema cinema, List<Integer> seats) {
//        this.cinema = cinema;
//        this.seats = seats;
//    }
//
//    @Override
//    public void run() {
//        synchronized(cinema) {
//            boolean flag = cinema.bookTicket(seats);
//            if (flag) {
//                System.out.println("出票成功" + Thread.currentThread().getName() + "-->位置为" + seats);
//            } else {
//                System.out.println("出票失败" + Thread.currentThread().getName() + "-->位置数有不符的" );
//            }
//        }
//    }
//}
//class Cinema {
//    List<Integer> available;
//    String name;
//
//    public Cinema(List<Integer> available, String name) {
//        this.available = available;
//        this.name = name;
//    }
//    public boolean bookTicket(List<Integer> seats) {
//        System.out.println("欢迎光临"+this.name+",可用位置为："+available);
//        List<Integer> tmp = new ArrayList<>();
//        tmp.addAll(available);
//        //相减
//        tmp.removeAll(seats);
//        if (available.size() - tmp.size() != seats.size()) {
//            return false;
//        }
//        available = tmp;
//        return true;
//    }
//}
//class Customer implements Runnable {
//    Cinema cinema;
//    int seats;
//    public Customer(Cinema cinema,int seats) {
//        this.cinema = cinema;
//        this.seats = seats;
//    }
//    @Override
//    public void run() {
//        synchronized(cinema) {
//            boolean flag = cinema.bookTicket(seats);
//            if (flag) {
//                System.out.println("出票成功" + Thread.currentThread().getName() + "-->位置为" + seats);
//            } else {
//                System.out.println("出票失败" + Thread.currentThread().getName() + "-->位置不够");
//            }
//        }
//    }
//}
//class Cinema {
//    int available; //可用的位置
//    String name;
//
//    public Cinema(int available, String name) {
//        this.available = available;
//        this.name = name;
//    }
//
//    //购票
//    public boolean bookTicket(int seats) {
//        System.out.println("可用位置为："+available);
//        if (seats > available) {
//            return false;
//        }
//        available -= seats;
//        return true;
//    }
//}