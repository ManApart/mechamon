package ui.battleScene.menus

import com.soywiz.korge.view.Image
import com.soywiz.korge.view.addTo
import ui.battleScene.BattleControls
import ui.battleScene.BattleOption
import ui.battleScene.BattleScene
import ui.battleScene.Combatant

class InspectWho(
    private val parent: BattleScene,
    private val background: Image,
    private val playerCombatant: Combatant,
    private val enemyCombatant: Combatant,
    private val backMenu: TopLevel
) : BattleMenu {
    private var battleControls = getControls()
    private val inspectSelfMenu by lazy { Inspect(parent, background, playerCombatant, this) }
    private val inspectThemMenu by lazy { Inspect(parent, background, enemyCombatant, this) }

    override suspend fun draw() {
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
        battleControls.selectedAction.action.invoke()
    }

    override suspend fun onBack() {
        parent.draw(backMenu)
    }

    private fun getControls(): BattleControls {
        val up = BattleOption("")
        val right = BattleOption("Them") {
            parent.draw(inspectThemMenu)
        }
        val left = BattleOption("You") {
            parent.draw(inspectSelfMenu)
        }
        val down = BattleOption("Back") {
            parent.draw(backMenu)
        }
        return BattleControls(up, down, left, right)
    }

}