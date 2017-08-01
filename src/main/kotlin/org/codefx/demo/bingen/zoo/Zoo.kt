package org.codefx.demo.bingen.zoo

fun main(args: Array<String>) {
    val cats = listOf(Cat("Luna"), Cat("Mo"), Cat("Felix"))
    val fishes = listOf(Fish("#1"), Fish("#2"), Fish("#3"))

    val animals: MutableList<Animal> = mutableListOf()
    animals.addAll(cats)
    animals.addAll(fishes)

    animals.addAll(fishes)
    for (animal in animals) {
        println("${animal.name()} says '${animal.noise()}' and moves ${animal.move()}.")
    }

    println("\n------------------\n")

    val visitors: List<Visitor> = listOf(Parent(), Parent(), Kid(), Parent(), Kid(), Kid())
    for (visitor in visitors) {
        visitor.watchAnimal(cats.get(0))
        visitor.petAnimal(cats.get(0))
    }

}
