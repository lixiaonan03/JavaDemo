package Jvm;

/**
 * @author：李晓楠 时间：2022/7/29 15:12
 */
public class MainActivityTest {

    private static HandleTest handleTest = new HandleTest();

    /**
     * MainActivityTest$1 类对象为这个
     * 是一个继承了 HandleTest 的一个新的匿名类 类名为这个 MainActivityTest$1
     * 如果使用了这种匿名类要主要 处理内存泄漏的问题
     */
    private static HandleTest handleTest1 = new HandleTest(){
        @Override
        public void handleMessage() {
            super.handleMessage();
        }
    };

    public static void main(String[] args) {
        System.out.println("=="+handleTest.getClass());
        System.out.println("=="+handleTest1.getClass());

        final String s1 = "aa";
        final String s2 = "bb";
        String str1 = s1 + s2;
        String str2 = "aabb";
        System.out.println(str1.equals(str2));
    }
}
