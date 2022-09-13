package kotlintest.outer


/**
 * @author：李晓楠
 * 时间：2022/8/31 08:53
 */
open class Animal()

class Dog(name: String) : Animal()
class Pig(name: String) : Animal()



class Box<T>(t: T){
    var value =t
}


fun main() {
    val box: Box<Int> = Box<Int>(1)

}

