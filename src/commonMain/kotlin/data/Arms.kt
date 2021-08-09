package data

import core.Arm

val arms = listOf(
    Arm("Standard Right", "Lightweight and balanced. Delivers a quick jab.", 3, true, actions["Jab"]!!),
    Arm("Standard Left", "Slightly bulkier than its right arm companion, this delivers a strong uppercut.", 3, false, actions["Uppercut"]!!)
).associateBy { it.name }