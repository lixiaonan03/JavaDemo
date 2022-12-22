package kotlintest.snspend

import kotlinx.coroutines.*
import kotlin.Result as Result

/**
 * @author：李晓楠
 * 时间：2022/8/1 19:36
 */
suspend fun main():Unit = coroutineScope{
    // coroutineScope  该函数被 suspend 修饰，是一个挂起函数，前面我们说了挂起函数是不会阻塞线程的，它只会挂起协程，而不阻塞线程。
    launch {
        delay(3000L)
        println("World! =${Thread.currentThread().name}")
    }
    println("Hello,=${Thread.currentThread().name}")

    launch(CoroutineName("name1")){

    }
    launch(CoroutineName("name1")+ Job()){

    }

    launch(Dispatchers.IO){
        println("Dispatchers.IO步==${Thread.currentThread().name}")
    }
    println("Hello,.IO步=${Thread.currentThread().name}")
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



