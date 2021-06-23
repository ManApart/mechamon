package data

import core.actions.SimpleAttack

val NOTHING = SimpleAttack("Nothing", 0, 0)

val actions = listOf(
    NOTHING,
    SimpleAttack("Jab", 1, 1),
    SimpleAttack("Uppercut", 3, 3)
).associateBy { it.name }