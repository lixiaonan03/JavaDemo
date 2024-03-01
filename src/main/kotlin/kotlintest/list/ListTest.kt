package kotlintest.list

import org.jetbrains.kotlin.com.google.common.collect.ArrayListMultimap

/**
 *  @author lixiaonan
 *  功能描述: 集合的一些测试类
 *  时 间： 2021/9/15 12:04 下午
 */
object ListTest {


    @JvmStatic
    fun main(args: Array<String>) {
        testAssociate()

        val numbers = listOf(1, 2, 3, 4, 5)
        val newlist = numbers.drop(3)
        println(newlist.toString())
        val doubledNumbers = numbers.map { it * 2 }
        println(doubledNumbers.toString())
    }

    private fun arrayMapTest() {

    }

    /**
     * 测试associate 的方法
     * "associate" 是 Kotlin 中一个非常方便的高阶函数，可以帮助我们快速地将集合或映射中的元素转换为我们需要的形式。
     */
    private fun testAssociate(){
        val strings = listOf("a", "ab", "b")
        //{a=1, ab=2, b=1} associateWith 以lambda 表达式作为value值
//        val map = strings.associateWith { it.length }

        //{atest=1, abtest=2, btest=1}
        val map = strings.associate { it+"test" to it.length }

        //{1=b, 2=ab}  associateBy 以lambda 表达式作为key
//        val map =  strings.associateBy {  it.length}
        println(map.toString())
    }
}