package org.codefx.demo.bingen.energy_model

import org.junit.Assert.assertTrue
import org.junit.Test

class HouseHoldTest {

    @Test
    fun houseHoldAnnouncesConsumptionGivenAtConstruction() {
        val houseHold = Household(10)

        val order = houseHold.announceConsumption()

        assertTrue(order.compareTo(EnergyOrder(10)) <= 0)
    }

}
