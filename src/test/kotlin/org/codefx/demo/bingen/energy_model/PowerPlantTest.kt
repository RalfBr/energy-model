package org.codefx.demo.bingen.energy_model

import org.junit.Assert.assertEquals
import org.junit.Test

class PowerPlantTest {

    @Test
    fun ifEnergySufficientFulfillsOrder() {
        val plant = PowerPlant(100)
        val order = EnergyOrder(20)

        val produced = plant.fulfill(order)

        assertEquals(Energy(20), produced)
        assertEquals(Energy(80), plant.capacity)
    }

    @Test
    fun ifEnergyTooSmallProducesAvailableEnergy() {
        val plant = PowerPlant(100)
        val order = EnergyOrder(120)

        val produced = plant.fulfill(order)

        assertEquals(Energy(100), produced)
        assertEquals(Energy(0), plant.capacity)
    }

}
