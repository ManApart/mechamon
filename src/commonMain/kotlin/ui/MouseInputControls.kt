package ui

import MAIN_VIEW_SIZE
import com.soywiz.korge.view.Container
import com.soywiz.korge.view.alignLeftToRightOf
import com.soywiz.korge.view.fixedSizeContainer
import com.soywiz.korge.view.solidRect
import com.soywiz.korim.color.Colors
import com.soywiz.korma.geom.Point

fun Container.createMouseControls(mainView: Container) {
    fixedSizeContainer(200, MAIN_VIEW_SIZE) {
        alignLeftToRightOf(mainView)
        solidRect(200, MAIN_VIEW_SIZE, Colors.PINK)


    }

}

private suspend fun createHoldableButton(text: String, position: Point, action: ()-> Unit){

}