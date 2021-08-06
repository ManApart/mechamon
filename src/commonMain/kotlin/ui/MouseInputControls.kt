package ui

import WINDOW_WIDTH
import com.soywiz.korge.input.onClick
import com.soywiz.korge.input.onDown
import com.soywiz.korge.input.onUp
import com.soywiz.korge.view.*
import com.soywiz.korim.color.Colors
import com.soywiz.korma.geom.Point
import ui.tiledScene.PlayerCharacter

private const val BUTTON_WIDTH = 80.0
private const val BUTTON_HEIGHT = 40.0
private const val GUI_WIDTH = 450

fun Container.createMouseControls(player: PlayerCharacter) {
    fixedSizeContainer(GUI_WIDTH, 200) {
        xy(WINDOW_WIDTH - GUI_WIDTH, 0)
        createClickableButton("Esc", Point(0, 0))
        createClickableButton("Z", Point(0.0, BUTTON_HEIGHT * 2)) { player.startABattle() }
        createHoldableButton("<-", Point(BUTTON_WIDTH * 1.5, BUTTON_HEIGHT)) { player.pressedLeft = true }
        createHoldableButton("^", Point(BUTTON_WIDTH * 2, 0.0)) { player.pressedUp = true }
        createHoldableButton("->", Point(BUTTON_WIDTH * 2.5, BUTTON_HEIGHT)) { player.pressedRight = true }
        createHoldableButton("v", Point(BUTTON_WIDTH * 2, BUTTON_HEIGHT * 2)) { player.pressedDown = true }
        createClickableButton("Space", Point(BUTTON_WIDTH * 4, 0.0)) { player.printTile() }
    }

}

private fun Container.createHoldableButton(
    text: String,
    position: Point,
    action: () -> Unit = { println("Pressed $text") }
) {
    val button = roundRect(BUTTON_WIDTH, BUTTON_HEIGHT, 5.0, fill = Colors["#b9aea0"]) {
        position(position.x, position.y)
        var pressed = false
        onDown {
            pressed = true
        }
        onUp {
            pressed = false
        }
        addUpdater {
            if (pressed) {
                action()
            }
        }
    }

    text(text).centerOn(button)
}

private fun Container.createClickableButton(
    text: String,
    position: Point,
    action: () -> Unit = { println("Pressed $text") }
) {
    val button = roundRect(BUTTON_WIDTH, BUTTON_HEIGHT, 5.0, fill = Colors["#b9aea0"]) {
        position(position.x, position.y)
        onClick {
            action()
        }
    }
    text(text).centerOn(button)
}