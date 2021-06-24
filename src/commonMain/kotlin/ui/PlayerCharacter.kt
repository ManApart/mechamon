package ui

import MAP_RENDER_SCALE
import TILE_SIZE
import com.soywiz.klock.TimeSpan
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


    private suspend fun setupControls() {
        val velocity = 1.0
        keys {
            down(Key.RIGHT) { tryMove(velocity) }
            down(Key.LEFT) { tryMove(-velocity) }
            down(Key.UP) { tryMove(yd = -velocity) }
            down(Key.DOWN) { tryMove(yd = velocity) }
            up(Key.SPACE) { getTerrainUnderMe() }
        }
    }

    private fun tryMove(xd: Double = 0.0, yd: Double = 0.0) {
        val source = getSpriteAnchor()
        val right = getTile(source + Point(xd, 0.0))
        val down = getTile(source + Point(0.0, yd))
        val both = getTile(source + Point(xd, yd))
        when {
            bot.core.getMovement(both.type.terrain) > 0 -> {
                sprite.x += xd
                sprite.y += yd
            }
            bot.core.getMovement(right.type.terrain) > 0 -> sprite.x += xd
            bot.core.getMovement(down.type.terrain) > 0 -> sprite.y += yd
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

    private fun getTile(source: Point): Tile {
        val scale = TILE_SIZE * MAP_RENDER_SCALE
        val x = (source.x / scale).toInt()
        val y = (source.y / scale).toInt()
        return Game.terrain.get(x, y)
    }

}