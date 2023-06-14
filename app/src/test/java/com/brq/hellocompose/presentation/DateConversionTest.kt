package com.brq.hellocompose.presentation

import com.brq.hellocompose.features.home.data.remote.model.toDateFormat
import org.junit.Assert
import org.junit.Test

class DateConversionTest {

    @Test
    fun `WHEN passing response date MUST return Correct Date mask`(){
        val origin = "2013-04-12"
        val result = origin.toDateFormat()
        val expected = "12-04-2013"
        Assert.assertEquals(expected, result)
    }

}