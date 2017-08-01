package org.codefx.demo.bingen.bank

/**
 * Money is a value (i.e. a data class). The amount can never be negative.
 *
 * Note that the currency has a default value[1], meaning it does not have to be specified
 *
 * [1]: https://kotlinlang.org/docs/reference/functions.html#default-arguments
 */
data class Money(val centAmount: Int, val currency: Currency = Currency.EURO): Comparable<Money> {

    init {
        if (centAmount < 0) {
            println("!! AMOUNT NEGATIVE")
        }
    }

    constructor(balance: Balance) : this(balance.centAmount, balance.currency)

    override fun compareTo(other: Money): Int {
        return this.centAmount - other.centAmount
    }

}

/**
 * Balance is a value (i.e. a data class). The amount can be negative.
 */
data class Balance(val centAmount: Int, val currency: Currency = Currency.EURO): Comparable<Balance> {

    constructor(money: Money) : this(money.centAmount, money.currency)

    fun plus(other: Money): Balance {
        val newAmount = this.centAmount + other.centAmount
        return Balance(newAmount, currency)
    }

    fun minus(other: Money): Balance {
        val newAmount = this.centAmount - other.centAmount
        return Balance(newAmount, currency)
    }

    fun equalOrMoreThan(other: Balance): Boolean {
        return this.centAmount >= other.centAmount
    }

    fun isOverdrawn(): Boolean {
        return this.centAmount < 0
    }

    override fun toString(): String {
        return "$centAmount ${currency}Ct)"
    }

    override fun compareTo(other: Balance): Int {
        return this.centAmount - other.centAmount
    }

}

enum class Currency {
    EURO
}
