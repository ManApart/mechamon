package ui.battleScene

import com.soywiz.klock.TimeSpan
import com.soywiz.korge.ui.UIText
import com.soywiz.korge.ui.uiText
import com.soywiz.korge.view.*
import com.soywiz.korim.color.Colors
import core.Battle
import core.Bot
import core.Part
import ui.Resources
import ui.tiledScene.Direction

class Combatant(
    val bot: Bot,
    private val facing: Direction,
    private val backgroundWidth: Double
) : Container() {
    private lateinit var sprite: Sprite
    private lateinit var headLabel: UIText
    private lateinit var armLeftLabel: UIText
    private lateinit var armRightLabel: UIText
    private lateinit var coreLabel: UIText
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
        headLabel = drawHPLabel(bot.head, 0, -5)
        armLeftLabel = drawHPLabel(bot.armLeft, -9, 7)
        armRightLabel = drawHPLabel(bot.armRight, 9, 7)
        coreLabel = drawHPLabel(bot.core, 0, 20)
        redraw()
    }

    private fun drawHPLabel(part: Part, x: Int, y: Int): UIText {
        solidRect(14, 6, Colors.BLACK) {
            alpha = .2
            xy(x, y)
        }
        return uiText(hpText(part)) {
            xy(x, y)
            scale = .3
        }
    }

    private fun hpText(part: Part): String {
        return "HP: ${part.health}/${part.totalHealth}"
    }

    fun redraw() {
        val adjustment = (backgroundWidth - sprite.width * 2) / 10.toDouble() * position + sprite.width / 2
        xy(adjustment.toInt(), 40)

        headLabel.text = hpText(bot.head)
        armLeftLabel.text = hpText(bot.armLeft)
        armRightLabel.text = hpText(bot.armRight)
        coreLabel.text = hpText(bot.core)
    }


}