package kotlintest.pattern.decorator

/**
 * 装饰者模式的使用demo   https://www.jianshu.com/p/d80b6b4b76fc
 * @author：李晓楠
 * 时间：2023/8/15 16:26
 */
object Client {
    @JvmStatic
    fun main(args: Array<String>) {
       val sage = Monkey()
        //使用了1种功能
        val fish = Fish(sage)
        fish.move()
        //使用了2种功能
        val bird = Bird(Fish(sage))
        bird.move()
    }
}


/**
 * 抽象组件  抽象构件角色“齐天大圣”接口，定义了一个move()方法，这是所有的具体构建类和装饰类必须实现的。
 */
interface TheGreatestSage {
    fun move()
}

/**
 * 具体组件  具体构件角色“齐天大圣”类，实现了“齐天大圣”接口的move()方法。
 */
class Monkey: TheGreatestSage {
    override fun move() {
        println("Monkey Move")
    }
}

/**
 * 抽象装饰  抽象装饰角色“七十二变”类，继承了“齐天大圣”接口，从外类来扩展“齐天大圣”类的功能，但对于“齐天大圣”来说，是无需知道“七十二变”的存在的。
 */
open class Change(private val sage:TheGreatestSage):TheGreatestSage{
    override fun move() {
        sage.move()
    }

}

/**
 * 具体的装饰 鱼儿
 */
class Fish(sage: TheGreatestSage) : Change(sage) {

    override fun move() {
        super.move()
        println("Fish Move")
    }
}

/**
 * 具体的装饰 鸟儿
 */
class Bird(sage: TheGreatestSage) : Change(sage) {

    override fun move() {
        super.move()
        println("Bird Move")
    }
}