package ui.battleScene.menus

import com.soywiz.korge.view.*
import ui.Button
import ui.battleScene.*
import kotlin.math.abs

class MoveMenu(
    private val parent: BattleScene,
    private val background: Image,
    private val playerCombatant: Combatant,
    private val enemyCombatant: Combatant,
    private val backMenu: ActionMenu
) : BattleMenu {
    private var battleControls = getControls()
    private lateinit var distance: Button
    private lateinit var movePoints: Button

    override suspend fun draw() {
        parent.screen.removeChildren()

        background.addTo(parent.screen)
        parent.screen.addChild(playerCombatant)
        parent.screen.addChild(enemyCombatant)

        val dist = parent.config.battle.distance
        distance = Button(parent.screen, 50, 0, "Distance: $dist")
        val terrain = parent.config.battle.terrain
        val totalMP = playerCombatant.bot.core.getMovement(terrain) / 10
        movePoints = Button(parent.screen, 50, 20, "MP: ${playerCombatant.bot.mp}/$totalMP")

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
        parent.config.battle.move(playerCombatant.bot, -amount)
        val dist = parent.config.battle.distance
        distance.updateText("Distance: $dist")

        val terrain = parent.config.battle.terrain
        val totalMP = playerCombatant.bot.core.getMovement(terrain) / 10
        movePoints.updateText("MP: ${playerCombatant.bot.mp}/$totalMP")

        if (dist in 1..10) {
            when{
                amount > 0 && abs(playerCombatant.position - enemyCombatant.position) > 1 -> {
                    playerCombatant.position += amount
                    playerCombatant.redraw()
                }
                amount > 0 && enemyCombatant.position < 10 -> {
                    enemyCombatant.position += amount
                    enemyCombatant.redraw()
                }
                amount < 0 && playerCombatant.position > 0 -> {
                    playerCombatant.position += amount
                    playerCombatant.redraw()
                }
                amount < 0 && enemyCombatant.position < 10 -> {
                    enemyCombatant.position -= amount
                    enemyCombatant.redraw()
                }
            }

        }
    }

}