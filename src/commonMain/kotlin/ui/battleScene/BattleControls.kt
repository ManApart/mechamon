package ui.battleScene

import com.soywiz.korev.Key
import com.soywiz.korge.input.keys
import com.soywiz.korge.view.*
import com.soywiz.korim.color.Colors
import ui.Button
import ui.buttonHeight
import ui.buttonWidth
import ui.tiledScene.Direction

data class BattleControls(
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
        createButton(buttonWidth * 1.5, buttonHeight * 4.5, up, highlighted == Direction.UP)
        createButton(buttonWidth * 2.1, buttonHeight * 5.5, right, highlighted == Direction.RIGHT)
        createButton(buttonWidth * .9, buttonHeight * 5.5, left, highlighted == Direction.LEFT)
        createButton(buttonWidth * 1.5, buttonHeight * 6.5, down, highlighted == Direction.DOWN)
        xy(-90, 300)
    }

    private fun createButton(x: Double, y: Double, option: BattleOption, highlighted: Boolean) {
        if (highlighted) {
            roundRect(buttonWidth, buttonHeight, buttonHeight / 10, fill = Colors.BLACK) {
                position(x, y)
            }
        }
        Button(this, x, y, option.displayText) {
            option.action()
        }
    }

    private fun updateChoice(highlighted: Direction) {
        this.selectedAction = determineSelectedAction(highlighted)
        removeChildren()
        draw(highlighted)
        onHighlight(highlighted)
    }

}

