package ui.battleScene

import com.soywiz.korev.Key
import com.soywiz.korge.input.keys
import com.soywiz.korge.input.onClick
import com.soywiz.korge.view.*
import com.soywiz.korim.color.Colors
import core.Battle
import core.Bot

class BattleControls(private val battle: Battle, private val bot: Bot) : Container() {

    suspend fun init() {
        val up = BattleOption("Inspect")
        val right = BattleOption("Action")
        val left = BattleOption("Flee")
        val down = BattleOption("Self")

        createButton(60, 90, up.displayText)
        createButton(80, 110, right.displayText)
        createButton(40, 110, left.displayText)
        createButton(60, 130, down.displayText)
        keys {
            up(Key.UP) {
                up.action(battle, bot)
            }
            up(Key.RIGHT) {
                right.action(battle, bot)
            }
            up(Key.LEFT) {
                left.action(battle, bot)
            }
            up(Key.DOWN) {
                down.action(battle, bot)
            }
        }
    }

    private fun createButton(x: Int, y: Int, displayText: String){
        val button = roundRect(40.0, 20.0, 5.0, fill = Colors["#b9aea0"]){
            position(x, y)
        }
        text(displayText).centerOn(button)

    }


}