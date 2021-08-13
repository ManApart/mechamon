package ui.battleScene.menus

import com.soywiz.korge.view.Image
import com.soywiz.korge.view.addTo
import core.actions.Action
import ui.Button
import ui.battleScene.BattleControls
import ui.battleScene.BattleOption
import ui.battleScene.BattleScene
import ui.battleScene.Combatant

class ActionMenu(
    private val parent: BattleScene,
    private val background: Image,
    private val playerCombatant: Combatant,
    private val enemyCombatant: Combatant,
    private val backMenu: TopLevel
) : BattleMenu {
    private var battleControls = getControls()
    private lateinit var apInfo: Button

    private val moveMenu by lazy { MoveMenu(parent, background, playerCombatant, enemyCombatant, this) }

    override suspend fun draw() {
        parent.screen.removeChildren()

        background.addTo(parent.screen)
        parent.screen.addChild(playerCombatant)
        parent.screen.addChild(enemyCombatant)
        playerCombatant.redraw()
        enemyCombatant.redraw()

        val head = playerCombatant.bot.head
        apInfo = Button(parent.screen, 0, 0, "AP: ${head.ap}/${head.totalAP}")

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
        val bot = playerCombatant.bot
        val up = BattleOption(bot.head.action.name) { pickTarget(bot.head.action) }
        val right = BattleOption(bot.armRight.action.name) { pickTarget(bot.armRight.action) }
        val left = BattleOption(bot.armLeft.action.name) { pickTarget(bot.armLeft.action) }
        val down = BattleOption("Move") { parent.draw(moveMenu) }
        return BattleControls(up, down, left, right)
    }

    private fun pickTarget(action: Action) {
        val menu = TargetMenu(parent, background, playerCombatant, enemyCombatant, this, action)
            parent.draw(menu)
    }

}
