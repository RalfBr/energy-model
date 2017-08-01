package org.codefx.demo.bingen.energy_model

interface EnergyProducer {

    fun canFulfill(order: EnergyOrder): Boolean

    fun fulfill(order: EnergyOrder): Energy

}
