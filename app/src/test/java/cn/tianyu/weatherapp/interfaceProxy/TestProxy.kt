package cn.tianyu.weatherapp.interfaceProxy

interface CanFly {

    fun fly()
}

class Wings{
    fun move() = println("I'm flying")
}

class AnimalWithWings: CanFly{

    val wings: Wings = Wings()

    override fun fly() = wings.move()

}

class Bird: CanFly by AnimalWithWings()

fun main(args: Array<String>) {
    val bird = Bird()
    bird.fly()
}