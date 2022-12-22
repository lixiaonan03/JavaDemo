package kotlintest.snspend

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

/**
  *  @author 李晓楠
  *  功能描述: 协程创建的基本练习说明
  *  时 间： 2022/12/22 14:10 
  */
object GlobalScopeCreatTest {

    @JvmStatic
    fun main(args: Array<String>) {
//        runBloTest()
        asyncTest2()
        Thread.sleep(6000L) // 阻塞主线程 2 秒钟来保证 JVM 存活
    }

    /**
     * runBlocking 顶层函数，创建一个新的协程同时 --阻塞当前线程-- ，直到其内部所有逻辑以及子协程所有逻辑全部执行完成，
     * 返回值是泛型T，一般在项目中不会使用，主要是为main函数和测试设计的。
     * runBlocking 虽然会阻塞当前线程的，但其内部运行的协程又是非阻塞的。
     */
    private fun runBloTest() {
        println("start==${Thread.currentThread().name}")
        //context上下文使用默认值,阻塞当前线程，直到代码块中的逻辑完成
        runBlocking {
            //这里是协程体
            delay(1000)//挂起函数，延迟1000毫秒
            println("runBlocking==${Thread.currentThread().name}")
        }
        println("end== ${Thread.currentThread().name}")
    }

    /**
     * 在应用范围内启动一个新协程，不会阻塞调用线程，协程的生命周期与应用程序一致。
     * 表示一个不绑定任何Job的全局作用域，用于启动顶层协程，这些协程在整个应用程序生命周期中运行，不会提前取消(不存在Job)。
     *
     * 默认协程 会在 DefaultDispatcher-worker-1 线程执行
     * launchTest =start===main
        launchTest==end===main
        GlobalScope.launch===DefaultDispatcher-worker-1
     */
    private fun launchTest() {
        println("launchTest =start===${Thread.currentThread().name}")
        //创建一个全局作用域协程，不会阻塞当前线程，生命周期与应用程序一致
        GlobalScope.launch {
            //在这1000毫秒内该协程所处的线程不会阻塞
            //协程将线程的执行权交出去，该线程继续干它要干的事情，到时间后会恢复至此继续向下执行
            delay(1000)//1秒无阻塞延迟（默认单位为毫秒）
            println("GlobalScope.launch===${Thread.currentThread().name}")
        }
        println("launchTest==end===${Thread.currentThread().name}")//主线程继续，而协程被延迟
    }

    /**
     * 通过CoroutineContext至少一个协程上下文参数创建一个 CoroutineScope对象。
     * 协程上下文控制协程生命周期和线程调度，使得协程和该组件生命周期绑定，组件销毁时，协程一并销毁，从而实现安全可靠地协程调用。这是在应用中最推荐使用的协程使用方式。
     */
    private fun launchTest2() {
        println("start=${Thread.currentThread().name}")
        //开启一个IO模式的协程，通过协程上下文创建一个CoroutineScope对象,需要一个类型为CoroutineContext的参数
        val job = CoroutineScope(Dispatchers.IO).launch {
            delay(1000)//1秒无阻塞延迟（默认单位为毫秒）
            println("CoroutineScope.launch=${Thread.currentThread().name}")
        }
        println("end=${Thread.currentThread().name}")//主线程继续，而协程被延迟
    }

    /**
     * 通过launch在一个协程中启动子协程，可以根据业务需求创建一个或多个子协程：
     * CoroutineScope.launch==DefaultDispatcher-worker-1
        CoroutineScope.launch 结束的==DefaultDispatcher-worker-1
        launch 子协程==DefaultDispatcher-worker-1

       默认在协程在一个线程中执行 都是 Dispatchers.Default 并且子协程不会阻塞父协程的运行
     */
    private fun launchTest3() {
        println("start==${Thread.currentThread().name}")
        GlobalScope.launch {
            delay(1000)
            println("CoroutineScope.launch==${Thread.currentThread().name}")

            //在协程内创建子协程
            launch {
                delay(1500)//1.5秒无阻塞延迟（默认单位为毫秒）
                println("launch 子协程==${Thread.currentThread().name}")
            }
            //子协程不会阻塞 DefaultDispatcher-worker-1 会执行这句 在执行子协程中的内容
            println("CoroutineScope.launch 结束的==${Thread.currentThread().name}")
        }
        println("end==${Thread.currentThread().name}")
    }


