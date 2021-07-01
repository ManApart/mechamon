package ui

import WINDOW_SIZE
import com.soywiz.korge.scene.Module
import com.soywiz.korinject.AsyncInjector
import com.soywiz.korma.geom.*

object MainModule : Module() {
    override val mainScene = TiledScene::class
    override val title: String = "Mechamon"
    override val size: SizeInt = SizeInt(Size(WINDOW_SIZE, WINDOW_SIZE))
    override val windowSize = size
    override val icon: String = "battleBackgrounds/Forest.png"
    override val scaleMode: ScaleMode = ScaleMode.NO_SCALE
    override val clipBorders: Boolean = false
    override val scaleAnchor: Anchor = Anchor.TOP_LEFT

    override suspend fun AsyncInjector.configure() {
        mapInstance("map")
        mapInstance(PlayerCharacter(Game.playerBot))
        mapInstance(Point(0,0))
        mapPrototype { TiledScene(get(), get(), get()) }
        mapPrototype { BattleScene(get()) }
    }
}