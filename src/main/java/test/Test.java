package test;

/**
 * @author：李晓楠 时间：2022/8/17 15:54
 */
public class Test<T> {
    private T key;

    public T getKey() {
        return key;
    }

    public void setKey(T key) {
        this.key = key;
    }

    public Test(T key) {
        this.key = key;
    }

    /**
     * 泛型方法
     * @param t
     * @param <R>
     * @return
     */
    public <R> String show(R t){
        return t.toString();
    }

    private static class A {

        void func(int a) {
            this.func(a, 0);
        }

        void func(int a, int b) {
            System.out.println("A.func(" + a + ", " + b + ")");
        }
    }


    private static class B extends A {

        @Override
        void func(int a, int b) {
            System.out.println("B.func(" + a + ", " + b + ")");
        }
    }

    public static void main(String[] args) throws Exception {

        int aa =Integer.MAX_VALUE >> 2;
        System.out.println("aaa="+aa);

        A a = new B();
        a.func(123);
    }

}

