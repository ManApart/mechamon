package ui

import com.soywiz.korge.input.onClick
import com.soywiz.korge.view.*
import com.soywiz.korim.color.Colors

class Button(
    parent: Container,
    x: Int,
    y: Int,
    displayText: String,
    width: Int = 40,
    height: Int = 20,
    val onButtonClick: () -> Unit = {}
) {
    private val button = parent.roundRect(width.toDouble(), height.toDouble(), 5.0, fill = Colors["#b9aea0"]) {
        position(x, y)
        onClick { onButtonClick() }
    }

    private val text = parent.text(displayText)

    init {
        scaleText()
    }

    fun updateText(newText: String) {
        text.text = newText
        scaleText()
    }

    private fun scaleText() {
        with(text) {
            val w = button.unscaledWidth - 2
            val h = button.unscaledHeight - 2
            val scaledW = h / unscaledHeight * unscaledWidth
            val scaledH = w / unscaledWidth * unscaledHeight
            if (scaledW <= scaledH) {
                scaledWidth = scaledW
                scaledHeight = h
            } else {
                scaledWidth = w
                scaledHeight = scaledH
            }
            centerOn(button)
        }
    }

}