package ui

import MAP_RENDER_SCALE
import TILE_SIZE
import com.soywiz.klock.TimeSpan
import com.soywiz.korev.Key
import com.soywiz.korge.input.keys
import com.soywiz.korge.input.onKeyDown
import com.soywiz.korge.view.*
import com.soywiz.korim.format.readBitmap
import com.soywiz.korio.file.std.resourcesVfs
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
        val walking = SpriteAnimation(
            spriteMap = image,
            spriteWidth = 16,
            spriteHeight = 20,
            columns = 4
        )
        val sprite = sprite(standing)
        sprite.xy(0, 0)

        sprite.playAnimationLooped(spriteDisplayTime = TimeSpan(200.0))

        return sprite
    }


    private suspend fun setupControls() {
        val velocity = 1.0
        keys {
            down(Key.RIGHT) { sprite.x += velocity }
            down(Key.LEFT) { sprite.x -= velocity }
            down(Key.UP) { sprite.y -= velocity }
            down(Key.DOWN) { sprite.y += velocity }
            up(Key.SPACE) { getTerrainUnderMe() }
        }
    }

    private fun getTerrainUnderMe() {
        println("Terrain under me")
        val scale = TILE_SIZE * MAP_RENDER_SCALE
        val x = (sprite.x / scale).toInt()
        val y = (sprite.y / scale).toInt()
        val tile = Game.terrain.get(x, y)
        println("Clicked Tile: $tile")
    }

}