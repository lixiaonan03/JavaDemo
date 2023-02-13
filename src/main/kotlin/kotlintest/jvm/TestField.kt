package kotlintest.jvm

/**
 * @JvmField使得编译器不再对该字段生成getter/setter并将其作为公开字段
 * @author：李晓楠
 * 时间：2023/2/10 17:10
 */
class TestField {
    var id1:Int? = null
    @JvmField  //这个字段Java 调用的时候没有set get 方法的
    var id2 = 200 //2
}