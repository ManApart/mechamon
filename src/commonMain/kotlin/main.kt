import com.soywiz.korge.Korge
import com.soywiz.korim.color.Colors
import ui.PlayerCharacter

const val TILE_SIZE = 16
const val WINDOW_SIZE = 640

suspend fun main() = Korge(width = WINDOW_SIZE, height = WINDOW_SIZE, bgcolor = Colors["#2b2b2b"], title = "Mechamon") {

    Game.stage = this
    val player = PlayerCharacter(Game.playerBot)
    player.init()

    loadLevel("map", player)
}

