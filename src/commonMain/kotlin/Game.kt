import com.soywiz.korge.tiled.TiledMap
import com.soywiz.korge.tiled.readTiledMap
import com.soywiz.korge.tiled.tiledMapView
import com.soywiz.korge.view.Stage
import com.soywiz.korge.view.fixedSizeContainer
import com.soywiz.korge.view.position
import com.soywiz.korge.view.scaleView
import com.soywiz.korio.async.launchImmediately
import com.soywiz.korio.file.std.resourcesVfs
import com.soywiz.korma.geom.Point
import core.Bot
import core.PlayerAI
import ui.Door
import ui.PlayerCharacter
import ui.TileMap
import ui.parseTerrain
import kotlin.properties.Delegates

object Game {
    var terrain: TileMap by Delegates.notNull()
    var stage: Stage by Delegates.notNull()
    var playerBot = Bot()


    fun useDoor(player: PlayerCharacter, door: Door) {
        stage.launchImmediately {
            stage.loadLevel(door.level, player, Point(door.x, door.y))
        }
    }

}

suspend fun Stage.loadLevel(
    levelName: String,
    player: PlayerCharacter,
    playerStartTile: Point = Point(0,0)
) {
    val tiledMap = resourcesVfs["$levelName.tmx"].readTiledMap()
    Game.terrain = parseTerrain(tiledMap)
    removeChildren()

    fixedSizeContainer(WINDOW_SIZE, WINDOW_SIZE, clip = false) {
        scaleView(WINDOW_SIZE, WINDOW_SIZE, 2.0, false) {
            tiledMapView(tiledMap, smoothing = false)
            addChild(player)
            player.setTile(playerStartTile)
        }
    }
}