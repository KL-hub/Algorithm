package lixiang.leetcode.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description //
 * 三个不同的线程将会共用一个 Foo 实例。
 *
 * 线程 A 将会调用 one() 方法
 * 线程 B 将会调用 two() 方法
 * 线程 C 将会调用 three() 方法
 * 请设计修改程序，以确保 two() 方法在 one() 方法之后被执行，three() 方法在 two() 方法之后被执行。
 * @Author 李项
 * @Date 2020/4/16
 * @Version 1.0
 */
public class Foo {
    private AtomicInteger step1=new AtomicInteger(0);
    private AtomicInteger step2=new AtomicInteger(0);
    public Foo() {

    }

    public void first(Runnable printFirst) throws InterruptedException {
        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        System.out.println("first"+"  "+printFirst.hashCode());
        step1.incrementAndGet();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        while(step1.get()!=1){

        }
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        System.out.println("second"+"  "+printSecond.hashCode());
        step2.incrementAndGet();
    }

    public void third(Runnable printThird) throws InterruptedException {
        while(step2.get()!=1){
        }
        // printThird.run() outputs "third". Do not change or remove this line.
        System.out.println("third"+"  "+printThird.hashCode());
        printThird.run();
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 30; i++) {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {

                }
            };
            Foo foo = new Foo();
            foo.first(runnable);
            foo.second(runnable);
            foo.third(runnable);
        }

    }
}
