package core

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
        val choices = mutableListOf<ActionChoice>()
        val ap = head.totalAP

        choices.addAll(head.actions.map { ActionChoice(it, ap) })
        if (core.health > 0) {
            choices.addAll(core.actions.map { ActionChoice(it, ap) })
        }
        if (armLeft.health > 0) {
            choices.addAll(armLeft.actions.map { ActionChoice(it, ap) })
        }
        if (armRight.health > 0) {
            choices.addAll(armRight.actions.map { ActionChoice(it, ap) })
        }

        return choices
    }

}