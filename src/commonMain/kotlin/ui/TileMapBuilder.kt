package ui

import TILE_SIZE
import com.soywiz.korge.tiled.TiledMap

fun parseTerrain(tiledMap: TiledMap): TileMap {
    val tileLayer = tiledMap.tileLayers.first()
    val tileTypes = getTileTypes(tiledMap)
    val tiles = (0 until tileLayer.width).flatMap { x ->
        (0 until tileLayer.height).map { y ->
            val tileId = tileLayer[x, y] - 1
//            val door = parseDoor(x, y, tiledMap)
            Tile(x, y, tileTypes[tileId]!!)
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

//private fun parseDoor(x: Int, y: Int, tiledMap: TiledMap): Door? {
//    if (tiledMap.objectLayers.isNotEmpty()) {
//        val layer = tiledMap.objectLayers.first()
//        layer.
//    }
//    return null
//}

fun parseDoors(tiledMap: TiledMap, terrain: TileMap) {
    if (tiledMap.objectLayers.isNotEmpty()) {
        tiledMap.objectLayers.first().objects.forEach { candidated ->
            val props = candidated.properties
            if (props.containsKey("level") && props.containsKey("x") && props.containsKey("y")) {
                val door = Door(props["level"]!!.string, props["x"]!!.string.toInt(), props["y"]!!.string.toInt())
                val x = (candidated.bounds.position.x /TILE_SIZE).toInt()
                val y = (candidated.bounds.position.y /TILE_SIZE).toInt()
                terrain.get(x,y)!!.door = door
            }

        }

    }
}