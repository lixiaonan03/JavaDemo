package kotlintest.outer

import java.lang.reflect.ParameterizedType


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
    println((box.javaClass.genericSuperclass is ParameterizedType))
}


internal open class Person<T, V>
internal class Teacher
internal class Student : Person<Student?, Teacher?>()
