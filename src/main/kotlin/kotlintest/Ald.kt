package kotlintest

/**
 * @author：李晓楠
 * 时间：2022/7/5 20:15
 */
class Ald(val cc: String){

    init {
        println("Aha init1 ")
    }

    constructor(name: String, ff: Float) : this("fdff") {
        println("Aha constructor 有参")
    }

    init {
        println("Aha init2")
    }

    class A1{

    }
    inner class A2{

    }
}