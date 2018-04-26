package cn.tianyu.weatherapp.reifiedtest

import org.junit.Test

class TypeClass<out T>(parameter: T) {
    val value: T = parameter

    fun doSomething(): T {
        return value
    }
}

class ReifiedInlineTest {
    @Test
    fun test1() {
        val t1 = TypeClass<String>("Hello world!")
        val t3: TypeClass<Any> = t1
        val t2 = TypeClass(25)
//    val t3 = TypeClass<String?>(null)
        run loop@{
            (1..5).forEach {
                if (it == 3) return@loop
                println(it)
            }
        }
        val oddLength = compose(::isOdd, ::length)
        val strings = listOf("a", "ab", "abc")
        println(strings.filter(oddLength))
    }
}

fun <T> typedFunction(item: T): List<T> = listOf(item)

fun isOdd(x: Int) = x % 2 != 0

fun length(s: String) = s.length

fun <A, B, C> compose(f: (B) -> C, g: (A) -> B): (A) -> C {
    return { x -> f(g(x)) }
}
