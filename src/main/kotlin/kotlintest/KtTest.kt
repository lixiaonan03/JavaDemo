package kotlintest

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.flow
import kotlin.coroutines.EmptyCoroutineContext

/**
  *  @author lixiaonan
  *  功能描述: 
  *  时 间： 2021/9/15 12:04 下午 
  */
object KtTest {


    @JvmStatic
    fun main(args: Array<String>) {
        val a:Int = 300000000
        println(a == 300000000)

        println("字符串更换===${"".replace(" ", "")}")
        println("字符串更换===${"1345".startsWith("123", false)}")


        val ss  = null
        println(ss?.addMoneyUnit)
    }
}