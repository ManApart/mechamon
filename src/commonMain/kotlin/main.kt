import com.soywiz.korge.Korge
import com.soywiz.korge.input.onClick
import com.soywiz.korge.tiled.readTiledMap
import com.soywiz.korge.tiled.tiledMapView
import com.soywiz.korge.view.fixedSizeContainer
import com.soywiz.korge.view.position
import com.soywiz.korge.view.scaleView
import com.soywiz.korim.color.Colors
import com.soywiz.korio.file.std.resourcesVfs
import ui.PlayerCharacter
import ui.parseTerrain

const val TILE_SIZE = 16

suspend fun main() = Korge(width = 1024, height = 800, bgcolor = Colors["#2b2b2b"]) {

    val tiledMap = resourcesVfs["map.tmx"].readTiledMap()
    Game.terrain = parseTerrain(tiledMap)

    val player = PlayerCharacter(Game.playerBot)

    fixedSizeContainer(1024, 800, clip = false) {
        position(10, 10)
        scaleView(1024, 800, 2.0, false) {
            tiledMapView(tiledMap, smoothing = false) {
                onClick {
                    val x = (it.currentPosLocal.x / TILE_SIZE).toInt()
                    val y = (it.currentPosLocal.y / TILE_SIZE).toInt()
                    val tile = Game.terrain.get(x, y)
                    println("Clicked Tile: $tile")
                }
            }
            addChild(player)
            player.init()
        }

    }

}