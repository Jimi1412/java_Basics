package juc;

import java.util.concurrent.TimeUnit;

class Data{
   // volatile int num=0;
     int num=0;
    public void addTo60(){
        this.num=60;
    }
    public void add(){num++;}
}
public class testJuc {
    public static void main(String[] args) {
        Data data=new Data();
        //不保证原子性
        for (int i = 0; i <20 ; i++) {
            new Thread(()->{
                for (int j = 1; j <=1000 ; j++) {
                    data.add();
                }
            },String.valueOf(i)).start();
        }
        while(Thread.activeCount()>2){
            Thread.yield();
        }
        System.out.println(data.num);
    }
    //保证可见性
    public void testSeek(){
        Data d=new Data();
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"\t come in"+d.num);
            try {TimeUnit.SECONDS.sleep(3); }
            catch (InterruptedException e) {e.printStackTrace();}
            d.addTo60();
            System.out.println(Thread.currentThread().getName()+"\t update"+d.num);
        },"aaa").start();
        while(d.num==0){ }
        System.out.println(Thread.currentThread().getName()+"\t overn"+d.num);
    }
}
