package dequeDemo

import java.util.*

/**
  *  @author lixiaonan
  *  功能描述: kotlin的练习使用的
  *  时 间： 2021/9/14 5:36 PM 
  */
object ListTest {


    @JvmStatic
    fun main(args: Array<String>) {
        var list: MutableList<String> = mutableListOf()
        list.add("0")
        list.add("1")
        list.add("2")
        list.add("3")
        list.add("4")
        list.add("5")
        list.add("6")
        list.add("7")
        list.add("8")
        list.add("9")
        list.add("10")
        list = list.subList(0, 9)
        println("size====" + list.size)

        val list3=list.map { it ->
            it + "ceshi"
        }.toMutableList()
        println("size====" + list3[1])


        val numbers = setOf(1, 2, 3)
        println(numbers.map { it * 3 })
        println(numbers.mapIndexed { idx, value -> value * idx })


        val strArr = arrayOf("张三", "李四", "王二麻")
        val list1 = Arrays.asList(*strArr)
        println("strArr====$strArr")
        strArr[1] = "lxn"
        println("size====" + list1[1])

    }
}
