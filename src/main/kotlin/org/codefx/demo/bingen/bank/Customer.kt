package org.codefx.demo.bingen.bank

class Customer(val name: String, var defaultAccount: AccountNumber) {

    val accounts: MutableList<AccountNumber> = mutableListOf()

    init {
        accounts.add(defaultAccount)
    }

    override fun toString(): String {
        return "Customer('$name', defaultAccount=$defaultAccount)"
    }

}
