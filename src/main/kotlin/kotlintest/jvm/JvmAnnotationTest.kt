package kotlintest.jvm

/**
 * @author：李晓楠
 * 时间：2022/9/1 17:37
 */
object JvmAnnotationTest {
    @JvmStatic
    fun main(vararg args: String) {

        //@JvmOverloads 重载方法调用的===
        method(1)
        method(1,false)
        method(1,false,"s")
        //@JvmStatic
        KtA.invokeStatic()
        KtA.invokeNoStatic()
        KtA.Companion.invokeStatic()
        KtA.Companion.invokeNoStatic()
        //@JvmField 测试的
        val testField = TestField()
        testField.id1 = 200
        testField.id2 = 200

        //@JvmSynthetic 测试  kt可以掉用 Java不可调用
        val kta = KtA()
        kta.visit()

        //@JvmName 、@JvmMultifileClass 类名标签测试
        getA()
        getB()

        //内联类测试
        val aa = Person1("1")




    }

    /**
     * 主要用于构造函数、方法中，同时不能用于抽象方法、接口中的方法等。
     * 方法重载方法中，在android 自定义View的构造方法中也用的多
     */
    @JvmOverloads
    fun method(a: Int, b: Boolean = true, c: String = "c") {
        println("JvmOverloads 重载方法调用的==$a==$b==$c")
    }
}

class KtA {


    companion object {
        /**
         * @JvmStatic用于声明静态方法。在具名对象及伴生对象中使用时，
         * 既会在相应对象的类中生成静态方法，也会在对象自身中生成实例方法
         */
        @JvmStatic
        fun invokeStatic() {}

        fun invokeNoStatic() {}
    }

    /**
     * @JvmSynthetic可以修饰于方法上，控制只能在Kotlin中调用
     */
    @JvmSynthetic
    fun visit() {

    }
}


