@file:JvmName("ToolExr")
package kotlintest

/**
 * @author：李晓楠 
 * 时间：2022/12/28 17:19
 */

val String.addMoneyUnit get() = "$$this"



fun String.toBankCode(): String {
    return this.replace(Regex("(.{4})"), "$1 ")
}