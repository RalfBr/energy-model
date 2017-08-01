package org.codefx.demo.bingen.math

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class TriangularNumberTest {

    @Test
    fun isTriangularNumber10() {
        val isTriangular = isTriangularNumber(10)
        assertTrue(isTriangular)
    }

    @Test
    fun isTriangularNumberMinus10() {
        val isTriangular = isTriangularNumber(-10)
        assertFalse(isTriangular)
    }

    @Test
    fun isTriangularNumber20() {
        val isTriangular = isTriangularNumber(20)
        assertFalse(isTriangular)
    }

    @Test
    fun isTriangularNumber1225() {
        val isTriangular = isTriangularNumber(1225)
        assertTrue(isTriangular)
    }


}
