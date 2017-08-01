package org.codefx.demo.bingen.energy_model

import com.univocity.parsers.common.record.Record
import com.univocity.parsers.csv.CsvParser
import com.univocity.parsers.csv.CsvParserSettings
import com.univocity.parsers.csv.CsvWriter
import com.univocity.parsers.csv.CsvWriterSettings
import org.codefx.demo.bingen.FileAccess

fun main(args: Array<String>) {
    val car = ElectricCar(20, 10)

    val households = readHouseholdsFromCsv("/households.csv")
    val consumers: MutableList<EnergyConsumer> = mutableListOf(car)
    consumers.addAll(households)

    val plants = listOf(PowerPlant(40), PowerPlant(20))
    val producers: MutableList<EnergyProducer> = mutableListOf(car)
    producers.addAll(plants)

    val exchange = EnergyExchange(consumers, producers)

    exchange.simulationStep()

    writeProductionToCsv(exchange.production, "production.csv")
}

private fun readHouseholdsFromCsv(fileName: String): List<Household> {
    val settings = CsvParserSettings()
    settings.format.setLineSeparator("\n")
    settings.isHeaderExtractionEnabled = true

    val csvParser = CsvParser(settings)
    val reader = FileAccess().getReader(fileName)
    val customerRows: MutableList<Record> = csvParser.parseAllRecords(reader)

    val households: MutableList<Household> = mutableListOf()

    for (record in customerRows) {
        val consumption = record.getInt(1)
        households.add(Household(consumption))
    }

    return households
}

private fun writeProductionToCsv(production: Map<EnergyProducer, Energy>, fileName: String) {
    val settings = CsvWriterSettings()
    settings.format.setLineSeparator("\n")

    val writer = FileAccess().getWriter(fileName)
    val csvWriter = CsvWriter(writer, settings)
    csvWriter.writeHeaders("PowerPlant", "Production")

    val rows: MutableList<Array<Any>> = mutableListOf()
    var plantIndex = 0

    for (producerAndProduction in production) {
        plantIndex += 1
        val production = producerAndProduction.value
        val row: Array<Any> = arrayOf("#$plantIndex", production)
        rows.add(row)
    }

    csvWriter.writeRowsAndClose(rows)
}
