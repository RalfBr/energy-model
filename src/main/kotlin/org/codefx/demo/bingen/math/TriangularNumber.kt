package org.codefx.demo.bingen.math

fun isTriangularNumber(n: Int): Boolean {
    // just positive numbers are possible
    if (n < 0) {
        return false
    }

    // A given number is a Triangular Number if the number can be formed out of the sum
    // of the infinite row 1 ; 2 ; 3 ; 4 ; 5 ; 6 ; ...
    // Example
    // The number 10 is a Triangular Number as 10 equals 1+2+3+4
    // The number 12 is not a Triangular Number as 12 is between 1+2+3+4 and 1+2+3+4+5

    // Our approach is to sum up the numbers of the theoretically infinite row until the
    // calculated sum equals the given number or the calculated sum is greater than
    // the given number (as the number is not a Triangular Number in this case).

    var calculatedSum: Int = 0
    var iteratedNumber: Int = 0

    while (calculatedSum < n) {
        calculatedSum += iteratedNumber
        iteratedNumber += 1
    }

    // now the calculated sum must either equal `n` or be larger;
    // in the first case `n` is triangular, otherwise it is not
    return calculatedSum == n
}
