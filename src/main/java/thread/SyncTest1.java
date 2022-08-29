package thread;

import java.util.Date;

/**
 * @author：李晓楠 时间：2022/8/28 17:42
 */
public class SyncTest1 implements TestDemo{
    private boolean running = true;

    private void stop(){
        running =false;
    }
    @Override
    public void runTest() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("执行打印变量的==="+running+"=="+System.currentTimeMillis());
                while (running){
                    System.out.println("while打印变量的==="+running+"=="+System.currentTimeMillis());
                }
            }
        }).start();
        try {
            System.out.println("sleep==="+System.currentTimeMillis());
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("stop==="+System.currentTimeMillis());
        stop();
    }
}
