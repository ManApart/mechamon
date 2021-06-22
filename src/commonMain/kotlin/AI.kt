interface AI {
    val parent: Bot

    fun takeTurn(battle: Battle)
}