package data

import core.Arm

val arms = listOf(
    Arm("StandardRight", "Lightweight and balanced. Delivers a quick jab.", 3, true, actions["Jab"]!!),
    Arm("StandardLeft", "Slightly bulkier than its right arm companion, this delivers a strong uppercut.", 3, false, actions["Uppercut"]!!)
).associateBy { it.name }