package juc;

import javafx.concurrent.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

//测试创建线程的几种方式
public class testThread extends Thread{
    public static void main(String[] args) throws ExecutionException, InterruptedException {
       /* MyThread mt=new MyThread("A");
        MyThread mt2=new MyThread("B");
        mt.start();
        mt2.start();*/
      /* MyRunnable m1=new MyRunnable("A");
       MyRunnable m2=new MyRunnable("B");
       Thread t1=new Thread(m1);
       Thread t2=new Thread(m2);
       t1.start();t2.start();*/
       /* MyCallable myCallable=new MyCallable("A");
        FutureTask futureTask=new FutureTask(myCallable);
        new Thread(futureTask).start();
        System.out.println(futureTask.get());*/
       //缓存线程池
        /*ScheduledExecutorService pool2=Executors.newScheduledThreadPool(5);
        for (int i = 0; i <10 ; i++) {
            Future<Integer> future=pool2.schedule(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    int num = new Random().nextInt(100);//生成随机数
                    System.out.println(Thread.currentThread().getName() + " : " + num);
                    return num;
                }
            },1,TimeUnit.MINUTES);
            pool2.shutdown();
        }*/
        //固定大小的线程池
        /*   ExecutorService pool=Executors.newFixedThreadPool(3);
        List<Future<Integer>> list=new ArrayList<>();
        for (int i = 0; i <10 ; i++) {
            Future<Integer> future=pool.submit(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    int sum = 0;

                    for (int i = 0; i <= 100; i++) {
                        sum += i;
                    }
                    return sum;
                }

            });
            list.add(future);
        }
        //记得关闭
        pool.shutdown();
        for (Future<Integer> future : list)
            System.out.println(future.get());
        */
    }
}
//第四种方式 使用线程池

//第三种方式：实现Callable接口
class MyCallable implements Callable<Integer> {
    private String name;

    MyCallable(String name) {
        this.name = name;
    }

    @Override
    public Integer call() throws Exception {
        int sum=0;
        for (int i = 0; i <=100 ; i++)
            sum+=i;
        return sum;
    }
}
//第二种方式： 实现Runnable接口
class MyRunnable implements Runnable{
    private String name;
    MyRunnable(String name){this.name=name;}
    @Override
    public void run() {
        for (int i = 0; i <10 ; i++)
            System.out.println(name+"运行,i= ："+i);
    }
}
//第一种方式，继承Thread 类 重写run 方法
class MyThread extends Thread{
    private String name;
    MyThread(String name){this.name=name;}
    @Override
    public void run() {
        for (int i = 0; i <10 ; i++)
            System.out.println(name+"运行,i= ："+i);
    }
}
