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


        // https://blog.csdn.net/qwe123147369/article/details/113104651
        final String s1 = "aa";
        final String s2 = "bb";
        String str1 = s1 + s2;
        String str2 = "aabb";
        //这个返回的是true final 修饰的编译器会优化  str1 = "aabb"
        System.out.println(str1 == str2);

        String s11 = "aa";
        String s21 = "bb";
        String str11 = s11 + s21;
        String str21 = "aabb";
        //str11 生成的时候会用 StringBuilder 生成对象的  对比内存地址的话 str11 和 str21 是不一样的
        System.out.println(str11 == str21);
    }
}
