package thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author：李晓楠 时间：2022/6/29 11:59
 */
public class MyCallable implements Callable<String> {
    @Override
    public String call() throws Exception {
        System.out.println("线程执行......");
        Thread.sleep(5000);
        System.out.println("线程执行完毕......");
        return "hello world!!!";
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> futureTask = new FutureTask<>(new MyCallable());
        /***
         * futureTask 实现了 Runnable接口
         * 所以新建线程的时候可以传入futureTask
         * FutureTask重写的run方法中实际是调用了Callable接口在call()方法
         * 所以执行线程的时候回执行call方法的内容
         */
        Thread thread = new Thread(futureTask);
        thread.start();
        String value = futureTask.get();
        System.out.println(value);
    }
}