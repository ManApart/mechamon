package ui

import Game
import MAIN_VIEW_SIZE
import WINDOW_SIZE
import com.soywiz.korge.scene.Module
import com.soywiz.korinject.AsyncInjector
import com.soywiz.korma.geom.*
import core.Battle
import core.Bot
import data.terrains
import ui.battleScene.BattleConfig
import ui.battleScene.BattleScene
import ui.tiledScene.PlayerCharacter
import ui.tiledScene.Tile
import ui.tiledScene.TileType
import ui.tiledScene.TiledScene

object MainModule : Module() {
    override val mainScene = BattleScene::class
//    override val mainScene = TiledScene::class
    override val title: String = "Mechamon"
    override val size: SizeInt = SizeInt(Size(MAIN_VIEW_SIZE, MAIN_VIEW_SIZE))
//    override val size: SizeInt = SizeInt(Size(WINDOW_SIZE, MAIN_VIEW_SIZE))
    override val windowSize = size
    override val icon: String = "battleBackgrounds/Forest.png"
    override val scaleMode: ScaleMode = ScaleMode.NO_SCALE
    override val clipBorders: Boolean = false
    override val scaleAnchor: Anchor = Anchor.TOP_LEFT

    override suspend fun AsyncInjector.configure() {
        mapInstance("map")
        mapInstance(PlayerCharacter(Game.playerBot))
        mapInstance(Point(0,0))
        mapInstance(BattleConfig(Battle(Bot(), Bot(), terrains["Dirt"]!!), "map", Tile(1, 0, TileType(0, "Dirt"))))
        mapPrototype { TiledScene(get(), get(), get()) }
        mapPrototype { BattleScene(get()) }
    }
}