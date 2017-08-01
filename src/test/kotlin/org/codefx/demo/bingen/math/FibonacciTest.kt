package org.codefx.demo.bingen.math

import org.junit.Assert.assertEquals
import org.junit.Test

class FibonacciTest {

    @Test
    fun testFibNegative() {
        // SETUP (nothing to do here)

        // EXECUTE
        val f = fib(-5)

        // ASSERT
        assertEquals(0, f)
    }

    @Test
    fun testFib0() {
        // SETUP (nothing to do here)

        // EXECUTE
        val f = fib(0)

        // ASSERT
        assertEquals(0, f)
    }

    @Test
    fun testFib1() {
        // SETUP (nothing to do here)

        // EXECUTE
        val f = fib(1)

        // ASSERT
        assertEquals(1, f)
    }

    @Test
    fun testFib3() {
        // SETUP (nothing to do here)

        // EXECUTE
        val f = fib(3)

        // ASSERT
        assertEquals(2, f)
    }

    @Test
    fun testFib7() {
        // SETUP (nothing to do here)

        // EXECUTE
        val f = fib(7)

        // ASSERT
        assertEquals(13, f)
    }

}
