package kotlintest.outer

/**
 * @author：李晓楠
 * 时间：2022/8/31 08:51
 */
class Product<out T> {
    constructor()

    fun produce(): T? {
        return null
    }
}