package ui.battleScene

import com.soywiz.klock.TimeSpan
import com.soywiz.korge.view.*
import core.Battle
import core.Bot
import ui.Resources
import ui.tiledScene.Direction

class Combatant(
    val bot: Bot,
    private val facing: Direction,
    private val backgroundWidth: Double,
    private val battle: Battle
) : Container() {
    private lateinit var sprite: Sprite
    var position = if (facing == Direction.RIGHT) 0 else 10

    suspend fun init() {
        val image = Resources.getImage("character.png")
        val margin = if (facing == Direction.RIGHT) 60 else 40
        val anim = SpriteAnimation(
            spriteMap = image,
            spriteWidth = 16,
            spriteHeight = 20,
            marginTop = margin
        )
        this.sprite = sprite(anim)
        this.sprite.smoothing = false
        sprite.playAnimationLooped(anim, spriteDisplayTime = TimeSpan(200.0))
        redraw()
    }

    fun redraw() {
        val adjustment = (backgroundWidth - sprite.width*2) / 10.toDouble() * position + sprite.width / 2
        xy(adjustment.toInt(), 40)
    }


}