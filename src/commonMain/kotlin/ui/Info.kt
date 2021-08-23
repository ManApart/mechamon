package ui

import com.soywiz.korge.view.*
import com.soywiz.korim.color.Colors

class Info(
    parent: Container,
    x: Double,
    y: Double,
    displayText: String,
    width: Double = buttonWidth,
    height: Double = buttonHeight,
    fontSize: Double = 20.0
) : Container() {
    constructor(
        parent: Container,
        x: Int,
        y: Int,
        displayText: String,
        width: Int = buttonWidth.toInt(),
        height: Int = buttonHeight.toInt(),
        fontSize: Int = 20
    ) : this(parent, x.toDouble(), y.toDouble(), displayText, width.toDouble(), height.toDouble(), fontSize.toDouble())

    private val button = parent.solidRect(width, height, Colors["#b9aea0"]) {
        position(x, y)
    }

    private val text = parent.text(formatText(displayText), fontSize).alignTopToTopOf(button).alignLeftToLeftOf(button)

    fun updateText(newText: String) {
        text.text = formatText(newText)
    }

    private fun formatText(text: String): String {
        //Eventually word wrap based on font size + unscaled width
        return text
    }


}