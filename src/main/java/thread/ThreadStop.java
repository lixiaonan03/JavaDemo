package thread;

import util.TimeUtil;

/**
 * @author lixiaonan
 * 功能描述: 线程中断的几种方式的
 * 时 间： 2022/8/29 22:07
 */
public class ThreadStop extends Thread {
    public volatile boolean exit = false;
    @Override
    public void run() {
        while (!exit) {
            syo("子线程在执行=="+exit);
        }
    }

    public static void main(String[] args) {
        int i = 4;
        switch (i) {
            case 1:
                variate();
                break;
            case 2:
                stop1();
                break;
            case 3:
                interrupt1();
                break;
            case 4:
                interrupt2();
                break;
            default:
                break;
        }
    }

    /**
     * 通过变量进行中断
     */
    private static void variate() {
        ThreadStop thread = new ThreadStop();
        thread.start();
        try {
            // 主线程延迟200毫秒
            sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 终止线程thread
        thread.exit = true;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        syo("线程结束的==");

    }

    /**
     * 通过stop方法中断
     */
    private static void stop1() {
        ThreadStop thread = new ThreadStop();
        thread.start();
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.stop();
    }

    /**
     * sleep interrupt 中断的判断
     */
    private static void interrupt1() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    syo("中断异常"+e.getMessage());
                }
            }
        });
        thread.start();
        syo("线程开始执行");
        thread.interrupt();
    }
    /**
     * sleep interrupt 中断的判断
     */
    private static void interrupt2() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (Thread.interrupted()){
                        sleep(2000);
                    }
                    syo("判断到了==中断异常");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    syo("中断异常"+e.getMessage());
                }
            }
        });
        thread.start();
        syo("线程开始执行");
        thread.interrupt();
        thread.isInterrupted();
    }


    public static void syo(String obj) {
        System.out.println(Thread.currentThread().getName() + "==" + obj + "===" + TimeUtil.getNowTimeForStr());
    }
}
