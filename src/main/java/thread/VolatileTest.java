package thread;

/**
 * volatile关键字的使用  只保证可见性 并不保证
 * @author：李晓楠 时间：2023/10/17 10:57
 */
public class VolatileTest {

    private volatile int a = 0;
    public static void main(String[] args) {
        VolatileTest volatileTest = new VolatileTest();
        volatileTest.test();
    }


    private void test() {
       Runnable runnable = new Runnable() {
           @Override
           public void run() {
               System.out.println("线程中开始=="+a+"==="+Thread.currentThread().getName());
               for (int i = 0; i < 100; i++) {a++;}
               System.out.println("线程中a=="+a+"==="+Thread.currentThread().getName());
           }
       };
       for (int i = 0; i < 15; i++) {
           new Thread(runnable).start();
       }
       System.out.println("结果a=="+a+Thread.currentThread().getName());
    }
}
