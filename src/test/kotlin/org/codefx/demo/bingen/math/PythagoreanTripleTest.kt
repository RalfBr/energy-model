package org.codefx.demo.bingen.math

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class PythagoreanTripleTest {

    @Test
    fun isPythagoreanTriple1() {
        val isTriple = isPythagoreanTriple(3, 4, 5)
        assertTrue(isTriple)
    }

    @Test
    fun isPythagoreanTriple2() {
        val isTriple = isPythagoreanTriple(2, 2, 2)
        assertFalse(isTriple)
    }

    @Test
    fun isPythagoreanTriple3() {
        val isTriple = isPythagoreanTriple(-3, 4, 5)
        assertFalse(isTriple)
    }

    @Test
    fun isPythagoreanTriple4() {
        val isTriple = isPythagoreanTriple(140, 171, 221)
        assertTrue(isTriple)
    }

}
