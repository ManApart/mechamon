package ui

import MAP_RENDER_SCALE
import TILE_SIZE
import com.soywiz.klock.TimeSpan
import com.soywiz.klock.milliseconds
import com.soywiz.korev.Key
import com.soywiz.korge.input.keys
import com.soywiz.korge.view.*
import com.soywiz.korim.format.readBitmap
import com.soywiz.korio.file.std.resourcesVfs
import com.soywiz.korma.geom.Point
import core.Bot

class PlayerCharacter(val bot: Bot) : Container() {
    private lateinit var sprite: Sprite

    suspend fun init() {
        this.sprite = buildSprite()
        setupControls()
    }

    private suspend fun buildSprite(): Sprite {
        val image = resourcesVfs["character.png"].readBitmap()
        val standing = SpriteAnimation(
            spriteMap = image,
            spriteWidth = 16,
            spriteHeight = 20,
        )
        val walkDown = SpriteAnimation(
            spriteMap = image,
            spriteWidth = 16,
            spriteHeight = 20,
            columns = 4
        )
        val walkUp = SpriteAnimation(
            spriteMap = image,
            spriteWidth = 16,
            spriteHeight = 20,
            columns = 4,
            marginTop = 20
        )
        val walkLeft = SpriteAnimation(
            spriteMap = image,
            spriteWidth = 16,
            spriteHeight = 20,
            columns = 4,
            marginTop = 40
        )
        val walkRight = SpriteAnimation(
            spriteMap = image,
            spriteWidth = 16,
            spriteHeight = 20,
            columns = 4,
            marginTop = 60
        )
        val sprite = sprite(walkUp)
        sprite.xy(0, 0)

        sprite.playAnimationLooped(spriteDisplayTime = TimeSpan(200.0))

        return sprite
    }


    private fun setupControls() {
        addUpdaterWithViews { views: Views, dt: TimeSpan ->
            var dx = 0.0
            var dy = 0.0
            val scale = if (dt == 0.0.milliseconds) 0.0 else (dt / 16.666666.milliseconds)
            if (views.input.keys[Key.RIGHT]) dx = 1.0 * scale
            if (views.input.keys[Key.LEFT]) dx = -1.0 * scale
            if (views.input.keys[Key.UP]) dy = -1.0 * scale
            if (views.input.keys[Key.DOWN]) dy = 1.0 * scale
            tryMove(dx, dy)
        }
    }

    private fun tryMove(xd: Double = 0.0, yd: Double = 0.0) {
        val source = getSpriteAnchor()
        if (source.x + xd < 0 || source.y + yd < 0) {
            return
        }
        val right = getTile(source + Point(xd, 0.0))
        val down = getTile(source + Point(0.0, yd))
        val both = getTile(source + Point(xd, yd))
        val moveBoth = both != null && bot.core.getMovement(both.type.terrain) > 0
        val moveRight = right != null && bot.core.getMovement(right.type.terrain) > 0
        val moveDown = down != null && bot.core.getMovement(down.type.terrain) > 0
        when {
            moveBoth -> {
                sprite.x += xd
                sprite.y += yd
            }
            moveRight -> sprite.x += xd
            moveDown -> sprite.y += yd
            else -> Unit
        }
    }

    private fun getTerrainUnderMe() {
        val tile = getTile(getSpriteAnchor())
        println("Terrain under me: $tile")
    }

    private fun getSpriteAnchor(): Point {
        val x = sprite.x + sprite.width / 2
        val y = sprite.y + sprite.height
        return Point(x, y)
    }

    private fun getTile(source: Point): Tile? {
        val scale = TILE_SIZE * MAP_RENDER_SCALE
        val x = (source.x / scale).toInt()
        val y = (source.y / scale).toInt()
        return Game.terrain.get(x, y)
    }

}