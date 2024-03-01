package kotlintest.pattern.gongchang

/**
 * 工厂模式的使用demo
 * @author：李晓楠
 * 时间：2023/10/10 14:22
 */
open class Computer(){
    open fun start(){}
}


class DellComputer:Computer(){
    override fun start() {
        println("戴尔电脑启动")
    }
}
class LenovoComputer:Computer(){
    override fun start() {
        println("联想电脑启动")
    }
}


/**
 * 静态工厂方法模式，由一个工厂对象决定创建出哪个产品类的实例
 */
class ComputerFactory{
    companion object{
        fun createComputer(type:String):Computer{
            return when(type){
                "Dell"->DellComputer()
                "Lenovo"->LenovoComputer()
                else->throw IllegalArgumentException("没有这个类型的电脑")
            }
        }
    }
}


/**
 * 工厂方法模式，定义一个创建对象的接口，让子类决定实例化哪一个类
 */
abstract class ComputerFactory2 {
    abstract fun <T : Computer?> createComputer(clz: Class<T>?): T
}
class GDComputerFactory : ComputerFactory2() {
    override fun <T : Computer?> createComputer(clz: Class<T>?): T {
        var computer: Computer? = null
        val classname = clz?.name
        try {
            computer = Class.forName(classname).getDeclaredConstructor().newInstance() as Computer
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return (computer as T?)!!
    }
}