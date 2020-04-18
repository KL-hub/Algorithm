package lixiang.leetcode.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description
 * //class FooBar {
 *   public void foo() {
 *     for (int i = 0; i < n; i++) {
 *       print("foo");
 *     }
 *   }
 *
 *   public void bar() {
 *     for (int i = 0; i < n; i++) {
 *       print("bar");
 *     }
 *   }
 * }
 * 两个不同的线程将会共用一个 FooBar 实例。其中一个线程将会调用 foo() 方法，另一个线程将会调用 bar() 方法。
 * 请设计修改程序，以确保 "foobar" 被输出 n 次。
 * @Author 李项
 * @Date 2020/4/17
 * @Version 1.0
 */
public class FooBar {
    private boolean isEmpty=true;
    private int n;
    //创建锁
    private Lock lock=new ReentrantLock();
    //创建条件
    private Condition condition=lock.newCondition();

    public FooBar(int n) {
        this.n = n;
    }

    public void foo() {
        lock.lock();
        try {
            while (!isEmpty){
                condition.await();
            }
            for (int i = 0; i < n; i++) {
                System.out.println("foo");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void bar() {
        lock.lock();
        try {
            while (isEmpty){
                condition.await();
            }
            for (int i = 0; i < n; i++) {
                System.out.println("bar");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

  }

}
