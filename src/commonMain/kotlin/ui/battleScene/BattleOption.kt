package ui.battleScene

import core.Battle
import core.Bot

class BattleOption(
    val displayText: String,
    val action: (Battle, Bot) -> Unit = { _, _ -> println("Pressed $displayText") }
) {
}