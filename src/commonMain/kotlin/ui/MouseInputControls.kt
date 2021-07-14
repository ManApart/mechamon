package ui

import MAIN_VIEW_SIZE
import com.soywiz.korge.input.onClick
import com.soywiz.korge.input.onDown
import com.soywiz.korge.input.onUp
import com.soywiz.korge.view.*
import com.soywiz.korim.color.Colors
import com.soywiz.korma.geom.Point

private const val BUTTON_WIDTH = 80.0
private const val BUTTON_HEIGHT = 40.0

fun Container.createMouseControls(mainView: Container) {
    fixedSizeContainer(MAIN_VIEW_SIZE, 200) {
        alignTopToBottomOf(mainView)
        createClickableButton("Esc", Point(0, 0))
        createHoldableButton("<-", Point(BUTTON_WIDTH * 1.5, BUTTON_HEIGHT))
        createHoldableButton("^", Point(BUTTON_WIDTH * 2, 0.0))
        createHoldableButton("->", Point(BUTTON_WIDTH * 2.5, BUTTON_HEIGHT))
        createHoldableButton("v", Point(BUTTON_WIDTH * 2, BUTTON_HEIGHT * 2))
        createClickableButton("Space", Point(BUTTON_WIDTH * 4, 0.0))
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