import com.soywiz.korge.Korge
import com.soywiz.korge.input.onClick
import com.soywiz.korge.tiled.readTiledMap
import com.soywiz.korge.tiled.tiledMapView
import com.soywiz.korge.view.fixedSizeContainer
import com.soywiz.korge.view.position
import com.soywiz.korim.color.Colors
import com.soywiz.korio.file.std.resourcesVfs
import ui.PlayerCharacter
import ui.parseTerrain

const val TILE_SIZE = 16
const val MAP_RENDER_SCALE = 2

suspend fun main() = Korge(width = 512, height = 512, bgcolor = Colors["#2b2b2b"]) {

    val tiledMap = resourcesVfs["map.tmx"].readTiledMap()
    Game.terrain = parseTerrain(tiledMap)

    val player = PlayerCharacter(Game.playerBot)

    fixedSizeContainer(256, 256, clip = false) {
        position(10, 10)
//		val camera = camera {
        tiledMapView(tiledMap) {
            scale = 2.0
            onClick {
                val x = (it.currentPosLocal.x / TILE_SIZE).toInt()
                val y = (it.currentPosLocal.y / TILE_SIZE).toInt()
                val tile = Game.terrain.get(x, y)
                println("Clicked Tile: $tile")
            }
        }
        addChild(player)
        player.init()
//		}

    }

    views.actualHeight

}