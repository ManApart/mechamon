package ui

import TILE_SIZE
import com.soywiz.korge.tiled.TiledMap

fun parseTerrain(tiledMap: TiledMap): TileMap {
    val tileLayer = tiledMap.tileLayers.first()
    val tileTypes = getTileTypes(tiledMap)
    val doors = parseDoors(tiledMap)
    val tiles = (0 until tileLayer.width).flatMap { x ->
        (0 until tileLayer.height).map { y ->
            val tileId = tileLayer[x, y] - 1
            val door = doors[x]?.get(y)
            val type = tileTypes[tileId] ?: throw IllegalArgumentException("Tile at ($x,$y) needs terrain tilesheet data!")
            Tile(x, y, type, door)
        }
    }
    return TileMap(tiles)
}

private fun getTileTypes(tiledMap: TiledMap): Map<Int, TileType> {
    return tiledMap.tilesets.first().data.tiles.map { data ->
        val id = data.id
        val terrain = (data.properties["terrain"] as TiledMap.Property.StringT).value
        TileType(id, terrain)
    }.associateBy { it.id }
}

private fun parseDoors(tiledMap: TiledMap) : Map<Int, Map<Int, Door>>{
    val doorMap = mutableMapOf<Int, MutableMap<Int, Door>>()
    if (tiledMap.objectLayers.isNotEmpty()) {
        tiledMap.objectLayers.first().objects.forEach { candidated ->
            val props = candidated.properties
            if (props.containsKey("level") && props.containsKey("x") && props.containsKey("y")) {
                val door = Door(props["level"]!!.string, props["x"]!!.string.toInt(), props["y"]!!.string.toInt())
                val x = (candidated.bounds.position.x /TILE_SIZE).toInt()
                val y = (candidated.bounds.position.y /TILE_SIZE).toInt()
                doorMap.getOrPut(x) { mutableMapOf() }[y] = door
            }
        }
    }
    return doorMap
}