package org.codefx.demo.bingen.bank

import com.univocity.parsers.common.record.Record
import com.univocity.parsers.csv.CsvParser
import com.univocity.parsers.csv.CsvParserSettings
import com.univocity.parsers.csv.CsvWriter
import com.univocity.parsers.csv.CsvWriterSettings
import org.codefx.demo.bingen.FileAccess

fun main(args: Array<String>) {
    val bank = readBankFromCsv("/accounts.csv")

    println(bank)

    val customer1 = bank.customers.get(0)
    val customer2 = bank.customers.get(1)

    val savings = bank.openAccount(customer1, limit = Balance(-5000))
    bank.transferBetweenAccounts(customer1.defaultAccount, savings, Money(1500))
    bank.transferBetweenAccounts(customer2.defaultAccount, savings, Money(1000))

    bank.newCustomer("Jane Doe", Money(100000), Balance(-50000))

    println(bank)
    println(savings)

    writeBankToCsv(bank, "editedAccounts.csv")
}

private fun readBankFromCsv(fileName: String): Bank {
    val settings = CsvParserSettings()
    settings.format.setLineSeparator("\n")
    settings.isHeaderExtractionEnabled = true

    val csvParser = CsvParser(settings)
    val reader = FileAccess().getReader(fileName)
    val customerRows: MutableList<Record> = csvParser.parseAllRecords(reader)

    val bank = Bank()

    for (record in customerRows) {
        val name = record.values.get(0)
        val balanceCents = recordValueToCents(record, 1)
        val limitCents = recordValueToCents(record, 2)

        bank.newCustomer(name, Money(balanceCents), Balance(limitCents))
    }

    return bank
}

private fun recordValueToCents(record: Record, index: Int): Int {
    // record -> extract string
    val string = record.getString(index)
    // string -> double
    val double = string.toDouble()
    // double -> value * 100
    val doubleCents = double * 100
    // double -> int
    val intCents = doubleCents.toInt()
    return intCents

    // less readable but fewer lines:
//    return (record.getString(index).toDouble() * 100).toInt()
}

private fun writeBankToCsv(bank: Bank, fileName: String) {
    val settings = CsvWriterSettings()
    settings.format.setLineSeparator("\n")

    val writer = FileAccess().getWriter(fileName)
    val csvWriter = CsvWriter(writer, settings)
    csvWriter.writeHeaders("Customer", "Balance", "Limit")

    val customerRows: MutableList<Array<Any>> = mutableListOf()
    for (customer in bank.customers) {
        val name = customer.name
        val balance: Balance = bank.balance(customer.defaultAccount)
        val balanceString = balanceToString(balance)
        val limit: Balance = bank.limit(customer.defaultAccount)
        val limitString = balanceToString(limit)
        val row: Array<Any> = arrayOf(name, balanceString, limitString)
        customerRows.add(row)
    }

    csvWriter.writeRowsAndClose(customerRows)
}

private fun balanceToString(balance: Balance): String {
    return centsToString(balance.centAmount)
}

private fun centsToString(cents: Int): String {
    val amount: Double = cents / 100.0
    val string = "%.2f".format(amount)
    return string
}
