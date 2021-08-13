package data

import core.actions.ActionResult
import core.actions.SimpleAttack

val NOTHING = SimpleAttack("Nothing", ActionResult.MISS, 0, 0)

val actions = listOf(
    NOTHING,
    SimpleAttack("Jab", ActionResult.DAMAGE, 1, 1),
    SimpleAttack("Uppercut", ActionResult.DAMAGE, 3, 3)
).associateBy { it.name }