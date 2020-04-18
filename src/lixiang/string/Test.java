package lixiang.string;

/**
 * @Description //TODO
 * @Author 李项
 * @Date 2020/4/17
 * @Version 1.0
 */
public class Test {
    private synchronized void a (){
        System.out.println("aaaaaaaa");

    }
    private void b(){
        synchronized (this){
            System.out.println("bbbbb");
        }
    }

    private synchronized static void c() {
        System.out.println("cccccccc");

    }

    private void d(){
        synchronized (Test.class){
            System.out.println("dddddd");
        }
    }

    public static void main(String[] args) {
        Test test = new Test();
        new Thread(()->{
            test.a();
        }).start();
        new Thread(()->{
            test.b();
        }).start();

    }
}
