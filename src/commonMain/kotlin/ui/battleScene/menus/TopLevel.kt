package ui.battleScene.menus

import com.soywiz.korge.view.Image
import com.soywiz.korge.view.addTo
import ui.battleScene.BattleControls
import ui.battleScene.BattleOption
import ui.battleScene.BattleScene
import ui.battleScene.Combatant
import ui.tiledScene.Direction

class TopLevel(
    private val parent: BattleScene,
    private val background: Image,
    private val playerCombatant: Combatant,
    private val enemyCombatant: Combatant,
) : BattleMenu {
    private var battleControls = getControls()
    private val inspectMenu by lazy { InspectWho(parent, background, playerCombatant, enemyCombatant, this) }

    override suspend fun reDraw() {
        parent.screen.removeChildren()
        background.addTo(parent.screen)
        parent.screen.addChild(playerCombatant)
        playerCombatant.init()

        parent.screen.addChild(enemyCombatant)
        enemyCombatant.init()

        parent.screen.addChild(battleControls)
        battleControls.init()
    }

    override suspend fun onAccept() {
        battleControls.selectedAction?.action?.invoke()
    }

    override suspend fun onBack() {
        println("Top Level back")
    }

    private fun reDrawOptions(highlighted: Direction? = null) {
        parent.screen.removeChild(battleControls)
        battleControls = getControls(highlighted)
        parent.screen.addChild(battleControls)
        battleControls.init()
    }

    private fun getControls(highlighted: Direction? = null): BattleControls {
        val up = BattleOption("Inspect") { parent.draw(inspectMenu) }
        val right = BattleOption("Action")
        val left = BattleOption("Flee") { parent.endBattle() }
        val down = BattleOption("Done")
        return BattleControls(up, down, left, right, ::reDrawOptions, highlighted)
    }

}