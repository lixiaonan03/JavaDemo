package kotlintest.suanfa.sku

/**
 * @author：李晓楠
 * 时间：2023/6/6 10:38
 */
object SkuTest {


    @JvmStatic
    fun main(args: Array<String>) {
        val specsGroupList = mutableListOf<SpecsGroup>()
        //设置颜色的
        val specsGroupColor = SpecsGroup(
            "1001", "颜色", 1, mutableListOf(
                Specs("148388309", "薄荷绿", true, false, false),
                Specs("148388336", "蓝紫色", true, false, false),
                Specs("148388356", "白粉色", true, false, false),
                Specs("148388364", "白色", true, false, false),
                Specs("148388378", "黑白色", true, false, false),
                Specs("148388406", "白绿色", true, false, false),
            )
        )
        //尺寸的
        val specsGroupSize = SpecsGroup(
            "1002", "尺寸", 3, mutableListOf(
                Specs("148418955", "22码(内长14.6 脚长约13.5~14.1)", true, false, false),
                Specs("148418928", "23码(内长15.2 脚长约14.1~14.7)", true, false, false),
                Specs("148418929", "24码(内长15.8 脚长约14.7~15.3)", true, false, false),
                Specs("148418930", "25码(内长16.4 脚长约15.3~15.9)", true, false, false),
                Specs("148418987", "26码(内长17 脚长约15.9~16.5)", true, false, false),
                Specs("148418988", "27码(内长17.6 脚长约16.5~17.1)", true, false, false),
                Specs("148418975", "28码(内长18.2 脚长约17.1~17.7)", true, false, false),
                Specs("148418990", "29码(内长18.8 脚长约17.7~18.3)", true, false, false),
                Specs("148418991", "30码(内长19.4 脚长约18.3~18.9)", true, false, false),
                Specs("148418992", "31码(内长20 脚长约18.9~19.5)", true, false, false),
                Specs("148418993", "32码(内长20.6 脚长约19~20.1)", true, false, false),
                Specs("148418994", "33码(内长21.2 脚长约20.1~20.7)", true, false, false),
                Specs("148418995", "34码(内长21.8脚长约20.7~21.3)", true, false, false),
                Specs("148418996", "35码(内长22.4 脚长约21.3~21.9)", true, false, false),
                Specs("148535453", "36", true, false, false),
                Specs("148535454", "37", true, false, false),
                Specs("148535455", "38", true, false, false),
                Specs("148535456", "39", true, false, false),
                Specs("148535457", "40", true, false, false),
            )
        )
        val timeColor = SpecsGroup(
            "1003", "发货时间", 3, mutableListOf(
                Specs("16936802", "6.8 24点前发", true, false, false),
                Specs("16936893", "6.10 24点前发", true, false, false),
                Specs("17012073", "7.7 24点前发", true, false, false),
            )
        )
        specsGroupList.add(specsGroupColor)
        specsGroupList.add(specsGroupSize)
        specsGroupList.add(timeColor)

        val skuList = mutableListOf<Sku>()
        val sku1 = Sku("16936810", 71, mutableListOf("148388364", "148418983", "16936893"),"白色,22码(内长14.6 脚长约13.5~14.1),6.12 24点前发")
        val sku2 = Sku("16936811", 104, mutableListOf("148388364", "148418984", "16936893"),"白色,23码(内长15.2 脚长约14.1~14.7),6.12 24点前发")
        val sku3 = Sku("16936812", 126, mutableListOf("148388364", "148418985", "16936893"),"白色,24码(内长15.8 脚长约14.7~15.3),6.12 24点前发")
        val sku4 = Sku("16936813", 126, mutableListOf("148388364", "148418986", "16936893"),"白色,25码(内长16.4 脚长约15.3~15.9),6.12 24点前发")
        val sku5 = Sku("16936814", 182, mutableListOf("148388364", "148418987", "16936893"),"白色,26码(内长17 脚长约15.9~16.5),6.12 24点前发")
        val sku6 = Sku("16936815", 200, mutableListOf("148388364", "148418988", "16936893"),"白色,27码(内长17.6 脚长约16.5~17.1),6.12 24点前发")
        val sku7 = Sku("16936816", 200, mutableListOf("148388364", "148418989", "16936893"),"白色,28码(内长18.2 脚长约17.1~17.7),6.12 24点前发")
        val sku8 = Sku("16936817", 200, mutableListOf("148388364", "148418990", "16936893"),"白色,29码(内长18.8 脚长约17.7~18.3),6.12 24点前发")
        val sku9 = Sku("16936818", 200, mutableListOf("148388364", "148418991", "16936893"),"白色,30码(内长19.4 脚长约18.3~18.9),6.12 24点前发")
        val sku10 = Sku("16936819", 191, mutableListOf("148388364", "148418992", "16936893"),"白色,31码(内长20 脚长约18.9~19.5),6.12 24点前发")
        val sku11 = Sku("16936820", 200, mutableListOf("148388364", "148418993", "16936893"),"白色,32码(内长20.6 脚长约19.5~20.1),6.12 24点前发")
        val sku12 = Sku("16936821", 200, mutableListOf("148388364", "148418994", "16936893"),"白色,33码(内长21.2 脚长约20.1~20.7),6.12 24点前发")
        val sku13 = Sku("16936822", 124, mutableListOf("148388364", "148418995", "16936893"),"白色,34码(内长21.8脚长约20.7~21.3),6.12 24点前发")
        val sku14 = Sku("16936823", 144, mutableListOf("148388364", "148418996", "16936893"),"白色,35码(内长22.4 脚长约21.3~21.9),6.12 24点前发")
        val sku15 = Sku("16936824", 36, mutableListOf("148388378", "148418983", "16936893"),"黑白色,22码(内长14.6 脚长约13.5~14.1),6.12 24点前发")
        val sku16 = Sku("16936825", 56, mutableListOf("148388378", "148418984", "16936893"),"黑白色,23码(内长15.2 脚长约14.1~14.7),6.12 24点前发")
        val sku17 = Sku("16936826", 79, mutableListOf("148388378", "148418985", "16936893"),"黑白色,24码(内长15.8 脚长约14.7~15.3),6.12 24点前发")
        val sku18 = Sku("16936827", 61, mutableListOf("148388378", "148418986", "16936893"),"黑白色,25码(内长16.4 脚长约15.3~15.9),6.12 24点前发")
        val sku19 = Sku("16936828", 78, mutableListOf("148388378", "148418987", "16936893"),"黑白色,26码(内长17 脚长约15.9~16.5),6.12 24点前发")
        val sku20 = Sku("16936829", 62, mutableListOf("148388378", "148418988", "16936893"),"黑白色,27码(内长17.6 脚长约16.5~17.1),6.12 24点前发")

        skuList.add(sku1)
        skuList.add(sku2)
        skuList.add(sku3)
        skuList.add(sku4)
        skuList.add(sku5)
        skuList.add(sku6)
        skuList.add(sku7)
        skuList.add(sku8)
        skuList.add(sku9)
        skuList.add(sku10)
        skuList.add(sku11)
        skuList.add(sku12)
        skuList.add(sku13)
        skuList.add(sku14)
        skuList.add(sku15)
        skuList.add(sku16)
        skuList.add(sku17)
        skuList.add(sku18)
        skuList.add(sku19)
        skuList.add(sku20)

        // 转变为只有specsId的数组  传入算法使用
        val allSpecsIdList = specsGroupList.map {
            if (it.specsList.isNullOrEmpty()) mutableListOf<String>()
            else it.specsList.map { item -> item.specsId }
        }.toMutableList()
        // allSpecsIdList 是个2维数组
        // [[148388309, 148388336, 148388356, 148388364, 148388378, 148388406], [148418955, 148418928, 148418929, 148418930, 148418987, 148418988, 148418975, 148418990, 148418991, 148418992, 148418993, 148418994, 148418995, 148418996, 148535453, 148535454, 148535455, 148535456, 148535457], [16936802, 16936893, 17012073]]
        println("==${allSpecsIdList}")
        // 用reduce方法将2维数组转变为1维数组  也就是转换成邻接矩阵中的列一共多少 每一列是啥内容
        vertex = allSpecsIdList.reduce { total, next ->
            val newList = total.toMutableList().apply {
                addAll(next)
            }
            return@reduce newList
        }.toMutableList()
        println("vertex==${vertex}")


        quantity = vertex.size
        // 记录每个sku的库存条件
        val skuStockDict: MutableMap<String, Int?> = mutableMapOf()
        skuList.forEach { sku ->
            skuStockDict[sku.skuId] = sku.availableStock
            if(sku.specsList.isNotEmpty()){
                applyCommodity(sku.specsList, sku.skuId)
            }
        }
    }
    var vertex: MutableList<String?> = mutableListOf()
    // 矩阵长度  通过维度type
    private var quantity = 0

