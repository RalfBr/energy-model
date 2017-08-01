package org.codefx.demo.bingen.bank

import org.codefx.demo.bingen.NullSafeComparator

class Bank {

    val customers: MutableList<Customer> = mutableListOf()
    val accounts: MutableMap<AccountNumber, Account> = mutableMapOf()

    var nextAccountNumber = 1

    fun createAccountNumber(): AccountNumber {
        val accountNumber = AccountNumber("$nextAccountNumber")
        nextAccountNumber += 1
        return accountNumber
    }

    fun createAccountAndNumber(openingDeposit: Money = Money(0), limit: Balance = Balance(0)): AccountNumber {
        val newNumber = createAccountNumber()
        val newAccount = Account(openingDeposit, limit)
        accounts.put(newNumber, newAccount)
        return newNumber
    }

    fun getAccountForNumber(number: AccountNumber): Account {
        if (!accounts.containsKey(number)) {
            println("!! ACCOUNT NUMBER UNKNOWN TO THIS BANK")
            throw IllegalArgumentException()
        }

        return accounts.get(number)!!
    }

    fun openAccount(customer: Customer, openingDeposit: Money = Money(0), limit: Balance = Balance(0)): AccountNumber {
        if (!customers.contains(customer)) {
            println("!! CUSTOMER DOES NOT BELONG TO THIS BANK")
        }


        val newNumber = createAccountAndNumber(openingDeposit, limit)
        customer.accounts.add(newNumber)
        return newNumber
    }

    fun closeAccount(customer: Customer, number: AccountNumber): Money {
        if (!customers.contains(customer)) {
            println("!! CUSTOMER DOES NOT BELONG TO THIS BANK")
            return Money(0)
        }

        val account = getAccountForNumber(number)
        if (account.balance.isOverdrawn()) {
            println("!! OVERDRAWN ACCOUNT CAN NOT BE CLOSED")
            return Money(0)
        }

        customer.accounts.remove(number)

        if (customer.accounts.isEmpty()) {
            customers.remove(customer)
        }

        return account.withdrawRemaining()
    }

    fun newCustomer(name: String, openingDeposit: Money = Money(0), limit: Balance = Balance(0)): Customer {
        val newNumber = createAccountAndNumber(openingDeposit, limit)
        val newCustomer = Customer(name, newNumber)
        customers.add(newCustomer)
        return newCustomer
    }

    fun limit(number: AccountNumber): Balance {
        val account = getAccountForNumber(number)
        return account.limit
    }

    fun balance(number: AccountNumber): Balance {
        val account = getAccountForNumber(number)
        return account.balance
    }

    fun deposit(number: AccountNumber, amount: Money): Money {
        val account = getAccountForNumber(number)
        return account.deposit(amount)
    }

    fun withdraw(number: AccountNumber, amount: Money): Money {
        val account = getAccountForNumber(number)
        return account.withdraw(amount)
    }

    fun transferBetweenAccounts(fromNumber: AccountNumber, toNumber: AccountNumber, amount: Money): Money {
        val from = getAccountForNumber(fromNumber)
        val to = getAccountForNumber(toNumber)
        val withdrawnAmount = from.withdraw(amount)
        to.deposit(withdrawnAmount)
        // TODO: reporting
        return withdrawnAmount
    }

    fun transferBetweenCustomers(from: Customer, to: Customer, amount: Money): Money {
        val fromAccount = from.defaultAccount
        val toAccount = to.defaultAccount
        return transferBetweenAccounts(fromAccount, toAccount, amount)
    }

    override fun toString(): String {
        var string = "Bank:"
        // note that customers.sortWith sorts the customers list
        // whereas customers.sortedWith returns a new list that is sorted,
        // i.e. customers remains unchanged
        for (customer in customers.sortedWith(CustomerByNameComparator())) {
            string += "\n\t$customer"
        }
        return string
    }

}

class CustomerByNameComparator: NullSafeComparator<Customer> {

    override fun compareNonNull(o1: Customer, o2: Customer): Int {
        // to compare customers by name we can fall back to String's compareTo method
        return o1.name.compareTo(o2.name)
    }

}
