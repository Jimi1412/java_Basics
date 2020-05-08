package juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//交替打印ABCABCABC
public class Alternate {
    public static void main(String[] args) {
        AlternateDemo p=new AlternateDemo();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <3 ; i++)
                    p.printA();
            }
        },"A").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <3 ; i++)
                    p.printB();
            }
        },"B").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <3 ; i++)
                    p.printC();
            }
        },"C").start();
    }

}
class AlternateDemo{
    private int num=1;
    Lock lock=new ReentrantLock();
    private Condition c1=lock.newCondition();
    private Condition c2=lock.newCondition();
    private Condition c3=lock.newCondition();

    public void printA() {
        lock.lock();
        try{
            if(num!=1) c1.await();
            System.out.print("A");
            num=2;
            c2.signal();
        }catch (Exception e){}
        finally {
            lock.unlock();
        }
    }
    public void printB() {
        lock.lock();
        try{
            if(num!=2) c2.await();
            System.out.print("B");
            num=3;
            c3.signal();
        }catch (Exception e){}
        finally {
            lock.unlock();
        }
    }
    public void printC() {
        lock.lock();
        try{
            if(num!=3) c3.await();
            System.out.print("C");
            num=1;
            c1.signal();
        }catch (Exception e){}
        finally {
            lock.unlock();
        }
    }
}