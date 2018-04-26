package cn.tianyu.weatherapp

import cn.tianyu.weatherapp.interfaceProxy.Bird
import com.google.gson.annotations.SerializedName
import org.junit.Assert.assertEquals
import org.junit.Test
import java.lang.reflect.Field

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun birdCanFly() {
        Bird().fly()
    }

    @Test
    fun getAnnotationValue() {
        val personClz: Class<Person> = Person::class.java
        val field: Field = personClz.getDeclaredField("name")
        field.isAccessible = true
        if (field != null) {
            if (field.isAnnotationPresent(FieldName::class.java)) {
                println(field.getDeclaredAnnotation(FieldName::class.java).name)
            }
            if (field.isAnnotationPresent(SerializedName::class.java)) {
                println(field.getDeclaredAnnotation(SerializedName::class.java).value)
            }
        }

        val nerdClz = Nerd::class.java
        val field2: Field = nerdClz.getDeclaredField("name")
        field.isAccessible = true
        if (field != null) {
            if (field2.isAnnotationPresent(SerializedName::class.java)) {
                println(field.getDeclaredAnnotation(SerializedName::class.java).value)
            }
        }
    }

    @Test
    fun testActivity(){

    }
}

@Target(AnnotationTarget.FIELD)
annotation class FieldName(val name: String)


class Person {

    @SerializedName("person_name")
    @FieldName("hahahhahaha")
    internal var name: String = "name"
        get() = field

}

data class Nerd(@SerializedName("nerd_name") val name:String)