    /**
     * params 代表sku 所占的维度 eg: [148388309(白色), 148418955（27码）, 16936802]
     */
    private fun applyCommodity(params: List<String>, id: String) {
        // 根据sku 先利用值，找到对应的行，然后再在当前行行中，找到对应的列，赋值
        params.forEach {
            if (vertex.contains(it)) {
                this.setAdjoinVertex(it, params, id);
            }
        }
    }

    /**
     * @param  param
     * 传入的顶点id
     * @param  sides
     * 该顶点id 可达的数组，即包含该顶点Id的数组进来，将对应的矩阵位置赋值为1
     */
    private fun setAdjoinVertex(param: String, sides: List<String>, skuId: String) {
        // 通过type，在矩阵中  找到对应的行  竖直方向
        val rowIndex = vertex.indexOf(param)
        sides.forEach { item ->
            // 获取到item在该行列的位置  水平方向
            val columnIndex = vertex.indexOf(item)

            if(columnIndex > -1) {
                // rowIndex * quantity 决定了 决定了rowIndex是行的位置  所以我们可以找到其对应在一维数组中的位置
                // 然后找到其在列中的位置，就能确定其在矩阵的位置
                // 注意:每个矩阵点都是一个map，包含了每个sku在期位置上的matrix，以skuId作为依据，避免两个不相连的数据被打通
                adjoinArray[rowIndex * quantity + columnIndex][skuId] = 1
            }
        }
    }

    // 矩阵数组  邻接矩阵   邻接矩阵 是一个用来描绘顶点与边关系的数据结构本质是一个二位数组，只是将所有元素根据行列的关系，放到一个数组中，
    // 适合用来处理最小数据单元之间的关联关系
    // 邻接矩阵，无向图的特点:
    // 矩阵的length必然是顶点个数的平方，length*length
    // 矩阵斜边必然无值
    // 矩阵依据斜边对称
    private val adjoinArray: MutableList<MutableMap<String, Int>> =
        MutableList(size = quantity * quantity) { mutableMapOf() }
}
