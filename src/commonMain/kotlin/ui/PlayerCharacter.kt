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

class PlayerCharacter(private val bot: Bot) : Container() {
    private lateinit var sprite: Sprite
    private lateinit var animator: PlayerAnimator
    private var facing = Direction.DOWN

    suspend fun init() {
        buildSprite()
        setupControls()
    }

    private suspend fun buildSprite(){
        val image = resourcesVfs["character.png"].readBitmap()
        this.sprite = sprite()
        this.animator = PlayerAnimator(image, sprite)
        sprite.xy(0, 0)

        animator.evaluate(facing, facing, false)
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
        val moveBoth = xd != 0.0 && yd != 0.0 && both != null && bot.core.getMovement(both.type.terrain) > 0
        val moveRight = xd != 0.0 && right != null && bot.core.getMovement(right.type.terrain) > 0
        val moveDown = yd != 0.0 && down != null && bot.core.getMovement(down.type.terrain) > 0
        val wasFacing = facing

        when {
            moveBoth -> {
                sprite.x += xd
                sprite.y += yd
                facing = fromDelta(xd, yd)
                animator.evaluate(facing, wasFacing)
            }
            moveRight ->{
                sprite.x += xd
                facing = fromDelta(xd, 0.0)
                animator.evaluate(facing, wasFacing)
            }
            moveDown -> {
                sprite.y += yd
                facing = fromDelta(0.0, yd)
                animator.evaluate(facing, wasFacing)
            }
            else -> {
                animator.evaluate(facing, facing, false)
            }
        }
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