import com.soywiz.korge.Korge
import ui.MainModule

const val TILE_SIZE = 16
const val WINDOW_WIDTH = 1920
const val WINDOW_HEIGHT = 1080

suspend fun main() = Korge(Korge.Config(module = MainModule))

