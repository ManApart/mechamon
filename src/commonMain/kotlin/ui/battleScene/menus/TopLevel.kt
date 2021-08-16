package ui.battleScene.menus

import com.soywiz.korge.view.Image
import ui.battleScene.BattleControls
import ui.battleScene.BattleOption
import ui.battleScene.BattleScene

class TopLevel(
    private val parent: BattleScene,
    private val background: Image
) : BattleMenu {
    private var battleControls = getControls()
    private val inspectMenu by lazy { InspectWho(parent, background, this) }
    private val actionMenu by lazy { ActionMenu(parent, background, this) }

    override suspend fun draw() {
        parent.drawBase(battleControls)
    }

    override suspend fun onAccept() {
        battleControls.selectedAction.action.invoke()
    }

    override suspend fun onBack() {
        println("Top Level back")
    }

    private fun getControls(): BattleControls {
        val up = BattleOption("Inspect") { parent.draw(inspectMenu) }
        val right = BattleOption("Action") { parent.draw(actionMenu) }
        val left = BattleOption("Flee") { parent.endBattle() }
        val down = BattleOption("Done")
        return BattleControls(up, down, left, right)
    }

}