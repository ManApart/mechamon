package core

class Bot(val ai: AI, var head: Head, var core: Core, var armRight: Arm, var armLeft: Arm) {

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