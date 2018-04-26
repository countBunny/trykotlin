package cn.tianyu.weatherapp

import cn.tianyu.weatherapp.utils.toDateString
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.text.DateFormat


class SimpleTest {

    @Test fun unitTestingWorks(){
        assertTrue(true)
    }

    @Test fun testLongToDateString(){
        assertEquals("2015-10-20", 1445275635000L.toDateString())
    }

    @Test fun testDateStringFullFormat(){
        assertEquals("2015年10月20日 星期二",1445275635000L.toDateString(DateFormat.FULL))
    }
}