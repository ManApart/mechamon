package ui.battleScene.menus

import com.soywiz.korev.Key
import com.soywiz.korge.input.keys
import com.soywiz.korge.view.Container
import com.soywiz.korge.view.Image
import com.soywiz.korge.view.addTo
import com.soywiz.korio.async.launchImmediately
import ui.battleScene.BattleControls
import ui.battleScene.BattleOption
import ui.battleScene.Combatant
import ui.tiledScene.Direction
import kotlin.coroutines.CoroutineContext

class TopLevel(
    private val parentView: Container,
    private val background: Image,
    private val playerCombatant: Combatant,
    private val enemyCombatant: Combatant,
    private val endBattle: () -> Unit,
    private val context: CoroutineContext

) {
    private var battleControls = getControls()
    private val selfMenu by lazy { Self(parentView, background, playerCombatant, this) }


    init {
        parentView.keys {
            up(Key.SPACE) {
                battleControls.selectedAction?.action?.invoke()
            }
        }
    }

    suspend fun reDraw() {
        parentView.removeChildren()
        background.addTo(parentView)
        parentView.addChild(playerCombatant)
        playerCombatant.init()

        parentView.addChild(enemyCombatant)
        enemyCombatant.init()

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
        val up = BattleOption("Inspect")
        val right = BattleOption("Action")
        val left = BattleOption("Flee") { endBattle() }
        val down = BattleOption("Self") { launchImmediately(context) { selfMenu.reDraw() } }
        return BattleControls(up, down, left, right, ::reDrawOptions, highlighted)
    }

}