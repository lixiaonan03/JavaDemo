package kotlintest.snspend

import kotlinx.coroutines.*

/**
  *  @author 李晓楠
  *  功能描述: 协程job 控制的说明
  *  时 间： 2022/12/22 14:10 
  */
object GlobalScopeJobTest {

    @JvmStatic
    fun main(args: Array<String>) {
        /**
         * Job 是协程的句柄。如果把门和门把手比作协程和Job之间的关系，那么协程就是这扇门，Job就是门把手。
         * 意思就是可以通过Job实现对协程的控制和管理。
         * Job具有生命周期并且可以取消，它也是上下文元素，继承自CoroutineContext。
         */
//        jobTest()
        cancelTest()
        Thread.sleep(6000L) // 阻塞主线程 2 秒钟来保证 JVM 存活
    }

    /**
     * 模拟一个无限循环的协程，当协程是活跃状态时每秒钟打印两次消息，1.2秒后取消协程
     */
    private fun jobTest() = runBlocking {
        val startTime = System.currentTimeMillis()
        val job = launch(Dispatchers.Default){
            var nextPrintTime = startTime
            var i = 0
            while (isActive) {//当job是活跃状态继续执行
                if (System.currentTimeMillis() >= nextPrintTime) {//每秒钟打印两次消息
                    println("job: I'm sleeping ${i++} ...")
                    nextPrintTime += 500
                }
            }
        }
        delay(1200)//延迟1.2s
        println("等待1.2秒后")
        //job.join()
        //job.cancel()
        /**
         * 协程的取消是 协作 的。一段协程代码必须协作才能被取消。 所有 kotlinx.coroutines 中的挂起函数都是 可被取消的 。
         * 它们检查协程的取消， 并在取消时抛出 CancellationException。 然而，如果协程正在执行计算任务，并且没有检查取消的话，那么它是不能被取消的
         * 参考：https://www.kotlincn.net/docs/reference/coroutines/cancellation-and-timeouts.html
         * 有点类似于线程中断的标志位
         */
        job.cancelAndJoin()//取消任务并等待任务完成
        println("协程被取消并等待完成")
    }

    private fun cancelTest(){
        runBlocking {
            val startTime = System.currentTimeMillis()
            val job = launch(Dispatchers.Default) {
                var nextPrintTime = startTime
                var i = 0
                while (i < 5) { // 一个执行计算的循环，只是为了占用 CPU
                    // 每秒打印消息两次
                    if (System.currentTimeMillis() >= nextPrintTime) {
                        println("job: I'm sleeping ${i++} ...")
                        nextPrintTime += 500L
                    }
                }
//               这种因为有判断所以取消之后会立马取消
//                while (isActive){
//                    println("job==isActive: I'm sleeping ${i++} ...")
//                    nextPrintTime += 500L
//                }

//                try {
//                    repeat(1000) { i ->
//                        println("job: I'm sleeping $i ...")
//                        delay(500L)
//                    }
//                } finally {
//                    println("job: I'm running finally")
//                }

//                try {
//                    repeat(1000){
//                        println("job: I'm sleeping $i ...")
//                        delay(500L)
//                    }
//                }finally {
//                    withContext(NonCancellable){
//                        println("job: I'm running finally")
//                        delay(1000L)
//                        println("job: And I've just delayed for 1 sec because I'm non-cancellable")
//                    }
//                }

                val result = withTimeoutOrNull(1300L){
                    repeat(1000) { i ->
                        println("I'm sleeping $i ...")
                        delay(500L)
                    }
                    "Done" // 在它运行得到结果之前取消它
                }
            }
            delay(1300L) // 等待一段时间
            println("main: I'm tired of waiting!")
            job.cancelAndJoin() // 取消一个作业并且等待它结束 因为取消的时候还没结束 所以协程需要执行完
            println("main: Now I can quit.")
        }
    }


    /**
     * Deferred
       Deferred继承自Job，具有与Job相同的状态机制。它是async构建协程返回的一个协程任务，
       可通过调用await()方法等待协程执行完成并获取结果。不同的是Job没有结果值，Deffer有结果值。
     */
}