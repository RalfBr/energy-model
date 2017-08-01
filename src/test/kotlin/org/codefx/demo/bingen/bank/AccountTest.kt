package org.codefx.demo.bingen.bank

import org.junit.Assert.assertEquals
import org.junit.Test

class AccountTest {

    @Test
    fun newAccountHasSpecifiedBalance() {
        // SETUP
        val account = Account(openingDeposit = Money(100))

        // EXECUTE
        val balance = account.balance

        // ASSERT
        assertEquals(Money(100), balance)
    }

    @Test
    fun depositIncreasesBalance() {
        // SETUP
        val account = Account()

        // EXECUTE
        account.deposit(Money(50))

        // ASSERT
        assertEquals(Money(50), account.balance)
    }

    @Test
    fun withdrawalDecreasesBalance() {
        // SETUP
        val account = Account(openingDeposit = Money(100))

        // EXECUTE
        val received = account.withdraw(Money(60))

        // ASSERT
        assertEquals(Money(60), received)
        assertEquals(Money(40), account.balance)
    }

    /*
     * Thanks to making sure there is no negative money,
     * the following tests became superfluous.
     */

//    @Test
//    fun negativeDepositDoesNotChangeBalance() {
//        // SETUP
//        val account = Account(openingDeposit = Money(100))
//
//        // EXECUTE
//        val deposited = account.deposit(Money(-50))
//
//        // ASSERT
//        assertEquals(0, deposited)
//        assertEquals(100, account.balance)
//    }
//
//    @Test
//    fun negativeWithdrawalDoesNotChangeBalance() {
//        // SETUP
//        val account = Account(openingDeposit = Money(100))
//
//        // EXECUTE
//        val received = account.withdraw(Money(-50))
//
//        // ASSERT
//        assertEquals(0, received)
//        assertEquals(100, account.balance)
//    }

    @Test
    fun accountCanBeOverdrawnToLimit() {
        // SETUP
        val account = Account(openingDeposit = Money(100), limit = Balance(-100))

        // EXECUTE
        val received = account.withdraw(Money(150))

        // ASSERT
        assertEquals(150, received)
        assertEquals(-50, account.balance)
    }

}
