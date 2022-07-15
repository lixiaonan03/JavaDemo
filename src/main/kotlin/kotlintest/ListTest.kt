package kotlintest

/**
 * @author：李晓楠
 * 时间：2022/6/29 12:23
 */
object ListTest {
    @JvmStatic
    fun main(vararg args: String) {
        val list = listOf<Int>(0,1,2,3,4,5)
        var listNew = list.subList(1,list.size).toMutableList()
        listNew.add(0,9)
        println("旧的==$list")
        println("新的==$listNew")
    }
}