package org.codefx.demo.bingen.bank

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class BankTest {

    val bank = Bank()

    // OPENING / CLOSING

    @Test
    fun newCustomerMustHaveAccount() {
        val john = bank.newCustomer("John Doe")
        assertFalse(john.accounts.isEmpty())
    }

    @Test
    fun newAccountMustBelongToCustomer() {
        val john = bank.newCustomer("John Doe")
        val account = bank.openAccount(john)

        assertTrue(john.accounts.contains(account))
    }

    @Test
    fun newAccountMustContainOpeningDeposit() {
        val john = bank.newCustomer("John Doe")
        val account = bank.openAccount(john, openingDeposit = Money(100))

        assertEquals(Balance(100), bank.balance(account))
    }

    @Test
    fun closingAccountPaysOutAllMoney() {
        val john = bank.newCustomer("John Doe")
        val account = bank.openAccount(john, openingDeposit = Money(150))

        assertEquals(Money(150), bank.closeAccount(john, account))
    }

    @Test
    fun closingOverdrawnAccountPaysOutNoMoney() {
        val john = bank.newCustomer("John Doe")
        val account = bank.openAccount(john, openingDeposit = Money(100), limit = Balance(-100))
        bank.withdraw(account, Money(150))

        val payout = bank.closeAccount(john, account)
        assertEquals(Money(0), payout)
    }

    // TRANSFERRING

    @Test
    fun depositingMoneyIncreasesBalance() {
        val john = bank.newCustomer("John Doe")

        bank.deposit(john.defaultAccount, Money(100))

        val newBalance = bank.balance(john.defaultAccount)
        assertEquals(Balance(100), newBalance)
    }

    @Test
    fun depositingMoneyTwiceIncreasesBalance() {
        val john = bank.newCustomer("John Doe")

        bank.deposit(john.defaultAccount, Money(100))
        bank.deposit(john.defaultAccount, Money(200))

        val newBalance = bank.balance(john.defaultAccount)
        assertEquals(Balance(300), newBalance)
    }

    @Test
    fun withdrawingMoneyDecreasesBalance() {
        val john = bank.newCustomer("John Doe")
        bank.deposit(john.defaultAccount, Money(100))

        bank.withdraw(john.defaultAccount, Money(80))

        val newBalance = bank.balance(john.defaultAccount)
        assertEquals(Balance(20), newBalance)
    }

    @Test
    fun overdrawingAccountLeadsToNegativeBalance() {
        val john = bank.newCustomer("John Doe")
        val account = bank.openAccount(john, openingDeposit = Money(100), limit = Balance(-100))

        val withdrawn = bank.withdraw(account, Money(150))

        assertEquals(Money(150), withdrawn)
        val newBalance = bank.balance(account)
        assertEquals(Balance(-50), newBalance)
    }

    @Test
    fun transferringBetweenAccountsChangesBalances() {
        val john = bank.newCustomer("John Doe")
        bank.deposit(john.defaultAccount, Money(200))
        val jane = bank.newCustomer("Jane Doe")
        bank.deposit(jane.defaultAccount, Money(500))

        bank.transferBetweenAccounts(jane.defaultAccount, john.defaultAccount, Money(100))

        assertEquals(Balance(300), bank.balance(john.defaultAccount))
        assertEquals(Balance(400), bank.balance(jane.defaultAccount))
    }

    @Test
    fun transferringBetweenCustomerChangesBalances() {
        val john = bank.newCustomer("John Doe")
        bank.deposit(john.defaultAccount, Money(200))
        val jane = bank.newCustomer("Jane Doe")
        bank.deposit(jane.defaultAccount, Money(500))

        bank.transferBetweenCustomers(jane, john, Money(100))

        assertEquals(Balance(300), bank.balance(john.defaultAccount))
        assertEquals(Balance(400), bank.balance(jane.defaultAccount))
    }

}
