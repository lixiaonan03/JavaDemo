package Jvm.lamda;

/**
 * @author：李晓楠 时间：2022/8/30 11:05
 */
class Java8 {
    interface Logger {
        void log(String s);
    }

    public static void main(String... args) {
        sayHi(s -> System.out.println(s));
    }

    private static void sayHi(Logger logger) {
        logger.log("Hello!");
    }
}