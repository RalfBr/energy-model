package org.codefx.demo.bingen.zoo

interface Animal {

    fun name(): String

    fun move(): String

    fun noise(): String

}

class Cat(val name: String): Animal {

    override fun name(): String {
        return name
    }

    override fun move(): String {
        return "prowl.."
    }

    override fun noise(): String {
        return "meow"
    }

    fun lookCondescending(): String {
        return "you mortal fool *sqints*"
    }

}

class Fish(val name: String): Animal {

    override fun name(): String {
        return name
    }

    override fun move(): String {
        return "swim, swim"
    }

    override fun noise(): String {
        return ""
    }

}
