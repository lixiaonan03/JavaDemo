package kotlintest.str

import kotlintest.ListTest

/**
 * @author：李晓楠
 * 时间：2023/1/18 14:59
 */
object StringTest {

    @JvmStatic
    fun main(vararg args: String) {
        val numStr = "026311111111111 11"
        println("截取后四位==${numStr.replace("\\s".toRegex(), "").takeLast(4)}")
        val numSub= numStr.substring(3)
        println("截取的字符串==$numSub")
        val count = numSub.count { it == numSub[1] }
        println("count==$count")
        val yes = count +3 == numStr.length
        println("numStr 是否重复的==$yes==")

        println( numStr.substring(3).count { it == numStr[3] } + 3 == numStr.length)
    }
}