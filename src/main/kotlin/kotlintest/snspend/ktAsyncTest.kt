package kotlintest.snspend

import kotlinx.coroutines.*
import util.TimeUtil
import kotlin.system.measureTimeMillis
import kotlin.Result as Result

/**
 * 协程中 async 练习使用
 * @author：李晓楠
 * 时间：2022/8/1 19:36
 */
 fun main():Unit = runBlocking{
     val time = measureTimeMillis {
         println("1执行开始===${TimeUtil.getNowTimeForStr()}===${Thread.currentThread().name}")
         val one = async { doOne() }
         println("1执行中间===${TimeUtil.getNowTimeForStr()}")
         val two = async { doTwo() }
         println("1执行结束===${TimeUtil.getNowTimeForStr()}")
         //await() 不能在协程之外调用，因为它需要挂起直到计算完成，而且只有协程可以以非阻塞的方式挂起。所以把它放到协程中。
         println("The result: ${one.await() + two.await()}")
     }

    println("Completed in $time ms")
    val time1 = measureTimeMillis {
        println("2执行开始===${TimeUtil.getNowTimeForStr()}")
        //await 会立马阻塞执行
        //2执行开始===2022-12-22 14:47:29:039
        //2执行中间====2022-12-22 14:47:30:042
        //2执行结束====2022-12-22 14:47:31:049
        val one = async { doOne() }.await()
        println("2执行中间====${TimeUtil.getNowTimeForStr()}")
        val two = async { doTwo() }.await()
        println("2执行结束====${TimeUtil.getNowTimeForStr()}")
        println("The result: ${one + two}")
    }
    println("Completed in $time1 ms")


    /**
     * 这2个 异步执行的代码 是在同一线程中执行的
     * 这个写法适合 需要等几个接口的数据回来之后一起处理的情况
     */
    launch {
        val deferreds = listOf(
            async {
                println("第一个异步==${Thread.currentThread().name}")
            },
            async {
                println("第二个异步==${Thread.currentThread().name}")
            }
        )
        deferreds.awaitAll()
    }
}

private suspend fun doOne():Int{
    delay(1000)
    println("doOne =${TimeUtil.getNowTimeForStr()}==${Thread.currentThread().name}")
    return 10
}
private suspend fun doTwo():Int{
    delay(1000)
    println("doTwo =${TimeUtil.getNowTimeForStr()}==${Thread.currentThread().name}")
    return 20
}



