package lixiang.leetcode.thread;

import jdk.nashorn.internal.ir.CallNode;

import java.util.concurrent.Semaphore;

/**
 * @Description //Semaphore可理解为通道，如果通道>1，则执行。否则，等待,release+1
 * @Author 李项
 * @Date 2020/4/18
 * @Version 1.0
 */
public class FooBar1 {
    private int n;

    public FooBar1(int n) {
        this.n = n;
    }
    Semaphore foo = new Semaphore(1);
    Semaphore bar = new Semaphore(0);
    public void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            foo.acquire();
            printFoo.run();
            System.out.println("foo");
            bar.release();
        }
    }

    public void bar(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            bar.acquire();
            printFoo.run();
            System.out.println("bar");
            foo.release();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        FooBar1 fooBar1 = new FooBar1(2);
        for (int i = 0; i <20; i++) {
            new Thread(()->{
                try {
                    fooBar1.foo(new Thread(()->{
                    }));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();

            new Thread(()->{
                try {
                    fooBar1.bar(new Thread(()->{
                    }));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
