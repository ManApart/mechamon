package ui

import com.soywiz.klock.TimeSpan
import com.soywiz.korge.view.*
import com.soywiz.korim.bitmap.Bitmap

class PlayerAnimator(image: Bitmap, private val sprite: Sprite) {
    private val movingAnims = mapOf(
        Direction.UP to walkingAnim(image, 20),
        Direction.DOWN to walkingAnim(image, 0),
        Direction.LEFT to walkingAnim(image, 40),
        Direction.RIGHT to walkingAnim(image, 60)
    )

    private val standingAnims = mapOf(
        Direction.UP to standingAnim(image, 20),
        Direction.DOWN to standingAnim(image, 0),
        Direction.LEFT to standingAnim(image, 40),
        Direction.RIGHT to standingAnim(image, 60)
    )

    private fun walkingAnim(image: Bitmap, marginTop: Int) : SpriteAnimation {
        return SpriteAnimation(
            spriteMap = image,
            spriteWidth = 16,
            spriteHeight = 20,
            columns = 4,
            marginTop = marginTop
        )
    }

    private fun standingAnim(image: Bitmap, marginTop: Int) : SpriteAnimation {
        return SpriteAnimation(
            spriteMap = image,
            spriteWidth = 16,
            spriteHeight = 20,
            marginTop = marginTop
        )
    }

    fun evaluate(facing: Direction, isMoving: Boolean = true) {
        val anim = if (isMoving){
            movingAnims[facing]
        } else {
            standingAnims[facing]
        }!!

        sprite.playAnimationLooped(anim, spriteDisplayTime = TimeSpan(200.0))

    }

}