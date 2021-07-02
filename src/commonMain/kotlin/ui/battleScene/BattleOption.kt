package ui.battleScene

import core.Battle
import core.Bot

class BattleOption(
    val battle: Battle,
    val bot: Bot,
    val displayText: String,
    val action: () -> Unit = { println("Pressed $displayText") }
) {
}