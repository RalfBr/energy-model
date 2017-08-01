package org.codefx.demo.bingen.energy_model

class EnergyExchange(val consumers: List<EnergyConsumer>, val producers: List<EnergyProducer>) {

    val production: MutableMap<EnergyProducer, Energy> = mutableMapOf()

    fun simulationStep() {
        for (consumer in consumers) {
            val order = consumer.announceConsumption()
            val produced = fulfillOrderWithSomeProducer(order)
            consumer.consume(produced)
        }
    }

    fun fulfillOrderWithSomeProducer(order: EnergyOrder): Energy {
        for (producer in producers) {
            if (producer.canFulfill(order)) {
                return fulfillOrderWithProducer(order, producer)
            }
        }
        return Energy(0)
    }

    fun fulfillOrderWithProducer(order: EnergyOrder, producer: EnergyProducer): Energy {
        val energy = producer.fulfill(order)
        if (production.containsKey(producer)) {
            // add energy to existing amount
            val producedEnergy: Energy = production.get(producer)!!
            val totalEnergy = producedEnergy.add(energy)
            production.put(producer, totalEnergy)
        } else {
            // put producer with energy
            production.put(producer, energy)
        }
        return energy
    }

}
