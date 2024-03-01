package designpattern.danli;

/**
 * 单例模式代码写法
 * @author：李晓楠 时间：2023/10/10 11:23
 */


/**
 * 饿汉模式 如果没有用到了这个类，那么就会造成内存的浪费
 */
//public class Singleton {
//    private static Singleton instance = new Singleton();
//
//    private Singleton() {
//    }
//    public static Singleton getInstance() {
//        return instance;
//    }
//}

/**
 * 懒汉模式  每次调用都需要同步，造成不必要的开销
 */
//public class Singleton {
//    private static Singleton instance = null;
//
//    private Singleton() {
//    }
//    public static synchronized Singleton getInstance() {
//        if (instance == null) {
//            instance = new Singleton();
//        }
//        return instance;
//    }
//}

/**
 * dsl
 */
//public class Singleton {
//    private static volatile Singleton instance;
//    private Singleton() {
//    }
//    public static Singleton getInstance() {
//        if (instance == null) {
//            synchronized (Singleton.class) {
//                if (instance == null) {
//                    instance = new Singleton();
//                }
//            }
//        }
//        return instance;
//    }
//}

/**
 * 静态内部类
 */
public class Singleton {
    private Singleton() {
    }
    private static class SingletonHolder {
        private static final Singleton INSTANCE = new Singleton();
    }
    public static Singleton getInstance() {
        return SingletonHolder.INSTANCE;
    }
}





