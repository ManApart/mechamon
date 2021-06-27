import com.soywiz.korge.Korge
import com.soywiz.korim.color.Colors
import ui.PlayerCharacter

const val TILE_SIZE = 16

suspend fun main() = Korge(width = 1024, height = 800, bgcolor = Colors["#2b2b2b"]) {

    Game.stage = this
    val player = PlayerCharacter(Game.playerBot)
    player.init()

    loadLevel("map", player)
}

