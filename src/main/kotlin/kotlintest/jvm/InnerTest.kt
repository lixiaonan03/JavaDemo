package kotlintest.jvm

import Jvm.Outer
import Jvm.Outer.LxnCallBack

/**
 * @author：李晓楠
 * 时间：2022/7/26 17:36
 */
object InnerTest {
    @JvmStatic
    fun main(args: Array<String>) {
        val outer = Outer()
        var num = args.size
        outer.setLxnCallBack(object : LxnCallBack {
            override fun out(aa: String) {
                num++;
                println("num===$num")
                println("接口输出的===$aa")
            }
        })
        outer.click(num)
    }
}