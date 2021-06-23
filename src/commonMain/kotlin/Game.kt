import ui.TileMap
import kotlin.properties.Delegates

object Game {
    var terrain: TileMap by Delegates.notNull()
}