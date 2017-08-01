package org.codefx.demo.bingen.math

import org.junit.Assert.assertEquals
import org.junit.Test

class RomanNumeralsTest {

    /*
     * ALL OF THESE TESTS FAIL BECAUSE 'RomanNumerals' IS INCOMPLETE.
     * CHANGE IT SO THESE TESTS PASS; IT SUFFICES TO FO UP TO 50 / "L"
     *
     * FOR BACKGROUND ON ROMAN NUMERALS AND HOW TO CONVERT TO THEM:
     * http://turner.faculty.swau.edu/mathematics/materialslibrary/roman/
     *
     * To make your life easier by foregoing the compaction step,
     * include 40/XL, 9/IX, and 4/IV in the list of replacements.
     *
     * Remember that strings can be concatenated with a simple + operation.
     */

    @Test
    fun asRoman1() {
        val roman = asRoman(1)
        assertEquals("I", roman)
    }

    @Test
    fun asRoman3() {
        val roman = asRoman(3)
        assertEquals("III", roman)
    }

    @Test
    fun asRoman4() {
        val roman = asRoman(4)
        assertEquals("IV", roman)
    }

    @Test
    fun asRoman5() {
        val roman = asRoman(5)
        assertEquals("V", roman)
    }

    @Test
    fun asRoman7() {
        val roman = asRoman(7)
        assertEquals("VII", roman)
    }

    @Test
    fun asRoman9() {
        val roman = asRoman(9)
        assertEquals("IX", roman)
    }

    @Test
    fun asRoman10() {
        val roman = asRoman(10)
        assertEquals("X", roman)
    }

    @Test
    fun asRoman23() {
        val roman = asRoman(23)
        assertEquals("XXIII", roman)
    }

    @Test
    fun asRoman39() {
        val roman = asRoman(39)
        assertEquals("XXXIX", roman)
    }

    @Test
    fun asRoman42() {
        val roman = asRoman(42)
        assertEquals("XLII", roman)
    }

    @Test
    fun asRoman50() {
        val roman = asRoman(50)
        assertEquals("L", roman)
    }

}
