package ui.battleScene.menus

import core.actions.Action
import data.NOTHING
import ui.Button
import ui.Info
import ui.battleScene.BattleControls
import ui.battleScene.BattleOption
import ui.battleScene.BattleScene
import ui.buttonHeight
import ui.tiledScene.Direction

class ActionMenu(
    private val parent: BattleScene,
    private val backMenu: TopLevel
) : BattleMenu {
    private var battleControls = getControls()
    private lateinit var apInfo: Info
    private lateinit var description: Info

    private val moveMenu by lazy { MoveMenu(parent, this) }

    override suspend fun draw() {
        parent.drawBase(battleControls)

        val head = parent.playerCombatant.bot.head
        with(parent.infoArea) {
            apInfo = Info(this, 0.0, 0.0, "AP: ${head.ap}/${head.totalAP}")

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
        val bot = parent.playerCombatant.bot
        val up = BattleOption(bot.head.action.name) { pickTarget(bot.head.action) }
        val right = BattleOption(bot.armRight.action.name) { pickTarget(bot.armRight.action) }
        val left = BattleOption(bot.armLeft.action.name) { pickTarget(bot.armLeft.action) }
        val down = BattleOption("Move") { parent.draw(moveMenu) }
        return BattleControls(up, down, left, right){ direction -> updateDescription(direction) }
    }

    private fun updateDescription(direction: Direction) {
        //TODO - use current bot whose turn it is
        description.updateText(parent.playerCombatant.bot.getPart(direction).description)
    }

    private fun pickTarget(action: Action) {
        if (action == NOTHING) return
        val menu = TargetMenu(parent, parent.background, this, action)
        parent.draw(menu)
    }

}
