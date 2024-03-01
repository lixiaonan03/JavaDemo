package kotlintest.suanfa

/**
 * @author：李晓楠
 * 时间：2023/8/9 15:08
 */
object RandomTest {

    @JvmStatic
    fun main(args: Array<String>) {
        val numbers = listOf(1, 2, 3, 4)
        val numberOfSelections = 2

        val selectedNumbers = selectRandomNumbers(numbers, numberOfSelections)
        println("随机选择的数字是：$selectedNumbers")
    }

    private fun selectRandomNumbers(numbers: List<Int>, numberOfSelections: Int): List<Int> {
        val random = java.util.Random()

        // 使用 Fisher-Yates 洗牌算法对数组进行随机排列
        // 参考这个地址：https://zhuanlan.zhihu.com/p/110630952
        val shuffledNumbers = numbers.toMutableList()
        // 按照倒序遍历
        for (i in shuffledNumbers.size - 1 downTo 1) {
            //生成一个包含i 的随机数
            val j = random.nextInt(i + 1)
            val temp = shuffledNumbers[i]
            //把最后一个数和随机数交换  然后依次替换前一个
            shuffledNumbers[i] = shuffledNumbers[j]
            shuffledNumbers[j] = temp
        }

        // 选择前 numberOfSelections 个元素作为结果
        return shuffledNumbers.subList(0, numberOfSelections)
    }

}