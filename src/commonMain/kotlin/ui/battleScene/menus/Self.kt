package ui.battleScene.menus

import com.soywiz.korev.Key
import com.soywiz.korge.input.keys
import com.soywiz.korge.view.Container
import com.soywiz.korge.view.Image
import com.soywiz.korge.view.addTo
import ui.battleScene.BattleControls
import ui.battleScene.BattleOption
import ui.battleScene.Combatant
import ui.tiledScene.Direction

class Self(
    private val parentView: Container,
    private val background: Image,
    private val playerCombatant: Combatant,
    private val backMenu: TopLevel
) {
    private var battleControls = getControls()


    init {
        parentView.keys {
            up(Key.SPACE) {
                battleControls.selectedAction?.action?.invoke()
            }
            up(Key.ESCAPE) {
                backMenu.reDraw()
            }
        }
    }

    suspend fun reDraw() {
        parentView.removeChildren()

        background.addTo(parentView)
        parentView.addChild(playerCombatant)
        playerCombatant.init()

        parentView.addChild(battleControls)
        battleControls.init()
    }

    private fun reDrawOptions(highlighted: Direction? = null) {
        parentView.removeChild(battleControls)
        battleControls = getControls(highlighted)
        parentView.addChild(battleControls)
        battleControls.init()
    }

    private fun getControls(highlighted: Direction? = null): BattleControls {
        val bot = playerCombatant.bot
        val up = BattleOption("${bot.head.health}/${bot.head.totalHealth}")
        val right = BattleOption("${bot.armRight.health}/${bot.armRight.totalHealth}")
        val left = BattleOption("${bot.armLeft.health}/${bot.armLeft.totalHealth}")
        val down = BattleOption("${bot.core.health}/${bot.core.totalHealth}")
        return BattleControls(up, down, left, right, ::reDrawOptions, highlighted)
    }

}