package kotlintest.suanfa

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
//        sortMaoPao(nums)
        val myArray = intArrayOf(5, 3, 9, 1, 7)
        quickSort(myArray)
        println(myArray.contentToString())
        //冒泡排序的
    }

    /**
     * 冒泡排序的   就是比较相关的元素，如果第一个比第二个大，就交换他们两个。  用2层循环完成    时间复杂度：O(n2)
     *
     * 比较相邻的元素。如果第一个比第二个大，就交换他们两个。对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对。这步做完后，最后的元素会是最大的数。
     * 针对所有的元素重复以上的步骤，除了最后一个。持续每次对越来越少的元素重复上面的步骤，直到没有任何一对数字需要比较。
     */
    private fun sortMaoPao(array: Array<Int>){
        //第一层遍历的
        for(i in array.indices){
            for(j in 0 until (array.size-1)){
                var temp:Int
                //如果前面的比后面的大，就交换位置  这样一轮之后就把最大的放到了最后面
                if(array[j] > array[j+1]){
                    temp = array[j]
                    array[j] = array[j+1]
                    array[j+1] = temp
                }
            }
        }
    }

    /**
     * 针对上面的排序进行优化，一轮下来已经把最大的放到最右边了 没
     *
     */
    private fun sortMaoPaoYouHua(array: Array<Int>) {
        val n = array.size

        for (i in 0 until n) {
            // 最后 i 个元素已经有序，无需再比较 跟上面的区别就在这个地方
            for (j in 0 until (n - i - 1)) {
                if (array[j] > array[j + 1]) {
                    // 如果前面的元素比后面的元素大，交换它们的位置
                    val temp = array[j]
                    array[j] = array[j + 1]
                    array[j + 1] = temp
                }
            }
        }
    }

    /**
     * 快排的写法
     */
    private fun quickSort(array: IntArray): IntArray {
        if (array.size <= 1) {
            return array
        }
        val pivot = array[array.size / 2]
        val (left, right) = partition(array, pivot)
        return quickSort(left) + pivot + quickSort(right)
    }

    private fun partition(array: IntArray, pivot: Int): Pair<IntArray, IntArray> {
        val left = array.filter { it < pivot }.toIntArray()
        val right = array.filter { it > pivot }.toIntArray()
        return Pair(left, right)
    }



}