package kotlintest.snspend

import kotlinx.coroutines.*
import java.math.BigInteger
import kotlin.concurrent.thread
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

/**
 * @author：李晓楠
 * 时间：2022/8/1 19:36
 */
suspend fun main():Unit = coroutineScope{
//    println(fibonacci.take(10).toList())
    println("Before")
    suspendCoroutine<Unit> {
        thread {
            println("Suspended")
            Thread.sleep(1000)
            it.resume(Unit)
            println("Resumed")
        }
    }
    println("After")

    launch {
        println("launch==${Thread.currentThread().name}")
    }



}



val fibonacci = sequence{
    var first = 0.toBigInteger()
    var second =1.toBigInteger()
    while (true){
        yield(first)
        val temp = first
        first += second
        second = temp
    }
}


