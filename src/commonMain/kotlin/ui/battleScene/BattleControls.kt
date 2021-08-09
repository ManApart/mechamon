package ui.battleScene

import com.soywiz.korev.Key
import com.soywiz.korge.input.keys
import com.soywiz.korge.input.onClick
import com.soywiz.korge.view.*
import com.soywiz.korim.color.Colors
import com.soywiz.korim.text.TextAlignment
import ui.scaledText
import ui.tiledScene.Direction

class BattleControls(
    private val up: BattleOption,
    private val down: BattleOption,
    private val left: BattleOption,
    private val right: BattleOption,
    private val onHighlight: (Direction) -> Unit = {}
) : Container() {
    var selectedAction: BattleOption = determineSelectedAction(Direction.UP)

    private fun determineSelectedAction(selected: Direction): BattleOption {
        return when (selected) {
            Direction.UP -> up
            Direction.DOWN -> down
            Direction.LEFT -> left
            Direction.RIGHT -> right
        }
    }

    fun init() {
        setupControls()
        draw(Direction.UP)
    }

    private fun setupControls() {
        keys {
            up(Key.UP) {
                updateChoice(Direction.UP)
            }
            up(Key.RIGHT) {
                updateChoice(Direction.RIGHT)
            }
            up(Key.LEFT) {
                updateChoice(Direction.LEFT)
            }
            up(Key.DOWN) {
                updateChoice(Direction.DOWN)
            }
        }
    }

    private fun draw(highlighted: Direction) {
        createButton(60, 90, up, highlighted == Direction.UP)
        createButton(80, 110, right, highlighted == Direction.RIGHT)
        createButton(40, 110, left, highlighted == Direction.LEFT)
        createButton(60, 130, down, highlighted == Direction.DOWN)
    }

    private fun createButton(x: Int, y: Int, option: BattleOption, highlighted: Boolean) {
        if (highlighted) {
            roundRect(40.0, 20.0, 2.0, fill = Colors.BLACK) {
                position(x, y)
            }
        }
        val button = roundRect(40.0, 20.0, 5.0, fill = Colors["#b9aea0"]) {
            position(x, y)
            onClick {
                option.action()
            }
        }
        val text = option.displayText
        scaledText(text, button)
    }

    private fun updateChoice(highlighted: Direction) {
        this.selectedAction = determineSelectedAction(highlighted)
        removeChildren()
        draw(highlighted)
        onHighlight(highlighted)
    }

}

