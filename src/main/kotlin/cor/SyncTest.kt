package cor

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.lang.Thread.sleep
import kotlin.concurrent.thread
import kotlin.coroutines.*

/**
 * 协程的初步学习的测试类
 * @author：李晓楠
 * 时间：2022/9/13 19:27
 */
object SyncTest {
    @JvmStatic
    fun main(args: Array<String>) {

        runBlocking {
            launch {
                println("Start: ${System.currentTimeMillis()}")

                // 使用 sleep 进行阻塞式挂起
                println("Sleeping for 2000ms...")
                sleep(20000)
                println("Wake up: ${System.currentTimeMillis()}")

                // 使用 delay 进行非阻塞式挂起
                println("Delaying for 2000ms...")
                delay(20000)
                println("Resumed: ${System.currentTimeMillis()}")
            }
        }


        //同步代码
        println("A")
        println("B")
        //异步代码
        val task = {
            println("c")
            println("线程执行的${Thread.currentThread().name}")
            callBack()
        }
        println("D")
        thread(block = task)
        println("E")


        //协程的初步学习
        val continuation  = suspend {
            println("协程开始==${Thread.currentThread().name}=")
            5
        }.createCoroutine(object:Continuation<Int>{
            override val context: CoroutineContext
                get() = EmptyCoroutineContext

            override fun resumeWith(result: Result<Int>) {
                println("协程结束的=$result==${Thread.currentThread().name}")
            }

        })
        //协程启动
        continuation.resume(Unit)

        //另一中创建
        val continuation2 = suspend {
            println("协程另一种创建的==${Thread.currentThread().name}=")
            "^"
        }.startCoroutine(object :Continuation<String>{
            override val context: CoroutineContext
                get() = EmptyCoroutineContext

            override fun resumeWith(result: Result<String>) {
                println("协程另一种结束的=$result==${Thread.currentThread().name}")
            }

        })



    }

    private fun callBack(){
        println("异步回调执行的${Thread.currentThread().name}")
    }


    suspend fun sunspendFun01(a:Int){
        return
    }

    suspend fun func02(a:String,b:String) =  suspendCoroutine<Int> {
        run {
            thread {
                it.resumeWith(Result.success(5))
            }
        }
    }
}