    /**
     * async类似于launch，都是创建一个不会阻塞当前线程的新的协程。它们区别在于：
     * async的返回是Deferred对象，可通过Deffer.await()等待协程执行完成并获取结果，而 launch 不行。常用于并发执行-同步等待和获取返回值的情况。
     *
     *  launch == DefaultDispatcher-worker-1
        asyncOne =DefaultDispatcher-worker-2
        result == HelloWord===DefaultDispatcher-worker-2
     *  注意：launch已经起了一个协程并切换到  worker-1 线程 所以 async 在worker-2 中执行
     *  执行完获取结果也是在 线程2 中 但是runBlocking启动的协程中线程并没切换 参考：ktAsyncTest 文件
     *
     */
    private fun asyncTest1() {
        println("start==${Thread.currentThread().name}")
        GlobalScope.launch {
            println("launch == ${Thread.currentThread().name}")
            val deferred: Deferred<String> = async {
                //协程将线程的执行权交出去，该线程继续干它要干的事情，到时间后会恢复至此继续向下执行
                delay(2000)//2秒无阻塞延迟（默认单位为毫秒）
                println("asyncOne =${Thread.currentThread().name}")
                "HelloWord"//这里返回值为HelloWord
            }

            //等待async执行完成获取返回值,此处并不会阻塞线程,而是挂起,将线程的执行权交出去
            //等到async的协程体执行完毕后,会恢复协程继续往下执行
            val result = deferred.await()
            println("result == $result===${Thread.currentThread().name}")
        }
        println("end==${Thread.currentThread().name}")
    }

    /**
     * async是不阻塞线程的，也就是说上面三个async{}异步任务是同时进行的 并且是在同一线程中 总耗时基本等于耗时最长的协程。
     * asyncOne =DefaultDispatcher-worker-2
        asyncTwo =DefaultDispatcher-worker-2
        asyncThr =DefaultDispatcher-worker-2
        result == 600===DefaultDispatcher-worker-2
        耗时 4039 ms
     */
    fun asyncTest2() {
        println("start==${Thread.currentThread().name}")
        GlobalScope.launch {
            val time = measureTimeMillis {//计算执行时间
                val deferredOne: Deferred<Int> = async {
                    delay(2000)
                    println("asyncOne =${Thread.currentThread().name}")
                    100//这里返回值为100
                }

                val deferredTwo: Deferred<Int> = async {
                    delay(3000)
                    println("asyncTwo =${Thread.currentThread().name}")
                    200//这里返回值为200
                }

                val deferredThr: Deferred<Int> = async {
                    delay(4000)
                    println("asyncThr =${Thread.currentThread().name}")
                    300//这里返回值为300
                }
                //等待所有需要结果的协程完成获取执行结果
                val result = deferredOne.await() + deferredTwo.await() + deferredThr.await()
                println("result == $result===${Thread.currentThread().name}")
            }
            print("耗时 $time ms")
        }
        println("end==${Thread.currentThread().name}")

        //因为执行的是异步方法 所以也会在子线程中执行 deferredGlobalScope =DefaultDispatcher-worker-2
        val deferredGlobalScope: Deferred<Int> = GlobalScope.async {
            //TODO 如果使用async作为最外层协程的开启方式，它期望最终是通过调用 await 来获取结果 (或者异常)，
            // 所以默认情况下它不会抛出异常。这意味着如果使用 async启动新的最外层协程，而不使用await，它会静默地将异常丢弃。
            // throw Throwable("")
            delay(2000)
            println("deferredGlobalScope =${Thread.currentThread().name}")
            200//这里返回值为200
        }
    }

}