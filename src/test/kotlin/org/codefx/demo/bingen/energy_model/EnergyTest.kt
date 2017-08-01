package org.codefx.demo.bingen.energy_model

import org.junit.Test

import org.junit.Assert.assertEquals

class EnergyTest {

    @Test
    fun ifEnergySufficesFulfillsOrdered() {
        val energy = Energy(100)
        val order = EnergyOrder(20)

        val produced = energy.produce(order)

        assertEquals(Energy(20), produced)
    }

    @Test
    fun ifEnergyTooSmallProducesAvailableEnergy() {
        val energy = Energy(100)
        val order = EnergyOrder(120)

        val produced = energy.produce(order)

        assertEquals(Energy(100), produced)
    }

    @Test
    fun reduceEnergyBySmallerOrderResultsInFullReduction() {
        val energy = Energy(100)
        val order = Energy(20)

        val reduced = energy.reduce(order)

        assertEquals(Energy(80), reduced)
    }

    @Test
    fun reduceEnergyByLargerOrderResultsInZero() {
        val energy = Energy(100)
        val order = Energy(200)

        val reduced = energy.reduce(order)

        assertEquals(Energy(0), reduced)
    }

}
