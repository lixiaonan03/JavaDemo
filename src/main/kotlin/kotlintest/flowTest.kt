package kotlintest

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

/**
 * @author：李晓楠
 * 时间：2022/2/17 20:42
 */
object flowTest {


    @JvmStatic
    fun main(args: Array<String>) {
        println("之前的的==${Thread.currentThread().name}")

        val ss =GlobalScope.launch{
            println("开始前==${Thread.currentThread().name}")
            val dd = makeLoinRequest("测试的12333")
            println("开始后==${Thread.currentThread().name}")
        }

        println("之后的==${Thread.currentThread().name}")
        Thread.sleep(10000)
//        GlobalScope.async {
//            flowTest()
//        }
//        Thread.sleep(2000)
//        GlobalScope.run {
//
//        }
//
//        GlobalScope.launch {
//
//        }

        // Job and Dispatcher are combined into a CoroutineContext which
        // will be discussed shortly
        val scope = CoroutineScope(Job() + Dispatchers.Main).launch {

        }
    }

//    private suspend fu = SparseArray<Long>()  n flowTest(){
//        flow{
//            for (i in 1..5){
//                delay(100)
//                emit(i)
//            }
//        }.collect {
//            println(it)
//        }
//    }

    suspend fun makeLoinRequest(jsonBody: String): String {
        delay(2000)
        println("协=$jsonBody===${Thread.currentThread().name}")
        return "的"

//        return withContext(Dispatchers.IO){
//            println("协程的==${Thread.currentThread().name}")
//            delay(2000)
//            return@withContext "的"
//        }
    }

}

