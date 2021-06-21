package data

import Head

val heads = listOf(
    Head("Standard", "A nondescript, basic head.", 5, 3)
).associateBy { it.name }