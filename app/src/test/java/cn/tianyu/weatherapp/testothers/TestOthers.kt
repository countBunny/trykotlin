package cn.tianyu.weatherapp.testothers

import cn.tianyu.weatherapp.R
import org.junit.Assert.assertEquals
import org.junit.Test


class TestThemTest {

    @Test
    fun innerClassTest() {
        val demo = Outer.Nested().foo()
        val demo2 = Outer().Inner().foo()
        System.out.println("demo = " + demo + ", demo2 = " + demo2)

    }

    @Test
    fun enumClassTest() {
        val searchIconRes = Icon.SEARCH.res
        assertEquals(R.mipmap.we1, searchIconRes)
        val values = Icon.values()
        val search = Icon.valueOf("SEARCH")
        val name = Icon.SEARCH.name
        val ordinal = Icon.SEARCH.ordinal
        values.sort()
    }

    @Test
    fun sealedTest() {
        val option: Option<Int> = Option.Some()
        val result = when (option) {
            is Option<out Any> -> "Contains a value"
            is Option.None -> "Empty"
        }
        System.out.println(result)
    }

    @Test
    fun throwExceptionTest() {
        try {
            throwException()
        } catch (e: MyException) {
            System.out.println("My Exception has been caught")
        } finally {
            // optional
        }
        boundDetect(2.3f)
    }

    private fun boundDetect(x: Any?) = when (x) {
        is Int -> "int instance"
        is String -> "String instance"
        else -> throw UnsupportedOperationException("not valid type")
    }

    private fun castToString(x :Any?) = try {
        x as String
    }catch (ex: ClassCastException){
        null
    }

    private fun throwException(){
        throw MyException("Exception message")
    }
}

class Outer {
    private val bar: Int = 1

    class Nested {
        fun foo() = 2
    }

    inner class Inner {
        fun foo() = bar
    }
}

enum class Day {
    SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY
}

enum class Icon(val res: Int) {
    UP(R.mipmap.we0),
    SEARCH(R.mipmap.we1),
    CAST(R.mipmap.we2)
}

sealed class Option<out T> {

    class Some<out T> : Option<T>()

    object None : Option<Nothing>()
}

class MyException(val msg: String) : Exception(msg)