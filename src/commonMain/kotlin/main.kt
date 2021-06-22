import com.soywiz.klock.milliseconds
import com.soywiz.klock.seconds
import com.soywiz.kmem.clamp
import com.soywiz.korev.Key
import com.soywiz.korge.*
import com.soywiz.korge.input.onClick
import com.soywiz.korge.tiled.readTiledMap
import com.soywiz.korge.tiled.tiledMapView
import com.soywiz.korge.tween.*
import com.soywiz.korge.view.*
import com.soywiz.korim.color.Colors
import com.soywiz.korim.format.*
import com.soywiz.korio.file.std.*
import com.soywiz.korma.geom.degrees
import com.soywiz.korma.interpolation.Easing
import kotlin.math.pow

suspend fun main() = Korge(width = 512, height = 512, bgcolor = Colors["#2b2b2b"]) {

	val tiledMap = resourcesVfs["map.tmx"].readTiledMap()
	fixedSizeContainer(256, 256, clip = true) {
		position(128, 128)
		val camera = camera {
			tiledMapView(tiledMap) {
				onClick {
					val x = (it.currentPosLocal.x / 16).toInt()
					val y = (it.currentPosLocal.y / 16).toInt()
					val tileId = tiledMap.tileLayers.first()[x, y]-1
					val tileData = tiledMap.tilesets.first().data.tiles.first { tileData ->  tileData.id == tileId }
					val terrain = tileData.properties["terrain"]
					println("Clicked Tile $x, $y, $tileId: $terrain")
				}
			}
		}
		var dx = 0.0
		var dy = 0.0
		addUpdater {
			//val scale = 1.0 / (it / 16.666666.hrMilliseconds)
			val scale = if (it == 0.0.milliseconds) 0.0 else (it / 16.666666.milliseconds)
			if (views.input.keys[Key.RIGHT]) dx -= 1.0
			if (views.input.keys[Key.LEFT]) dx += 1.0
			if (views.input.keys[Key.UP]) dy += 1.0
			if (views.input.keys[Key.DOWN]) dy -= 1.0
			dx = dx.clamp(-10.0, +10.0)
			dy = dy.clamp(-10.0, +10.0)
			camera.x += dx * scale
			camera.y += dy * scale
			dx *= 0.9.pow(scale)
			dy *= 0.9.pow(scale)
		}
	}

}