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
        println(isMatch("^((17[0,1])|(16[2,5,7]))\\d{8}$", "16500000000"))
        var list = arrayListOf<Int>()
        list.add(0)
        println("=="+!list.contains(0))
    }

}