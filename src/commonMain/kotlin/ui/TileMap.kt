package ui

import com.soywiz.korma.geom.Point

class TileMap(tiles: List<Tile>) {
    private val map: Map<Int, Map<Int, Tile>> = createTileMap(tiles)

    private fun createTileMap(tiles: List<Tile>): Map<Int, Map<Int, Tile>> {
        val map = mutableMapOf<Int, MutableMap<Int, Tile>>()
        tiles.forEach { tile ->
            map.getOrPut(tile.x) { mutableMapOf() }[tile.y] = tile
        }

        return map
    }

//    fun get(point: Point): Tile {
//        return get(point.x.toInt(), point.y.toInt())
//    }
    fun get(x: Int, y: Int): Tile {
        return map[x]!![y]!!
    }

}