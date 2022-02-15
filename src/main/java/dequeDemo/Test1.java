package dequeDemo;

import java.util.*;

/**
 * Created by lixiaonan on 2020/10/26.
 */
public class Test1 {


    public static void main(String[] args) {
        String str = "1";
        int byte_len = str.getBytes().length;
        int len = str.length();
        System.out.println("字节长度为：" + byte_len);
        System.out.println("字符长度为：" + len);
        System.out.println("系统默认编码方式：" + System.getProperty("file.encoding"));

        Deque<Student> readyMessages = new ArrayDeque<>();
        Deque<Student> runMsg = new ArrayDeque<>();

        Student one = new Student("1", 11);
        Student two = new Student("2", 12);
        Student three = new Student("3", 13);
        readyMessages.add(one);
        readyMessages.add(two);
        readyMessages.add(three);

        List<Student> executableCalls = new ArrayList<>();
        for (Iterator<Student> i = readyMessages.iterator(); i.hasNext(); ) {
            Student asyncCall = i.next();
            executableCalls.add(asyncCall);
            runMsg.add(asyncCall);
        }
        System.out.println("run size====" + runMsg.size());
        for (int i = 0, size = executableCalls.size(); i < size; i++) {
            Student appPushMessage = executableCalls.get(i);
            //处理单个消息

            boolean del = runMsg.remove(appPushMessage);
            System.out.println("del====" + del);
        }
        System.out.println("size====" + executableCalls.size());
        System.out.println("run size====" + runMsg.size());
    }
}
