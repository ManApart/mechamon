package ui.battleScene.menus

import com.soywiz.korge.view.Image
import com.soywiz.korge.view.addTo
import core.Part
import core.actions.Action
import data.NOTHING
import ui.Button
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
    private lateinit var apInfo: Button

    private val actionMenu by lazy { MoveMenu(parent, background, combatant, this) }

    override suspend fun draw() {
        parent.screen.removeChildren()

        background.addTo(parent.screen)
        parent.screen.addChild(combatant)
        combatant.init()

        val head = combatant.bot.head
        apInfo = Button(parent.screen, 0,0, "AP: ${head.ap}/${head.totalAP}")

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
        val up = BattleOption(bot.head.action.name) { doMove(bot.head.action) }
        val right = BattleOption(bot.armRight.action.name) { doMove(bot.armRight.action) }
        val left = BattleOption(bot.armLeft.action.name) { doMove(bot.armLeft.action) }
        val down = BattleOption("Move") { parent.draw(actionMenu) }
        return BattleControls(up, down, left, right)
    }

    private fun doMove(action: Action) {
        val battle = parent.config.battle

        val result = combatant.bot.takeAction(action, battle)

        val head = combatant.bot.head
        apInfo.updateText("AP: ${head.ap}/${head.totalAP}")
        println(result)

    }

}
