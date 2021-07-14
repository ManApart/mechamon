package ui.tiledScene

import com.soywiz.korma.geom.Point

data class Tile(val x: Int, val y: Int, val type: TileType, val door: Door? = null) {
    val point: Point = Point(x, y)
}