package test;

/**
 * 普通类实现了抽象类 抽象类继承了接口
 *
 * 由普通的类来实现接口，必须将接口所有抽象方法重写
 * 由抽象类来实现接口，则不必重写接口的方法。可以全部不重写或只重写一部分方法。
 *
 *
 * @author：李晓楠 时间：2022/9/15 22:53
 */
class ClassTest extends AbsTest{


    @Override
    public void test1() {

    }

    /**
     * 实现抽象类的方法
     */
    @Override
    public void test() {

    }
}
