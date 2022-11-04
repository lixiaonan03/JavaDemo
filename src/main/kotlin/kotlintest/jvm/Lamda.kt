package kotlintest.jvm

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.newCoroutineContext
import java.time.LocalDate

/**
 * @author：李晓楠
 * 时间：2022/9/21 19:18
 */
class Employee(
    val id: String,
    val joined: LocalDate,
    val managerId: String?
)

class EmployeeRepository(val allEmployees: () -> Sequence<Employee>) {
    fun joinedAfter(date: LocalDate) =
        allEmployees()
            .filter { it.joined >= date }
            .toList()

    fun reports(manager: Employee) =
        allEmployees()
            .filter { it.managerId == manager.id }
            .toList()
}

fun main() {
    val str = "123"
    val subStr =str.substring(0,2)
    println("$subStr=$str")

    val employee = Employee("1", LocalDate.MIN,"m1")
    val employee2 = Employee("1", LocalDate.MAX,"1")
    val list = arrayListOf<Employee>()
    list.add(employee)
    list.add(employee2)
    foo()
}

fun ordinaryFunction(block:() -> Unit){
    println("hi")
    block.invoke()
}
//sampleStart
fun foo() {
//    ordinaryFunction {
//        println("hi 方法里面的东西===")
//        return@ordinaryFunction // 错误：不能使 `foo` 在此处返回
//    }
    inlined {
        println("hi 方法里面的东西===")
        return
    }
}

inline  fun inlined(block: () -> Unit){
    println("hi")
    block.invoke()
}
