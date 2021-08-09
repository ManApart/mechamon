package ui

import com.soywiz.korge.view.*
import com.soywiz.korim.color.Colors
import ui.battleScene.BattleScene


fun BattleScene.createInfo(x: Int, y: Int, displayText: String): Text {
    val button = screen.roundRect(40.0, 20.0, 5.0, fill = Colors["#b9aea0"]) {
        position(x, y)
    }
    return screen.scaledText(displayText, button)
}

fun Container.scaledText(text: String, centerParent: View): Text {
    return text(text) {
        val w = centerParent.unscaledWidth - 2
        val h = centerParent.unscaledHeight - 2
        val scaledW = h / unscaledHeight * unscaledWidth
        val scaledH = w / unscaledWidth * unscaledHeight
        if (scaledW <= scaledH) {
            scaledWidth = scaledW
            scaledHeight = h
        } else {
            scaledWidth = w
            scaledHeight = scaledH
        }
    }.centerOn(centerParent)
}