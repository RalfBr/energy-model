package org.codefx.demo.bingen.math

fun fib(n: Int): Int {
    // return 0 for negative n's
    if (n <= 0)
        return 0
    if (n == 1)
        return 1

    var a = 0
    var b = 1
    for (i in 2..n) {
        val new = a + b
        a = b
        b = new
    }

    return b
}
