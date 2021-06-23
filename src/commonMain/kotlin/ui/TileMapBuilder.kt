package ui

import com.soywiz.korge.tiled.TiledMap

fun parseTerrain(tiledMap: TiledMap): TileMap {
    val tileLayer = tiledMap.tileLayers.first()
    val tileTypes = getTileTypes(tiledMap)
    val tiles = (0 until tileLayer.width).flatMap { x ->
        (0 until tileLayer.height).map { y ->
            val tileId = tileLayer[x, y] -1
            Tile(x, y, tileTypes[tileId]!!)
        }
    }
    return TileMap(tiles)
}

private fun getTileTypes(tiledMap: TiledMap) : Map<Int, TileType>{
    return tiledMap.tilesets.first().data.tiles.map { data ->
        val id = data.id
        val terrain = (data.properties["terrain"] as TiledMap.Property.StringT).value
        TileType(id, terrain)
    }.associateBy { it.id }
}
