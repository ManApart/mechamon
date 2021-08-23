package ui.battleScene.menus

import com.soywiz.korge.view.*
import ui.Button
import ui.Info
import ui.battleScene.*
import ui.buttonHeight
import ui.buttonWidth
import ui.tiledScene.Direction

class Inspect(
    private val parent: BattleScene,
    private val combatant: Combatant,
    private val backMenu: InspectWho
) : BattleMenu {
    private var battleControls = getControls()
    private lateinit var description: Info

    override suspend fun draw() {
        parent.drawBase(battleControls)

        with(parent.infoArea) {
            val head = combatant.bot.head
            Button(this, 0, 0, "AP: ${head.ap}/${head.totalAP}")

            val terrain = this@Inspect.parent.config.battle.terrain
            val totalMP = combatant.bot.core.getMovement(terrain) / 10
            Button(this, (unscaledWidth - buttonWidth * 1.2).toInt(), 0, "MP: ${combatant.bot.mp}/$totalMP")

            description = Info(
                this,
                0,
                buttonHeight.toInt(),
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
        val bot = combatant.bot
        val up = BattleOption("${bot.head.name}\nHP: ${bot.head.health}/${bot.head.totalHealth}")
        val right = BattleOption("${bot.armRight.name}\nHP: ${bot.armRight.health}/${bot.armRight.totalHealth}")
        val left = BattleOption("${bot.armLeft.name}\nHP: ${bot.armLeft.health}/${bot.armLeft.totalHealth}")
        val down = BattleOption("${bot.core.name}\nHP: ${bot.core.health}/${bot.core.totalHealth}")
        return BattleControls(up, down, left, right) { direction -> updateDescription(direction) }
    }

    private fun updateDescription(direction: Direction) {
        description.updateText(combatant.bot.getPart(direction).description)
    }

}
