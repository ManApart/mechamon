class Battle(val botA: Bot, val botB: Bot) {
    fun getOpponent(bot: Bot) : Bot {
        return if (bot == botA) botB else botA
    }

    fun getWinner() : Bot? {
        return when {
            botA.head.health == 0 -> botB
            botB.head.health == 0 -> botA
            else -> null
        }
    }

    fun tick(){

    }
}