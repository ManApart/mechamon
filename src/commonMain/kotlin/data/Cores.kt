package data

import core.Core

val cores = listOf(
    Core("Standard", "A nondescript, basic core.", 5, 6,2,2,0,0)
).associateBy { it.name }