package kotlintest

/**
 * @author：李晓楠
 * 时间：2022/7/15 15:45
 */
object InlineTest {

    @JvmStatic
    fun main(vararg args: String) {
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