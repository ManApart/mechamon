package core

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

}