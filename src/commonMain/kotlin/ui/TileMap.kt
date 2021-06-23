package ui

class TileMap(tiles: List<Tile>) {
    private val map: Map<Int, Map<Int, Tile>> = createTileMap(tiles)

    private fun createTileMap(tiles: List<Tile>): Map<Int, Map<Int, Tile>> {
        val map = mutableMapOf<Int, MutableMap<Int, Tile>>()
        tiles.forEach { tile ->
            map.getOrPut(tile.x) { mutableMapOf() }[tile.y] = tile
        }

        return map
    }

    fun get(x: Int, y: Int): Tile {
        return map[x]!![y]!!
    }

}