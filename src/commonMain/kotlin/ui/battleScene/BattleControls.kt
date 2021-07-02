package ui.battleScene

import com.soywiz.korev.Key
import com.soywiz.korge.input.keys
import com.soywiz.korge.input.onClick
import com.soywiz.korge.view.*
import com.soywiz.korim.color.Colors
import core.Battle
import core.Bot

class BattleControls(
    private val up: BattleOption,
    private val down: BattleOption,
    private val left: BattleOption,
    private val right: BattleOption
) : Container() {

    fun init() {
        createButton(60, 90, up.displayText)
        createButton(80, 110, right.displayText)
        createButton(40, 110, left.displayText)
        createButton(60, 130, down.displayText)
        keys {
            up(Key.UP) {
                up.action()
            }
            up(Key.RIGHT) {
                right.action()
            }
            up(Key.LEFT) {
                left.action()
            }
            up(Key.DOWN) {
                down.action()
            }
        }
    }

    private fun createButton(x: Int, y: Int, displayText: String) {
        val button = roundRect(40.0, 20.0, 5.0, fill = Colors["#b9aea0"]) {
            position(x, y)
        }
        text(displayText).centerOn(button)

    }


}