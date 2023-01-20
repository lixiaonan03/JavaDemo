package kotlintest.constructor

/**
 * @author：李晓楠
 * 时间：2022/8/30 18:08
 */
class Person(name:String){

    constructor(name:String,age:Int):this(name){

    }


    val sum = fun Int.(other: Int): Int = this + other



    constructor(name: String,age: Int,sex:String):this(name,age){
      
    }
}