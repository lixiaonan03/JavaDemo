package thread;

import util.TimeUtil;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.*;

/**
 * @author：李晓楠 时间：2022/4/27 11:36
 */
public class ThreadTest {

    public static void main(String[] args) {
        //创建线程的方式的
        int i = 4;
        switch (i) {
            case 1:
                creatThread1();
                break;
            case 2:
                creatThread2();
                break;
            case 3:
                creatThread3();
                break;
            case 4:
                creatThread4();
                break;
            default:
                tryRun(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            HashMap<String, String> map = new HashMap<>(2);
                            String aa = map.get("ke");
                            System.out.println("aa===" + aa);
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("runable 里面的");
                    }
                });
                System.out.println("runable 后面的 ");
                Set<String> set = new HashSet<String>();
                set.add("aaa");
                set.add("bbb");
                set.add("ccc");
                for (String s : set) {
                    System.out.println(s);
                }
                break;
        }


    }

    /**
     * 创建线程的一种方式的
     */
    public static void creatThread1() {
        LxnThreadDemo lxnThreadDemo = new LxnThreadDemo();
        LxnThreadDemo lxnThreadDemo1 = new LxnThreadDemo();
        lxnThreadDemo.setPriority(1);
        lxnThreadDemo.start();
        lxnThreadDemo1.start();
    }

    public static void creatThread2() {
        LxnThreadDemo1 lxnThreadDemo1 = new LxnThreadDemo1();
        lxnThreadDemo1.setI(4);
        Thread run1 = new Thread(lxnThreadDemo1);
        Thread run2 = new Thread(lxnThreadDemo1);
        run1.start();
        run2.start();
    }

    public static void creatThread3() {
        FutureTask<String> futureTask = new FutureTask<>(new MyCallable());
        /***
         * futureTask 实现了 Runnable接口
         * 所以新建线程的时候可以传入futureTask
         * FutureTask重写的run方法中实际是调用了Callable接口在call()方法
         * 所以执行线程的时候回执行call方法的内容
         */
        Thread thread = new Thread(futureTask);
        thread.start();
        String value = null;
        System.out.println("线程开始获取返回值===" + TimeUtil.getNowTimeForStr());
        try {
            value = futureTask.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(value);
    }

    /**
     * 通过线程池创建的
     */
    public static void creatThread4() {
        //四种线程池的方式的
        // 创建固定大小的线程池:
        ExecutorService executor = Executors.newFixedThreadPool(1);
        ExecutorService executorService = Executors.newCachedThreadPool();
        //同上面的1
        ExecutorService executorService1 = Executors.newSingleThreadExecutor();
        //定期执行任务的
        ScheduledExecutorService executorService2 = Executors.newScheduledThreadPool(2);


        // 1秒后执行一次性任务:
        executorService2.schedule(new Runnable() {
            @Override
            public void run() {
//                syo("定时任务线程的1");
            }
        }, 1, TimeUnit.SECONDS);
        // 2秒后开始执行定时任务，每1秒执行:  如果任务执行间隔时间过长超过定时时间间隔 则等执行我那立即执行
//        executorService2.scheduleAtFixedRate(new Runnable() {
//            @Override
//            public void run() {
//                syo("每1秒执行");
//                try {
//                    Thread.sleep(1100);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, 2, 1, TimeUnit.SECONDS);
        // 创建
        for(int i=0;i<5;i++) {
//            executor.submit(new Runnable() {
//                @Override
//                public void run() {
//                    syo("核心线程为1的");
//                    try {
//                        Thread.sleep(2000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            });
        }

        //通过自定义线程池的方式创建的
        ExecutorService executorService3 = new ThreadPoolExecutor(1, 6,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(2));
        for(int i=0;i<10;i++) {
            executorService3.submit(new Runnable() {
                @Override
                public void run() {
                    syo("自定义线程池的");
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                }
            });
        }
        //这种中断方式会导致报错的
//        executor.shutdownNow();
    }


    public static void syo(String obj){
        System.out.println(Thread.currentThread().getName()+"=="+obj+"==="+TimeUtil.getNowTimeForStr());
    }
    /**
     * 对所有上报的代码进行try-catch
     *
     * @param runnable
     */
    public static void tryRun(Runnable runnable) {
        try {
            runnable.run();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
