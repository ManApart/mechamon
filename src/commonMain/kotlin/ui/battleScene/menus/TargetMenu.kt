package ui.battleScene.menus

import com.soywiz.korge.view.*
import core.Part
import core.actions.Action
import ui.Button
import ui.battleScene.*

class TargetMenu(
    private val parent: BattleScene,
    private val background: Image,
    private val backMenu: ActionMenu,
    private val action: Action
) : BattleMenu {
    private var battleControls = getControls()
    private lateinit var apInfo: Button

    override suspend fun draw() {
        parent.drawBase(battleControls)

        val head = parent.playerCombatant.bot.head
        apInfo = Button(parent.screen, 0, 0, "AP: ${head.ap}/${head.totalAP}")
        val terrain = parent.config.battle.terrain
        val totalMP = parent.playerCombatant.bot.core.getMovement(terrain) / 10
        Button(parent.screen, background.width.toInt() - 40, 0, "MP: ${parent.playerCombatant.bot.mp}/$totalMP")
    }

    override suspend fun onAccept() {
        battleControls.selectedAction.action.invoke()
    }

    override suspend fun onBack() {
        parent.draw(backMenu)
    }

    private fun getControls(): BattleControls {
        val bot = parent.enemyCombatant.bot
        val up = BattleOption(bot.head.name) { doMove(action, bot.head) }
        val right = BattleOption(bot.armRight.name) { doMove(action, bot.armRight) }
        val left = BattleOption(bot.armLeft.name) { doMove(action, bot.armLeft) }
        val down = BattleOption(bot.core.name) { doMove(action, bot.core) }
        return BattleControls(up, down, left, right)
    }

    private fun doMove(action: Action, target: Part) {
        val battle = parent.config.battle
        val result = parent.playerCombatant.bot.takeAction(action, target, battle)

        val head = parent.playerCombatant.bot.head
        apInfo.updateText("AP: ${head.ap}/${head.totalAP}")
        println(result)

        parent.draw(backMenu)
    }


}
