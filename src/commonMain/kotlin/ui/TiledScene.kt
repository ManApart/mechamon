package ui

import WINDOW_SIZE
import com.soywiz.klock.TimeSpan
import com.soywiz.korge.scene.AlphaTransition
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.tiled.readTiledMap
import com.soywiz.korge.tiled.tiledMapView
import com.soywiz.korge.view.Container
import com.soywiz.korge.view.fixedSizeContainer
import com.soywiz.korge.view.scaleView
import com.soywiz.korio.async.launchImmediately
import com.soywiz.korio.file.std.resourcesVfs
import com.soywiz.korma.geom.Point

class TiledScene(
    private val levelName: String,
    private val player: PlayerCharacter,
    private val playerStartTile: Point = Point(0, 0)
) : Scene() {

    override suspend fun Container.sceneInit() {
        val tiledMap = resourcesVfs["$levelName.tmx"].readTiledMap()
        Game.terrain = parseTerrain(tiledMap)
        removeChildren()
        player.init(::useDoor)

        fixedSizeContainer(WINDOW_SIZE, WINDOW_SIZE, clip = false) {
            scaleView(WINDOW_SIZE, WINDOW_SIZE, 2.0, false) {
                tiledMapView(tiledMap, smoothing = false)
                addChild(player)
                player.setTile(playerStartTile)
            }
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

}