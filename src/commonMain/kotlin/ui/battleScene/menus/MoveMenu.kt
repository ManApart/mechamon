package ui.battleScene.menus

import com.soywiz.korge.view.*
import ui.Button
import ui.battleScene.*

class MoveMenu(
    private val parent: BattleScene,
    private val background: Image,
    private val combatant: Combatant,
    private val backMenu: ActionMenu
) : BattleMenu {
    private var battleControls = getControls()
    private lateinit var distance: Button
    private lateinit var movePoints: Button

    override suspend fun draw() {
        parent.screen.removeChildren()

        background.addTo(parent.screen)
        parent.screen.addChild(combatant)
        combatant.init()

        val dist = parent.config.battle.distance
        distance = Button(parent.screen, 50, 0, "Distance: $dist")
        val terrain = parent.config.battle.terrain
        val totalMP = combatant.bot.core.getMovement(terrain) / 10
        movePoints = Button(parent.screen, 50, 20, "MP: ${combatant.bot.mp}/$totalMP")

        parent.screen.addChild(battleControls)
        battleControls.init()
    }

    override suspend fun onAccept() {
        battleControls.selectedAction.action.invoke()
    }

    override suspend fun onBack() {
        parent.draw(backMenu)
    }

    private fun getControls(): BattleControls {
        val up = BattleOption("")
        val right = BattleOption("Closer") { move(true) }
        val left = BattleOption("Further") { move(false) }
        val down = BattleOption("Back") { parent.draw(backMenu) }
        return BattleControls(up, down, left, right)
    }

    private fun move(closer: Boolean) {
        val amount = if (closer) -1 else 1
        parent.config.battle.move(combatant.bot, amount)
        distance.updateText("Distance: ${parent.config.battle.distance}")

        val terrain = parent.config.battle.terrain
        val totalMP = combatant.bot.core.getMovement(terrain) / 10
        movePoints.updateText("MP: ${combatant.bot.mp}/$totalMP")
    }

}