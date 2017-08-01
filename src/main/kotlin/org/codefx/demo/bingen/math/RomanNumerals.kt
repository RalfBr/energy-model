package org.codefx.demo.bingen.math

fun asRoman(n: Int): String {
    if (n < 1 || 50 < n) {
        return "Invalid value $n for roman numerals."
    }

    var decimalRemainder = n
    var romanOutput = ""

    while (decimalRemainder >= 1) {
        if (decimalRemainder == 50) {
            romanOutput += "L"
            decimalRemainder -= 50
        } else if (decimalRemainder >= 40) {
            romanOutput += "XL"
            decimalRemainder -= 40
        } else if (decimalRemainder >= 10) {
            romanOutput += "X"
            decimalRemainder -= 10
        } else if (decimalRemainder >= 9) {
            romanOutput += "IX"
            decimalRemainder -= 9
        } else if (decimalRemainder >= 5) {
            romanOutput += "V"
            decimalRemainder -= 5
        } else if (decimalRemainder >= 4) {
            romanOutput += "IV"
            decimalRemainder -= 4
        } else if (decimalRemainder >= 1) {
            romanOutput += "I"
            decimalRemainder -= 1
        }
    }
    return romanOutput
}
