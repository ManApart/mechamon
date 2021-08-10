package core

import core.actions.Action
import data.arms
import data.cores
import data.heads
import ui.tiledScene.Direction

class Bot(
    var head: Head = heads["Standard"]!!,
    var core: Core = cores["Standard"]!!,
    var armRight: Arm = arms["Standard Right"]!!,
    var armLeft: Arm = arms["Standard Left"]!!
) {
    var mp = 0

    fun getPart(direction: Direction): Part {
        return when (direction) {
            Direction.UP -> head
            Direction.RIGHT -> armRight
            Direction.LEFT -> armLeft
            Direction.DOWN -> core
        }
    }

    fun takeAction(action: Action, battle: Battle): String {
        if (action.cost > head.ap){
            return "Not enough AP!"
        }

        println("Player does ${action.name}")
        head.ap -= action.cost

        if (!action.range.contains(battle.distance)){
            return "Player misses!"
        }

        val enemy = battle.getOpponent(this)
        

        return "Done!"
    }
}