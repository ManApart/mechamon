package core

import data.NOTHING
import data.arms
import data.cores
import data.heads

class Bot(
    var head: Head = heads["Standard"]!!,
    var core: Core = cores["Standard"]!!,
    var armRight: Arm = arms["StandardRight"]!!,
    var armLeft: Arm = arms["StandardLeft"]!!
) {

    fun getActions(): List<ActionChoice> {
        val ap = head.totalAP

        return listOf(head, core, armLeft, armRight)
            .filter { it.health > 0 }
            .filter { it.action != NOTHING }
            .map { ActionChoice(it.action, ap) }
            .toMutableList()
            .also { it.add(ActionChoice(NOTHING, true)) }
    }

}