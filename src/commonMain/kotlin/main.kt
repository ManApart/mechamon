import com.soywiz.korge.Korge
import ui.MainModule

const val TILE_SIZE = 16
const val WINDOW_SIZE = 640

suspend fun main() = Korge(Korge.Config(module = MainModule))

