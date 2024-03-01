package kotlintest

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.flow
import kotlin.coroutines.EmptyCoroutineContext

/**
  *  @author lixiaonan
  *  功能描述: 
  *  时 间： 2021/9/15 12:04 下午 
  */
object KotlinTest {


    @JvmStatic
    fun main(args: Array<String>) {
        Person()
        val s = """
    hello world
    !!!
""".trimIndent()

        val s2 = """
    $s
hello2
    world2
    !!!
""".trimIndent()
        println(s)
        println(s2)

        GlobalScope.launch(Dispatchers.IO) { // 在后台启动一个新的协程并继续
//            delay(1000L) // 非阻塞的等待 1 秒钟（默认时间单位是毫秒）
            println("World!") // 在延迟后打印输出
        }
        println("Hello,") // 协程已在等待时主线程还在继续

//        Thread.sleep(2000L) // 阻塞主线程 2 秒钟来保证 JVM 存活

//
//        GlobalScope.launch(
//            context = Dispatchers.IO, start = CoroutineStart.DEFAULT,block = {
//                // block 就是其中协程的
//
//
//               launch(Dispatchers.Main) {
//                   //再切回来的
//               }
//            }
//        )
//
//        runBlocking {
//
//        }


//        GlobalScope.launch(Dispatchers.Main) { // 从主线程开始
//            withContext(Dispatchers.IO){
//
//            }
//
//        }
    }
}