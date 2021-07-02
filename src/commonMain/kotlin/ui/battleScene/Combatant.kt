package ui.battleScene

import com.soywiz.klock.TimeSpan
import com.soywiz.korge.view.*
import com.soywiz.korim.bitmap.Bitmap
import core.Bot
import ui.Resources
import ui.tiledScene.Direction

class Combatant(private val bot: Bot, private val facing: Direction) : Container() {
    private lateinit var sprite: Sprite

    suspend fun init() {
        val image = Resources.getImage("character.png")
        val margin = if (facing == Direction.RIGHT) 60 else 40
        val x = if (facing == Direction.RIGHT) 10 else 130
        val anim = SpriteAnimation(
            spriteMap = image,
            spriteWidth = 16,
            spriteHeight = 20,
            marginTop = margin
        )
        this.sprite = sprite(anim)
        this.sprite.smoothing = false
        sprite.xy(x, 40)
        sprite.playAnimationLooped(anim, spriteDisplayTime = TimeSpan(200.0))
    }



}