package com.testing.myapplication

import junit.framework.TestCase
import org.junit.Test

class ApiTestingTest : TestCase(){

    private val testSample: ApiTesting = ApiTesting()
    @Test
    fun testSum() {
        val expected = "Successful"
        assertEquals(expected, testSample.test())
  }
}