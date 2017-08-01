package org.codefx.demo.bingen.energy_model

class PowerPlant(maxProduction: Int): EnergyProducer {

    var capacity = Energy(maxProduction)

    init {
        if (maxProduction < 0) {
            println("!! MAX PRODUCTION NEGATIVE")
        }
    }

    override fun canFulfill(order: EnergyOrder): Boolean {
        return capacity.compareTo(order) >= 0
    }

    override fun fulfill(order: EnergyOrder): Energy {
        val produced = capacity.produce(order)
        capacity = capacity.reduce(produced)
        return produced
    }

}
