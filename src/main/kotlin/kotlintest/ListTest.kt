package kotlintest

/**
 * @author：李晓楠
 * 时间：2022/6/29 12:23
 */
object ListTest {
    @JvmStatic
    fun main(vararg args: String) {
        val list = listOf<Int>(0,1,2,3,4,5)
        var listNew = list.subList(1,list.size).toMutableList()
        listNew.add(0,9)
        println("旧的==$list")
        println("新的==$listNew")

        MutableMap<>()

        listSort()


        val addTest = arrayListOf<Int>(1,2)
        val addNow = arrayListOf<Int>(3,4)
        addTest.addAll(addNow)
        println("添加==$addTest")
    }


    fun listSort() {
        val user1 = User(1, 4)
        val user2 = User(0, 3)
        val user3 = User(1, 1)
        val user4 = User(0, 2)
        val userList = mutableListOf(user2, user1, user4, user3)

        println("排序前：")
        userList.forEach(::println)

//        var a = userList.sortWith(Comparator { u1, u2 ->
//            if (u1.doYes != u2.doYes) {
//                u2.doYes.compareTo(u1.doYes) // 状态以降序排序
//            } else {
//                u1.sort.compareTo(u2.sort)         // 名字以升序排序
//            }
//        })
        userList.sortWith(compareBy({-it.doYes},{it.sort}))

        val newTest = userList.sortedWith(compareBy({-it.doYes},{it.sort}))

        println("排序后：")
        newTest.forEach(::println)
        println("排序后list：")
        userList.forEach(::println)
    }

}
data class User(val doYes:Int,val sort:Int)