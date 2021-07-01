package ui

import WINDOW_SIZE
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.view.Container
import com.soywiz.korge.view.fixedSizeContainer
import com.soywiz.korge.view.image
import com.soywiz.korge.view.scaleView
import core.Battle

class BattleScene(private val battle: Battle) : Scene() {

    override suspend fun Container.sceneInit() {
        val background = Resources.getImage("battleBackgrounds/${battle.terrain.battleName}.png")

        fixedSizeContainer(WINDOW_SIZE, WINDOW_SIZE, clip = false) {
            scaleView(WINDOW_SIZE, WINDOW_SIZE, 2.0, false) {
                image(background)
            }
        }
    }
}