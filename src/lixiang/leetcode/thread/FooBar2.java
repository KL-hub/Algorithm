package lixiang.leetcode.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description //公平锁也是实现交替执行一个不错的选择
 *              //虽然能使进程交替执行，但如果先执行线程二，会使总的执行次数减少
 * @Author 李项
 * @Date 2020/4/18
 * @Version 1.0
 */
public class FooBar2 {
    private int i;

    public FooBar2(int i) {
        this.i = i;
    }
    //创建一个公平锁
    Lock lock=new ReentrantLock(true);
    volatile boolean isFoo=true;
    public void foo(Runnable printFoo) throws InterruptedException {
        System.out.println("foo方法执行了");
        for (int j = 0; j < i;  j++) {
                lock.lock();
                try {
                if(isFoo){
                    printFoo.run();
                    System.out.println("foo");
                    isFoo=false;
                }
                }finally {
                    lock.unlock();
                }
            }



    }
    public void bar(Runnable printBar) throws InterruptedException {
        System.out.println("bar方法执行了");
            for (int j =0 ; j <i;j++) {
                lock.lock();
                try {
                if(!isFoo){
                    printBar.run();
                    System.out.println("bar");
                    isFoo=true;
                }
            }finally {
                lock.unlock();
            }
            }


    }

    public static void main(String[] args) throws InterruptedException {
        FooBar2 fooBar2 = new FooBar2(2);
        for (int i = 0; i <20; i++) {
            new Thread(()->{
                try {
                    fooBar2.foo(new Thread(()->{
                    }));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();

            new Thread(()->{
                try {
                    fooBar2.bar(new Thread(()->{
                    }));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();

        }
    }
}
