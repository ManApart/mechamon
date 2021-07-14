package ui.tiledScene

import Game
import MAIN_VIEW_SIZE
import WINDOW_SIZE
import com.soywiz.klock.TimeSpan
import com.soywiz.korge.scene.AlphaTransition
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.tiled.tiledMapView
import com.soywiz.korge.view.Container
import com.soywiz.korge.view.fixedSizeContainer
import com.soywiz.korge.view.scaleView
import com.soywiz.korio.async.launchImmediately
import com.soywiz.korma.geom.Point
import core.Battle
import core.Bot
import ui.Resources
import ui.battleScene.BattleConfig
import ui.battleScene.BattleScene
import ui.createMouseControls
import ui.play

class TiledScene(
    private val levelName: String,
    private val player: PlayerCharacter,
    private val playerStartTile: Point
) : Scene() {
    var musicName: String? = null

    override suspend fun Container.sceneInit() {
        val tiledMap = Resources.getMap("$levelName.tmx")
        Game.terrain = parseTerrain(tiledMap)
        musicName = parseMusic(tiledMap)
        play(coroutineContext, "music/$musicName.mp3")

        player.init(::useDoor, ::startBattle)

        fixedSizeContainer(WINDOW_SIZE, MAIN_VIEW_SIZE, clip = false) {
            val mainView = scaleView(MAIN_VIEW_SIZE/2, MAIN_VIEW_SIZE/2, 2.0, false) {
                tiledMapView(tiledMap, smoothing = false)
                addChild(player)
                player.setTile(playerStartTile)
            }
//            createMouseControls(mainView)
        }
    }

    private fun useDoor(door: Door) {
        launchImmediately {
            sceneContainer.changeTo<TiledScene>(
                door.level,
                player,
                Point(door.x, door.y),
                transition = AlphaTransition,
                time = TimeSpan(500.0)
            )
        }
    }

    private fun startBattle(tile: Tile) {
        val battle = Battle(Game.playerBot, Bot(), tile.type.terrain)
        val config = BattleConfig(battle, levelName, tile, musicName)
        launchImmediately {
            sceneContainer.changeTo<BattleScene>(
                config,
                transition = AlphaTransition,
                time = TimeSpan(500.0)
            )
        }
    }

}