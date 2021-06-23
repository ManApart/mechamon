package ui

import data.terrains

class TileType(val id: Int, terrainName: String) {
    init {
        if (!terrains.containsKey(terrainName)){
            throw IllegalArgumentException("$terrainName does not exist in " + terrains.keys.joinToString(","))
        }
    }
    val terrain = terrains[terrainName]!!

    override fun toString(): String {
        return terrain.name
    }
}