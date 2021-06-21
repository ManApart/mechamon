package actions

import Battle
import Bot
import Part

abstract class Action(val name: String, val cost: Int) {

    open fun use(parent: Bot, target: Part, context: Battle) {

    }

    open fun use(parent: Bot){

    }
}