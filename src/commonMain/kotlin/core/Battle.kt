package core

import kotlin.math.abs

class Battle(val botA: Bot, val botB: Bot, val terrain: Terrain) {
    var distance = 10

    fun getOpponent(bot: Bot): Bot {
        return if (bot == botA) botB else botA
    }

    fun getWinner(): Bot? {
        return when {
            botA.head.health == 0 -> botB
            botB.head.health == 0 -> botA
            else -> null
        }
    }

    fun tick() {
        botA.mp = botA.core.getMovement(terrain) / 10
        botB.mp = botB.core.getMovement(terrain) / 10
    }

    fun move(bot: Bot, amount: Int) {
        if (true){
//        if (bot.mp > 0){
            if (distance + amount in 1..10){
                bot.mp -= abs(amount)
                distance += amount
            } else {
                println("Can't get move more in that direction!")
            }
        } else {
            println("Bot has no MP!")
        }
    }
}