package kotlintest

/**
  *  @author lixiaonan
  *  功能描述: kotlin写的实体类的
  *  时 间： 2021/9/14 6:23 PM 
  */
class Person(){
    
    private var gender : Boolean = true

    constructor(name: String, gender: Boolean):this(){
        println("constructor")
    }

    /*初始化代码块*/
    init {
        println("Person init 2,gender:${gender}")
    }

    /*初始化代码块*/
    init {
        println("Person init 1")
    }
}
