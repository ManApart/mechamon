package ui.battleScene.menus

import com.soywiz.korev.Key
import com.soywiz.korge.input.keys
import com.soywiz.korge.view.*
import com.soywiz.korio.async.launchImmediately
import ui.battleScene.BattleControls
import ui.battleScene.BattleOption
import ui.battleScene.BattleScene
import ui.battleScene.Combatant
import ui.tiledScene.Direction

class InspectWho(
    private val parent: BattleScene,
    private val background: Image,
    private val playerCombatant: Combatant,
    private val enemyCombatant: Combatant,
    private val backMenu: TopLevel
) {
    private var battleControls = getControls()
    private val inspectSelfMenu by lazy { Inspect(parent, background, playerCombatant, this) }
    private val inspectThemMenu by lazy { Inspect(parent, background, enemyCombatant, this) }

    suspend fun reDraw() {
        parent.screen.keys {
            up(Key.SPACE) {
                battleControls.selectedAction?.action?.invoke()
            }
            up(Key.ESCAPE) {
                backMenu.reDraw()
            }
        }

        parent.screen.removeChildren()
        background.addTo(parent.screen)
        parent.screen.addChild(playerCombatant)
        playerCombatant.init()

        parent.screen.addChild(enemyCombatant)
        enemyCombatant.init()

        parent.screen.addChild(battleControls)
        battleControls.init()
    }

    private fun reDrawOptions(highlighted: Direction? = null) {
        parent.screen.removeChild(battleControls)
        battleControls = getControls(highlighted)
        parent.screen.addChild(battleControls)
        battleControls.init()
    }

    private fun getControls(highlighted: Direction? = null): BattleControls {
        val up = BattleOption("")
        val right = BattleOption("Them") {
            launchImmediately(parent.context) { inspectThemMenu.reDraw() }
        }
        val left = BattleOption("You") {
            launchImmediately(parent.context) { inspectSelfMenu.reDraw() }
        }
        val down = BattleOption("Back") {
            launchImmediately(parent.context) {
                backMenu.reDraw()
            }
        }
        return BattleControls(up, down, left, right, ::reDrawOptions, highlighted)
    }

}