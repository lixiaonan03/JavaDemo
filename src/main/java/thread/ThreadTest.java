package thread;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * @author：李晓楠 时间：2022/4/27 11:36
 */
public class ThreadTest {

    public static void main(String[] args) {
      tryRun(new Runnable() {
          @Override
          public void run() {
              try {
                  HashMap<String,String> map = new HashMap<>(2);
                  String aa =map.get("ke");
                  System.out.println("aa==="+aa);
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
        for (String s:set) {
            System.out.println(s);
        }
    }


    /**
     * 对所有上报的代码进行try-catch
     * @param runnable
     */
    public static void tryRun(Runnable runnable){
        try {
            runnable.run();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
