package core.actions

import core.Battle
import core.Bot
import core.Part
import kotlin.math.max

class SimpleAttack(name: String, cost: Int, val damage: Int) : Action(name, cost) {
    override fun use(parent: Bot, target: Part, battle: Battle) {
        val before = target.health
        target.health = max(0, target.health - damage)
        println("${target.name}: $before -> ${target.health}")
    }
}