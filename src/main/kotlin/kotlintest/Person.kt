package kotlintest

/**
  *  @author lixiaonan
  *  功能描述: kotlin写的实体类的
  *  时 间： 2021/9/14 6:23 PM 
  */
class Person{
    val firstProperty = "First property: ".also(::println)
    /**
     * 先执行init 方法 后执行构造方法
     */
    constructor(name: String, gender: Boolean){
        println("constructor有参")
    }
    constructor(){
        println("constructor")
    }
    private var gender : Boolean = true
    /*初始化代码块*/
    init {
        println("Person init 2,gender:${gender}")
    }
    /*初始化代码块*/
    init {
        println("Person init 1")
    }
    val twoProperty = "two property: ".also(::println)
    companion object{
        @JvmStatic
        val list = arrayListOf<String>()
    }
}
