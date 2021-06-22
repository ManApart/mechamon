package core.actions

import core.Battle
import core.Bot
import core.Part

abstract class Action(val name: String, val cost: Int) {

    open fun use(parent: Bot, target: Part, context: Battle) {

    }

    open fun use(parent: Bot){

    }
}