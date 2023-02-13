package Kotlin;


import kotlintest.jvm.*;

/**
 * Java 调用kotlin的类测试
 * @author lixiaonan
 */
public class KtTest {
    public static void main(String[] args) throws Exception {
        testKtJvmStatic();
        testKtJvmField();
        testKtJvmSynthetic();
        testKtJvmName();
    }

    /**
     * Java代码调用 kt 标注的@JvmStatic 方法
     */
    private static void testKtJvmStatic(){
        KtA.invokeStatic(); //正确，可以直接调用
//        KtA.invokeNoStatic(); //错误，这里调用不到
        KtA.Companion.invokeStatic(); //正确
        KtA.Companion.invokeNoStatic(); //正确

        /**
         * 这种是 Kotlin Object类调用静态方法的写法
         */
        JvmAnnotationTest.INSTANCE.method(1);
    }


    private static void testKtJvmField(){
        TestField testField = new TestField();
        testField.setId1(200);
        //@JvmField使得编译器不再对该字段生成getter/setter并将其作为公开字段，所以Java 没法掉用id2 的set get
//        testField.setId2(100);
    }

    private static void testKtJvmSynthetic(){
        KtA kta = new KtA();
        //@JvmSynthetic可以修饰于方法上，控制只能在Kotlin中调用
//        kta.visit();
    }

    /**
     * @JvmName 注解可以生成类名；如果类名已存在，可以修改已生成的 Java 类的类名。
     * 包名相同并且类名相同或者有相同的 @JvmName 注解有会错误，可以通过@JvmMultifileClass把他们合并到一起，如：
     */
    private static void testKtJvmName(){

        generate.getA();
        //这种就类似余额Java
        // 类中 public final class NewName {
        //   public static final void getB() {
        //   }
        //}
        NewName.getB();
    }
}
