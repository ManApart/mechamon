package ui.battleScene.menus

import com.soywiz.korge.view.Image
import core.actions.Action
import ui.Button
import ui.battleScene.BattleControls
import ui.battleScene.BattleOption
import ui.battleScene.BattleScene

class ActionMenu(
    private val parent: BattleScene,
    private val backMenu: TopLevel
) : BattleMenu {
    private var battleControls = getControls()
    private lateinit var apInfo: Button

    private val moveMenu by lazy { MoveMenu(parent, this) }

    override suspend fun draw() {
        parent.drawBase(battleControls)

        val head = parent.playerCombatant.bot.head
        apInfo = Button(parent.battleArea, 0, 0, "AP: ${head.ap}/${head.totalAP}")
    }

    override suspend fun onAccept() {
        battleControls.selectedAction.action.invoke()
    }

    override suspend fun onBack() {
        parent.draw(backMenu)
    }

    private fun getControls(): BattleControls {
        val bot = parent.playerCombatant.bot
        val up = BattleOption(bot.head.action.name) { pickTarget(bot.head.action) }
        val right = BattleOption(bot.armRight.action.name) { pickTarget(bot.armRight.action) }
        val left = BattleOption(bot.armLeft.action.name) { pickTarget(bot.armLeft.action) }
        val down = BattleOption("Move") { parent.draw(moveMenu) }
        return BattleControls(up, down, left, right)
    }

    private fun pickTarget(action: Action) {
        val menu = TargetMenu(parent, parent.background, this, action)
        parent.draw(menu)
    }

}
