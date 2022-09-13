package kotlintest.jvm

/**
 * @author：李晓楠
 * 时间：2022/9/1 17:37
 */
object EqualTest {
    @JvmStatic
    fun main(vararg args: String) {
        val a: Int = 128
        println(a === a) // true，值相等，对象地址相等

        //经过了装箱，创建了两个不同的对象
        val boxedA: Int? = a
        val anotherBoxedA: Int? = a

        //虽然经过了装箱，但是值是相等的，都是10000
        println(boxedA === anotherBoxedA) //  false，值相等，对象地址不一样
        println(boxedA == anotherBoxedA) // true，值相等

        val sum = fun Int.(other: Int): Int = this + other
        val ff = sum.invoke(5,7)
        println(ff)

    }
}