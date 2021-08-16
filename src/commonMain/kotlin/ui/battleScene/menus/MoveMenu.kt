package ui.battleScene.menus

import com.soywiz.korge.view.*
import ui.Button
import ui.battleScene.*
import kotlin.math.abs

class MoveMenu(
    private val parent: BattleScene,
    private val background: Image,
    private val backMenu: ActionMenu
) : BattleMenu {
    private var battleControls = getControls()
    private lateinit var distance: Button
    private lateinit var movePoints: Button

    override suspend fun draw() {
        parent.screen.removeChildren()

        background.addTo(parent.screen)
        parent.screen.addChild(parent.playerCombatant)
        parent.screen.addChild(parent.enemyCombatant)

        val dist = parent.config.battle.distance
        distance = Button(parent.screen, 50, 0, "Distance: $dist")
        val terrain = parent.config.battle.terrain
        val totalMP = parent.playerCombatant.bot.core.getMovement(terrain) / 10
        movePoints = Button(parent.screen, 50, 20, "MP: ${parent.playerCombatant.bot.mp}/$totalMP")

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
        val amount = if (closer) 1 else -1
        parent.config.battle.move(parent.playerCombatant.bot, -amount)
        val dist = parent.config.battle.distance
        distance.updateText("Distance: $dist")

        val terrain = parent.config.battle.terrain
        val totalMP = parent.playerCombatant.bot.core.getMovement(terrain) / 10
        movePoints.updateText("MP: ${parent.playerCombatant.bot.mp}/$totalMP")

        if (dist in 1..10) {
            when{
                amount > 0 && abs(parent.playerCombatant.position - parent.enemyCombatant.position) > 1 -> {
                    parent.playerCombatant.position += amount
                    parent.playerCombatant.redraw()
                }
                amount > 0 && parent.enemyCombatant.position < 10 -> {
                    parent.enemyCombatant.position += amount
                    parent.enemyCombatant.redraw()
                }
                amount < 0 && parent.playerCombatant.position > 0 -> {
                    parent.playerCombatant.position += amount
                    parent.playerCombatant.redraw()
                }
                amount < 0 && parent.enemyCombatant.position < 10 -> {
                    parent.enemyCombatant.position -= amount
                    parent.enemyCombatant.redraw()
                }
            }

        }
    }

}