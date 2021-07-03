package core

class NPCAI(override val parent: Bot) : AI {
    override fun takeTurn(battle: Battle) {
//        val move = parent.core.getMovement(battle.terrain)
//        val actions = parent.getActions().filter { it.possible }
//        val target = battle.getOpponent(parent).core
//        actions.firstOrNull()?.action?.use(parent, target, battle)
    }
}