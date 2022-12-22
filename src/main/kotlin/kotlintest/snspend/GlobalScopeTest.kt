package kotlintest.snspend

import kotlinx.coroutines.*

/**
  *  @author 李晓楠
  *  功能描述: 协程作用域的测试
  *  时 间： 2022/12/22 15:53
  */
object GlobalScopeTest {

    @JvmStatic
    fun main(args: Array<String>) {
        scopeTest()
        Thread.sleep(6000L)
    }

    private fun scopeTest() {
        //创建一个根协程
        GlobalScope.launch {//父协程
            println("GlobalScope父协程==${Thread.currentThread().name}")
            launch {//子协程
                println("GlobalScope的子协程==${Thread.currentThread().name}")
            }
            launch {//第二个子协程
                println("GlobalScope的第二个子协程==${Thread.currentThread().name}")
            }
        }

        //为UI组件创建主作用域
//        val mainScope = MainScope()
//        mainScope.launch {//启动协程
//            //todo
//            println("mainScope==${Thread.currentThread().name}")
//        }


        GlobalScope.launch {//父协程
            //创建一个独立的协程作用域，直到所有启动的协程都完成后才结束自身。它是一个挂起函数，
            // 需要运行在协程内或挂起函数内。当这个作用域中的任何一个子协程失败时，
            // 这个作用域失败，所有其他的子程序都被取消。为并行分解工作而设计的。
            coroutineScope{
                println("coroutineScope==${Thread.currentThread().name}")
            }
        }
        GlobalScope.launch {//父协程
//            与coroutineScope类似，不同的是子协程的异常不会影响父协程，也不会影响其他子协程。
//            （作用域本身的失败(在block或取消中抛出异常)会导致作用域及其所有子协程失败，但不会取消父协程。）
            supervisorScope{
                println("supervisorScope==${Thread.currentThread().name}")
            }
        }
    }

}