package kotlintest.jvm

/**
 * @author：李晓楠
 * 时间：2023/2/13 10:37
 */
//1.5.0之前，inline标记内联类
inline class Person(private val name: String = "")

//1.5.0之后，@JvmInline + value 标记内联类
//内联类构造参数中有且只能有一个成员变量，最终被内联到字节码中的value。，
// 上述代码经过内联优化会在字节码中将Person对象转换为String值，从而由堆分配优化为栈分配。
// 可以简单理解为 JVM中没有这个类的类型
@JvmInline
value class Person1(private val name: String = "")
