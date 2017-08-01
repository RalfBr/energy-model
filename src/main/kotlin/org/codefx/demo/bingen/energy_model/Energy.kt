package org.codefx.demo.bingen.energy_model

data class Energy(val amount: Int) : Comparable<Energy> {

    init {
        if (amount < 0) {
            println("!! AMOUNT NEGATIVE")
        }
    }

    override fun compareTo(other: Energy): Int {
        return this.amount - other.amount
    }

    fun compareTo(order: EnergyOrder): Int {
        return this.amount - order.amount
    }

    fun produce(order: EnergyOrder): Energy {
        val reduction = Math.min(this.amount, order.amount)
        return Energy(reduction)
    }

    fun reduce(energy: Energy): Energy {
        val reduction = Math.max(0, this.amount - energy.amount)
        return Energy(reduction)
    }

    fun add(energy: Energy): Energy {
        return Energy(this.amount + energy.amount)
    }

}

data class EnergyOrder(val amount: Int) : Comparable<EnergyOrder> {

    init {
        if (amount < 0) {
            println("!! AMOUNT NEGATIVE")
        }
    }

    override fun compareTo(other: EnergyOrder): Int {
        return this.amount - other.amount
    }

}
