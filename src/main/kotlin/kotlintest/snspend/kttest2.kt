package kotlintest.snspend

import kotlinx.coroutines.*
import util.TimeUtil
import kotlin.system.measureTimeMillis
import kotlin.Result as Result

/**
 * @author：李晓楠
 * 时间：2022/8/1 19:36
 */
 fun main():Unit = runBlocking{
     val time = measureTimeMillis {
         println("1执行开始===${TimeUtil.getNowTimeForStr()}")
         val one = async { doOne() }
         println("1执行中间===${TimeUtil.getNowTimeForStr()}")
         val two = async { doTwo() }
         println("1执行结束===${TimeUtil.getNowTimeForStr()}")
         println("The result: ${one.await() + two.await()}")
     }

    println("Completed in $time ms")
    val time1 = measureTimeMillis {
        println("2执行开始===${TimeUtil.getNowTimeForStr()}")
        val one = async { doOne() }.await()
        println("2执行中间====${TimeUtil.getNowTimeForStr()}")
        val two = async { doTwo() }.await()
        println("2执行结束====${TimeUtil.getNowTimeForStr()}")
        println("The result: ${one + two}")
    }
    println("Completed in $time1 ms")
}

private suspend fun doOne():Int{
    delay(1000)
    return 10
}
private suspend fun doTwo():Int{
    delay(1000)
    return 20
}



