package kotlintest.suanfa

/**
 * @author：李晓楠
 * 时间：2022/9/4 11:33
 */
object Ser {
    @JvmStatic
    fun main(args: Array<String>) {
       val nums = arrayOf(1, 2, 3, 4)
       println("2分查找的===${fen2(nums,2)}")
    }


    /**
     * 2分查找的
     */
    fun  fen2(nums:Array<Int>,target:Int):Int{
       var left = 0
       var right = nums.size -1
       while (left <= right){
           val mid = (right -left)/2 +left
           val num  = nums[mid]
           if(num == target){
               return mid
           }else if(num > target){
               right = mid-1
           }else{
               left = mid+1
           }
       }

       return -1
    }
}