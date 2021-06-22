package data

import core.Core

val cores = listOf(
    Core("Standard", "A nondescript, basic core.", 5, 4,3,3,0,0)
).associateBy { it.name }