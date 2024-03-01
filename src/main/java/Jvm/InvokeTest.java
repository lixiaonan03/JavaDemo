package Jvm;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 反射调用练习
 * @author：李晓楠 时间：2023/10/11 16:58
 */
public class InvokeTest {

    public static void main(String[] args) {

    }

    private static void invokeTest() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        //获取String所对应的Class对象
        Class<?> c = String.class;
        //一种方法获取实例
        Object str = c.getDeclaredConstructor().newInstance();

        //另外一种获取实例的方式
        //获取String类带一个String参数的构造器
        Constructor<?> constructor = c.getConstructor(String.class);
        //根据构造器创建实例
        Object obj = constructor.newInstance("23333");
        System.out.println(obj);


    }
}
