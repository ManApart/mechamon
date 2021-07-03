package ui.battleScene

class BattleOption(
    val displayText: String,
    val action: () -> Unit = { println("Pressed $displayText") }
) {
}