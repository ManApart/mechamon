package data

import Arm

val arms = listOf(
    Arm("Standard", "Lightweight and balanced. Delivers a quick jab.", 3, true, listOf(actions["Jab"]!!)),
    Arm("Standard", "Slightly bulkier than its right arm companion, this delivers a strong uppercut.", 3, false, listOf(actions["Uppercut"]!!))
).associateBy { it.name }