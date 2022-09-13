@file:JvmName("ZhengZeUtil")
package kotlintest

import java.util.regex.Pattern

/**
 * @author：李晓楠
 * 时间：2022/6/27 16:47
 */
object zhengze {

    /**
     * Return whether input matches the regex.
     *
     * @param regex The regex.
     * @param input The input.
     * @return `true`: yes<br></br>`false`: no
     */
    fun isMatch(regex: String?, input: CharSequence?): Boolean {
        return input != null && input.isNotEmpty() && Pattern.matches(regex, input)
    }

    @JvmStatic
    fun main(vararg args: String) {
        //正则表达式测试的
//        println(isMatch("^((17[0,1])|(16[2,5,7]))\\d{8}$", "16500000000"))
//        var list = arrayListOf<Int>()
//        list.add(0)
//        println("=="+!list.contains(0))
        val allSpecsIdList = mutableListOf<List<String>>()
        val allSpecsIdList1= mutableListOf<String>()
        val allSpecsIdList2= mutableListOf<String>()
        val allSpecsIdList3= mutableListOf<String>()
        allSpecsIdList1.add("1")
        allSpecsIdList1.add("2")
        allSpecsIdList2.add("3")
        allSpecsIdList2.add("4")
        allSpecsIdList3.add("5")
        allSpecsIdList.add(allSpecsIdList1)
        allSpecsIdList.add(allSpecsIdList2)
        allSpecsIdList.add(allSpecsIdList3)

       val ss = allSpecsIdList.reduce { total, next ->
            val list = mutableListOf<String>()
            list.addAll(total)
            println("==total=${total}")
            println("==next=${next}")
            list.addAll(next)
            return@reduce list
        }.toMutableList()
        println("==ss=${ss.size}")
    }

}