package org.codefx.demo.bingen.zoo

interface Visitor {

    fun petAnimal(animal: Animal)

    fun watchAnimal(animal: Animal)

}

class Kid: Visitor {

    override fun petAnimal(animal: Animal) {
        println("Kid pets ${animal.name()}, which ${animal.move()} away.")
    }

    override fun watchAnimal(animal: Animal) {
        println("Too impatient!")
    }

}

class Parent: Visitor {

    override fun petAnimal(animal: Animal) {
        println("Too \"cool\".")
    }

    override fun watchAnimal(animal: Animal) {
        println("Parent watches ${animal.name()}, which makes ${animal.noise()}")
    }

}
