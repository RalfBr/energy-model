package org.codefx.demo.bingen.bank

/**
 * An account is an entity (i.e. regular class), which has both a balance and a limit.
 *
 * Note that [openingDeposit] and [limit] are arguments to the constructor
 * but not a fields (i.e. they are unavailable after an instance was constructed).
 * To keep them around, we declare two fields [balance] and [limit] that we assign
 * during construction.
 */
class Account(openingDeposit: Money = Money(0), limit: Balance = Balance(0)) {

    var balance = Balance(openingDeposit)
    var limit = limit

    fun deposit(amount: Money): Money {
        // deposit amount
        balance = balance.plus(amount)
        return amount
    }

    fun withdraw(amount: Money): Money {
        if (!balance.minus(amount).equalOrMoreThan(limit)) {
            // insufficient balance; pay out 0
            return Money(0, Currency.EURO)
        }

        // balance suffices, pay out the money
        balance = balance.minus(amount)
        return amount
    }

    fun withdrawRemaining(): Money {
        if (balance.isOverdrawn()) {
            // mo money left; pay out 0
            return Money(0, Currency.EURO)
        }

        // balance suffices, pay all out the money
        val remainingAmount = Money(balance)
        return withdraw(remainingAmount)
    }

    override fun toString(): String {
        return "Account(balance=$balance, limit=$limit)"
    }

}
