package juc;

import java.util.concurrent.CountDownLatch;

public class testCountLatch {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch cdl=new CountDownLatch(10);
        LatchDemo ld=new LatchDemo(cdl);
        long start = System.currentTimeMillis();
        for (int i = 0; i <10 ; i++)
            new Thread(ld).start();
        cdl.await();
        long end = System.currentTimeMillis();
        System.out.println("need time:"+ (end-start));

    }



}
class LatchDemo implements Runnable{
    CountDownLatch cdl;
    LatchDemo(CountDownLatch cdl){this.cdl=cdl;}
    @Override
    public void run() {
        try {
            for (int i = 0; i < 50000; i++) {
            if (i % 2 == 0) {
                System.out.println(i);
            }
        }

        }finally {
            cdl.countDown();//每次减一
        }
    }
}
