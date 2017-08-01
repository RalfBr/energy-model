package org.codefx.demo.bingen.energy_model

class ElectricCar(val consumption: Int, initialStorage: Int): EnergyConsumer, EnergyProducer {

    var storage = Energy(initialStorage)

    override fun announceConsumption(): EnergyOrder {
        return EnergyOrder(consumption)
    }

    override fun consume(energy: Energy) {
        println("Drive...")
    }

    override fun canFulfill(order: EnergyOrder): Boolean {
        return storage.compareTo(order) >= 0
    }

    override fun fulfill(order: EnergyOrder): Energy {
        val produced = storage.produce(order)
        storage = storage.reduce(produced)
        return produced
    }

}
