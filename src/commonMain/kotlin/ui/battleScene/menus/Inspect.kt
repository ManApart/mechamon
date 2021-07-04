package ui.battleScene.menus

import com.soywiz.korge.view.*
import com.soywiz.korim.color.Colors
import ui.battleScene.BattleControls
import ui.battleScene.BattleOption
import ui.battleScene.BattleScene
import ui.battleScene.Combatant

class Inspect(
    private val parent: BattleScene,
    private val background: Image,
    private val combatant: Combatant,
    private val backMenu: InspectWho
) : BattleMenu {
    private var battleControls = getControls()

    override suspend fun draw() {
        parent.screen.removeChildren()

        background.addTo(parent.screen)
        parent.screen.addChild(combatant)
        combatant.init()

        val head = combatant.bot.head
        createInfo(50, 0, "AP: ${head.ap}/${head.totalAP}")
        val terrain = parent.config.battle.terrain
        val totalMP = combatant.bot.core.getMovement(terrain) / 10
        createInfo(50, 20, "MP: ${combatant.bot.mp}/$totalMP")

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
        val up = BattleOption("${bot.head.health}/${bot.head.totalHealth}")
        val right = BattleOption("${bot.armRight.health}/${bot.armRight.totalHealth}")
        val left = BattleOption("${bot.armLeft.health}/${bot.armLeft.totalHealth}")
        val down = BattleOption("${bot.core.health}/${bot.core.totalHealth}")
        return BattleControls(up, down, left, right)
    }

    private fun createInfo(x: Int, y: Int, displayText: String) {
        val button = parent.screen.roundRect(40.0, 20.0, 5.0, fill = Colors["#b9aea0"]) {
            position(x, y)
        }
        parent.screen.text(displayText).centerOn(button)

    }

}