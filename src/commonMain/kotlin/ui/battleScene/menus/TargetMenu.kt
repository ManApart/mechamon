package ui.battleScene.menus

import com.soywiz.korge.view.*
import core.Part
import core.actions.Action
import ui.Button
import ui.Info
import ui.battleScene.*
import ui.buttonHeight
import ui.tiledScene.Direction

class TargetMenu(
    private val parent: BattleScene,
    private val background: Image,
    private val backMenu: ActionMenu,
    private val action: Action
) : BattleMenu {
    private var battleControls = getControls()
    private lateinit var apInfo: Info
    private lateinit var description: Info

    override suspend fun draw() {
        parent.drawBase(battleControls)

        //TODO - use current bot whose turn it is
        val bot = parent.playerCombatant.bot
        val head = bot.head
        val terrain = parent.config.battle.terrain
        val totalMP = bot.core.getMovement(terrain) / 10
        with(parent.infoArea) {
            apInfo = Info(this, 0, 0, "AP: ${head.ap}/${head.totalAP}")
            Info(this, background.width.toInt() - 40, 0, "MP: ${bot.mp}/$totalMP")

            description = Info(
                this,
                0,
                (buttonHeight * 1.2).toInt(),
                head.description,
                this.unscaledWidth.toInt(),
                buttonHeight.toInt() * 5
            )
        }
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
        return BattleControls(up, down, left, right){ direction -> updateDescription(direction) }
    }

    private fun doMove(action: Action, target: Part) {
        val battle = parent.config.battle
        val result = parent.playerCombatant.bot.takeAction(action, target, battle)

        val head = parent.playerCombatant.bot.head
        apInfo.updateText("AP: ${head.ap}/${head.totalAP}")
        println(result)

        parent.draw(backMenu)
    }

    private fun updateDescription(direction: Direction) {
        description.updateText(parent.playerCombatant.bot.getPart(direction).description)
    }


}
