package data

import actions.SimpleAttack

val actions = listOf(
    SimpleAttack("Jab", 1, 1),
    SimpleAttack("Uppercut", 3, 3)
).associateBy { it.name }