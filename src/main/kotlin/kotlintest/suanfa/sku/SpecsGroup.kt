package kotlintest.suanfa.sku

/**
 * @author：李晓楠
 * 时间：2023/6/6 10:27
 */
data class SpecsGroup(
   val specsGroupId: String?,
   val specsGroupTitle: String?,
   // 当前维度的展示类型：1--上图下字，2--左图右字，3--无图有字
   val specsGroupType: Int,
   val specsList:List<Specs>,
   val defaultChoose:Int = -1,
)

/**
 * 每一个维度下面的specs   eg:红
 */
data class Specs(
   val specsId: String?,
   val specsName: String?,
   val checkEnable:Boolean?,  // 客户端自定义字段 是否可以点击
    val soldOut:Boolean?,     // 客户端自定义字段 是否已经售罄
    val selected:Boolean?,    // 客户端自定义字段 是否选中
)

data class Sku(
   val skuId: String,
   val availableStock:Int?,
   val specsList:List<String>,
   val describe:String,
)