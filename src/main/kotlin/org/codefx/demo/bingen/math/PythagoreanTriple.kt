package org.codefx.demo.bingen.math

fun isPythagoreanTriple (n_1 : Int , n_2 : Int, n_3: Int) : Boolean {
    // just positive numbers are possible
    if (n_1 < 0 || n_2 < 0 || n_3 < 0) {
        return false
    }

    // Three numbers are Pythagorean Triples if a combination of the numbers n_1 , n_2 , n_3
    // is able to come up to the following equation (1): a^2 + b^2 = c^2
    // The biggest number needs to be the c in the equation (1)
    val sortedNs = listOf(n_1, n_2, n_3).sorted()

    val a = sortedNs[0]
    val b = sortedNs[1]
    val c = sortedNs[2]

    // Now testing the equation (1)
    return a*a + b*b == c*c
}
