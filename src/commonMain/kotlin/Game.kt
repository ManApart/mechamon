import com.soywiz.korge.view.Stage
import com.soywiz.korge.view.fixedSizeContainer
import com.soywiz.korge.view.image
import com.soywiz.korge.view.scaleView
import com.soywiz.korim.format.readBitmap
import com.soywiz.korio.async.launchImmediately
import com.soywiz.korio.file.std.resourcesVfs
import core.Battle
import core.Bot
import core.Terrain
import ui.TileMap
import kotlin.properties.Delegates

object Game {
    var terrain: TileMap by Delegates.notNull()
    var stage: Stage by Delegates.notNull()
    var playerBot = Bot()
    var battle: Battle? = null


    fun startBattle(enemy: Bot, terrain: Terrain) {
        battle = Battle(playerBot, enemy, terrain)
        stage.launchImmediately {
            stage.loadBattle(battle!!)
        }
    }

}

suspend fun Stage.loadBattle(battle: Battle) {
    val background = resourcesVfs["battleBackgrounds/${battle.terrain.name}.png"].readBitmap()
    removeChildren()

    fixedSizeContainer(WINDOW_SIZE, WINDOW_SIZE, clip = false) {
        scaleView(WINDOW_SIZE, WINDOW_SIZE, 2.0, false) {
            image(background)
        }
    }
}