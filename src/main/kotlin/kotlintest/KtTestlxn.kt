package kotlintest

import kotlinx.coroutines.*

/**
  *  @author lixiaonan
  *  功能描述: 
  *  时 间： 2021/9/15 12:04 下午 
  */
object KtTestlxn {


    @JvmStatic
    fun main(args: Array<String>) {
//        otherSS()
        //从1 到 100的循环
        var na = 0
        var zhi = 0
        for (i in 1..100) {
            val num = (1..100).random()
            println("${i}生成的数字$num")
            if (num%2 == 0) {
                na++
            } else {
                zhi++
            }
        }
        println("生成的数字中0的个数$na"+"生成的数字中1的个数$zhi")
    }

    suspend fun simple():Sequence<Int> = sequence {
        for (i in 1..3) {
            Thread.sleep(1000)
            yield(i)
        }
    }



    private fun otherSS(){
        val sss = 1 shl 30;



        val a:Int = 300000000
        println( sss)


        println("字符串更换===${"".replace(" ", "")}")
        println("字符串更换===${"1345".startsWith("123", false)}")


        val ss  = null
        println(ss?.addMoneyUnit)

        val ss1 = "12345678"
        println(ss1.toBankCode())


        //从1 到 100的循环
        for (i in 1..100) {
            println((1..4).random())
        }

        //从1 到 100的循环
        for (i in 1..100) {
            println((1..4).random())
        }
    }
}