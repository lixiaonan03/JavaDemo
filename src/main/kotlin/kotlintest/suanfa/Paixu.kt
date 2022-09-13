package kotlintest.suanfa

import java.util.*

/**
  *  @author 李晓楠
  *  功能描述: 排序算法的
  *  时 间： 2022/9/6 09:13
  */
object Paixu {
    @JvmStatic
    fun main(args: Array<String>) {
        //3中排序方法
        val nums = arrayOf(5,8,6,3,9,2,1,7)
        sortMaoPao(nums)
        println(nums.contentToString())
        //冒泡排序的
    }

    /**
     * 冒泡排序的
     */
    private fun sortMaoPao(array: Array<Int>){
        for (i in array.indices){
            for (j in 0 until (array.size-1)){
                var temp: Int
                if(array[j] > array[j+1]){
                    temp = array[j]
                    array[j] = array[j+1]
                    array[j+1] = temp
                }
            }
        }
    }
}