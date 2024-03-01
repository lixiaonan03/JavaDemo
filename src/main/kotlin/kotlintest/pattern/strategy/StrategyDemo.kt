package kotlintest.pattern.strategy

/**
 * 策略模式的使用demo
 * @author：李晓楠
 * 时间：2023/8/9 17:54
 */
object StrategyDemo {

    @JvmStatic
    fun main(args: Array<String>) {
        val travelContext = TravelContext(FlyTravel())
        travelContext.travel()
    }
}


/**
 * 使用的上下文环境  封装一个上下文环境
 */
class TravelContext(private val travel: Travel) {
    fun travel() {
        travel.onTravel()
    }
}

/**
 * 抽离共同的行为  定义具体的行为
 */
interface Travel {
    fun onTravel()
}

class FlyTravel : Travel {
    override fun onTravel() {
        println("飞机出行")
    }
}

class TrainTravel : Travel {
    override fun onTravel() {
        println("火车出行")
    }
}


class CarTravel : Travel {
    override fun onTravel() {
        println("汽车出行")
    }
}