package ui.battleScene.menus

import com.soywiz.korge.view.*
import ui.Button
import ui.battleScene.*
import ui.tiledScene.Direction

class Inspect(
    private val parent: BattleScene,
    private val background: Image,
    private val combatant: Combatant,
    private val backMenu: InspectWho
) : BattleMenu {
    private var battleControls = getControls()
    private lateinit var description: Button

    override suspend fun draw() {
        parent.battleArea.removeChildren()

        background.addTo(parent.battleArea)
        parent.battleArea.addChild(combatant)
        combatant.init()

        val head = combatant.bot.head
        Button(parent.battleArea, 0,0, "AP: ${head.ap}/${head.totalAP}")
        val terrain = parent.config.battle.terrain
        val totalMP = combatant.bot.core.getMovement(terrain) / 10
        Button(parent.battleArea, background.width.toInt()- 40,0, "MP: ${combatant.bot.mp}/$totalMP")

        description =  Button(parent.battleArea, 20, 30, head.description, 120, 40)

        parent.battleArea.addChild(battleControls)
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
        val up = BattleOption("${bot.head.name}\nHP: ${bot.head.health}/${bot.head.totalHealth}")
        val right = BattleOption("${bot.armRight.name}\nHP: ${bot.armRight.health}/${bot.armRight.totalHealth}")
        val left = BattleOption("${bot.armLeft.name}\nHP: ${bot.armLeft.health}/${bot.armLeft.totalHealth}")
        val down = BattleOption("${bot.core.name}\nHP: ${bot.core.health}/${bot.core.totalHealth}")
        return BattleControls(up, down, left, right) {direction -> updateDescription(direction)}
    }

    private fun updateDescription(direction: Direction) {
        description.updateText(combatant.bot.getPart(direction).description)
    }

}
