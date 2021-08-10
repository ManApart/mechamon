package ui.battleScene.menus

import com.soywiz.korge.view.Image
import com.soywiz.korge.view.addTo
import ui.battleScene.BattleControls
import ui.battleScene.BattleOption
import ui.battleScene.BattleScene
import ui.battleScene.Combatant

class ActionMenu(
    private val parent: BattleScene,
    private val background: Image,
    private val combatant: Combatant,
    private val backMenu: TopLevel
) : BattleMenu {
    private var battleControls = getControls()

    private val actionMenu by lazy { MoveMenu(parent, background, combatant, this) }

    override suspend fun draw() {
        parent.screen.removeChildren()

        background.addTo(parent.screen)
        parent.screen.addChild(combatant)
        combatant.init()

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
        val bot = combatant.bot
        val up = BattleOption(bot.head.action.name)
        val right = BattleOption(bot.armRight.action.name)
        val left = BattleOption(bot.armLeft.action.name)
        val down = BattleOption("Move") { parent.draw(actionMenu) }
        return BattleControls(up, down, left, right)
    }

}
