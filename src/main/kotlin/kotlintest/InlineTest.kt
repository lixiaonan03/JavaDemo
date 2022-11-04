package kotlintest

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * @author：李晓楠
 * 时间：2022/7/15 15:45
 */
object InlineTest {

    @JvmStatic
    fun main(vararg args: String) {
         var i=0
         var i1=0
         println("i++ == ${i++}")
         // 这个地方会阻塞主线程
         runBlocking {
             launch(Dispatchers.IO){
                 delay(1000)
                 println("协程里面的= ${Thread.currentThread().name}")
             }
         }
         println("++i == ${++i1}")
         test()
         run()
    }

    /**
     * 直接声明内联函数 意义不大
     */
    inline fun  test(){
         println("我是内联函数")
     }

    /**
     * 传参如果是函数类型 查看对应的java就会发现 参数其实是创建了一个 Function1 对象这种情况下如果用内联函数可以
     * 节约对象
     */
    private inline fun runCatch(block:(lxn:String) -> Unit){
        try {
           block.invoke("传入的参数的")
        }catch(e:Exception){
           e.printStackTrace()
        }
    }

    private fun run(){
        runCatch {
            println("run方法中的$it")
        }
    }
}