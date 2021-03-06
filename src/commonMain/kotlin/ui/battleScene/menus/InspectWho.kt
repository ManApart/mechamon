package ui.battleScene.menus

import ui.battleScene.BattleControls
import ui.battleScene.BattleOption
import ui.battleScene.BattleScene

class InspectWho(
    private val parent: BattleScene,
    private val backMenu: TopLevel
) : BattleMenu {
    private var battleControls = getControls()
    private val inspectSelfMenu by lazy { Inspect(parent, parent.playerCombatant, this) }
    private val inspectThemMenu by lazy { Inspect(parent, parent.enemyCombatant, this) }

    override suspend fun draw() {
        parent.drawBase(battleControls)
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