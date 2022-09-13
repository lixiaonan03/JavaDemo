package kotlintest.outer

/**
 * @author：李晓楠
 * 时间：2022/8/30 18:19
 */
class Outer(var name:String){
    //声明为inner的嵌套类叫做内部类，内部类可以调用外部类的属性，但是嵌套类却不行。
    inner class Inner(var log: Double, var lat: Double) {
        fun print() {
            println(name) // 嵌套类调用外部的属性，这里会编译报错
        }
    }



    fun main(args: Array<String>) {

        setT(object : T(){ // 这里需要带括号，T类必须是open的，
            override fun t() { // 所覆盖的方法也必须是open的

            }
        })
    }

    fun setT(t: T) {

    }

    lateinit var textViews: List<out String>
    lateinit var textViews1: List<String>
}


open class T {
    open fun t() {

    }
}