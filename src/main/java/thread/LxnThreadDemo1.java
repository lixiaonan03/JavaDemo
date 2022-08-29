package thread;

/**
  *  @author lixiaonan
  *  功能描述: 线程的第二种创建方式的
  *  时 间： 2022/8/29 15:55
  */
public class LxnThreadDemo1 implements Runnable{

    private int i;

    public void setI(int i) {
        this.i = i;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"=runable=="+(i++));
    }
}
