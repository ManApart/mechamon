package ui

import com.soywiz.korge.tiled.TiledMap
import com.soywiz.korge.tiled.readTiledMap
import com.soywiz.korim.bitmap.Bitmap
import com.soywiz.korim.format.readBitmap
import com.soywiz.korio.file.std.resourcesVfs

object Resources {
    private val images = mutableMapOf<String, Bitmap>()
    private val maps = mutableMapOf<String, TiledMap>()


    suspend fun getImage(path: String): Bitmap {
        return images.getOrPut(path) {
            resourcesVfs[path].readBitmap()
        }
    }

    suspend fun getMap(path: String): TiledMap {
        return maps.getOrPut(path) {
            resourcesVfs[path].readTiledMap()
        }
    }
}