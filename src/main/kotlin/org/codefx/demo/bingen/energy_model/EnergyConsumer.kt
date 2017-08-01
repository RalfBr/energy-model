package org.codefx.demo.bingen.energy_model

interface EnergyConsumer {

    fun announceConsumption(): EnergyOrder

    fun consume(energy: Energy)

}
