package core.actions

import core.Battle
import core.Bot
import core.Part
import kotlin.math.max

class SimpleAttack(name: String, type: ActionResult, cost: Int, val damage: Int, range: IntRange = (1..1)) : Action(name, type, cost, range) {
    override fun use(parent: Bot, target: Part, battle: Battle) {
        val before = target.health
        target.health = max(0, target.health - damage)
        println("${target.name}: $before -> ${target.health}")
    }
}