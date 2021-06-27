package ui

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

    private suspend fun buildSprite() {
        val image = resourcesVfs["character.png"].readBitmap()
        this.sprite = sprite()
        this.sprite.smoothing = false
        this.animator = PlayerAnimator(image, sprite)
        sprite.xy(0, 0)

        animator.evaluate(facing, false)
    }


    private fun setupControls() {
        keys {
            up(Key.SPACE) {
                printTile()
            }
        }
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
        if (source.x + xd < sprite.width / 2 || source.y + yd < sprite.height/2) {
            return
        }
        val sourceTile = getTile(source)

        when {
            xd != 0.0 && yd != 0.0 && canMove(source, xd, yd) -> {
                sprite.x += xd
                sprite.y += yd
                facing = fromDelta(xd, yd)
                animator.evaluate(facing)
                tileChanged(sprite.x, sprite.y, sourceTile)
            }
            xd != 0.0 && canMove(source, xd, 0.0) -> {
                sprite.x += xd
                facing = fromDelta(xd, 0.0)
                animator.evaluate(facing)
                tileChanged(sprite.x, sprite.y, sourceTile)
            }
            yd != 0.0 && canMove(source, 0.0, yd) -> {
                sprite.y += yd
                facing = fromDelta(0.0, yd)
                animator.evaluate(facing)
                tileChanged(sprite.x, sprite.y, sourceTile)
            }
            else -> {
                animator.evaluate(facing, false)
            }
        }
    }

    private fun canMove(source: Point, xd: Double, yd: Double): Boolean {
        val tile = getTile(source + Point(xd, yd))
        return tile != null && bot.core.getMovement(tile.type.terrain) > 0
    }

    private fun getSpriteAnchor(): Point {
        val x = sprite.x + sprite.width / 2
        val y = sprite.y + sprite.height
        return Point(x, y)
    }

    private fun getTile(source: Point): Tile? {
        val x = (source.x / TILE_SIZE).toInt()
        val y = (source.y / TILE_SIZE).toInt()
        return Game.terrain.get(x, y)
    }

    private fun printTile(){
        val tile = getTile(getSpriteAnchor())
        println("Standing on $tile")
    }

    private fun tileChanged(x: Double, y: Double, oldTile: Tile?) {
        val newTile = getTile(Point(x, y))
        if (newTile != null && newTile != oldTile){
            if (newTile.door != null){
                Game.useDoor(this, newTile.door)
            }
        }
    }

    fun setTile(playerStartTile: Point){
        sprite.x = playerStartTile.x * TILE_SIZE
        sprite.y = playerStartTile.y * TILE_SIZE
    }

}