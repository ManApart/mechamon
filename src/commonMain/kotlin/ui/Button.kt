package ui

import com.soywiz.korge.input.onClick
import com.soywiz.korge.view.*
import com.soywiz.korim.color.Colors

const val buttonWidth = 40.0 * 3
const val buttonHeight = 20.0 * 3

class Button(
    parent: Container,
    x: Double,
    y: Double,
    displayText: String,
    width: Double = buttonWidth,
    height: Double = buttonHeight,
    val onButtonClick: () -> Unit = {}
) {
    constructor(
        parent: Container,
        x: Int,
        y: Int,
        displayText: String,
        width: Int = buttonWidth.toInt(),
        height: Int = buttonHeight.toInt(),
        onButtonClick: () -> Unit = {}
    ) : this(parent, x.toDouble(), y.toDouble(), displayText, width.toDouble(), height.toDouble(), onButtonClick)

    private val button = parent.roundRect(width, height, width/5, fill = Colors["#b9aea0"]) {
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