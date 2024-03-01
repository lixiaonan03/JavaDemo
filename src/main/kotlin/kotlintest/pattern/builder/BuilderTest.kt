package kotlintest.pattern.builder

/**
 * builder 建造者模式
 */
object BuilderTest {
    @JvmStatic
    fun main(args: Array<String>) {
        val computer = Computer2("i7", "华硕", "三星")
        println(computer.toString())

        val computer2 = Computer2.Builder()
            .setCpu("i7")
            .setMainBoard("华硕")
            .setRam("三星")
            .build()
        println(computer2.toString())
    }
}

/**
 * Builder 构建者模式创建Computer
 */
class Computer2(val mCpu:String,val mMainBoard:String,val mRam:String){
    class Builder {
        private var cpu: String = ""
        private var mainBoard: String = ""
        private var ram: String = ""

        fun setCpu(cpu: String): Builder {
            this.cpu = cpu
            return this
        }

        fun setMainBoard(mainBoard: String): Builder {
            this.mainBoard = mainBoard
            return this
        }

        fun setRam(ram: String): Builder {
            this.ram = ram
            return this
        }

        fun build(): Computer2 {
            return Computer2(cpu, mainBoard, ram)
        }
    }
}



