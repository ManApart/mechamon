package ui

import WINDOW_SIZE
import com.soywiz.korge.scene.Module
import com.soywiz.korinject.AsyncInjector
import com.soywiz.korma.geom.Point
import com.soywiz.korma.geom.Size
import com.soywiz.korma.geom.SizeInt

object MainModule : Module() {
    override val mainScene = TiledScene::class
    override val title: String = "Mechamon"
    override val size: SizeInt = SizeInt(Size(WINDOW_SIZE, WINDOW_SIZE))
    override val windowSize = size
    override val icon: String = "battleBackgrounds/Beach.png"



    override suspend fun AsyncInjector.configure() {
        mapInstance("map")
        mapInstance(PlayerCharacter(Game.playerBot))
        mapInstance(Point(0,0))
        mapPrototype { TiledScene(get(), get(), get()) }
        mapPrototype { BattleScene(get()) }
    }
}